package genericAdventure;

import java.util.ArrayList;

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
	private int intang=0;
	private int solarArmour=0;
	private int vaccinated=0;
	private int planCCounter=-1;
	private int reviveType=0;
	private int swedish=0;
	private final boolean hayFever;
	private ArrayList<Enemy>enemyList;
	public Player(byte[]inputs,ItemPool itempool,Boolean getStartingItems) {
		if(inputs[0]==1) {
			hayFever=true;
		}
		else {
			hayFever=false;
		}
		switch(inputs[1]) {
			case 1:
				className="Soldier";
				maxhp=200;
				atk=113;
				def=100;
				if(getStartingItems) {
					Inventory.silentAdd(itempool.get(0),this);
				}
				break;
			case 2:
				className="Wizard";
				maxhp=150;
				atk=107;
				def=100;
				if(getStartingItems) {
					Inventory.silentAdd(itempool.get(1),this);
				}
				break;
			case 3:
				className="Lawyer";
				maxhp=150;
				atk=113;
				def=100;
				if(getStartingItems) {
					Inventory.silentAdd(itempool.get(2),this);
				}
				break;
			case 4:
				className="Paladin";
				maxhp=300;
				atk=77;
				def=195;
				if(getStartingItems) {
					Inventory.silentAdd(itempool.get(3),this);
					Inventory.silentAdd(itempool.get(8),this);
				}
				break;
			case 5:
				className="Spy";
				maxhp=125;
				atk=120;
				def=97;
				if(getStartingItems) {
					Inventory.silentAdd(itempool.get(4),this);
					Inventory.silentAdd(itempool.get(7),this);
				}
		}
		hp=maxhp;
		/*bonusMaxhp=0;
		bonusAtk=0;
		bonusDef=0;*/
	}
	public void setEnemyList(ArrayList<Enemy>enemyList) {
		try {
			this.enemyList.removeAll(this.enemyList);
			this.enemyList.addAll(enemyList);
		}
		catch(Exception e) {
		}
	}
	public void damage(Integer rawDamage) {
		Integer[] damage=new Integer[1];
		if(damage[0]>0) {
			damage[0]=(int)(damage[0]*(100/def));
		}
		if(getMarkForDeath()>0&&damage[0]>0) {
			damage[0]=(int)(damage[0]*1.35);
		}
		if(getIntang()>0&&damage[0]>0) {
			damage[0]=1;
		}
		if(getInvuln()>0&&damage[0]>0) {
			damage[0]=0;
		}
		Integer[]arrayDamage={damage[0]};
		if(damage[0]>0) {
			Inventory.eventFlagHandler("damage", this, enemyList, damage);
		}
		if(damage[0]>0) {
			System.out.println(className+" took "+damage[0]+" damage.");
		}
		else if(damage[0]<0) {
			System.out.println(className+" healed "+(-damage[0])+" health.");
		}
		else {
			System.out.println(className+" didn't take any damage.");
		}
		if(hp>maxhp) {
			hp=maxhp;
		}
		hp-=(int)(damage[0]*(100/def));
	}
	public void damage(double damage) {
		Integer[] intDamage=new Integer[1];
		if(damage>0) {
			damage=(int)(damage*(100/def));
		}
		if(getMarkForDeath()>0&&damage>0) {
			damage*=1.35;
		}
		if(getIntang()>0&&damage>0) {
			damage=1;
		}
		if(getInvuln()>0&&damage>0) {
			damage=0;
		}
		intDamage[0]=(int)damage;
		if(intDamage[0]>0) {
			Inventory.eventFlagHandler("damage", this, enemyList, intDamage);
		}
		if(intDamage[0]>0) {
			System.out.println(className+" took "+intDamage[0]+" damage.");
		}
		else if(intDamage[0]<0) {
			System.out.println(className+" healed "+(-intDamage[0])+" health.");
		}
		else {
			System.out.println(className+" didn't take any damage.");
		}
		if(hp>maxhp) {
			hp=maxhp;
		}
		hp-=(int)(intDamage[0]*(100/def));
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
	public void setSolarArmour(int solarArmour) {
		this.solarArmour+=solarArmour;
	}
	public int getSolarArmour() {
		return solarArmour;
	}
	public void setVaccinated(int vaccinated) {
		this.vaccinated+=vaccinated;
	}
	public int getVaccinated() {
		return vaccinated;
	}
	public void setPlanCCounter(int planCCounter) {
		this.planCCounter=planCCounter;
	}
	public int getPlanCCounter() {
		return planCCounter;
	}
	public void setReviveType(int reviveType) {
		this.reviveType=reviveType;
	}
	public int getReviveType() {
		return reviveType;
	}
	public void setSwedish(int swedish) {
		this.swedish=swedish;
	}
	public int getSwedish() {
		return swedish;
	}
 	public void setAfterburn(int increment) {
		afterburn+=increment;
	}
	public int getAfterburn() {
		return afterburn;
	}
	public void setPoison(int increment) {
		if(vaccinated<=0) {
			poison+=increment;
		}
		else {
			Inventory.directGet("Booster Shot").damage(1,this);
		}
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
	public void setIntang(int increment) {
		intang+=increment;
	}
	public int getIntang() {
		return intang;
	}
	public String stringAfterburn() {
		return "("+afterburn+" Afterburn)";
	}
	public String stringPoison() {
		return "("+poison+" Poison)";
	}
	public String stringMarkForDeath() {
		return "("+markedForDeath+" Mark For Death)";
	}
	public String stringUnaware() {
		return "("+unaware+" Unaware)";
	}
	public String stringFreeze() {
		return "("+freeze+" Freeze)";
	}
	public String stringInvuln() {
		return "("+invuln+" Invuln)";
	}
	public String stringIntang() {
		return "("+intang+" Intangible)";
	}
	public String stringStatus() {
		String status="";
		if(afterburn>0) {
			status+=stringAfterburn();
		}
		if(poison>0) {
			status+=stringPoison();
		}
		if(markedForDeath>0) {
			status+=stringMarkForDeath();
		}
		if(unaware>0) {
			status+=stringUnaware();
		}
		if(freeze>0) {
			status+=stringFreeze();
		}
		if(invuln>0) {
			status+=stringInvuln();
		}
		if(intang>0) {
			status+=stringIntang();
		}
		return status;
	}
	public boolean getHayFever() {
		return hayFever;
	}
	public void checkDead() {
		Inventory.eventFlagHandler("death",this,enemyList,new Integer[] {0});
		if(hp<=0) {
			if(reviveType==0) {
				System.out.println("You died.");
				System.out.println("You made it "+Floor.level+" floors as the "+getClassName()+".");
				System.out.println("Your final item count was "+Inventory.size()+".");
				System.exit(0);
			}
			else if(reviveType==1) {
				System.out.println("You died.");
				System.out.println("You escape into a nested universe where you're still alive and continue playing.");
				hp=maxhp;
				clearNegativeStatus();
			}
		}
	}
	public void clearNegativeStatus() {
		planCCounter=-1;
		afterburn=0;
		poison=0;
		markedForDeath=0;
		unaware=0;
		freeze=0;
	}
	public void clearPositiveStatus() {
		invuln=0;
		intang=0;
	}
	public void clearStatus() {
		clearNegativeStatus();
		clearPositiveStatus();
	}
	public void statusTick() {
		if(planCCounter>0) {
			planCCounter--;
		}
		else if(planCCounter==0) {
			damage(999);
		}
		if(afterburn>0) {
			afterburn--;
			if(solarArmour>0) {
				atk++;
				Inventory.directGet("Solar Panel Armour").damage(1,this);
			}
			hp-=(int)((1/32.0)*maxhp);
		}
		if(poison>0) {
			poison--;
			hp-=(int)((1/16.0)*maxhp);
		}
		if(markedForDeath>0) {
			markedForDeath--;
		}
		if(unaware>0) {
			unaware--;
		}
		if(freeze>0) {
			freeze--;
		}
		if(invuln>0) {
			invuln--;
		}
		if(intang>0) {
			intang--;
		}
	}
}
