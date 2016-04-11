import java.util.*;


public abstract class Property extends BoardLocation {

		protected String name;
		protected int cost;
		protected Player owner;
		protected boolean available;

		public Property() {
			this(" ", 0, 0);
		}

		public Property(String name, int cost, int spacesFromGo) {
			super(spacesFromGo);

			this.name = name;
			this.available = true;
			this.owner = null;

			if ( cost > 0) 
				this.cost = cost;
			else
				this.cost = 0;
		}

		public abstract String[] getPossibleActions(Player player);
		protected abstract String calculateRent(Player player);

		public String[] getRequiredActions(Player player) {
			ArrayList<String> actions = new ArrayList<String>();

			// Pay Rent
			if ( player != this.owner && this.available == false ) 
				actions.add(this.calculateRent(player));

			return actions.toArray(new String[actions.size()]);	
		}

		protected ArrayList<String> addPropertyGenericAvailableActions(ArrayList<String> actions, Player player) {
			
			// Buy this property
			if ( player != this.owner && this.owner == null )
				actions.add("Buy this Property");

			return this.addGenericAvailableActions(actions, player);
		
		}

		public String respondToAction(Player player, String action) {
			switch ( action ) {

				case "Buy this Property":   this.buyProperty(player);
																	  break;

				case "Buy Houses/Hotels": this.buyHouses(player);
																		break;
			}		
			return "Successfull";
		}
		

		public void releaseProperty() {
			this.owner = null;
			this.available = true;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setCost(int cost) {
			if (cost > 0)
				this.cost = cost;
		}

		public String getName() {
			return this.name;
		}

		public int getCost() {
			return this.cost;
		}

		public Player getOwner() {
			return this.owner;
		}

		public boolean getAvailability() {
			return this.available;
		}

		protected void buyProperty(Player player) {
			if ( this.owner == null && this.available == true && player.getMoney() > this.getCost() ) {

				// Deduct Money
				player.deductMoney(this.cost);

				// Check if Utility
				if ( this.getClass().getName() == "Utility" ) {
					player.incrementUtilities();
					((Utility) this).setBothUtilitiesOwned(true);

					for ( Property property : player.getPropertiesOwned()) {
						if ( property.getClass().getName() == "Utility" )
							((Utility)property).setBothUtilitiesOwned(true);
					}
				}
				// Check if RailRoad
				else if ( this.getClass().getName() == "Railroad" ) {
					player.incrementRailRoads();
					((Railroad) this).incrementMultiplier();

					for ( Property property : player.getPropertiesOwned()) {
						if ( property.getClass().getName() == "Railroad" )
							((Railroad) property).incrementMultiplier();
					}
				}
				
				// Set the owner
				this.owner = player;
				this.available = false;
				player.getPropertiesOwned().add(this);

				System.out.format("You have purchased %s for $%d\n", this.name, this.cost);
			}
			else
				System.out.println("The property could not be purchased. Please check availability and funds.");

		}
}