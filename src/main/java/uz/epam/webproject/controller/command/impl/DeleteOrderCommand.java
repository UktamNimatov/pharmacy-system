package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.MedicineService;
import uz.epam.webproject.service.OrderService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.MedicineServiceImpl;
import uz.epam.webproject.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderService orderService = OrderServiceImpl.getInstance();
        long orderId = Long.parseLong(request.getParameter(ParameterName.ORDER_ID));
        try {
            if (isAdmin(session)) {
                if (orderService.delete(orderId)) {
                    logger.info("order with id " + orderId + " successfully deleted");
                    List<Order> orderList = orderService.findAll();
                    request.setAttribute(ParameterName.ORDER_LIST, orderList);
                    request.setAttribute(ParameterName.ORDER_DELETED, ParameterName.ORDER_DELETED);
                } else {
                    logger.info("order with id " + orderId + " not deleted");
                    request.setAttribute(ParameterName.ORDER_NOT_DELETED, ParameterName.ORDER_NOT_DELETED);
                }
            }
            session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_ALL_ORDERS_PAGE);
            return new Router(ParameterName.BOOTSTRAP_ALL_ORDERS_PAGE);

        } catch (ServiceException e) {
            logger.error("error in deleting the medicine by id ", e);
            throw new CommandException(e);
        }
    }


    @Override
    public boolean isAdmin(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.ADMIN);
    }
}
