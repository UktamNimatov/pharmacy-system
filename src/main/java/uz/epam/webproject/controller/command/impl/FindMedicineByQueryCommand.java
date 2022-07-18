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
import java.util.List;

public class FindMedicineByQueryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NO_MEDICINE = "no_medicine_with_this_name";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MedicineService medicineService = MedicineServiceImpl.getInstance();
        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_ORDER_MEDICINE_PAGE);
        Router router;
        String searchQuery = request.getParameter(ParameterName.MEDICINE_SEARCH_QUERY);
        try {
            List<Medicine> medicineList = medicineService.findMedicineByQuery(searchQuery);
            if (medicineList != null) {
                request.setAttribute(ParameterName.MEDICINE_LIST_BY_QUERY, medicineList);
            } else {
                request.setAttribute(NO_MEDICINE, NO_MEDICINE);
            }
            router = new Router(ParameterName.BOOTSTRAP_ORDER_MEDICINE_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            logger.error("error in finding medicine by search query", e);
            throw new CommandException(e);
        }
        return router;
    }
}
