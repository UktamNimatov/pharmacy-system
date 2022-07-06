package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.list.OrderMedicineList;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.order.OrderStatus;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.OrderMedicineListService;
import uz.epam.webproject.service.OrderService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.OrderMedicineListServiceImpl;
import uz.epam.webproject.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Optional;

public class OrderMedicineCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        OrderService orderService = OrderServiceImpl.getInstance();
        OrderMedicineListService orderMedicineListService = OrderMedicineListServiceImpl.getInstance();

        User user = (User) session.getAttribute(ParameterName.USER);

        Timestamp now = new Timestamp(System.currentTimeMillis());


        session.setAttribute(ParameterName.TEMPORARY_USER, user);

        Order order = new Order(user.getId(), OrderStatus.NEW, now);
        OrderMedicineList orderMedicineList;


        logger.info("now ordered time: " + now);

        try {
            if (session.getAttribute(ParameterName.ROLE) == UserRole.GUEST) {
                return new Router(ParameterName.INDEX_PAGE, Router.Type.FORWARD);
            }

            if (orderService.addOrder(order)){
                logger.info("order added");

                Optional<Order> optionalOrder = orderService.findOrderByOrderedTime(now);

                logger.info("now ordered time: " + now);
                if (optionalOrder.isEmpty()){
                    throw new CommandException("error in find order by ordered time");
                }
                HashMap<Medicine, String> medicineQuantityMap = (HashMap<Medicine, String>) session.getAttribute(ParameterName.MEDICINE_QUANTITY_MAP);
                for (Medicine medicine : medicineQuantityMap.keySet()) {
                    logger.info("currently this medicine is being added: " + medicine.toString());
                    orderMedicineList = new OrderMedicineList(optionalOrder.get().getId(), medicine.getId(), Integer.parseInt(medicineQuantityMap.get(medicine)), medicine.getPrice());
                    orderMedicineListService.addOrderMedicineList(orderMedicineList);
                }
            }

        } catch (ServiceException e) {
            logger.error("error in adding a new order: ");
            throw new CommandException(e);
        }

        return new Router(ParameterName.BOOTSTRAP_CLIENT_INFO_PAGE, Router.Type.FORWARD);
    }

}
