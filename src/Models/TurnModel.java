package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Beans.Card;
import Beans.Deck;
import Beans.Turn;

public class TurnModel {

	public Turn start(int bet) {

		// TODO : temporaire
		Scanner sc = new Scanner(System.in);

		Turn turn = new Turn();

		turn.setBet(bet);
		turn.setDeck(new Deck());

		DeckModel deck = new DeckModel();

		// On cr�e et m�lange le paquet
		deck.init(turn.getDeck());
		deck.mix(turn.getDeck());

		// On distribue les 2 cartes de l'utilisateur
		turn.setUserHand(new ArrayList<Card>());
		distributeACard(turn.getUserHand(), deck.pick(turn.getDeck()));
		distributeACard(turn.getUserHand(), deck.pick(turn.getDeck()));

		// Si il y un As dans la main du joueur il vaut 11, on ne v�rifie pas le
		// deuxi�me carte sinon 11 + 11 = 22 perdu
		for (Card card : turn.getUserHand()) {
			if (card.getName().charAt(0) == 'A') {
				card.setValue(11);
				break;
			}
		}

		// On d�fini le score de l'utilisateur
		turn.setUserScore(this.setHandValue(turn.getUserHand()));
		
		// TODO : � faire dans l'affichage (les 2 cartes de l'utilisateur)
		System.out.println("[User]");
		System.out.println("Card 1: " + turn.getUserHand().get(0).getName());
		System.out.println("Card 2: " + turn.getUserHand().get(1).getName());
		System.out.println("Total: " + turn.getUserScore());
		System.out.println();

		// On distribue les 2 cartes du croupier
		turn.setCroupierHand(new ArrayList<Card>());
		distributeACard(turn.getCroupierHand(), deck.pick(turn.getDeck()));
		distributeACard(turn.getCroupierHand(), deck.pick(turn.getDeck()));

		// Si il y un As dans la main du croupier il vaut 11 et on ne v�rifie as
		// le deuxi�me sinon 11 + 11 = 22 perdu
		for (Card card : turn.getCroupierHand()) {
			if (card.getName().charAt(0) == 'A') {
				card.setValue(11);
				break;
			}
		}

		// TODO : � faire dans l'affichage (la deuxi�me carte n'est pas �
		// affichier !)
		System.out.println("[Croupier]");
		System.out.println("Card 1: " + turn.getCroupierHand().get(0).getName());
		System.out.println("Card 2: Hidden Card");
		System.out.println("Total: " + turn.getCroupierHand().get(0).getValue());
		System.out.println();

		// Si la premi�re carte du croupier vaut 10 ou 11 on v�rifie si il a un
		// blackjack
		if (turn.getCroupierHand().get(0).getValue() == 11 || turn.getCroupierHand().get(0).getValue() == 10) {

			System.out.println("Croupier checks if he has blackjack...\n");


			turn.setCroupierScore(this.setHandValue(turn.getCroupierHand()));
			
			// Si il en a un
			if (turn.getCroupierScore() == 21) {
				
				// TODO : � faire dans l'affichage
				System.out.println("[Croupier]");
				System.out.println("Card 1: " + turn.getCroupierHand().get(0).getName());
				System.out.println("Card 2: " + turn.getCroupierHand().get(1).getName());
				System.out.println("Total: " + turn.getCroupierScore());
				System.out.println();

				// On d�fini le score de l'utilisateur
				turn.setUserScore(this.setHandValue(turn.getUserHand()));

				// On regard s'il y a �galit�
				if (turn.getUserScore() == 21) {
					turn.setWin(0);
					System.out.println("You don't win or lost");
				}

				// Sinon c'est une d�faite d'office
				else {
					turn.setWin(-1);
					System.out.println("You lost " + turn.getBet());
				}
				return turn;
			} else {
				System.out.println("Dealer does not have a blackjack, moving on...\n");
			}
		}

		// On v�rifie si l'utilisateur a un BlackJack
		if (this.setHandValue(turn.getUserHand()) == 21) {
			// On d�fini son score � 21
			turn.setUserScore(21);
			turn.setWin(1);
			System.out.println("Blackjack, You Won! " + turn.getBet() + turn.getBet() / 2 + "\n");
			return turn;
		}

		// Boucle de demande de carte
		do {
			System.out.println("Please choose a valid option: [(S)top (P)ick]");

			String input = sc.nextLine();

			// Si on choisit de tirer une carte de plus
			if (input.equals("P")) {
				
				// On distribue une carte � l'utilisateur
				this.distributeACard(turn.getUserHand(), deck.pick(turn.getDeck()));
				
				System.out.println("Picked " + turn.getUserHand().get(turn.getUserHand().size() - 1).getName());

				// On calcul le score de l'utilisateur
				turn.setUserScore(this.setHandValue(turn.getUserHand()));

				System.out.println("Total cards value now : " + turn.getUserScore());
				System.out.println();

				// Si l'utilisateur a plus de 21 = D�faite
				if (turn.getUserScore() > 21) {
					System.out.println("Exceeded!\n");
					turn.setWin(-1);
					System.out.println("You lost " + turn.getBet());
					return turn;
				}
				// Si l'utilisateur a 21 on le signal
				else if (turn.getUserScore() == 21) {
					System.out.println("Good job! I assume you want to stand from now on...\n");
				}
			}
			
			// Si on d�cide de s'arr�ter
			else if (input.equals("S")) {
				
				// On regard la deuxi�me carte du croupier
				System.out.println("[Croupier]");
				System.out.println("Card 1: " + turn.getCroupierHand().get(0).getName());
				System.out.println("Card 2: " + turn.getCroupierHand().get(1).getName());

				//On red�finit le score du croupier
				turn.setCroupierScore(this.setHandValue(turn.getCroupierHand()));
				
				// Tant qui'il est plus petit que 17 on tire
				while (turn.getCroupierScore() < 17) {

					this.distributeACard(turn.getCroupierHand(), deck.pick(turn.getDeck()));					
					turn.setCroupierScore(this.setHandValue(turn.getCroupierHand()));					
					System.out.println("Card " + turn.getCroupierHand().size() + " : "
							+ turn.getCroupierHand().get(turn.getCroupierHand().size() - 1).getName());
				}

				
				turn.setCroupierScore(this.setHandValue(turn.getCroupierHand()));
				System.out.println("Total : " + turn.getCroupierScore());
				System.out.println();

				// Si le score du croupier d�passe 21 = Victoire
				if (turn.getCroupierScore() > 21) {
					turn.setWin(1);
					System.out.println("Croupier exceed! You win! " + turn.getBet());
					return turn;
				// Sinon
				} else {
					// On regard le score de l'utilisateur
					turn.setUserScore(this.setHandValue(turn.getUserHand()));
					// Si il est plus bas que celui du croupier = D�faite
					if (turn.getCroupierScore() > turn.getUserScore()) {
						turn.setWin(-1);
						System.out.println("Croupier has " + turn.getCroupierScore() + " and player has " + turn.getUserScore()
								+ ", croupier wins!");
						return turn;
					// Si il y a �galit�
					} else if (turn.getCroupierScore() == turn.getUserScore()) {
						turn.setWin(0);
						System.out.println("Croupier has " + turn.getCroupierScore() + " and player has " + turn.getUserScore()
								+ ", nobody wins!");
						return turn;
					// Si il est meilleur que celui du croupier = Victoire
					} else {
						turn.setWin(1);
						System.out.println("Player has " + turn.getUserScore() + " and croupier has " + turn.getCroupierScore()
								+ ", player wins!");
						return turn;
					}
				}
			}
		} while (true);
	}

	private void distributeACard(List<Card> hand, Card card) {
		hand.add(card);
	}

	private int setHandValue(List<Card> hand) {
		int value = 0;
		for (Card card : hand) {
			value += card.getValue();
		}
		return value;
	}
}
