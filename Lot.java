import java.util.*;


public class Lot extends Property {

		protected String color;
		protected int rent;
		protected int housePrice;
		protected boolean hotelOwned;
		protected int housesOwned;

		public Lot() {
				this(" ", 0, " ", 0, 0, 0);
		}

		public Lot(String name, int cost, String color, int spacesFromGo, int rent, int housePrice) {
			super(name, cost, spacesFromGo);

			this.color = color;

			if ( rent >= 0 )
				this.rent = rent;
			else
				this.rent = 0;

			if ( housePrice >= 0) 
				this.housePrice = housePrice;
			else
				this.housePrice = 0;

			this.hotelOwned = false;
			this.housesOwned = 0;
		}

		public String[] getPossibleActions(Player player) {
			ArrayList<String> actions = new ArrayList<String>();

			return this.addPropertyGenericAvailableActions(actions, player).toArray(new String[actions.size()]);
		}

	
		public void setColor(String color) {
			this.color = color;
		}

		public void setRent(int rent) {
			if ( rent >= 0)
				this.rent = rent;
		}

		public void setHousePrice(int housePrice) {
			if ( housePrice >= 0) 
				this.housePrice = 0;
		}

		public void setHousesOwned(int housesOwned) {
			if (housesOwned > 0 && housesOwned < 5)
				this.housesOwned = housesOwned;
		}

		public void setHotelOwned(boolean hotelOwned) {
			this.hotelOwned = hotelOwned;
		}

		public String getColor() {
			return this.color;
		}

		public int getRent() {
			return this.rent;
		}

		public int getHousePrice() {
			return this.housePrice;
		}

		public int getHousesOwned() {
			return this.housesOwned;
		}

		public boolean getHotelOwned() {
			return this.hotelOwned;
		}

		protected String calculateRent(Player player) {

			int multiplier = this.housesOwned + 1;
			if ( this.hotelOwned )
				multiplier++;

			switch ( multiplier ) {
				case 1: multiplier = 1;
								break;
				case 2: multiplier = 5;
								break;
				case 3: multiplier = 5;
								break;
				case 4: multiplier = 45;
								break;
				case 5: multiplier = 135;
								break;
				case 6: multiplier = 405; 
								break;
			}

			player.deductMoney(this.rent*multiplier);
			return String.format("You owe $%f. You have been dedcuted this amount", (float)(multiplier * this.rent));
		}
}