package Models;

import java.time.LocalDate;
import java.util.ArrayList;

import Beans.Game;
import Beans.Turn;
import Beans.User;
import DAO.AbstractDAOFactory;
import DAO.GameDAO;

public class GameModel {

	private Game game = new Game();

	// Attributs utile pour la DAO
	private AbstractDAOFactory adf;
	private GameDAO gameDAO;
	public GameModel(User u){
		game.setUser(u);
	}
	public void setGame(Game game){

		this.game = game;
	}
	// Utile pour l'enregistrement des turn
	public int GetIdGame() throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		gameDAO = (GameDAO)adf.getGameDAO();
		
		return gameDAO.getId(game);
	}
	
	// Fonction d'initialisation du game
	public Game init() {
		game.setDate(LocalDate.now());
		game.setTurns(new ArrayList<Turn>());
		return game;
	}
	
	// Enregistrement du Game dans la DB
	public void CreateGame() throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		gameDAO = (GameDAO)adf.getGameDAO();
		
		gameDAO.create(game);	
		
	}

	// Mise à jour du Game pour chaque tour ajouté
	public void UpdateGame() throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		gameDAO = (GameDAO)adf.getGameDAO();
		
		gameDAO.update(game);
	}
}
