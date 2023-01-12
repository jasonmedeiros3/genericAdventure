package genericAdventure;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Inventory {
	private static ArrayList<Item> inventory=new ArrayList<Item>();
	private static short weight;
	private final static short MAXWEIGHT=100;
	public static void add(Item item) {
		Random rand=new Random();
		int seed=rand.nextInt(100);
		weight=0;
		for(Item i: inventory) {
			weight+=i.getWeight();
		}
		if(weight+item.getWeight()<=MAXWEIGHT) {
			inventory.add(item);
		}
		else {
			while(weight+item.getWeight()>MAXWEIGHT) {
				try {
					cancellableRemove();
				}
				catch(InputMismatchException e) {
					System.out.println("Something went wrong. Try again.");
				}
				catch(Exception e) {
					System.out.println(e.getMessage() == null ? "Something went wrong. Try again." : e.getMessage());
				}
			}
		}
		if(seed<=50) {
			System.out.println("Looks like a "+item.getName().toLowerCase()+".");
		}
		else if(seed<=80) {
			System.out.println("Looks kind of like a "+item.getName().toLowerCase()+".");
		}
		else if(seed<=95) {
			System.out.println("Looks like it might be a "+item.getName().toLowerCase()+".");
		}
		else {
			System.out.println("You find a "+item.getName().toLowerCase()+". It tastes a little funny.");
		}
	}
	public static void silentAdd(Item item) {
		weight=0;
		for(Item i: inventory) {
			weight+=i.getWeight();
		}
		if(weight+item.getWeight()<=MAXWEIGHT) {
			inventory.add(item);
		}
		else {
			while(weight+item.getWeight()>MAXWEIGHT) {
				try {
					cancellableRemove();
				}
				catch(InputMismatchException e) {
					System.out.println("Something went wrong. Try again.");
				}
				catch(Exception e) {
					System.out.println(e.getMessage() == null ? "Something went wrong. Try again." : e.getMessage());
				}
			}
		}
	}
	public static void directRemove(Item item) throws Exception {
		inventory.remove(inventory.indexOf(item));
	}
	public static void remove() throws Exception {
		int count=0;
		Scanner s = new Scanner(System.in);
		System.out.println("Select an item to remove.");
		for(Item i:inventory) {
			System.out.println(++count+". "+i.getName());
		}
		//Count variable reused as input value to save on memory
		count=s.nextInt();
		if(count<1||count>=inventory.size()) {
			throw new Exception("That isn't a valid item.");
		}
		weight-=inventory.get(count-1).getWeight();
		inventory.remove(count-1);
		System.out.println("Item removed.");
	}
	public static boolean cancellableRemove() throws Exception {
		int count=0;
		Scanner s = new Scanner(System.in);
		System.out.println("Select an item to remove, or enter zero to cancel.");
		for(Item i:inventory) {
			System.out.println(++count+". "+i.getName());
		}
		//Count variable reused as input value to save on memory
		count=s.nextInt();
		if(count==0) {
			return true;
		}
		if(count<1||count>=inventory.size()) {
			throw new Exception("That isn't a valid item.");
		}
		weight-=inventory.get(count-1).getWeight();
		inventory.remove(count-1);
		System.out.println("Item removed.");
		return false;
	}
	public static int size() {
		return inventory.size();
	}
	public static Item get(int index) {
		return inventory.get(index);
	}
}