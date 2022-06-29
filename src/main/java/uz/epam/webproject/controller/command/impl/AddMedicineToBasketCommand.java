package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.list.OrderMedicineList;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.service.MedicineService;
import uz.epam.webproject.service.OrderMedicineListService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.MedicineServiceImpl;
import uz.epam.webproject.service.impl.OrderMedicineListServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class AddMedicineToBasketCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ALREADY_IN_BASKET = "already_in_basket";
    private static final String ALREADY_IN_BASKET_MESSAGE = "item already in basket";
    private static final String QUANTITY = "quantity";
    protected static Double SUM = 0d;
    protected static Double TRANSACTION_COST = 0d;
    protected static Double TOTAL_COST = 0d;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        long medicineId = Long.parseLong(request.getParameter(ParameterName.MEDICINE_ID));

        String quantity = "1";

        logger.info(quantity + " is the value of quantity");
        HashMap<Medicine, String> medicineQuantityMap = (HashMap<Medicine, String>) session.getAttribute("medicine_quantity_map");

        Medicine medicineToAdd;
        List<Medicine> medicineBasket = (List<Medicine>) session.getAttribute(ParameterName.MEDICINE_BASKET);
        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_ORDER_MEDICINE_PAGE);
        try {
           Optional<Medicine> optionalMedicine = medicineService.findById(medicineId);
           if (optionalMedicine.isEmpty()){
               throw new ServiceException("could not find the medicine with id number: " + medicineId);
           }
           medicineToAdd = optionalMedicine.get();

//           if (medicineBasket.contains(medicineToAdd)) {
//           }else {
//              medicineBasket.add(medicineToAdd);
//              logger.info(medicineToAdd.toString() + " is being added " + " and the quantity is " + quantity);
//               medicineQuantityMap.putIfAbsent(medicineToAdd, quantity);
//           }

            if (medicineQuantityMap.containsKey(medicineToAdd)) {
                logger.info("this medicine already exists ");
               request.setAttribute(ALREADY_IN_BASKET, ALREADY_IN_BASKET_MESSAGE);
//                medicineQuantityMap.replace(medicineToAdd, quantity);
            }else {
                logger.info("new medicine is being added with the new quantity");
                medicineQuantityMap.put(medicineToAdd, quantity);
            }
            session.setAttribute("medicine_quantity_map", medicineQuantityMap);
           session.setAttribute(ParameterName.MEDICINE_BASKET, medicineBasket);

            double interval;
            SUM = 0d;
            for (Medicine medicine1 : medicineQuantityMap.keySet()){
                interval = medicine1.getPrice() * Double.parseDouble(medicineQuantityMap.get(medicine1));
                SUM = SUM + interval;
            }
            TRANSACTION_COST = SUM / 100;
            TOTAL_COST = TRANSACTION_COST + SUM;
            session.setAttribute("sum", String.format("%.2f", SUM));
            session.setAttribute("transaction_cost", String.format("%.2f", TRANSACTION_COST));
            session.setAttribute("total_cost", String.format("%.2f", TOTAL_COST));
        } catch (ServiceException e) {
            logger.error("error in adding a medicine to basket", e);
            throw new CommandException(e);
        }
        return new Router(ParameterName.BOOTSTRAP_ORDER_MEDICINE_PAGE, Router.Type.FORWARD);
    }

    @Override
    public boolean isPharmacist(HttpSession session) {
        return false;
    }


    @Override
    public boolean isAdmin(HttpSession session) {
        return false;
    }

    @Override
    public boolean isDoctor(HttpSession session) {
        return false;
    }
}
