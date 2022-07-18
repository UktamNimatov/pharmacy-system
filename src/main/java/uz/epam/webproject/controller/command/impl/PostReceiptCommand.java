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
import uz.epam.webproject.service.RequestReceiptService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.ReceiptServiceImpl;
import uz.epam.webproject.service.impl.RequestReceiptServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class PostReceiptCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NO_PERMISSION = "You have no access to this page";
    private static final String SUCCESS_MESSAGE = " successfully posted ";
    private static final String UNSUCCESS_MESSAGE = " failed to post ";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ReceiptService receiptService = ReceiptServiceImpl.getInstance();
        RequestReceiptService requestReceiptService = RequestReceiptServiceImpl.getInstance();

        Router router;

        User currentUser = (User) session.getAttribute(ParameterName.USER);
        long doctorId = currentUser.getId();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long clientUserId = Long.parseLong(request.getParameter(ParameterName.USER_ID));
        String receiptUsage = request.getParameter(ParameterName.RECEIPT_USAGE);

        Receipt receipt = new Receipt(doctorId, clientUserId, receiptUsage, now);

        try {
            if (receiptService.addReceipt(receipt)) {
                request.setAttribute(ParameterName.OPERATION_MESSAGE, SUCCESS_MESSAGE);
                session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_HOME_PAGE);
                requestReceiptService.changeStatus(Long.parseLong(request.getParameter(ParameterName.TEMP_REQUEST_RECEIPT_ID)));

            } else {
                request.setAttribute(ParameterName.OPERATION_MESSAGE, UNSUCCESS_MESSAGE);
            }
            router = new Router(ParameterName.BOOTSTRAP_HOME_PAGE);

        } catch (ServiceException e) {
            logger.error("error in posting a new receipt ", e);
            throw new CommandException(e);
        }
        return router;
    }
}
