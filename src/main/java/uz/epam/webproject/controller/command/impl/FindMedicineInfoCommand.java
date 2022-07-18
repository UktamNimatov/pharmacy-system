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
import java.util.List;
import java.util.Optional;

public class FindMedicineInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        long medicineId = Long.parseLong(request.getParameter(ParameterName.MEDICINE_ID));
        Medicine medicineInfo;
        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_MEDICINE_LIST_TABLE);
        try {
            Optional<Medicine> optionalMedicine = medicineService.findById(medicineId);
            if (optionalMedicine.isEmpty()) {
                throw new ServiceException("could not find the medicine with id number: " + medicineId);
            }
            medicineInfo = optionalMedicine.get();
            session.setAttribute(ParameterName.TEMPORARY_MEDICINE, medicineInfo);
            session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_MEDICINE_INFO_PAGE);
            return new Router(ParameterName.BOOTSTRAP_MEDICINE_INFO_PAGE);
        } catch (ServiceException e) {
            logger.error("error in deleting the medicine by id ", e);
            throw new CommandException(e);
        }
    }

}
