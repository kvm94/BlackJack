package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.User;
import Models.UserModel;

public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ATT_USER         = "user";
    public static final String ATT_MODEL        = "model";
    public static final String ATT_SESSION_USER = "sessionUser";
    public static final String VIEW             = "/WEB-INF/Views/connection.jsp";
	
    public Connection() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		UserModel model = new UserModel();

		User user = model.connect(request.getParameter("mail"), request.getParameter("password"));

		HttpSession session = request.getSession();

		if (model.getErrors().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, user);
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
		}
		request.setAttribute(ATT_MODEL, model);
	    request.setAttribute(ATT_USER, user);

	    this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}
