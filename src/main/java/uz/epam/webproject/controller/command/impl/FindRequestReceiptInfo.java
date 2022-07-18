package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.entity.receipt.RequestReceipt;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.ReceiptService;
import uz.epam.webproject.service.RequestReceiptService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.ReceiptServiceImpl;
import uz.epam.webproject.service.impl.RequestReceiptServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class FindRequestReceiptInfo implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RequestReceiptService requestReceiptService = RequestReceiptServiceImpl.getInstance();

        RequestReceipt requestReceipt;

        long requestReceiptId = Long.parseLong(request.getParameter(ParameterName.REQUEST_RECEIPT_ID));
        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_REQUEST_RECEIPT_TABLE);

        try {
            if (isAdmin(session) || isDoctor(session)) {
                Optional<RequestReceipt> requestReceiptOptional = requestReceiptService.findById(requestReceiptId);
                if (requestReceiptOptional.isEmpty()) {
                    throw new ServiceException("could not find the request receipt with id number: " + requestReceiptId);
                }
                requestReceipt = requestReceiptOptional.get();
                request.setAttribute(ParameterName.TEMP_REQUEST_RECEIPT, requestReceipt);
                session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_MAKE_RECEIPT_PAGE);
                return new Router(ParameterName.BOOTSTRAP_MAKE_RECEIPT_PAGE);

            }
            return new Router(ParameterName.BOOTSTRAP_HOME_PAGE, Router.Type.REDIRECT);
        } catch (ServiceException e) {
            logger.error("error in finding the request receipt with id from database", e);
            throw new CommandException(e);
        }
    }
}
