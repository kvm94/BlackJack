package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Beans.Game;
import Beans.Turn;
import Beans.User;
import DAO.AbstractDAOFactory;
import DAO.GameDAO;

public class GameModel {

	private Game game = new Game();

	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
	private AbstractDAOFactory adf;
	private GameDAO gameDAO;
	
	public GameModel(User u){
		game.setUser(u);
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Game init() {
		game.setDate(LocalDate.now());
		game.setTurns(new ArrayList<Turn>());
		
		
		
		return game;
	}
	
	public void CreateGame() throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		gameDAO = (GameDAO)adf.getGameDAO();
		
		gameDAO.create(game);	
		
	}
	
	public int GetIdGame() throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		gameDAO = (GameDAO)adf.getGameDAO();
		
		return gameDAO.getId(game);
	}
	
	public void UpdateGame() throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		gameDAO = (GameDAO)adf.getGameDAO();
		
		gameDAO.update(game);
	}
	
	public void setGame(Game game){
		this.game = game;
	}
}
