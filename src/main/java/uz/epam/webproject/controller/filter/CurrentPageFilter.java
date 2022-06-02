package uz.epam.webproject.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.CommandType;
import uz.epam.webproject.controller.command.ParameterName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter(urlPatterns = {"/controller", "/pages/*"})
public class CurrentPageFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    public static final String COMMAND_DELIMITER = "?";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpServletRequest.getParameter(ParameterName.COMMAND) != null){
//            String commandName = httpServletRequest.getParameter(ParameterName.COMMAND);
            String currentPage = httpServletRequest.getServletPath() + COMMAND_DELIMITER + httpServletRequest.getQueryString();
            httpSession.setAttribute(ParameterName.CURRENT_PAGE, currentPage);
            logger.log(Level.INFO, "currentPage is ----> " + currentPage);
        }else {
            httpSession.setAttribute(ParameterName.CURRENT_PAGE, httpServletRequest.getServletPath());
            logger.log(Level.INFO, "currentPage is ----> " + httpServletRequest.getServletPath());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
