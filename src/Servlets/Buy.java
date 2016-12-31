package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.User;
import Models.TransactionModel;

public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_MODEL = "model";
	private static final String ATT_SESSION_USER = "sessionUser";
	private static final String VIEW = "/WEB-INF/Views/transaction.jsp";

	public Buy() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATT_SESSION_USER);

		TransactionModel transactionModel = new TransactionModel();

		Beans.Transaction transaction = transactionModel.init();
		transaction.setAmount(transactionModel.buyToken(request.getParameter("buyAmount")));

		if (transactionModel.getErrors().isEmpty()) {
			user.setCapital(user.getCapital() + transaction.getAmount());

			session.setAttribute(ATT_SESSION_USER, user);
			
			// TODO : CREATE transaction + UPDATE User (capital)
			
		}

		request.setAttribute(ATT_MODEL, transactionModel);
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
