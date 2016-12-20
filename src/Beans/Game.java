package Beans;

import java.time.LocalDate;
import java.util.List;

public class Game {
	
	private int			id;
	private LocalDate 	date;
	private int 		nbrTurns;
	private int			resultGame;
	private List<Turn> 	turns;
	private User		user;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getNbrTurns() {
		return nbrTurns;
	}
	public void setNbrTurns(int nbrTurns) {
		this.nbrTurns = nbrTurns;
	}	
	
	public int getResultGame() {
		return resultGame;
	}
	public void setResultGame(int resultGame) {
		this.resultGame = resultGame;
	}
	
	public List<Turn> getTurns() {
		return turns;
	}
	public void setTurns(List<Turn> turns) {
		this.turns = turns;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
