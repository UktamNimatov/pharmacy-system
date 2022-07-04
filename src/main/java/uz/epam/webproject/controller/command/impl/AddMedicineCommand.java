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

public class AddMedicineCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String MEDICINE_NOT_CREATED_MESSAGE = "medicine could not be created";
    private static final String MEDICINE_CREATED_MESSAGE = "medicine_created with title: ";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        Router router = new Router(ParameterName.HOME_PAGE, Router.Type.REDIRECT);
        HttpSession session = request.getSession();

        String title = request.getParameter(ParameterName.MEDICINE_TITLE);
        double price = Double.parseDouble(request.getParameter(ParameterName.MEDICINE_PRICE));
        String description = request.getParameter(ParameterName.MEDICINE_DESCRIPTION);
        boolean withPrescription = Boolean.parseBoolean(request.getParameter(ParameterName.WITH_PRESCRIPTION));

        Medicine medicine = new Medicine(title, price, description, withPrescription);
        try {
            if (isPharmacist(session) || isAdmin(session)) {
                if (medicineService.addEntity(medicine)) {
                    request.setAttribute(ParameterName.MEDICINE_CREATED, MEDICINE_CREATED_MESSAGE+medicine.getTitle());
                    session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_MEDICINE_LIST_TABLE);
                    router = new Router(ParameterName.BOOTSTRAP_MEDICINE_LIST_TABLE, Router.Type.FORWARD);
                } else {
                    request.setAttribute(ParameterName.MEDICINE_NOT_CREATED, MEDICINE_NOT_CREATED_MESSAGE);
                    session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_HOME_PAGE);
                    router = new Router(ParameterName.BOOTSTRAP_HOME_PAGE, Router.Type.FORWARD);
                }
            }
        } catch (ServiceException e) {
            logger.error("error in adding a new medicine ", e);
            throw new CommandException(e);
        }
        return router;
    }

    @Override
    public boolean isPharmacist(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.PHARMACIST);
    }

    @Override
    public boolean isAdmin(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.ADMIN);
    }
}
