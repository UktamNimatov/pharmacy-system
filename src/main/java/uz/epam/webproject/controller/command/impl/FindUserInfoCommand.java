package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.list.OrderMedicineList;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.MedicineService;
import uz.epam.webproject.service.OrderMedicineListService;
import uz.epam.webproject.service.OrderService;
import uz.epam.webproject.service.UserService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.MedicineServiceImpl;
import uz.epam.webproject.service.impl.OrderMedicineListServiceImpl;
import uz.epam.webproject.service.impl.OrderServiceImpl;
import uz.epam.webproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class FindUserInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NO_ORDERS_YET = "no_orders_yet";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        UserService userService = UserServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();

        User tempUserToUpdate;
        long id = Long.parseLong(request.getParameter(ParameterName.USER_ID));
        try {
            if (isAdmin(session)) {
                Optional<User> optionalUser = userService.findById(id);
                if (optionalUser.isEmpty()) {
                    throw new CommandException("could not find the user with id number: " + id);
                }
                tempUserToUpdate = optionalUser.get();
                List<Order> orderList = orderService.findOrdersByUserId(tempUserToUpdate.getId());

                if (orderList != null) {
                    request.setAttribute(ParameterName.ORDERS_BY_USER, orderList);
                }

                request.setAttribute(ParameterName.TEMPORARY_USER, tempUserToUpdate);
                return new Router(ParameterName.BOOTSTRAP_CLIENT_INFO_PAGE, Router.Type.FORWARD);
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
