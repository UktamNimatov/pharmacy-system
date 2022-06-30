package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.OrderService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.OrderServiceImpl;
import uz.epam.webproject.validator.impl.OrderValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindAllOrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Router router;
        try {
            if (isPharmacist(session) || isDoctor(session) || isAdmin(session)) {
                List<Order> orders = orderService.findAll();
                if (orders != null) {
                    session.setAttribute(ParameterName.ORDERS, orders);
                    request.setAttribute(ParameterName.ORDERS, orders);
                    session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.LIST_OF_ORDERS_PAGE);
                    router = new Router(ParameterName.LIST_OF_ORDERS_PAGE, Router.Type.FORWARD);
                    return router;
                }
            }
            session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.SIDEBAR_PAGE);
            router = new Router(ParameterName.SIDEBAR_PAGE, Router.Type.FORWARD);

        } catch (ServiceException e) {
            logger.info("error in finding all orders ", e);
            throw new CommandException(e);
        }
        return router;
    }

    @Override
    public boolean isPharmacist(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.PHARMACIST);
    }

    @Override
    public boolean isDoctor(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.DOCTOR);
    }

    @Override
    public boolean isAdmin(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.ADMIN);
    }
}
