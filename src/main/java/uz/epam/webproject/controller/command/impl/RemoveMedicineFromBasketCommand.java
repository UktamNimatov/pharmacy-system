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
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RemoveMedicineFromBasketCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        long medicineId = Long.parseLong(request.getParameter(ParameterName.MEDICINE_ID));
        Medicine medicineToRemove;
        List<Medicine> medicineBasket = (List<Medicine>) session.getAttribute(ParameterName.MEDICINE_BASKET);
        HashMap<Medicine, String> medicineQuantityMap = (HashMap<Medicine, String>) session.getAttribute(ParameterName.MEDICINE_QUANTITY_MAP);

        try {
            Optional<Medicine> optionalMedicine = medicineService.findById(medicineId);
            if (optionalMedicine.isEmpty()){
                throw new CommandException("could not find the medicine with id number: " + medicineId);
            }
            medicineToRemove = optionalMedicine.get();
            medicineQuantityMap.remove(medicineToRemove);
            session.setAttribute(ParameterName.MEDICINE_QUANTITY_MAP, medicineQuantityMap);
            session.setAttribute(ParameterName.MEDICINE_BASKET, medicineBasket);

            AddMedicineToBasketCommand.getStatistics(request);

//            double interval;
//            AddMedicineToBasketCommand.SUM = 0d;
//            for (Medicine medicine1 : medicineQuantityMap.keySet()){
//                interval = medicine1.getPrice() * Double.parseDouble(medicineQuantityMap.get(medicine1));
//                AddMedicineToBasketCommand.SUM = AddMedicineToBasketCommand.SUM + interval;
//            }
//            AddMedicineToBasketCommand.TRANSACTION_COST = AddMedicineToBasketCommand.SUM / 100;
//            AddMedicineToBasketCommand.TOTAL_COST = AddMedicineToBasketCommand.TRANSACTION_COST + AddMedicineToBasketCommand.SUM;
//            session.setAttribute("sum", String.format("%.2f", AddMedicineToBasketCommand.SUM));
//            session.setAttribute("transaction_cost", String.format("%.2f", AddMedicineToBasketCommand.TRANSACTION_COST));
//            session.setAttribute("total_cost", String.format("%.2f", AddMedicineToBasketCommand.TOTAL_COST));
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
