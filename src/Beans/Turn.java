package Beans;

public class Turn {
	
	private int		id;
	private boolean win;
	private int 	croupierScore;
	private int 	userScore;
	private int		bet;
	private Game	game;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}
	
	public int getCroupierScore() {
		return croupierScore;
	}
	public void setCroupierScore(int croupierScore) {
		this.croupierScore = croupierScore;
	}
	
	public int getUserScore() {
		return userScore;
	}
	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}
	
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}	
}
