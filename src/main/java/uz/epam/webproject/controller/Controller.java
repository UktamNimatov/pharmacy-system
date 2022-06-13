package uz.epam.webproject.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.CommandType;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init() throws ServletException {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        response.setContentType("text/html");
        String strCommand = request.getParameter(ParameterName.COMMAND);
        logger.info("command is: " + strCommand);
        Command command = CommandType.of(strCommand);
        Router router;
        try {
            router = command.execute(request);
            logger.info("moving to page: " + router.getPage());
            if (router.getActionType() == Router.Type.FORWARD){
                logger.info("inside if");
                request.getRequestDispatcher(router.getPage()).forward(request, response);
            }else {
                logger.info("inside else");
                response.sendRedirect(request.getContextPath() + router.getPage());
            }
        } catch (CommandException e) {
            logger.error("error in executing command" + strCommand, e);
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
    }
}
