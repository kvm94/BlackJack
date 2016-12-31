package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Beans.Game;
import Beans.Turn;

public class GameModel {

	private Game game = new Game();

	private String result;
	private Map<String, String> errors = new HashMap<String, String>();

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
}
