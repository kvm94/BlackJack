package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Game;
import Beans.Turn;
import Beans.User;
import Models.GameModel;
import Models.TurnModel;
import Models.UserModel;

public class StopTurn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_MODEL = "model";
	private static final String ATT_TURN = "turn";
	private static final String ATT_SESSION_GAME = "sessionGame";
	private static final String ATT_SESSION_USER = "sessionUser";
	private static final String ATT_SESSION_TURN = "sessionTurn";
	private static final String VIEW = "/WEB-INF/Views/blackjack.jsp";

	public StopTurn() {
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
		Turn turn = (Turn) session.getAttribute(ATT_SESSION_TURN);
		User user = (User) session.getAttribute(ATT_SESSION_USER);
		Game game = (Game) session.getAttribute(ATT_SESSION_GAME);

		TurnModel turnModel = new TurnModel();
		GameModel gameModel = new GameModel(user);
		UserModel userModel = new UserModel();
		turn = turnModel.stop(turn);
		gameModel.setGame(game);
		
		request.setAttribute(ATT_MODEL, turnModel);
		request.setAttribute(ATT_TURN, turn);

		if (turn.isWin() != 2) {
			if (turn.isWin() == 0) {
				user.setCapital(user.getCapital() + turn.getBet());
			} else if (turn.isWin() == 1) {
				user.setCapital(user.getCapital() + (2 * turn.getBet()));
			} else if (turn.isWin() == 2) {
				user.setCapital(user.getCapital() + (5 / 2 * turn.getBet()));
			}
			game.getTurns().add(turn);
			game.setNbrTurns(game.getNbrTurns() + 1);
			
			try {
				if(game.getNbrTurns() > 1) {
					gameModel.UpdateGame();
				}
				else{
					gameModel.CreateGame();
				}
				turn.setIdGame(game.getId());
				turnModel.CreateTurn(turn);
				userModel.updateUser(user);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
		}

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}
