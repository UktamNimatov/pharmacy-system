package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.UserService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;


public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ALREADY_EXISTING_LOGIN = " - already existing login";
    private static final String ALREADY_EXISTING_EMAIL = " - already existing email";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        Router router;
        String login = request.getParameter(ColumnName.LOGIN);
        String password = request.getParameter(ColumnName.PASSWORD);
        String firstName = request.getParameter(ColumnName.FIRST_NAME);
        String lastName = request.getParameter(ColumnName.LAST_NAME);
        String email = request.getParameter(ColumnName.EMAIL);
        String role = request.getParameter(ColumnName.ROLE);

        logger.info("retrieved login is: " + login);
        logger.info("retrieved password is: " + password);
        logger.info("retrieved firstName is: " + firstName);
        logger.info("retrieved lastName is: " + lastName);

        User user = new User();

        try {
            if (userService.isLoginAvailable(login)) {
                user.setLogin(login);
            } else {
                request.setAttribute(ParameterName.UNAVAILABLE_LOGIN, login + ALREADY_EXISTING_LOGIN);
                return new Router(/*ParameterName.REGISTRATION_PAGE*/ ParameterName.BOOTSTRAP_REGISTRATION_PAGE, Router.Type.FORWARD);
            }
            if (userService.isEmailAvailable(email)) {
                logger.info("service level : " + email + " is available");
                user.setEmail(email);
            } else {
                request.setAttribute(ParameterName.UNAVAILABLE_EMAIL_ADDRESS, email + ALREADY_EXISTING_EMAIL);
                return new Router(/*ParameterName.REGISTRATION_PAGE*/ ParameterName.BOOTSTRAP_REGISTRATION_PAGE, Router.Type.FORWARD);
            }
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setRole(UserRole.valueOf(role));
            logger.info("user.toString() ===> " + user.toString());

            request.setAttribute(ParameterName.USER, user);
            if (userService.registerUser(user)) {
                return new Router(ParameterName.USERS_PAGE /*ParameterName.REGISTRATION_CONFIRMATION_PAGE*/, Router.Type.FORWARD);
            } else {
                return new Router(ParameterName.REGISTRATION_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("error in registering a new user", e);
            throw new CommandException(e);
        }
//        return router;
    }
}
