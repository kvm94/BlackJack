package Beans;

import java.time.LocalDate;
import java.util.List;

public class Game {

	private int id;
	private LocalDate date;
	private int nbrTurns;
	private int resultGame;
	private List<Turn> turns;
	private User user;

	public LocalDate getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public int getNbrTurns() {
		return nbrTurns;
	}

	public int getResultGame() {
		return resultGame;
	}

	public List<Turn> getTurns() {
		return turns;
	}

	public User getUser() {
		return user;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNbrTurns(int nbrTurns) {
		this.nbrTurns = nbrTurns;
	}

	public void setResultGame(int resultGame) {
		this.resultGame = resultGame;
	}

	public void setTurns(List<Turn> turns) {
		this.turns = turns;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
