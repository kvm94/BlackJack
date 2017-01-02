package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Game;
import Beans.User;
import Models.GameModel;

public class Play extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_SESSION_GAME = "sessionGame";
	private static final String ATT_TURN = "turn";
	private static final String VIEW = "/WEB-INF/Views/blackjack.jsp";

	public Play() {
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
		if (session.getAttribute(ATT_SESSION_GAME) == null) {
			User user = (User)session.getAttribute("sessionUser");
			GameModel model = new GameModel(user);
			Game game = model.init();
			session.setAttribute(ATT_SESSION_GAME, game);
		}

		request.setAttribute(ATT_TURN, null);

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}
