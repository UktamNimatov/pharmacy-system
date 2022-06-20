package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.service.UserService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        Router router;
        try {
            List<User> users = userService.findAll();
            if (users != null){
            request.setAttribute(ParameterName.USERS, users);
            router = new Router(ParameterName.NEW_LIST_OF_USERS_PAGE , Router.Type.FORWARD);
            }else {
                router = new Router(ParameterName.HOME_PAGE, Router.Type.REDIRECT);

            }

        } catch (ServiceException e) {
            logger.error("error in getting all users from database", e);
            throw new CommandException(e);
        }
        return router;
    }
}
