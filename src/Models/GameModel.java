package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Beans.Game;
import Beans.Turn;

public class GameModel {

	public Game init(int capital) {
		
		// TODO : temporaire
		Scanner sc = new Scanner(System.in);
		
		Game game = new Game();
		// On définit la date
		game.setDate(LocalDate.now());
		// On instancie la liste de turn
        game.setTurns(new ArrayList<Turn>());
        
        TurnModel turn = new TurnModel();
        
		boolean play = true;
		// Tant qu'on a du capital et qu'on veut rejourer
		while (capital > 0 && play)
        {
			// On demande combien l'utilisateur va miser sur le turn.
			System.out.println("How much would you like to bet? (1 - " + capital + ")");
			
			// TODO : temporaire
			int bet = sc.nextInt();	        
	        
	        // On ajoute le trun à la liste eton le lance en même temps
			game.getTurns().add(turn.start(bet));
            
			if (game.getTurns().get(game.getTurns().size() - 1).isWin() == 1) {
				capital += bet;
				game.setResultGame(game.getResultGame() + bet);
			} else if (game.getTurns().get(game.getTurns().size() - 1).isWin() == -1) {
				capital -= bet;
				game.setResultGame(game.getResultGame() - bet);
			}
			
			// On demande si on veut lancer un autre turn
            System.out.println("Please choose a valid option: [(S)top (P)lay]");
            sc = new Scanner(System.in);
    		String input = sc.nextLine();
    		play = input.equals("P") ? true : false;
        }
		
		return game;

	}
}
