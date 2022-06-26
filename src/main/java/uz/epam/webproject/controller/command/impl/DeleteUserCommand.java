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
import java.util.List;
import java.util.Optional;

public class DeleteUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        long userId = Long.parseLong(request.getParameter(ParameterName.USER_ID));
        try {
            Optional<User> optionalUser = userService.findById(userId);
            String userToString = "";
            if (optionalUser.isPresent()){
                userToString =  optionalUser.get().toString();
            }
            if (isAdmin(session) || true) {
                if (userService.delete(userId)) {
                    logger.info("User with id " + userId + " successfully deleted");
                    List<User> userList = userService.findAll();
                    request.setAttribute(ParameterName.USERS, userList);
                    request.setAttribute(ParameterName.USER_DELETED, userToString);
                } else {
                    logger.info("User with id " + userId + " not deleted");
                    request.setAttribute(ParameterName.USER_NOT_DELETED, ParameterName.USER_NOT_DELETED);
                }
            }
            session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_USERS_LIST_TABLE);
            return new Router(ParameterName.BOOTSTRAP_USERS_LIST_TABLE);

        } catch (ServiceException e) {
            logger.error("error in deleting the user by id ", e);
            throw new CommandException(e);
        }
    }

    @Override
    public boolean isAdmin(HttpSession session) {
        return  session.getAttribute(ParameterName.ROLE).equals(UserRole.ADMIN.toString());
    }

    @Override
    public User getAuthUser(HttpSession session) {
        return null;
    }
}
