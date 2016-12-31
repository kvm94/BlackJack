package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.User;

public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_USER = "user";
	private static final String ATT_SESSION_USER = "sessionUser";
	private static final String VIEW = "/WEB-INF/Views/menu.jsp";

	public Menu() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATT_SESSION_USER);
		request.setAttribute(ATT_USER, user);
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
