package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Deconnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public static final String VIEW = "/connection";
    
    public Deconnection() {
        super();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) 
    		throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }


}
