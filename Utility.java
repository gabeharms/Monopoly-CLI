import java.util.*;


public class Utility extends Property {

		private static final int baseMultiplier = 4;
		private static final int bothOwnedMultiplier = 10;
		private boolean bothUtilitiesOwned;

		public Utility() {
			this("Electrical Company", 150, 7);
		}

		public Utility(String name, int cost, int spacesFromGo) {
			super(name, cost, spacesFromGo);

			this.bothUtilitiesOwned = false;
		}

		public String[] getPossibleActions(Player player) {
			ArrayList<String> actions = new ArrayList<String>();

			return this.addPropertyGenericAvailableActions(actions, player).toArray(new String[actions.size()]);
		}

		public void setBothUtilitiesOwned(boolean bothUtilitiesOwned) {
			this.bothUtilitiesOwned = bothUtilitiesOwned;
		}

		public boolean getBothUtilitiesOwned() {
			return this.bothUtilitiesOwned;
		}

		public int getBaseMulitiplier() {
			return this.baseMultiplier;
		}

		public int getBothOwnedMultiplier() {
			return this.bothOwnedMultiplier;
		}

		protected String calculateRent(Player player) {
			
			int diceValue = (int)(Math.random()*6) + 1 + (int)(Math.random()*6) + 1;
			float multiplier;

			System.out.format("You rolled a %d. ", diceValue);

			if ( this.bothUtilitiesOwned ) 
				multiplier = bothOwnedMultiplier;
			else
				multiplier = baseMultiplier;

			player.deductMoney((float)(multiplier*diceValue));

			return String.format("You owe $%f. You have been dedcuted this amount", (float)(diceValue * multiplier));

		}
} 