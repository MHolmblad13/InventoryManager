import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Inventory {
	public static List<Item> itemList = new ArrayList<>(100);
	public static Scanner scan = new Scanner(System.in);
	public static double prevTotalValue = 0; //holds onto the total value after every report
	
	public static void main(String args[]) {
		executeManager();
	}

	private static void executeManager() {
		String itemName; 
		int quantity;
		double sell;
		System.out.println("\nAvailable commands: \n create \n delete \n updateBuy \n updateSell \n report \n quit");
		System.out.println("Please enter a command: ");
		String command = scan.next();
		switch (command) {
			case "create":
				itemName = scan.next();
				double cost = scan.nextInt();
				sell = scan.nextInt();
				CreateItem(itemName, cost, sell);
				executeManager();
				break;
			case "delete":
				itemName = scan.next();
				DeleteItem(itemName);
				executeManager();
				break;
			case "updateBuy":
				itemName = scan.next();
				quantity = scan.nextInt();
				UpdateBuy(itemName, quantity);
				executeManager();
				break;
			case "updateSell":
				itemName = scan.next();
				quantity = scan.nextInt();
				UpdateSell(itemName, quantity);
				executeManager();
				break;
			case "report":
				Report();
				executeManager();
				break;
			case "updateSellPrice":
				itemName = scan.next();
				sell = scan.nextDouble();
				UpdateSellPrice(itemName, sell);
				executeManager();
			case "quit":
				System.out.println("Thank you for using the inventory manager.");
				break;
			default:
				System.out.println("That is not a valid command.");
				executeManager();
				break;
		}
	}

	/**
	 * Every command method goes through the same process. Make sure the item
	 * exists, so that the user doesn't try to change something they can't Then have
	 * the user insert the changes they would like to make.
	 */

	private static void CreateItem(String name, double cost, double sell) {
		if (containsName(itemList, name) == true) {
			System.out.println("I'm sorry, that item already exists");
		} else {
			System.out.println("Item " + name + " has been added to inventory.");
			itemList.add(new Item(name, cost, sell, 0));
		}
	}

	private static void DeleteItem(String name) {
		if (containsName(itemList, name) == false) {
			System.out.println("I'm sorry, that item does not exist");
		} else {
			int index = getIndex(itemList, name);
			itemList.remove(index);
			System.out.println("The item has been removed from the inventory.");
		}
	}

	private static void UpdateBuy(String name, int quantity) {
		if (containsName(itemList, name) == false) {
			System.out.println("I'm sorry, that item does not exist");
		} else {
			int index = getIndex(itemList, name);
			itemList.get(index).addQuantity(quantity);
			System.out.println("The quantity for "+name+" has been updated.");
		}
	}

	private static void UpdateSell(String name, int sold) {
		if (containsName(itemList, name) == false) {
			System.out.println("I'm sorry, that item does not exist");
		} else {
			int index = getIndex(itemList, name);
			itemList.get(index).removeQuantity(sold);
			System.out.println("The quantity for "+name+" has been updated.");
		}
	}
	
	private static void UpdateSellPrice(String name, double sellPrice) {
		if (containsName(itemList, name) == false) {
			System.out.println("I'm sorry, that item does not exist");
		} else {
			int index = getIndex(itemList, name);
			itemList.get(index).changeSellPrice(sellPrice);
			System.out.println("The sell price for "+name+" is now "+sellPrice+".");
		}
	}

	private static void Report() {
		double totalValue = 0;
		double totalValueGain = 0;
		if (itemList.isEmpty()) {
			System.out.println("There are no items in the inventory.");
		} else {
			System.out.println("\t INVENTORY REPORT");
			System.out.println("Item Name \tBought At \tSold At \tAvailableQty \tValue");
			System.out.println("--------- \t--------- \t------- \t------------ \t-----");
			for (int i = 0; i < itemList.size(); i++) {
				System.out.println(itemList.get(i).toString());
				totalValue = 0;
				totalValue += itemList.get(i).getValue();
			}
			System.out.println("----------------------------------------------------------");
			System.out.println("Total Value                                           "+totalValue);
			if(prevTotalValue != 0)
				totalValueGain = totalValue - prevTotalValue;
			System.out.println("Value since last report                               "+totalValueGain);
			prevTotalValue = totalValue;
		}
	}

	/**
	 * Utility methods. They are used to retrieve information about the list.
	 */

	public static boolean containsName(List<Item> items, String name) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals(name))
				return true;
		}
		return false;
	}

	public static int getIndex(List<Item> items, String name) {
		for (int i = 0; i < items.size(); i++)
			if (items.get(i).getName().equals(name))
				return i;
		
		// this line is unneeded since we already know the item exists when we call this
		return -1;
	}
}
