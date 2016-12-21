package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter {
	public static final String ACCES_CONNEXION  = "/connection";
    public static final String ATT_SESSION_USER = "sessionUser";

    public void init( FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
    		throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String chemin = request.getRequestURI().substring(request.getContextPath().length());
        
        if (chemin.startsWith("/registration") || chemin.startsWith("/Resources")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();

        if (session.getAttribute(ATT_SESSION_USER) == null) {
            request.getRequestDispatcher(ACCES_CONNEXION).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {}
}
