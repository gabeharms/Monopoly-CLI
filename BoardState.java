import java.util.*;


public class BoardState {

	private Player currentPlayer;
	private int currentDiceValue;
	private ArrayList<Player> allPlayers;
	private ArrayList<BoardLocation> gameBoard;

	private int numOfPlayers;

	public BoardState() {
		this(0);
	}

	public BoardState(int numOfPlayers) {
		if ( numOfPlayers > 0)
			this.numOfPlayers = numOfPlayers;
		else 
			this.numOfPlayers = 1;

		this.currentPlayer = null;
		this.currentDiceValue = 0;

		this.allPlayers = new ArrayList<Player>();
		this.gameBoard = new ArrayList<BoardLocation>();

	}
	public boolean isGameOver() {
		int bankruptCount = 0;
		Player winner = null;

		for ( Player player : this.allPlayers ) {
			if ( player.isBankrupt() )
				bankruptCount++;
			else
				winner = player;
		}


		if ( bankruptCount >= (this.allPlayers.size()-1) ) {
			if ( winner != null ) {
				System.out.format("The game is now over. The winner is %s\n", winner.getName());
			}
			else
				System.out.format("The game is over, and I'm not sure who won. Sorry\n");

			return true;
		}
		return false;
	}

	protected void initPlayers() {
		System.out.println(Constants.WHITE + "**************************\n" + Constants.BLUE + "Game Settings:\n" + Constants.WHITE + "************************");
		System.out.print(Constants.RED + "How many players: " + Constants.WHITE);
		String input = System.console().readLine();
		this.setNumOfPlayers(Integer.parseInt(input));

		for (int i = 0; i < this.numOfPlayers; i++) {
			Player player = new Player();
			System.out.format(Constants.WHITE + "\t*************************\n\t*" + Constants.RED + "Player %d Name: " + Constants.WHITE, i+1);
			input = System.console().readLine();
			player.setName(input);

			System.out.format( "\t*" + Constants.RED + "Player %d Token: " + Constants.WHITE, i+1);
			input = System.console().readLine();
			player.setToken(input);
			player.setNumLocation(0);
			player.setBoardLocation(this.gameBoard.get(0));
			this.allPlayers.add(player);
		}
	}

	protected void pickFirstPlayerRandomly() {
		Collections.shuffle(allPlayers);
	}

	protected void nextTurn() {
		this.currentPlayer = allPlayers.remove(0); // Get next player in list
	}

	public void endTurn() {
		this.allPlayers.add(this.currentPlayer); // Put old player back in list
	}

	protected void rollDice() {
		this.currentDiceValue = (int)(Math.random()*6) + 1 + (int)(Math.random()*6) + 1;
		System.out.format("You rolled a %d\n", this.currentDiceValue);
	}

	protected void movePlayer() {
		this.currentPlayer.setNumLocation(this.currentPlayer.getNumLocation() + this.currentDiceValue);
		this.currentPlayer.setBoardLocation(this.gameBoard.get(this.currentPlayer.getNumLocation()));
		System.out.format("You landed on: %s\n", this.currentPlayer.getBoardLocation().getName());
	
		if ( this.currentPlayer.getBoardLocation().getClass().getName() == "Utility" && !((Property)this.currentPlayer.getBoardLocation()).getAvailability()) {
			System.out.print("Hit enter to Roll dice again to calculate rent...");
		}
	}

	protected String getCurrentPlayerInfo() {
		return this.currentPlayer.quickString();
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setCurrentDiceValue(int currentDiceValue) {
		if ( currentDiceValue > 1 && currentDiceValue < 13)
			this.currentDiceValue = currentDiceValue;
	}

	public void setNumOfPlayers(int numOfPlayers) {
		if ( numOfPlayers > 0 && numOfPlayers < 8)
			this.numOfPlayers = numOfPlayers;
	}

	public ArrayList<Player> getAllPlayers() {
		return this.allPlayers;
	} 

	public ArrayList<BoardLocation> getGameBoard() {
		return this.gameBoard;
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public int getCurrentDiceValue() {
		return this.currentDiceValue;
	}

	public int getNumOfPlayers() {
		return this.numOfPlayers;
	}


	public void initializeGameBoard() {
		gameBoard.add(new CornerSquare(0, "GO!"));
		gameBoard.add(new Lot("Mediteranean Ave", 60, "Brown", 1, 2, 50));
		gameBoard.add(new CardSquare(2, "Community Chest"));
		gameBoard.add(new Lot("Baltic Ave", 60, "Brown", 3, 4, 50));
		gameBoard.add(new TaxSquare(4, "Income Tax", 200));
		gameBoard.add(new Railroad("Reading Railroad", 5 ));
		gameBoard.add(new Lot("Oriental Ave", 100, "Teal", 6, 6, 50));
		gameBoard.add(new CardSquare(7, "Chance"));
		gameBoard.add(new Lot("Vermont Ave", 100, "Teal", 8, 6, 50));
		gameBoard.add(new Lot("Connecticut Ave", 100, "Teal", 9, 8, 50));

		gameBoard.add(new CornerSquare(10, "Jail"));
		gameBoard.add(new Lot("St. Charles Place", 140, "Pink", 11, 10, 100));
		gameBoard.add(new Utility("Electric Company", 150, 12));
		gameBoard.add(new Lot("State Ave", 140, "Pink", 13, 10, 100));
		gameBoard.add(new Lot("Virginia Ave", 160, "Pink", 14, 12, 100));
		gameBoard.add(new Railroad("Pennsylvania Railroad", 15 ));
		gameBoard.add(new Lot("St. James Place", 180, "Orange", 16, 14, 100));
		gameBoard.add(new CardSquare(17, "Community Chest"));
		gameBoard.add(new Lot("Tennessee Ave", 180, "Orange", 18, 14, 100));
		gameBoard.add(new Lot("New York Ave", 200, "Orange", 19, 16, 100));		
		
		gameBoard.add(new CornerSquare(20, "Free Parking"));
		gameBoard.add(new Lot("Kentucky Ave", 220, "Red", 21, 18, 150));
		gameBoard.add(new CardSquare(22, "Chance"));
		gameBoard.add(new Lot("Indiana Ave", 220, "Red", 23, 18, 150));
		gameBoard.add(new Lot("Illinois Ave", 240, "Red", 24, 20, 150));
		gameBoard.add(new Railroad("B & 0 Railroad", 25 ));
		gameBoard.add(new Lot("Atlantic Ave", 260, "Yellow", 26, 22, 150));
		gameBoard.add(new Lot("Ventnor Ave", 260, "Yellow", 27, 22, 150));
		gameBoard.add(new Utility("Water Works", 150, 28));
		gameBoard.add(new Lot("Marvin Gardens", 280, "Orange", 29, 23, 150));	
	 
		gameBoard.add(new CornerSquare(30, "Go To Jail"));
		gameBoard.add(new Lot("Pacific Ave", 300, "Green", 31, 26, 200));
		gameBoard.add(new Lot("North Carolina Ave", 300, "Green", 32, 26, 200));
		gameBoard.add(new CardSquare(33, "Community Chest"));
		gameBoard.add(new Lot("Pennsylvania Ave", 320, "Green", 33, 28, 200));
		gameBoard.add(new Railroad("Short Line", 35 ));
		gameBoard.add(new CardSquare(36, "Chance"));
		gameBoard.add(new Lot("Park Place", 350, "Blue", 37, 35, 200));
		gameBoard.add(new TaxSquare(38, "Luxury Tax", 100));
		gameBoard.add(new Lot("Boardwalk", 400, "Blue", 39, 50, 200));		
	}

}