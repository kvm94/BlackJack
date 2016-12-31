package Beans;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	private List<Card> cards = new ArrayList<Card>();

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}
