package genericAdventure;

public class Player {
	private int maxhp;
	//private int bonusMaxhp;
	private int hp;
	private int atk;
	//private int bonusAtk;
	private int def;
	//private int bonusDef;
	private String className;
	private int afterburn=0;
	private int poison=0;
	private int markedForDeath=0;
	private int unaware=0;
	private int freeze=0;
	private int invuln=0;
	public Player(byte input,ItemPool itempool) {
		switch(input) {
			case 1:
				className="Soldier";
				maxhp=200;
				atk=120;
				def=108;
				Inventory.silentAdd(itempool.get(0));
				break;
			case 2:
				className="Wizard";
				maxhp=125;
				atk=107;
				def=100;
				Inventory.silentAdd(itempool.get(1));
				break;
			case 3:
				className="Lawyer";
				maxhp=150;
				atk=113;
				def=100;
				Inventory.silentAdd(itempool.get(2));
				break;
			case 4:
				className="Paladin";
				maxhp=300;
				atk=77;
				def=105;
				Inventory.silentAdd(itempool.get(3));
				Inventory.silentAdd(itempool.get(8));
				break;
			case 5:
				className="Spy";
				maxhp=125;
				atk=107;
				def=97;
				Inventory.silentAdd(itempool.get(4));
				Inventory.silentAdd(itempool.get(7));
		}
		hp=maxhp;
		/*bonusMaxhp=0;
		bonusAtk=0;
		bonusDef=0;*/
	}
	public void damage(int damage) {
		hp-=(int)(damage*(100/def));
	}
	public int getHp() {
		return hp;
	}
	public int getMaxHp() {
		return maxhp;
	}
	public int getAtk() {
		return atk;
	}
	public int getDef() {
		return def;
	}
	public String getClassName() {
		return className;
	}
	public void setMaxHp(int increment) {
		maxhp+=increment;
		if(maxhp<=0) {
			maxhp=10;
		}
	}
	/*public void setBonusMaxHp(int increment) {
		bonusMaxhp+=increment;
		if(maxhp+bonusMaxhp<10) {
			bonusMaxhp=-maxhp+10;
		}
	}*/
	/*public void resetMaxHp() {
		bonusMaxhp=0;
	}*/
	public void setAtk(int increment) {
		atk+=increment;
		if(atk<=0) {
			atk=1;
		}
	}
	/*public void setBonusAtk(int increment) {
		bonusAtk+=increment;
		if(atk+bonusAtk<10) {
			bonusAtk=-atk+10;
		}
	}*/
	/*public void resetAtk() {
		bonusAtk=0;
	}*/
	public void setDef(int increment) {
		def+=increment;
		if(def<=0) {
			def=1;
		}
	}
	/*
	public void setBonusDef(int increment) {
		bonusDef+=increment;
		if(def+bonusDef<10) {
			bonusDef=-def+10;
		}
	}*/
	/*public void resetDef() {
		bonusDef=0;
	}*/
	/*public void resetStats() {
		resetMaxHp();
		resetAtk();
		resetDef();
	}*/
	public void setHp(int increment) {
		hp+=increment;
	}
	public void setAfterburn(int increment) {
		afterburn+=increment;
	}
	public int getAfterburn() {
		return afterburn;
	}
	public void setPoison(int increment) {
		poison+=increment;
	}
	public int getPoison() {
		return poison;
	}
	public void setMarkForDeath(int increment) {
		markedForDeath+=increment;
	}
	public int getMarkForDeath() {
		return markedForDeath;
	}
	public void setUnaware(int increment) {
		unaware+=increment;
	}
	public int getUnaware() {
		return unaware;
	}
	public void setFreeze(int increment) {
		freeze+=increment;
	}
	public int getFreeze() {
		return freeze;
	}
	public void setInvuln(int increment) {
		invuln+=increment;
	}
	public int getInvuln() {
		return invuln;
	}

}
