package Models;

import Beans.Card;

public class CardModel {

	public Card define(int i, int j) {

		Card card = new Card();

		card.setName(this.defineName(i, j));
		card.setValue(this.defineValue(j));

		return card;
	}

	private String defineName(int i, int j) {

		String name = "";

		switch (j) {
		case 0:
			name += 'A'; // As
			break;
		case 1:
			name += '2';
			break;
		case 2:
			name += '3';
			break;
		case 3:
			name += '4';
			break;
		case 4:
			name += '5';
			break;
		case 5:
			name += '6';
			break;
		case 6:
			name += '7';
			break;
		case 7:
			name += '8';
			break;
		case 8:
			name += '9';
			break;
		case 9:
			name += 'T'; // Ten
			break;
		case 10:
			name += 'J'; // Jack
			break;
		case 11:
			name += 'Q'; // Queen
			break;
		case 12:
			name += 'K'; // King
			break;
		}

		switch (i) {
		case 0:
			name += 'S'; // Spade
			break;
		case 1:
			name += 'T'; // Tile
			break;
		case 2:
			name += 'C'; // Clover
			break;
		case 3:
			name += 'H'; // Heart
			break;
		}

		return name;
	}

	private int defineValue(int j) {
		if (j <= 8)
			return j + 1;
		else
			return 10;
	}
}
