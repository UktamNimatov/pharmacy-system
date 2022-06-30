package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.MedicineService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UpdateMedicineCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        Medicine medicineToUpdate;
        long id = Long.parseLong(request.getParameter(ParameterName.MEDICINE_ID));
        try {
            Optional<Medicine> optionalMedicine = medicineService.findById(id);
            if (optionalMedicine.isEmpty()){
                throw new CommandException("could not find the medicine with id number: " + id);
            }
            medicineToUpdate = optionalMedicine.get();

            if (isAdmin(session) || isPharmacist(session)) {

                String title = request.getParameter(ColumnName.TITLE);
                double price = Double.parseDouble(request.getParameter(ColumnName.PRICE));
                String description = request.getParameter(ColumnName.DESCRIPTION);

                if (!title.equals(medicineToUpdate.getTitle())) {
                    medicineToUpdate.setTitle(title);
                }

                if (price != medicineToUpdate.getPrice()) {
                    medicineToUpdate.setPrice(price);
                }

                if (!description.equals(medicineToUpdate.getDescription())) {
                    medicineToUpdate.setDescription(description);
                }

                if (medicineService.updateMedicine(medicineToUpdate)) {
                    logger.info("update operation is successful " + medicineToUpdate.toString());
                    session.setAttribute(ParameterName.UPDATED_MEDICINE, medicineToUpdate);
                    session.setAttribute(ParameterName.TEMPORARY_MEDICINE, medicineToUpdate);
                    return new Router(ParameterName.BOOTSTRAP_MEDICINE_INFO_PAGE, Router.Type.FORWARD);
                }
                session.setAttribute(ParameterName.OPERATION_MESSAGE, ParameterName.FAILED_TO_UPDATE_MEDICINE);
                return new Router(ParameterName.BOOTSTRAP_MEDICINE_PROFILE_PAGE);

            }
            return new Router(ParameterName.BOOTSTRAP_HOME_PAGE, Router.Type.FORWARD);
        }catch (ServiceException e){
            logger.error("error in updating the medicine ", e);
            throw new CommandException(e);
        }
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
