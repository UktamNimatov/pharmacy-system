package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.service.OrderService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class FindAllOrdersByUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParameterName.USER);

        List<Order> orderList;
        try {
            orderList = orderService.findOrdersByUserId(user.getId());
            if (orderList != null && Objects.requireNonNull(orderList).size() != 0) {
                session.setAttribute(ParameterName.ORDERS_BY_USER, orderList);
            }
        } catch (ServiceException e) {
            logger.error("error in finding all orders by user with id " + user.getId());
            throw new CommandException(e);
        }
        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_CLIENT_INFO_PAGE);
        return new Router(ParameterName.BOOTSTRAP_CLIENT_INFO_PAGE, Router.Type.FORWARD);
    }

}
