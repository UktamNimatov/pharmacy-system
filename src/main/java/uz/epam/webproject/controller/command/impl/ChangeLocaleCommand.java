package uz.epam.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.concurrent.ForkJoinPool;

public class ChangeLocaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String LANGUAGE_ENGLISH = "EN";
    private static final String LANGUAGE_RUSSIAN = "RU";
    private static final String LANGUAGE_UZBEK = "UZ";
    private static final String LOCALE_ENGLISH = "en_US";
    private static final String LOCALE_RUSSIAN = "ru_RU";
    private static final String LOCALE_UZBEK = "uz_UZ";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(ParameterName.LOCALE);
        session.setAttribute(ParameterName.LOCALE, locale);
        logger.info("session.getAttribute(ParameterName.CURRENT_PAGE is " + session.getAttribute(ParameterName.CURRENT_PAGE));
        return new Router(String.valueOf(session.getAttribute(ParameterName.CURRENT_PAGE)));
//        return new Router(ParameterName.BOOTSTRAP_HOME_PAGE);
    }
}
