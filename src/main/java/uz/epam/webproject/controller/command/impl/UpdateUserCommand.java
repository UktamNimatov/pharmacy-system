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
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UpdateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ALREADY_EXISTING_LOGIN = " - already existing login";
    private static final String ALREADY_EXISTING_EMAIL = " - already existing email";
    private static final String INVALID_CERTIFICATE_NUMBER = " - invalid certificate number";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        User userToUpdate;
        String id = request.getParameter(ParameterName.USER_ID);
        try {
            Optional<User> optionalUser = userService.findById(Long.parseLong(id));
            if (optionalUser.isEmpty()) {
                throw new ServiceException("could not find the user with id number: " + id);
            }
            userToUpdate = optionalUser.get();
            logger.info("retrieved user from database is " + userToUpdate.toString());

            String login = request.getParameter(ColumnName.LOGIN);
            logger.info("login from input: " + login);
            String firstName = request.getParameter(ColumnName.FIRST_NAME);
            logger.info("login from first_name: " + firstName);
            String lastName = request.getParameter(ColumnName.LAST_NAME);
            logger.info("login from last_name: " + lastName);
            String email = request.getParameter(ColumnName.EMAIL);
            logger.info("login from email: " + email);
            String certificate = request.getParameter(ColumnName.CERTIFICATE);
            logger.info("login from certificate: " + certificate);

            if (userToUpdate.getLogin().equals(login)) {
                userToUpdate.setLogin(login);
                logger.info("as the logins are the same: " + login + " is set");
            } else {
                if (userService.isLoginAvailable(login)) {
                    userToUpdate.setLogin(login);
                } else {
                    request.setAttribute(ParameterName.UNAVAILABLE_LOGIN, login + ALREADY_EXISTING_LOGIN);
                    return new Router(ParameterName.BOOTSTRAP_PROFILE_PAGE, Router.Type.FORWARD);
                }
            }

            if (userToUpdate.getEmail().equals(email)) {
                userToUpdate.setEmail(email);
                logger.info("as the emails are the same: " + email + " is set");
            } else {
                if (userService.isEmailAvailable(email)) {
                    userToUpdate.setEmail(email);
                } else {
                    request.setAttribute(ParameterName.UNAVAILABLE_EMAIL_ADDRESS, email + ALREADY_EXISTING_EMAIL);
                    return new Router(ParameterName.BOOTSTRAP_PROFILE_PAGE, Router.Type.FORWARD);
                }
            }

            if (certificate != null) {
                if (userService.isCertificateValid(certificate)) {
                    userToUpdate.setCertificateSerialNumber(certificate);
                } else {
                    request.setAttribute(ParameterName.INVALID_CERTIFICATE_NUMBER, certificate + INVALID_CERTIFICATE_NUMBER);
                    return new Router(ParameterName.BOOTSTRAP_PROFILE_PAGE, Router.Type.FORWARD);
                }
            }

            userToUpdate.setFirstName(firstName);
            logger.info(firstName + " is set as firstName");
            userToUpdate.setLastName(lastName);
            logger.info(lastName + " is set as lastName");

            if (userService.updateUser(userToUpdate)) {
                logger.info("update operation is successful " + userToUpdate.toString());
                session.setAttribute(ParameterName.UPDATED_USER, userToUpdate);
                return new Router(ParameterName.USERS_PAGE, Router.Type.FORWARD);
            } else {
                logger.info("unsuccessful update operation " + userToUpdate.toString());
                return new Router(ParameterName.BOOTSTRAP_PROFILE_PAGE, Router.Type.FORWARD);
            }

        } catch (ServiceException e) {
            logger.error("error in updating the user ", e);
            throw new CommandException(e);
        }

    }

    @Override
    public boolean isPharmacist(HttpSession session) {
        return false;
    }

    @Override
    public boolean isAdmin(HttpSession session) {
        return false;
    }

    @Override
    public boolean isDoctor(HttpSession session) {
        return false;
    }
}
