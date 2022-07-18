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
import java.util.*;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "Incorrect login or password";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String userName = request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);
        String defaultLocale = request.getParameter(ParameterName.LOCALE);

        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.LOCALE, defaultLocale);
        Router router;

        UserService userService = UserServiceImpl.getInstance();
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();
        OrderMedicineListService orderMedicineListService = OrderMedicineListServiceImpl.getInstance();

        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.INDEX_PAGE);
        logger.info("current page now ::::: " + session.getAttribute(ParameterName.CURRENT_PAGE));
        logger.info("request.contextPath is " + request.getContextPath());
        logger.info("request.servletPath is " + request.getServletPath());
        try {
            List<Medicine> medicineList = medicineService.findAll();

            HashMap<Medicine, String> medicineQuantityMap = new HashMap<>();
            session.setAttribute(ParameterName.MEDICINE_QUANTITY_MAP, medicineQuantityMap);

            session.setAttribute(ParameterName.MEDICINE_LIST, medicineList);

            if (userService.authenticate(userName, password)) {
                session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_HOME_PAGE);
                logger.info("current page now ::::: " + session.getAttribute(ParameterName.CURRENT_PAGE));
                UserRole userRole = userService.findUserRole(userName);
                Optional<User> optionalUser = userService.findByLogin(userName);

                User user;

                if (optionalUser.isEmpty()) {
                    return new Router(ParameterName.INDEX_PAGE, Router.Type.FORWARD);
                }

                List<OrderMedicineList> orderMedicineLists;
                user = optionalUser.get();
                session.setAttribute(ParameterName.USER, user);

                HashMap<Order, Double> orderAndCost = new HashMap<>();

                List<Double> orderTotalCostList = new ArrayList<>();
                double sumTotal = 0;

                HashMap<OrderMedicineList, String> medicineNameQuantityMap = new HashMap<>();

                List<Order> orderList = orderService.findOrdersByUserId(user.getId());
                if (orderList != null) {
                    session.setAttribute(ParameterName.ORDERS_BY_USER, orderList);

                    for (Order order : orderList) {
                        double orderTotalCost = 0d;
                        double interval;
                        orderMedicineLists = orderMedicineListService.findAllMedicineOfOneOrder(order.getId());
                        for (OrderMedicineList orderMedicineList : orderMedicineLists) {
                            Optional<Medicine> optionalMedicine = medicineService.findById(orderMedicineList.getMedicine_id());
                            if (optionalMedicine.isPresent()) {
                                Medicine medicine = optionalMedicine.get();
                                medicineNameQuantityMap.put(orderMedicineList, medicine.getTitle());
                            }
                            interval = orderMedicineList.getMedicine_quantity() * orderMedicineList.getMedicine_price();
                            orderTotalCost = orderTotalCost + interval;
                        }
                        orderTotalCostList.add(orderTotalCost);
                        orderAndCost.put(order, Double.parseDouble(String.format("%.2f", orderTotalCost)));
                    }

                    session.setAttribute(ParameterName.MEDICINE_NAME_QUANTITY_MAP, medicineNameQuantityMap);
                    session.setAttribute(ParameterName.ORDER_AND_COST, orderAndCost);
                }
                for (double oneOrderCost : orderTotalCostList) {
                    sumTotal = sumTotal + oneOrderCost;
                }

                session.setAttribute(ParameterName.ORDERS_TOTAL_COST, String.format("%.2f", sumTotal));


                session.setAttribute(ParameterName.USERNAME, userName);
                session.setAttribute(ParameterName.ROLE, userRole);
                router = new Router(ParameterName.BOOTSTRAP_HOME_PAGE, Router.Type.FORWARD);
            } else {
                request.setAttribute(ParameterName.ERROR_MESSAGE_SIGN_IN, ERROR_MESSAGE);
                router = new Router(ParameterName.INDEX_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("error in login", e);
            throw new CommandException(e);
        }
        return router;
    }
}
