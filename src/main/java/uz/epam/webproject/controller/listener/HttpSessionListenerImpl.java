package uz.epam.webproject.controller.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.entity.user.UserRole;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_LOCALE = "en_US";
    private static final String DEFAULT_LANGUAGE = "EN";

    public HttpSessionListenerImpl() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();

        httpSession.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.INDEX_PAGE);
        httpSession.setAttribute(ParameterName.LOCALE, DEFAULT_LOCALE);
        httpSession.setAttribute(ParameterName.LANGUAGE, DEFAULT_LANGUAGE);

        httpSession.setAttribute(ParameterName.ROLE, UserRole.GUEST);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
