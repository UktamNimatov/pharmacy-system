package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.user.User;
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
    private static final String USER_ID = "userId";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        long userId = Long.parseLong(request.getParameter(USER_ID));
        try {
            Optional<User> optionalUser = userService.findById(userId);
            String userToString = "";
            if (optionalUser.isPresent()){
                userToString =  optionalUser.get().toString();
            }
            if (userService.delete(userId)){
                logger.info("User with id " + userId + " successfully deleted");
                List<User> userList = userService.findAll();
                request.setAttribute(ParameterName.USERS, userList);
                request.setAttribute(ParameterName.USER_DELETED, userToString);
            }else {
                logger.info("User with id " + userId + " not deleted");
                request.setAttribute(ParameterName.USER_NOT_DELETED, ParameterName.USER_NOT_DELETED);
            }
            return new Router(ParameterName.NEW_LIST_OF_USERS_PAGE);

        } catch (ServiceException e) {
            logger.error("error in deleting the user by id ", e);
            throw new CommandException(e);
        }
    }

    @Override
    public boolean isPharmacist(HttpSession session) {
        return false;
    }

    @Override
    public User getAuthUser(HttpSession session) {
        return null;
    }
}
