import java.util.*;


public class Railroad extends Property {

		private static final int baseRent = 25;
		public static final int railroadCost = 200;
		private int multiplier;

		public Railroad() {
			this(" ", 0);
		}

		public Railroad(String name, int spacesFromGo) {
			super(name, railroadCost, spacesFromGo);

			this.multiplier = 1;
		}

		public String[] getPossibleActions(Player player) {
			ArrayList<String> actions = new ArrayList<String>();

			return this.addPropertyGenericAvailableActions(actions, player).toArray(new String[actions.size()]);
		}

		public void incrementMultiplier() {
			if ( this.multiplier <= 3)
				this.multiplier++;
		}

		public void decrementMultiplier() {
			if ( this.multiplier < 0 )
				this.multiplier--;
		}

		public void setMultiplier(int multiplier) {
			if ( multiplier >=0 && multiplier < 5) 
				this.multiplier = multiplier;
		}

		public int getMultiplier() {
			return this.multiplier;
		}

		public int getBaseRent() {
			return this.baseRent;
		}

		protected String calculateRent(Player player) {

			player.deductMoney(multiplier*baseRent);
			return String.format("You owe $%f. You have been dedcuted this amount", (float)(multiplier * baseRent));
		}
}