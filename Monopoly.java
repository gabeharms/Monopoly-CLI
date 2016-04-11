import java.util.*;

public class Monopoly {


	private BoardState boardState;

	public Monopoly() {
		this.boardState = new BoardState();
	}

	public void setupGame() {
		this.boardState.initializeGameBoard();
		this.boardState.initPlayers();
		this.boardState.pickFirstPlayerRandomly();
	}

	public void playGame() {
		String[] options;
		int selection;
		boolean endTurn = false;

		while ( !boardState.isGameOver() ) {
			// Grab New Player
			this.boardState.nextTurn();

			// Tell player its their turn
			System.out.format("%s, its your turn.\n", this.boardState.getCurrentPlayer().getName());

			// Print current space, money left, and properties owned
			System.out.format(this.boardState.getCurrentPlayerInfo());

			// Roll the dice
			System.out.println("Press Enter to Roll the Dice: ");
			System.console().readLine();
			this.boardState.rollDice();

			// Print the space landed on
			this.boardState.movePlayer();
			System.console().readLine();

			// Print required actions
			this.printRequired(this.boardState.getCurrentPlayer().getBoardLocation().getRequiredActions(this.boardState.getCurrentPlayer()));
		
			endTurn = false;
			while( !endTurn ) {
				// Print available options
				options = this.printAvailable(this.boardState.getCurrentPlayer().getBoardLocation().getPossibleActions(this.boardState.getCurrentPlayer()));

				// Handle Chosen Option
				selection = this.getSelectionFromUser(options);
				System.out.println(this.boardState.getCurrentPlayer().getBoardLocation().respondToAction(this.boardState.getCurrentPlayer(), options[selection]));
			
				if ( options[selection] == "End Turn") {
					endTurn = true;
					boardState.endTurn();
				}
			}
		}
	}

	public BoardState getBoardState() {
		return this.boardState;
	}

	private String[] printAvailable(String[] actions) {
		System.out.println("Here are your options, please choose a number:");
		
		for ( int i = 0; i < actions.length; i++) {
			System.out.format("%d). %s\n", i, actions[i]);
		}

		return actions;
	}

	private void printRequired(String[] actions) {
		if ( actions.length > 0) {
			for ( int i = 0; i < actions.length; i++) {
				System.out.println(actions[i]);
			}
		}
	}

	private int getSelectionFromUser(String[] options) {
		String input;
		int index = 0;
		boolean validInput = false;

		while ( !validInput ) {
			
			try {
				System.out.print("Which option would you like: ");
				
				input = System.console().readLine();
				
				index = Integer.parseInt(input);
				if ( index < options.length && index >= 0 ) 
					validInput = true;
				else 
					System.out.println("Invalid Option. Number must be within valid range.");
			}	
			catch ( NumberFormatException e) {
				System.out.println("Invalid Option. Please enter a valid number");
		 	}			
		}
		return index;
	}

}
