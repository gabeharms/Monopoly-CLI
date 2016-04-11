import java.util.*;


public class TaxSquare extends BoardLocation {

		protected String taxType;
		protected int taxAmount;

		public TaxSquare() {
			this(0, "Income Tax", 200);
		}

		public TaxSquare(int spacesFromGo, String taxType, int taxAmount) {
			super(spacesFromGo);

			this.taxType = taxType;
			this.taxAmount = taxAmount;
		}

		public String[] getPossibleActions(Player player) {
			ArrayList<String> actions = new ArrayList<String>();

			return this.addGenericAvailableActions(actions, player).toArray(new String[actions.size()]);
		}

		public String[] getRequiredActions(Player player) {			
			player.deductMoney(this.taxAmount);

			return new String[] {String.format("Taxes due. You have been deducted $%d", this.taxAmount)};
		}


		public String getName() {
			return this.taxType;
		}

		public void setTaxType(String taxType) {
			this.taxType = taxType;
		}

		public void setTaxAmount(int taxAmount) {
			this.taxAmount = taxAmount;
		}

		public String getTaxType() {
			return this.taxType;
		}

		public int getTaxAmount() {
			return this.taxAmount;
		}

}