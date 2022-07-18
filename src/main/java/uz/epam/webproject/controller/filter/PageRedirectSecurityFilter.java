package uz.epam.webproject.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.controller.command.ParameterName;
import uz.epam.webproject.entity.user.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = {"/startbootstrap/*", "/*command*"}, initParams = {@WebInitParam(name = "INDEX", value = "/index.jsp")})
public class PageRedirectSecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter("INDEX");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        UserRole role = (UserRole) httpRequest.getSession().getAttribute(ParameterName.ROLE);
        if (httpRequest.getSession().getAttribute(ParameterName.ROLE).equals(null)) {
            logger.info("this filter is working ______------ ");
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        }else {
            httpRequest.getRequestDispatcher(ParameterName.CURRENT_PAGE);
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }
}
