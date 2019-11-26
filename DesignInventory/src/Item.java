public class Item {
	private String name;
	private double costPrice;
	private double sellPrice;
	private double prevSellPrice = sellPrice;
	private int quantity;
	private int soldQuantity;
	private double value;
	private double earnedValue = 0;

	public Item(String name, double costPrice, double sellPrice, int quantity) {
		this.name = name;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.quantity = 0;
		this.value = costPrice * quantity;
	}

	public String getName() {
		return name;
	}
	
	public double getValue() {
		earnedValue = soldQuantity*prevSellPrice;
		value += earnedValue;
		return value;
	}

	public String toString() {
		String s = "";
		s += this.name;
		s += "\t\t" + this.costPrice;
		s += "\t\t" + this.sellPrice;
		s += "\t\t" + this.quantity;
		s += "\t\t" + this.value;
		return s;
	}

	public double changeCostPrice(double newPrice) {
		costPrice = newPrice;
		return costPrice;
	}

	public double changeSellPrice(double newPrice) {
		prevSellPrice = sellPrice;
		sellPrice = newPrice;
		return sellPrice;
	}

	public int addQuantity(int num) {
		quantity += num;
		return quantity;
	}

	public Object removeQuantity(int num) {
		if (num > quantity) {
			quantity -= num;
			soldQuantity = num;
			return quantity;
		} else {
			return "Can't remove that many items, because there are this many: " + this.quantity;
		}
	}
}
