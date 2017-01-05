package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Beans.Card;
import Beans.Deck;
import Beans.Turn;
import DAO.AbstractDAOFactory;
import DAO.TurnDAO;

public class TurnModel {

	private static final String CHAMP_BET = "bet";

	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
	
	private AbstractDAOFactory adf;
	private TurnDAO turnDAO;

	public int bet(String bet, double capital) {

		int betInt = 0;

		try {
			validBet(bet);
		} catch (Exception e) {
			setError(CHAMP_BET, e.getMessage());
		}
		try {
			betInt = Integer.parseInt(bet);
		} catch (Exception e) {

		}
		try {
			validBet(betInt, capital);
		} catch (Exception e) {
			setError(CHAMP_BET, e.getMessage());
		}

		if (errors.isEmpty()) {
			result = "Succès de la mise.";
		} else {
			result = "Échec de la mise.";
		}
		return betInt;
	}

	private void distributeACard(List<Card> hand, Card card) {
		hand.add(card);
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Turn pick(Turn turn) {

		DeckModel deck = new DeckModel();

		this.distributeACard(turn.getUserHand(), deck.pick(turn.getDeck()));

		// On calcul le score de l'utilisateur
		turn.setUserScore(this.setHandValue(turn.getUserHand()));

		// Si l'utilisateur a plus de 21 = Défaite
		if (turn.getUserScore() > 21) {
			turn.setWin(0);
		}
		// Si l'utilisateur a 21 on le signal
		else if (turn.getUserScore() == 21) {
			// Si égalité
			if (turn.getCroupierScore() == 21) {
				turn.setWin(1);
			// Si victoire
			} else {
				turn.setWin(2);
			}
		}

		return turn;
	}

	public String printHand(List<Card> hand) {
		String handString = "";
		int i = 1;
		for (Card card : hand) {
			handString += "Carte " + i + " : ";
			handString += card.getName() + "</br>";
			i++;
		}
		return handString;
	}

	private void setError(String field, String message) {
		errors.put(field, message);
	}

	public int setHandValue(List<Card> hand) {
		int value = 0;
		for (Card card : hand) {
			value += card.getValue();
		}
		return value;
	}

	public Turn start(int bet) {

		Turn turn = new Turn();

		turn.setBet(bet);
		turn.setDeck(new Deck());
		turn.setWin(-2);

		DeckModel deck = new DeckModel();

		// On crée et mélange le paquet
		deck.init(turn.getDeck());
		deck.mix(turn.getDeck());

		// On distribue les 2 cartes de l'utilisateur
		turn.setUserHand(new ArrayList<Card>());
		distributeACard(turn.getUserHand(), deck.pick(turn.getDeck()));
		distributeACard(turn.getUserHand(), deck.pick(turn.getDeck()));

		// Si il y un As dans la main du joueur il vaut 11, on ne vérifie pas le
		// deuxième carte sinon 11 + 11 = 22 perdu
		for (Card card : turn.getUserHand()) {
			if (card.getName().charAt(0) == 'A') {
				card.setValue(11);
				break;
			}
		}

		// On défini le score de l'utilisateur
		turn.setUserScore(this.setHandValue(turn.getUserHand()));

		// On distribue les 2 cartes du croupier
		turn.setCroupierHand(new ArrayList<Card>());
		distributeACard(turn.getCroupierHand(), deck.pick(turn.getDeck()));
		distributeACard(turn.getCroupierHand(), deck.pick(turn.getDeck()));

		// Si il y un As dans la main du croupier il vaut 11 et on ne vérifie as
		// le deuxième sinon 11 + 11 = 22 perdu
		for (Card card : turn.getCroupierHand()) {
			if (card.getName().charAt(0) == 'A') {
				card.setValue(11);
				break;
			}
		}

		// On défini le score du croupier
		turn.setCroupierScore(this.setHandValue(turn.getCroupierHand()));

		if (turn.getUserScore() == 21) {
			// Si égalité
			if (turn.getCroupierScore() == 21) {
				turn.setWin(1);
			// Si BLACKJACK
			} else {
				turn.setWin(3);
			}
		}

		return turn;
	}

	public Turn stop(Turn turn) {

		DeckModel deck = new DeckModel();

		// Tant qui'il est plus petit que 17 il tire
		while (turn.getCroupierScore() < 17) {
			this.distributeACard(turn.getCroupierHand(), deck.pick(turn.getDeck()));
			turn.setCroupierScore(this.setHandValue(turn.getCroupierHand()));
		}

		// Si le score du croupier dépasse 21 = Victoire
		if (turn.getCroupierScore() > 21) {
			turn.setWin(2);
			return turn;
			// Sinon
		} else {
			// On regard le score de l'utilisateur
			turn.setUserScore(this.setHandValue(turn.getUserHand()));
			// Si il est plus bas que celui du croupier = Défaite
			if (turn.getCroupierScore() > turn.getUserScore()) {
				turn.setWin(0);
				return turn;
				// Si il y a égalité
			} else if (turn.getCroupierScore() == turn.getUserScore()) {
				turn.setWin(1);
				return turn;
				// Si il est meilleur que celui du croupier = Victoire
			} else {
				turn.setWin(2);
				return turn;
			}
		}
	}

	private void validBet(int bet, double betMax) throws Exception {
		if (bet > betMax) {
			throw new Exception("Vous n'avez pas assez de capital pour miser autaunt.");
		} else if (bet <= 0) {
			throw new Exception("Merci de saisir une mise valide.");
		}
	}

	private void validBet(String bet) throws Exception {
		if (bet != null) {
			if (!bet.matches("\\d+")) {
				throw new Exception("Merci de saisir une mise valide.");
			}
		} else {
			throw new Exception("Merci de saisir une mise.");
		}
	}
	
	public void CreateTurn(Turn turn) throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		turnDAO = (TurnDAO)adf.getTurnDAO();
		
		turnDAO.create(turn);	
		
	}
}
