package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.MedicineService;
import uz.epam.webproject.service.UserService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.MedicineServiceImpl;
import uz.epam.webproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";
    private static final String ERROR_MESSAGE = "Incorrect login or password";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String userName = request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);
        String defaultLocale = request.getParameter(ParameterName.LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.LOCALE, defaultLocale);
        Router router;

        UserService userService = UserServiceImpl.getInstance();
        MedicineService medicineService = MedicineServiceImpl.getInstance();

        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.INDEX_PAGE);
        logger.info("current page now ::::: " + session.getAttribute(ParameterName.CURRENT_PAGE));
        logger.info("request.contextPath is " + request.getContextPath());
        logger.info("request.servletPath is " + request.getServletPath());
        try {
            List<Medicine> medicineList = medicineService.findAll();
            List<Medicine> medicineBasket = new ArrayList<>();

            HashMap<Medicine, String> medicineQuantityMap = new HashMap<>();
            session.setAttribute("medicine_quantity_map", medicineQuantityMap);

            session.setAttribute(ParameterName.MEDICINE_LIST, medicineList);
            session.setAttribute(ParameterName.MEDICINE_BASKET, medicineBasket);

            if(userService.authenticate(userName, password)){
                session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_HOME_PAGE);
                logger.info("current page now ::::: " + session.getAttribute(ParameterName.CURRENT_PAGE));
                UserRole userRole = userService.findUserRole(userName);
                Optional<User> optionalUser = userService.findByLogin(userName);
                optionalUser.ifPresent(user -> session.setAttribute(ParameterName.USER, user));
                request.setAttribute(ParameterName.USERNAME, userName);
                session.setAttribute(ParameterName.USERNAME, userName);
                session.setAttribute(ParameterName.PASSWORD, password);
                session.setAttribute(ParameterName.ROLE, userRole.toString());
                router = new Router(ParameterName.BOOTSTRAP_HOME_PAGE /*ParameterName.HOME_PAGE*/, Router.Type.FORWARD);
            }else {
                request.setAttribute(ParameterName.ERROR_MESSAGE_SIGN_IN, ERROR_MESSAGE);
                router = new Router(ParameterName.INDEX_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("error in login", e);
            throw new CommandException(e);
        }
        return router;
    }
}
