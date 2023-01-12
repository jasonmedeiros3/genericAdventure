package genericAdventure;

import java.util.ArrayList;

public class ActiveItem implements Item {
	private String name;
	private boolean isPassive;
	private short weight;
	private int durability;
	private int maxDurability;
	private byte type;
	private boolean mapCompatible;
	public ActiveItem(String s,short w,int d,byte t,boolean m) {
		name=s;
		weight=w;
		durability=d;
		maxDurability=d;
		type=t;
		mapCompatible=m;
		isPassive=false;
	}
	@Override
	public void doEffect(String eventFlag,Player player,ArrayList<Enemy> enemyList,Integer[] damage,byte target) throws Exception {
		switch(getName()) {
			case "Rocket Launcher":
				target=selectTarget(enemyList);
				rocketLaunch(player,enemyList,target);
				break;
			case "Staff of Lower Back Pain":
				target=selectTarget(enemyList);
				backPain(player,enemyList.get(target));
				break;
			case "Lawsuit":
				target=selectTarget(enemyList);
				lawsuit(player,enemyList.get(target));
				break;
			case "$8 Spear":
				target=selectTarget(enemyList);
				cheapSpear(player,enemyList.get(target));
				break;
			case "Butter Knife":
				target=selectTarget(enemyList);
				butterKnife(player,enemyList.get(target));
				break;
			case "Six Shooter":
				target=selectTarget(enemyList);
				sixShooter(player,enemyList.get(target));
				break;
			case "Staff of Arthritis":
				target=selectTarget(enemyList);
				arthritis(player,enemyList.get(target));
				break;
			case "Black Box":
				target=selectTarget(enemyList);
				blackBox(player,enemyList,target);
				break;
			case "Fountain Pen":
				target=selectTarget(enemyList);
				fountainPen(player,enemyList.get(target));
				break;
			case "Water Canteen":
				waterCanteen(player);
				break;
			case "Uber Canteen":
				uberCanteen(player);
				break;
			case "Dead Ringer":
				deadRinger(player);
				break;
			default:
		}
	}
	private byte selectTarget(ArrayList<Enemy> enemyList) {
		int counter=0;
		int input;
		byte byteInput;
		for(Enemy e:enemyList) {
			System.out.println(++counter+". "+e.getName()+e.stringStatus());
		}
		System.out.println("Select a target.");
		try {
			input=Room.getIntInput(1,enemyList.size());
			byteInput=(byte)input;
			return (byte)(byteInput-1);
		} catch (Exception e) {
		}
		return 0;
	}
	private void rocketLaunch(Player player,ArrayList<Enemy> enemyList,byte target) throws Exception {
		enemyList.get(target).damage(50*player.getAtk()/100.0);
		try {
			enemyList.get(target-1).damage((int)(24*player.getAtk()/100));
		}
		catch(Exception e) {
		}
		try {
			enemyList.get(target+1).damage((int)(24*player.getAtk()/100));
		}
		catch(Exception e) {
		}
		damage(1);
	}
	private void backPain(Player player,Enemy enemy) throws Exception {
		enemy.damage(25);
		damage(1);
	}
	private void lawsuit(Player player,Enemy enemy) throws Exception {
		enemy.damage(30*player.getAtk()/100.0);
		enemy.setMarkForDeath(2);
		damage(1);
	}
	private void cheapSpear(Player player,Enemy enemy) throws Exception {
		enemy.damage(65*player.getAtk()/100.0);
		damage(1);
	}
	private void butterKnife(Player player,Enemy enemy) throws Exception {
		if(enemy.getUnaware()>0) {
			enemy.damage(enemy.getHp()/6.3+32*player.getAtk()/96.0);
			System.out.println("A backstab.");
			damage(2);
		}
		else {
			enemy.damage(32*player.getAtk()/100.0);
			damage(1);
		}
	}
	private void sixShooter(Player player,Enemy enemy) throws Exception {
		if(enemy.getUnaware()>0) {
			enemy.damage(48*player.getAtk()/100.0);
		}
	}
	private void arthritis(Player player,Enemy enemy) throws Exception {
		enemy.damage(26+(enemy.getDef()/9.8)*(player.getAtk()/120.0));
		damage(1);
	}
	private void blackBox(Player player,ArrayList<Enemy> enemyList,byte target) throws Exception {
		enemyList.get(target).damage(50*player.getAtk()/100.0);
		try {
			enemyList.get(target-1).damage(24*player.getAtk()/100.0);
			player.damage(-3*player.getAtk()/100.0);
		}
		catch(Exception e) {
		}
		try {
			enemyList.get(target+1).damage(24*player.getAtk()/100.0);
			player.damage(-3*player.getAtk()/100.0);
		}
		catch(Exception e) {
		}
		player.damage(-10*player.getAtk()/100.0);
		damage(1);
	}
	private void fountainPen(Player player,Enemy enemy) throws Exception {
		enemy.damage(47*player.getAtk()/100.0);
	}
	private void waterCanteen(Player player) throws Exception {
		player.damage(-8);
	}
	private void uberCanteen(Player player) throws Exception {
		player.setInvuln(3);
	}
	private void deadRinger(Player player) throws Exception {
		System.out.println("The Dead Ringer is armed.");
		Inventory.add(new PassiveItem("Armed Dead Ringer",(short)0,1,(byte)0));
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public short getWeight() {
		return weight;
	}
	@Override
	public int getMaxDurability() {
		return maxDurability;
	}
	@Override
	public int getDurability() {
		return durability;
	}
	@Override
	public void damage(int damage) {
		durability-=damage;
		if(durability<=0) {
			try {
				Inventory.directRemove(this);
			} catch (Exception e) {
			}
		}
	}
	@Override
	public void setMaxDurability(int value) {
		maxDurability=value;
	}
	@Override
	public void setPassive(boolean passive) {
		isPassive=passive;
	}
	@Override
	public byte getType() {
		return type;
	}
	public boolean isPassive() {
		return isPassive;
	}
	public boolean getMapCompatibility() {
		return mapCompatible;
	}
}
