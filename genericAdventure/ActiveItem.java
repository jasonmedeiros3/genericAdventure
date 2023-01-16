package genericAdventure;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ActiveItem implements Item {
	private String name;
	private boolean isPassive;
	private short weight;
	private int durability;
	private int maxDurability;
	private final byte type;
	private final boolean mapCompatible;
	private final String[] wineTastingAdjectives= {"light-bodied","oaky","dry","steely","velvety","hot","buttery","corked","earthy","full-bodied","herbaceous"
			,"lingering","musty","nutty","opulent","perfumed","raisiny","robust","supple","young"
	};
	private final String[] wineTastingNouns= {"malt","vintage","hop","body","backbone","bouquet","fermentation","grip","legs","lees","length","mouthfeel"
			,"nose","noble rot","structure","tannin","typicity","texture"
	};
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
			case "Red Flask":
				redFlask(player);
				break;
			case "M1911":
				target=selectTarget(enemyList);
				m1911(player,enemyList.get(target));
				break;
			case "Staple Gun":
				target=selectTarget(enemyList);
				stapleGun(player,enemyList.get(target));
				break;
			case "Conniver Kunai":
				target=selectTarget(enemyList);
				kunai(player,enemyList.get(target));
				break;
			case "Direct Hit":
				target=selectTarget(enemyList);
				directHit(player,enemyList,target);
				break;
			case "Syrup of Ipecac":
				ipecac(player);
				break;
			case "Weighted Coin":
				coin(enemyList);
				break;
			case "Taunton Dart Gun":
			case "Blowgun":
				Boolean mode=selectDartMode();
				target=selectTarget(enemyList);
				dart(enemyList.get(target),mode);
				break;
			case "Shotgun":
				target=selectTarget(enemyList);
				shotgun(player,enemyList.get(target));
				break;
			case "Poison Pen":
				target=selectTarget(enemyList);
				poisonPen(player,enemyList.get(target));
				break;
			case "$15 Spear":
				target=selectTarget(enemyList);
				expensiveSpear(player,enemyList.get(target));
				break;
			case "Sewing Needle":
				target=selectTarget(enemyList);
				needle(player,enemyList.get(target));
				break;
			case "Plan C":
				planC(player,enemyList);
				break;
			case "Pinot Noir":
				pinotNoir(player);
			default:
		}
	}
	private boolean selectDartMode() {
		Scanner s=new Scanner(System.in);
		byte input=0;
		while(true) {
			System.out.println("Select a dart type.\n1. Emetic\n2. Sedative");
			try {
				input=s.nextByte();
				if(input!=1&&input!=2) {
					throw new Exception();
				}
				if(input==1) {
					return true;
				}
				return false;
			}
			catch(Exception e) {
			}
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
		System.out.println("Fired a rocket.");
		enemyList.get(target).damage(50*player.getAtk()/100.0,player);
		try {
			enemyList.get(target-1).damage((int)(24*player.getAtk()/100),player);
		}
		catch(Exception e) {
		}
		try {
			enemyList.get(target+1).damage((int)(24*player.getAtk()/100),player);
		}
		catch(Exception e) {
		}
		damage(1,player);
	}
	private void backPain(Player player,Enemy enemy) throws Exception {
		System.out.println("Gave an enemy back pain.");
		enemy.damage(25,player);
		damage(1,player);
	}
	private void lawsuit(Player player,Enemy enemy) throws Exception {
		System.out.println("Filed a lawsuit.");
		enemy.damage(30*player.getAtk()/100.0,player);
		enemy.setMarkForDeath(2);
		damage(1,player);
	}
	private void cheapSpear(Player player,Enemy enemy) throws Exception {
		System.out.println("Thrust the $8 spear.");
		enemy.damage(65*player.getAtk()/100.0,player);
		damage(1,player);
	}
	private void butterKnife(Player player,Enemy enemy) throws Exception {
		System.out.println("Swung the butterknife.");
		if(enemy.getUnaware()>0) {
			enemy.damage(enemy.getHp()/6.3+32*player.getAtk()/96.0,player);
			System.out.println("A backstab.");
			damage(2,player);
		}
		else {
			enemy.damage(32*player.getAtk()/100.0,player);
			damage(1,player);
		}
	}
	private void sixShooter(Player player,Enemy enemy) throws Exception {
		System.out.println("Fired the six shooter.");
		if(enemy.getUnaware()>0) {
			enemy.damage(48*player.getAtk()/100.0,player);
		}
		else {
			enemy.damage(32*player.getAtk()/100.0,player);
		}
		damage(1,player);
	}
	private void arthritis(Player player,Enemy enemy) throws Exception {
		System.out.println("Gave an enemy arthritis.");
		enemy.damage(26+(enemy.getDef()/9.8)*(player.getAtk()/120.0),player);
		damage(1,player);
	}
	private void blackBox(Player player,ArrayList<Enemy> enemyList,byte target) throws Exception {
		System.out.println("Fired a health-stealing rocket.");
		enemyList.get(target).damage(50*player.getAtk()/100.0,player);
		try {
			enemyList.get(target-1).damage(24*player.getAtk()/100.0,player);
			player.damage(-3*player.getAtk()/100.0);
		}
		catch(Exception e) {
		}
		try {
			enemyList.get(target+1).damage(24*player.getAtk()/100.0,player);
			player.damage(-3*player.getAtk()/100.0);
		}
		catch(Exception e) {
		}
		player.damage(-10*player.getAtk()/100.0);
		damage(1,player);
	}
	private void fountainPen(Player player,Enemy enemy) throws Exception {
		System.out.println("Stabbed with the fountain pen.");
		enemy.damage(47*player.getAtk()/100.0,player);
		damage(1,player);
	}
	private void waterCanteen(Player player) throws Exception {
		System.out.println("Drank some water.");
		player.damage(-8);
		damage(1,player);
	}
	private void uberCanteen(Player player) throws Exception {
		System.out.println("Drank some... invincibility juice?");
		player.setInvuln(3);
		damage(1,player);
	}
	private void deadRinger(Player player) throws Exception {
		System.out.println("The Dead Ringer is armed.");
		Inventory.add(new PassiveItem("Armed Dead Ringer",(short)0,1,(byte)0),player);
		damage(1,player);
	}
	private void redFlask(Player player) throws Exception {
		System.out.println("Drank from the red flask.");
		player.damage(-(player.getMaxHp())/2.5);
	}
	private void m1911(Player player,Enemy enemy) {
		System.out.println("Fired the M1911.");
		enemy.damage(60*player.getAtk()/100.0,player);
	}
	private void stapleGun(Player player,Enemy enemy) {
		System.out.println("Fired a staple.");
		enemy.damage(40*player.getAtk()/100.0,player);
	}
	private void kunai(Player player,Enemy enemy) {
		double damage=enemy.getHp()/5.9+32*player.getAtk()/90.0;
		System.out.println("Swung the kunai.");
		if(enemy.getUnaware()>0) {
			if(enemy.damage(damage,player)) {
				player.damage(-damage);
			}
			System.out.println("A backstab.");
			damage(2,player);
		}
		else {
			enemy.damage(40*player.getAtk()/100.0,player);
			damage(1,player);
		}
	}
	private void directHit(Player player,ArrayList<Enemy>enemyList,byte target) {
		System.out.println("Fired an accurate rocket.");
		enemyList.get(target).damage(76*player.getAtk()/100.0,player);
		try {
			enemyList.get(target-1).damage(7*player.getAtk()/100.0,player);
		}
		catch(Exception e) {
		}
		try {
			enemyList.get(target+1).damage(7*player.getAtk()/100.0,player);
		}
		catch(Exception e) {
		}
	}
	private void ipecac(Player player) {
		System.out.println("You drink the syrup of ipecac.");
		System.out.println("You feel violently nauseous.");
		player.setPoison(5);
	}
	private void coin(ArrayList<Enemy>enemyList) {
		System.out.println("You throw a coin as a distraction.");
		for(Enemy e:enemyList) {
			e.setUnaware(2);
		}
	}
	private void dart(Enemy enemy,boolean mode) {
		System.out.println("You fire a"+(mode?"n emetic":" sedative")+" dart.");
		if(mode) {
			enemy.setPoison(3);
		}
		else {
			enemy.setUnaware(5);
		}
	}
	private void shotgun(Player player,Enemy enemy) {
		System.out.println("You fire a shotgun. You experience some recoil.");
		enemy.damage(90*player.getAtk()/100.0, player);
		player.damage(8*player.getAtk()/100.0);
		damage(1,player);
	}
	private void poisonPen(Player player,Enemy enemy) {
		if(enemy.getUnaware()>0) {
			System.out.println("You sneak up on the enemy and inject the poison.");
			enemy.damage(5*player.getAtk()/100.0, player);
			enemy.setPoison(5);
			damage(1,player);
		}
		else {
			System.out.println("The enemy sees you coming and you haphazardly swing the pen.");
			enemy.damage(7*player.getAtk()/100.0, player);
			enemy.setPoison(1);
			damage(2,player);
		}
	}
	private void expensiveSpear(Player player,Enemy enemy) {
		System.out.println("Thrust the $15 spear.");
		enemy.damage(85*player.getAtk()/100.0, player);
	}
	private void needle(Player player,Enemy enemy) {
		System.out.println("Swung the sewing needle.");
		if(enemy.getUnaware()>0) {
			enemy.damage(enemy.getHp()/4.9+54*player.getAtk()/96.0,player);
			System.out.println("A backstab.");
			damage(2,player);
		}
		else {
			enemy.damage(40*player.getAtk()/100.0,player);
			damage(1,player);
		}
	}
	private void planC(Player player,ArrayList<Enemy>enemyList) {
		System.out.println("Used Plan C.");
		for(Enemy e:enemyList) {
			e.damage(199, player);
		}
		player.setPlanCCounter(3);
	}
	private void pinotNoir(Player player) {
		Random rand=new Random();
		if(rand.nextInt(48)!=47) {
			System.out.println("Drank some Pinot Noir.");
		}
		else {
			System.out.println("Decanted the Pinot Noir and drank it.");
		}
		System.out.println("The wine is very "+wineTastingAdjectives[rand.nextInt(wineTastingAdjectives.length)]);
		System.out.println("The "+wineTastingNouns[rand.nextInt(wineTastingNouns.length)]+" is alright but not great.");
		player.setAtk(10);
		player.setMarkForDeath(3);
		player.setUnaware(5);
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
	public void damage(int damage,Player player) {
		durability-=damage;
		if(durability<=0) {
			try {
				Inventory.directRemove(this,player);
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
	@Override
	public boolean isPassive() {
		return isPassive;
	}
	@Override
	public boolean getMapCompatibility() {
		return mapCompatible;
	}
}
