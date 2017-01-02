package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.User;
import Models.TransactionModel;
import Models.UserModel;

public class Sell extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_MODEL = "model";
	private static final String ATT_SESSION_USER = "sessionUser";
	private static final String VIEW = "/WEB-INF/Views/transaction.jsp";

	public Sell() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATT_SESSION_USER);

		TransactionModel transactionModel = new TransactionModel();
		UserModel userModel = new UserModel();

		Beans.Transaction transaction = transactionModel.init();
		transaction.setAmount(transactionModel.sellToken(request.getParameter("sellAmount"), user.getCapital()));

		if (transactionModel.getErrors().isEmpty()) {
			user.setCapital(user.getCapital() + transaction.getAmount());

			session.setAttribute(ATT_SESSION_USER, user);
			
			// TODO : CREATE transaction + UPDATE User (capital)
			try {
				transaction.setUser(user);
				transactionModel.createTransaction(transaction);
				userModel.updateUser(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.setAttribute(ATT_MODEL, transactionModel);
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
