package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.UserService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(ParameterName.PAGES_PATH);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        Router router;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        logger.info("retrieved login is: " + login);
        logger.info("retrieved password is: " + password);
        logger.info("retrieved firstName is: " + firstName);
        logger.info("retrieved lastName is: " + lastName);

        User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            user.setRole(UserRole.CLIENT);
        logger.info("user.toString() ===> "+user.toString());

        try {
            if (userService.registerUser(user)){
                request.setAttribute("user", user);
                router = new Router(resourceBundle.getString(ParameterName.USERS_PAGE));
            }else {
                router = new Router(resourceBundle.getString(ParameterName.HOME_PAGE), Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("error in registering a new user", e);
            throw new CommandException(e);
        }
        return router;
    }
}
