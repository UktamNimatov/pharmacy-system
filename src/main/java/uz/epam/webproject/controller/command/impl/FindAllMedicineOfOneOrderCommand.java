package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.rolling.action.IfFileName;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.list.OrderMedicineList;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.OrderMedicineListService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.OrderMedicineListServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindAllMedicineOfOneOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderMedicineListService orderMedicineListService = OrderMedicineListServiceImpl.getInstance();

        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_CLIENT_INFO_PAGE);
        List<OrderMedicineList> orderMedicineListList;
        try {
            if (isAdmin(session)) {
                long orderId = Long.parseLong(request.getParameter(ParameterName.ORDER_ID));
                orderMedicineListList = orderMedicineListService.findAllMedicineOfOneOrder(orderId);
                if (orderMedicineListList != null) {
                    request.setAttribute(ParameterName.MEDICINES_OF_ONE_ORDER, orderMedicineListList);
                }
            }
        } catch (ServiceException e) {
            logger.error("error in finding medicines of one order with order id ");
            throw new CommandException(e);
        }
        return new Router(ParameterName.BOOTSTRAP_CLIENT_INFO_PAGE, Router.Type.FORWARD);
    }
}
