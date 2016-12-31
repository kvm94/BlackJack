package Beans;

import java.util.List;

public class Turn {

	private int id;
	private int idGame;
	private int win;
	private int croupierScore;
	private int userScore;
	private double bet;
	private Deck deck;
	private List<Card> userHand;
	private List<Card> croupierHand;

	public double getBet() {
		return bet;
	}

	public List<Card> getCroupierHand() {
		return croupierHand;
	}

	public int getCroupierScore() {
		return croupierScore;
	}

	public Deck getDeck() {
		return deck;
	}

	public int getId() {
		return id;
	}

	public List<Card> getUserHand() {
		return userHand;
	}

	public int getUserScore() {
		return userScore;
	}

	public int isWin() {
		return win;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public void setCroupierHand(List<Card> croupierHand) {
		this.croupierHand = croupierHand;
	}

	public void setCroupierScore(int croupierScore) {
		this.croupierScore = croupierScore;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserHand(List<Card> userHand) {
		this.userHand = userHand;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}
}
