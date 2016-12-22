package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.User;
import Models.UserModel;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_USER = "user";
	private static final String ATT_MODEL = "model";
	private static final String VIEW = "/WEB-INF/Views/registration.jsp";
	
    public Registration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		UserModel model = new UserModel();
		
		User user = model.regist(request.getParameter("mail"), request.getParameter("password"), request.getParameter("confirmPassword"), request.getParameter("name"), request.getParameter("firstName"), request.getParameter("birthDate"));
		
		request.setAttribute(ATT_MODEL, model);
		request.setAttribute(ATT_USER, user);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}