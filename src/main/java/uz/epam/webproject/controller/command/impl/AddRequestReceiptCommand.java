package uz.epam.webproject.controller.command.impl;

import org.apache.catalina.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.entity.receipt.RequestReceipt;
import uz.epam.webproject.entity.receipt.RequestReceiptStatus;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.service.RequestReceiptService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.service.impl.RequestReceiptServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class AddRequestReceiptCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String SUCCESS_MESSAGE = " request successfully posted ";
    private static final String UNSUCCESS_MESSAGE = " failed to post ";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RequestReceiptService requestReceiptService = RequestReceiptServiceImpl.getInstance();

        String illnessDescription = request.getParameter(ColumnName.ILLNESS_DESCRIPTION);
        User currentUser = (User) session.getAttribute(ParameterName.USER);
        long userId = currentUser.getId();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        RequestReceipt requestReceipt = new RequestReceipt(illnessDescription, userId, RequestReceiptStatus.NEW, now);

        try {
            if (requestReceiptService.addEntity(requestReceipt)) {
                request.setAttribute(ParameterName.OPERATION_MESSAGE, SUCCESS_MESSAGE);
            } else {
                request.setAttribute(ParameterName.OPERATION_MESSAGE, UNSUCCESS_MESSAGE);
            }
            return new Router(ParameterName.BOOTSTRAP_HOME_PAGE);
        } catch (ServiceException e) {
            logger.error("error in adding a request receipt: ");
            throw new CommandException(e);
        }
    }

}
