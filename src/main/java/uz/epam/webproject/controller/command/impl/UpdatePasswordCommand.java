package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.service.UserService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdatePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String PASSWORD_CHANGE_SUCCESS = "Password has been successfully changed";
    private static final String PASSWORD_CHANGE_FAILURE = "New password is not valid";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ParameterName.USERNAME);
//        String login = request.getParameter(ParameterName.USERNAME);
        logger.info("retrieved login is " + login);
        String newPassword = request.getParameter(ParameterName.NEW_PASSWORD);
        logger.info("retrieved newPassword is " + newPassword);
        UserService userService = UserServiceImpl.getInstance();
        Router router;
        try {
            if (userService.updatePassword(login, newPassword)){
                request.setAttribute(ParameterName.USERNAME, login);
                request.setAttribute(ParameterName.PASSWORD_CHANGE_MESSAGE, PASSWORD_CHANGE_SUCCESS);
                router = new Router(ParameterName.HOME_PAGE);
            }else {
                router = new Router(ParameterName.PASSWORD_UPDATE_PAGE);
                request.setAttribute(ParameterName.PASSWORD_CHANGE_MESSAGE, PASSWORD_CHANGE_FAILURE);
            }
        } catch (ServiceException e) {
            logger.error("error in updating the password", e);
            throw new CommandException(e);
        }
        return router;
    }
}
