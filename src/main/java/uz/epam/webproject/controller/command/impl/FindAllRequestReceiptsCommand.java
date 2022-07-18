package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.receipt.RequestReceipt;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.RequestReceiptService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.RequestReceiptServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindAllRequestReceiptsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RequestReceiptService requestReceiptService = RequestReceiptServiceImpl.getInstance();
        Router router;

        try {
            if (isAdmin(session) || isDoctor(session)) {
                List<RequestReceipt> requestReceipts = requestReceiptService.findAll();
                if (requestReceipts != null) {
                    session.setAttribute(ParameterName.REQUEST_RECEIPTS, requestReceipts);
                    session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_USERS_LIST_TABLE);
                    router = new Router(ParameterName.BOOTSTRAP_REQUEST_RECEIPT_TABLE, Router.Type.FORWARD);
                    return router;
                }
            }
            session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_HOME_PAGE);
            router = new Router(ParameterName.BOOTSTRAP_HOME_PAGE, Router.Type.REDIRECT);

        } catch (ServiceException e) {
            logger.error("error in getting all request receipts from database", e);
            throw new CommandException(e);
        }
        return router;
    }
}
