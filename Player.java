import java.util.*;


public class Player {

		private String name;
		private String token;
		private float money;
		private boolean bankrupt;
		private int numLocation;
		private BoardLocation boardLocation;
		private ArrayList<Property> propertiesOwned;
		private int railRoadsOwned;
		private int utilitiesOwned;

		public Player() {
			this(" ", " ");
		}

		public Player(String name, String token ) {
			this.name = name;
			this.token = token;

			this.money = 2000;
			this.bankrupt = false;
			this.numLocation = 0;

			propertiesOwned = new ArrayList<Property>();

			this.boardLocation = null;
		}

		public void propertyBought(Property newProperty) {
			this.propertiesOwned.add(newProperty);
		}

		public void propertySold(Property soldProperty) {

		}

		public void setName(String name) {
			this.name = name;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public void setMoney(float money) {
			this.money = money;
		}

		public void setRailRoadsOwned(int railRoads) {
			if ( railRoads >= 0 && railRoads < 5) 
				this.railRoadsOwned = railRoads;
		}

		public void setUtilitiesOwned(int utilities) {
			if ( utilities >= 0 && utilities < 3 )
				this.utilitiesOwned = utilities;
		}

		public void incrementUtilities() {
			if ( this.utilitiesOwned <= 1)
				this.utilitiesOwned++;
		}

		public void incrementRailRoads() {
			if ( this.railRoadsOwned <= 3) 
				this.railRoadsOwned++;
		}

		public void deductMoney(float deduction) {
			if ( deduction >= 0 ) {
				if ( deduction > this.money) {
					this.money = 0;
					this.becameBankrupt();
				}
				else {
					this.money -= deduction;
				}
			}
		}

		public void awardMoney(float addition) {
			if ( addition >= 0) 
				this.money += addition;
		}

		public void setNumLocation(int numLocation) {
			this.numLocation = numLocation;
			if ( this.numLocation >= 40) 
				this.numLocation -= 40;
		}

		public void setBoardLocation(BoardLocation boardLocation) {
			this.boardLocation = boardLocation;
		}

		public String getName() {
			return this.name;
		}

		public String getToken() {
			return this.token;
		}

		public float getMoney() {
			return this.money;
		}

		public int getNumLocation() {
			return this.numLocation;
		}

		public BoardLocation getBoardLocation() {
			return this.boardLocation;
		}

		public ArrayList<Property> getPropertiesOwned() {
			return this.propertiesOwned;
		}

		public boolean isBankrupt() {
			return this.bankrupt;
		}

		public String quickString() {
			return String.format("Space #: %d\nMoney Left: %f\nProperties: %s\n", this.numLocation, this.money, this.propertiesOwnedToString());
		}

		private String propertiesOwnedToString() {
			StringBuilder properties = new StringBuilder();
			for (Property property: propertiesOwned) {
				properties.append(property.name);
			}
			return properties.toString();
		}

		private void becameBankrupt() {
			this.bankrupt = true;
		}
}