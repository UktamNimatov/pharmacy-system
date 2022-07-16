package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.receipt.Receipt;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.ReceiptService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.ReceiptServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindAllReceiptsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NO_PERMISSION = "You have no access to this page";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ReceiptService receiptService = ReceiptServiceImpl.getInstance();

        Router router;
        try {
            if (isAdmin(session) || isDoctor(session)) {
                List<Receipt> receipts = receiptService.findAll();
                if (receipts != null) {
                    session.setAttribute(ParameterName.RECEIPTS_LIST, receipts);
                    session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_RECEIPT_LIST_TABLE);
                    router = new Router(ParameterName.BOOTSTRAP_RECEIPT_LIST_TABLE, Router.Type.FORWARD);
                    return router;
                }
            }
            session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_HOME_PAGE);
            router = new Router(ParameterName.BOOTSTRAP_HOME_PAGE, Router.Type.REDIRECT);

        } catch (ServiceException e) {
            logger.error("error in getting all receipts from database", e);
            throw new CommandException(e);
        }
        return router;
    }

    @Override
    public boolean isAdmin(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.ADMIN);
    }

    @Override
    public boolean isDoctor(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.DOCTOR);
    }
}
