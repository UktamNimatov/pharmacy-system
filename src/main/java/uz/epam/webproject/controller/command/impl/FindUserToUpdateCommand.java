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
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class FindUserToUpdateCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        User tempUserToUpdate;
        long id = Long.parseLong(request.getParameter(ParameterName.USER_ID));
        try {

            if (isAdmin(session)) {
                Optional<User> optionalUser = userService.findById(id);
                if (optionalUser.isEmpty()) {
                    throw new ServiceException("could not find the user with id number: " + id);
                }
                tempUserToUpdate = optionalUser.get();
                session.setAttribute(ParameterName.TEMPORARY_USER, tempUserToUpdate);
                return new Router(ParameterName.BOOTSTRAP_CLIENT_PROFILE_PAGE, Router.Type.FORWARD);
            }
            return new Router(ParameterName.BOOTSTRAP_HOME_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            logger.error("error in finding the user by id ", e);
            throw new CommandException(e);
        }
    }


    @Override
    public boolean isAdmin(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.ADMIN);
    }

}
