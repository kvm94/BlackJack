package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Turn;
import Beans.User;
import Models.TurnModel;

public class Bet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_MODEL = "model";
	private static final String ATT_TURN = "turn";
	private static final String ATT_SESSION_USER = "sessionUser";
	private static final String ATT_SESSION_TURN = "sessionTurn";
	private static final String VIEW = "/WEB-INF/Views/blackjack.jsp";

	public Bet() {
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

		TurnModel turnModel = new TurnModel();

		Turn turn = turnModel.start(turnModel.bet(request.getParameter("bet"), user.getCapital()));

		if (turnModel.getErrors().isEmpty()) {
			user.setCapital(user.getCapital() - turn.getBet());

			request.setAttribute(ATT_TURN, turn);

			session.setAttribute(ATT_SESSION_USER, user);
			session.setAttribute(ATT_SESSION_TURN, null);
			session.setAttribute(ATT_SESSION_TURN, turn);
		}
		request.setAttribute(ATT_MODEL, turnModel);
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}
