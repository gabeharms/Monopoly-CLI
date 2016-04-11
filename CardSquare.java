import java.util.*;


public class CardSquare extends BoardLocation {

		protected String cardType;

		public CardSquare() {
			this(0, "GO");
		}

		public CardSquare(int spacesFromGo, String cardType) {
			super(spacesFromGo);

			this.cardType = cardType;
		}

		public String[] getPossibleActions(Player player) {
			ArrayList<String> actions = new ArrayList<String>();

			return this.addGenericAvailableActions(actions, player).toArray(new String[actions.size()]);
		}

		public String[] getRequiredActions(Player player) {
			String output;

			output = "Drawing a card ... ";

			Random rand = new Random();
			int randValue = rand.nextInt(401);
			randValue -= 200;

			if ( randValue >= 0) {
				output += String.format("You have won $%d", Math.abs(randValue));
				player.awardMoney(randValue);
			}
			else {
				output = String.format("You have been deducted $%d", Math.abs(randValue));
				player.deductMoney(Math.abs(randValue));
			}

			return new String[] { output };
		}
		
		public String getName() {
			return this.cardType;
		}

		public void setCardType(String cardType) {
			this.cardType = cardType;
		}

		public String getCardType() {
			return this.cardType;
		}

}