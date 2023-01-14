package genericAdventure;

import java.util.ArrayList;

public class PassiveItem implements Item {
	private String name;
	private boolean isPassive;
	private short weight;
	private int durability;
	private int maxDurability;
	private byte type;
	private boolean shimmeringVeil;
	private boolean mantle;
	private int deadRingerCounter=2;
	public PassiveItem(String s,short w,int d,byte t) {
		name=s;
		weight=w;
		maxDurability=d;
		durability=d;
		type=t;
		isPassive=true;
		shimmeringVeil=true;
		mantle=true;
		deadRingerCounter=0;
	}
	@Override
	public void doEffect(String eventFlag,Player player,ArrayList<Enemy> enemyList,Integer[] damage,byte target) {
		switch(getName()) {
			case "Paper-Thin Disguise":
				paperThinDisguise(eventFlag,player,enemyList);
				break;
			case "Cardboard Shield":
				cardboardShield(eventFlag,player,damage);
				break;
			case "Riot Shield":
				riotShield(eventFlag,player,damage);
				break;
			case "Heat Death-Proof Vest":
				heatDeathVest(eventFlag,player);
				break;
			case "Shimmering Veil":
				shimmeringVeil(eventFlag,player,damage);
				break;
			case "Armed Dead Ringer":
				armedDeadRinger(eventFlag,player,damage);
				break;
			case "Abstract Concept":
				abstractConcept(eventFlag,player);
				break;
			case "Temptation":
				temptation(eventFlag,player);
				break;
			case "Holy Mantle":
				holyMantle(eventFlag,player,damage);
				break;
			case "Synthol":
				synthol(eventFlag,player);
				break;
			case "Brass Knuckles":
				brassKnuckles(eventFlag,player);
				break;
			case "Pot Lid":
				potLid(eventFlag,player,damage);
				break;
			default:
		}
	}
	private void paperThinDisguise(String eventFlag,Player player,ArrayList<Enemy> enemyList) {
		if(eventFlag.equals("battleStart")) {
			for(Enemy e:enemyList) {
				e.setUnaware(1);
				damage(1,player);
			}
		}
	}
	private void cardboardShield(String eventFlag,Player player,Integer[] damage) {
		if(eventFlag.equals("addItem")) {
			player.setDef(5);
		}
		else if(eventFlag.equals("removeItem")) {
			player.setDef(-5);
		}
		else if(eventFlag.equals("damage")) {
			if(damage[0]<=5) {
				damage[0]=0;
				damage(1,player);
			}
			damage(1,player);
		}
	}
	private void riotShield(String eventFlag,Player player,Integer[] damage) {
		if(eventFlag.equals("addItem")) {
			player.setDef(25);
		}
		else if(eventFlag.equals("removeItem")) {
			player.setDef(-25);
		}
		else if(eventFlag.equals("damage")) {
			if(damage[0]<=10) {
				damage[0]=0;
				damage(1,player);
			}
			damage(1,player);
		}
	}
	private void heatDeathVest(String eventFlag,Player player) {
		if(eventFlag.equals("addItem")) {
			player.setDef(20);
		}
		else if(eventFlag.equals("removeItem")) {
			player.setDef(-20);
		}
		else if(eventFlag.equals("damage")) {
			damage(1,player);
		}
	}
	private void shimmeringVeil(String eventFlag,Player player,Integer[] damage) {
		if(eventFlag.equals("addItem")) {
			player.setDef(10);
		}
		else if(eventFlag.equals("removeItem")) {
			player.setDef(-25);
		}
		else if(eventFlag.equals("battleStart")) {
			shimmeringVeil=true;
		}
		else if(eventFlag.equals("damage")&&shimmeringVeil) {
			damage[0]=0;
			shimmeringVeil=false;
			damage(1,player);
		}
		else if(eventFlag.equals("newFloor")) {
			damage(-3,player);
		}
	}
	private void armedDeadRinger(String eventFlag,Player player,Integer[] damage) {
		if(eventFlag.equals("turnStart")) {
			deadRingerCounter--;
		}
		else if(eventFlag.equals("damage")) {
			damage[0]=(int)(damage[0]/4);
			damage(1,player);
		}
		if(deadRingerCounter<=0) {
			damage(1,player);
		}
	}
	private void abstractConcept(String eventFlag,Player player) {
		if(eventFlag.equals("addItem")) {
			player.setAtk(-10);
		}
		else if(eventFlag.equals("removeItem")) {
			player.setAtk(10);
		}
		else if(eventFlag.equals("battleEnd")) {
			try {
				Inventory.directRemove(this,player);
			} catch (Exception e) {
			}
		}
	}
	private void temptation(String eventFlag,Player player) {
		if(eventFlag.equals("addItem")) {
			player.setDef(-10);
		}
		else if(eventFlag.equals("removeItem")) {
			player.setAtk(10);
		}
		else if(eventFlag.equals("battleEnd")) {
			try {
				Inventory.directRemove(this,player);
			} catch (Exception e) {
			}
		}
	}
	private void holyMantle(String eventFlag,Player player,Integer[] damage) {
		if(eventFlag.equals("battleStart")) {
			mantle=true;
		}
		else if(eventFlag.equals("damage")) {
			damage[0]=0;
			mantle=false;
			damage(1,player);
		}
	}
	private void synthol(String eventFlag,Player player) {
		if(eventFlag.equals("addItem")) {
			player.setAtk(3);
		}
		else if(eventFlag.equals("removeItem")) {
			player.setAtk(-3);
		}
	}
	private void brassKnuckles(String eventFlag,Player player) {
		if(eventFlag.equals("addItem")) {
			player.setAtk(8);
		}
		else if(eventFlag.equals("removeItem")) {
			player.setAtk(-8);
		}
	}
	private void potLid(String eventFlag,Player player,Integer[] damage) {
		if(eventFlag.equals("addItem")) {
			player.setDef(10);
		}
		else if(eventFlag.equals("removeItem")) {
			player.setDef(-10);
		}
		else if(eventFlag.equals("damage")) {
			damage[0]-=1;
			damage(1,player);
		}
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
	public byte getType() {
		return type;
	}
	public boolean isPassive() {
		return isPassive;
	}
}