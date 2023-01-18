package genericAdventure;

import java.util.ArrayList;

public interface Item {
	public String getName();
	public short getWeight();
	public int getMaxDurability();
	public int getDurability();
	public byte getType();
	public boolean isPassive();
	public void damage(int damage,Player player);
	public void setMaxDurability(int value);
	public void doEffect(String eventFlag,Player player,ArrayList<Enemy> enemyList,Integer[] damage,byte target) throws Exception;
	public boolean getMapCompatibility();
}
