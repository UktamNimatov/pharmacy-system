package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.MedicineService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ChangeMedicineQuantityCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String QUANTITY = "quantity";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        long medicineId = Long.parseLong(request.getParameter(ParameterName.MEDICINE_ID));
        String quantity = request.getParameter(QUANTITY);
        logger.info("quantity is " + quantity);

        HashMap<Medicine, String> medicineQuantityMap = (HashMap<Medicine, String>) session.getAttribute(ParameterName.MEDICINE_QUANTITY_MAP);
        Medicine medicine;
        try {

//            if (session.getAttribute(ParameterName.ROLE) == UserRole.GUEST) {
//                return new Router(ParameterName.INDEX_PAGE, Router.Type.FORWARD);
//            }

            Optional<Medicine> optionalMedicine = medicineService.findById(medicineId);
            if (optionalMedicine.isEmpty()){
                throw new CommandException("could not find the medicine with id number: " + medicineId);
            }
            medicine = optionalMedicine.get();
            if (medicineQuantityMap.containsKey(medicine)) {
                logger.info("this medicine already exists just changing the quantity");
                medicineQuantityMap.replace(medicine, quantity);
            }else {
                logger.info("new medicine is being added with the new quantity");
                medicineQuantityMap.put(medicine, quantity);
            }

            session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_ORDER_MEDICINE_PAGE);
            session.setAttribute(ParameterName.MEDICINE_QUANTITY_MAP, medicineQuantityMap);
            logger.info("medicine_quantity_map is " + medicineQuantityMap.toString());

            AddMedicineToBasketCommand.getStatistics(request);

        } catch (ServiceException e) {
            logger.error("error in adding a medicine to basket", e);
            throw new CommandException(e);
        }
        return new Router(ParameterName.BOOTSTRAP_ORDER_MEDICINE_PAGE, Router.Type.FORWARD);
    }

}
