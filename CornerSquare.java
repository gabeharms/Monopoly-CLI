import java.util.*;


public class CornerSquare extends BoardLocation {

		private String cornerType;

		public CornerSquare() {
			this(0, "GO");
		}

		public CornerSquare(int spacesFromGo, String cornerType) {
			super(spacesFromGo);

			this.cornerType = cornerType;
		}

		public String[] getPossibleActions(Player player) {
			ArrayList<String> actions = new ArrayList<String>();

			return this.addGenericAvailableActions(actions, player).toArray(new String[actions.size()]);
		}

		public String[] getRequiredActions(Player player) {
			String[] actions = {};
			return actions;
		}

		public String getName() {
			return this.cornerType;
		}

		public void setCornerType(String cornerType) {
			this.cornerType = cornerType;
		}

		public String getCornerType() {
			return this.cornerType;
		}

}