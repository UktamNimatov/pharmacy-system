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
import java.util.List;
import java.util.Optional;

public class AddMedicineToBasketCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ALREADY_IN_BASKET = "already_in_basket";
    private static final String ALREADY_IN_BASKET_MESSAGE = "item already in basket";
    private static final String QUANTITY = "quantity";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        long medicineId = Long.parseLong(request.getParameter(ParameterName.MEDICINE_ID));
        Medicine medicineToAdd;
        List<Medicine> medicineBasket = (List<Medicine>) session.getAttribute(ParameterName.MEDICINE_BASKET);
        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_ORDER_MEDICINE_PAGE);
        try {
           Optional<Medicine> optionalMedicine = medicineService.findById(medicineId);
           if (optionalMedicine.isEmpty()){
               throw new ServiceException("could not find the medicine with id number: " + medicineId);
           }
           medicineToAdd = optionalMedicine.get();
           if (medicineBasket.contains(medicineToAdd)) {
               request.setAttribute(ALREADY_IN_BASKET, ALREADY_IN_BASKET_MESSAGE);
           }else {
              medicineBasket.add(medicineToAdd);
           }
           session.setAttribute(ParameterName.MEDICINE_BASKET, medicineBasket);
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
