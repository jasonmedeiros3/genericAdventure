package genericAdventure;

import java.util.ArrayList;

/*public class Item {
	private String name;
	private boolean isPassive;
	private short weight;
	private int durability;
	private int maxDurability;
	private byte type;
	public Item(String s,short w,int d,byte t) {
		name=s;
		weight=w;
		maxDurability=d;
		type=t;
		durability=maxDurability;
	}
	public String getName() {
		return name;
	}
	public short getWeight() {
		return weight;
	}
	public int getMaxDurability() {
		return maxDurability;
	}
	public int getDurability() {
		return durability;
	}
	public void damage(int damage) {
		durability-=damage;
	}
	public void setMaxDurability(int value) {
		maxDurability+=value;
	}
	public void setPassive(boolean passive) {
		isPassive=passive;
	}
}*/
public interface Item {
	public String getName();
	public short getWeight();
	public int getMaxDurability();
	public int getDurability();
	public byte getType();
	public boolean isPassive();
	public void damage(int damage);
	public void setMaxDurability(int value);
	public void setPassive(boolean passive);
	public void doEffect(String eventFlag,Player player,ArrayList<Enemy> enemyList,Integer damage,byte target) throws Exception;
}
