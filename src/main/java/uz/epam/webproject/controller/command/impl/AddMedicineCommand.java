package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.service.MedicineService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddMedicineCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        Router router;
        HttpSession session = request.getSession();

        String title = request.getParameter(ParameterName.MEDICINE_TITLE);
        double price = Double.parseDouble(request.getParameter(ParameterName.MEDICINE_PRICE));
        String description = request.getParameter(ParameterName.MEDICINE_DESCRIPTION);
        boolean withPrescription = Boolean.parseBoolean(request.getParameter(ParameterName.WITH_PRESCRIPTION));

        Medicine medicine = new Medicine(title, price, description, withPrescription);
        try {
            if (medicineService.addEntity(medicine)){
                session.setAttribute(ParameterName.MEDICINE_CREATED, medicine);
                router = new Router(ParameterName.MEDICINE_CREATED_PAGE, Router.Type.FORWARD);
            }else{
                router = new Router(ParameterName.HOME_PAGE, Router.Type.REDIRECT);
            }

        } catch (ServiceException e) {
            logger.error("error in adding a new medicine ", e);
            throw new CommandException(e);
        }
        return router;
    }
}
