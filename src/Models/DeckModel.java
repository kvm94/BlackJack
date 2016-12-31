package Models;

import java.util.Random;

import Beans.Card;
import Beans.Deck;

public class DeckModel {

	public int count(Deck deck) {

		return deck.getCards().size();
	}

	public Deck init(Deck deck) {

		CardModel card = new CardModel();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				deck.getCards().add(card.define(i, j));
			}
		}

		return deck;
	}

	public Deck mix(Deck deck) {

		Random random = new Random();

		int n = deck.getCards().size();

		while (n > 1) {
			n--;
			int k = random.nextInt(n + 1);
			Card card = deck.getCards().get(k);
			deck.getCards().set(k, deck.getCards().get(n));
			deck.getCards().set(n, card);
		}

		return deck;
	}

	public Card pick(Deck deck) {

		if (deck.getCards().size() <= 0) {
			this.init(deck);
			this.mix(deck);
		}

		Card cardToPick = deck.getCards().get(deck.getCards().size() - 1);
		deck.getCards().remove(deck.getCards().size() - 1);

		return cardToPick;
	}

	public void PrintDeck(Deck deck) {

		int i = 1;

		for (Card card : deck.getCards()) {
			System.out.println("Card " + i + ": " + card.getName() + ". Value: " + card.getValue());
			i++;
		}
	}

}
