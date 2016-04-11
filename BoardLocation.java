import java.util.*;


public abstract class BoardLocation {

		private static final int maxSpaces = 40;

		protected int spacesFromGo;


		public BoardLocation() {
			this(0);
		}

		public BoardLocation(int spacesFromGo) {
			if ( spacesFromGo >= 0 && spacesFromGo < maxSpaces ) 
				this.spacesFromGo = spacesFromGo;
			else
				this.spacesFromGo = 0;
		}

		public abstract String getName();
		public abstract String[] getPossibleActions(Player player);
		public abstract String[] getRequiredActions(Player player);

		public void setSpacesFromGo (int spacesFromGo) {
			if ( spacesFromGo >= 0 && spacesFromGo < maxSpaces ) 
				this.spacesFromGo = spacesFromGo;
		}

		public int getSpacesFromGo() {
			return this.spacesFromGo;
		}

		protected ArrayList<String> addGenericAvailableActions(ArrayList<String> actions, Player player) {

			// Buy Hotels
			if ( player.getPropertiesOwned().size() > 0 )
				actions.add("Buy Houses/Hotels");

			actions.add("End Turn");
			return actions;

		}
		public String respondToAction(Player player, String action) {
			switch ( action ) {

				case "Buy Houses/Hotels": this.buyHouses(player);
																		break;
			}		
			return "";
		}

		protected void buyHouses(Player player) {
			ArrayList<Property> properties = player.getPropertiesOwned();
			int selection;
			boolean validInput = false;
			Lot chosenProperty = null;

			System.out.println("Select one of your properties to buy houses/hotels for");
			for ( int i = 0; i < properties.size(); i++ ) {
				if ( properties.get(i).getClass().getName() == "Lot" ) 
					System.out.format("\t%d). %s\n", i, properties.get(i).getName());
				else 
					System.out.format("\t%d). %s ( Cannot Purchase Houses on this )\n", i, properties.get(i).getName());
			}
			System.out.format("\t%d). Exit Menu\n", properties.size());
			while ( !validInput) {
				System.out.print("Which number: ");
				try {
					selection = Integer.parseInt(System.console().readLine());
					if ( selection == properties.size() ) 
						return;
					else if ( properties.get(selection).getClass().getName() == "Lot") {
						chosenProperty = (Lot)properties.get(selection);
						validInput = true;
					}
					else
						System.out.println("Please choose a property that is allowed to have houses");
				}
				catch ( NumberFormatException e ) {
					System.out.println("Enter Valid Number");
				}
			}
			validInput = false;
			System.out.format("%s was selected.\nYou have %d Houses and %d Hotels on this property\n", chosenProperty.getHousesOwned(), chosenProperty.getHousesOwned(), ((chosenProperty.getHotelOwned()) ? 1 : 0));
			if ( !chosenProperty.getHotelOwned() ) {
				System.out.format("You may purchase more for $%d each. ", chosenProperty.getHousePrice());
				while ( !validInput ) {
					System.out.format("How many houses/hotels would you like: ");
					try {
						selection = Integer.parseInt(System.console().readLine());
						if ( selection <=5 ) {
							validInput = true;
							this.purchaseHouses(player, chosenProperty, selection);
						}
						else {
							System.out.println("Enter Valid Number");
						}
					}
					catch ( NumberFormatException e ) {
						System.out.println("Enter Valid Number");
					}
				}
			}
			else {
				System.out.println("You have already purchased the max amount of houses/hotels for this property");
			}
		}

		private void purchaseHouses(Player player, Lot chosenProperty, int selection) {
			int housesLeft = 4 - chosenProperty.getHousesOwned();
			int purchaseCost = selection * chosenProperty.getCost();

			if ( player.getMoney() >= purchaseCost ) {
				if ( housesLeft >= selection ) {
						// Buy selection amount of houses
						player.deductMoney(purchaseCost);
						chosenProperty.setHousesOwned(chosenProperty.getHousesOwned() + selection);

						System.out.format("You have successfully bought %d house(s) for %s", selection, chosenProperty.getName());
				}
				else if ( (housesLeft - selection) == -1 ) {
						player.deductMoney(purchaseCost);
						// Buy 4-this.housesOwned houses
						chosenProperty.setHousesOwned(4);
						// Then buy one hotel
						chosenProperty.setHotelOwned(true);

						System.out.format("You have successfully bought %d house(s) and a hotel for %s", selection, chosenProperty.getName());
				}
				else {
						System.out.format("You cannot buy %d amount of houses/hotels. The max you can purchase is %d\n", selection, housesLeft + ((chosenProperty.getHotelOwned()) ? 1 : 0));
				}
			}
			else {
				System.out.println("You cannot afford this purchase");
			}

		}
}

