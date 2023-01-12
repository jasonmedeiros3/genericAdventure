package genericAdventure;

import java.util.Random;

public class Enemy {
	private String name;
	private int maxhp;
	private int hp;
	private int atk;
	private int def;
	private boolean dead;
	private int afterburn=0;
	private int poison=0;
	private int markedForDeath=0;
	private int unaware=0;
	private int weight;
	private int freeze=0;
	private int invuln=0;
	private int intang=0;
	public Enemy(String biome) {
		Random rand=new Random();
		int seed=rand.nextInt(101);
		switch(biome) {
			case "forest":
				if(seed<=30) {
					nameSelector("Squirrel");
				}
				else if(seed<=52) {
					nameSelector("Branch Manager");
				}
				else if(seed<=79) {
					nameSelector("Cardboard Deer");
				}
				else if(seed<=89) {
					nameSelector("Semi-Sentient Tree");
				}
				else {
					nameSelector("Poacher");
				}
				break;
			case "clown factory":
				if(seed<=18) {
					nameSelector("Clown");
				}
				else if(seed<=40) {
					nameSelector("Defective Clown");
				}
				else if(seed<=54) {
					nameSelector("Assembly Line Machine");
				}
				else {
					nameSelector("Weaponized Circus Drone");
				}
				break;
			case "tundra":
				if(seed<=25) {
					nameSelector("Polar Camel");
				}
				else if(seed<=42) {
					nameSelector("Psychrophilic Amphibious Jaguar");
				}
				else if(seed<=69) {
					nameSelector("Cheese-Coated Tourist");
				}
				else {
					nameSelector("Completely Normal Shark");
				}
				break;
			case "consulate":
				if(seed<=25) {
					nameSelector("Lawyer");
				}
				else if(seed<=50) {
					nameSelector("Consul");
				}
				else if (seed <= 75){
					nameSelector("Consulate Janitor");
				} else {
					nameSelector("Protestor");
				}
				break;
			case "vineyard":
				if (seed <= 28) {
					nameSelector("Sapient Grapevine");
				} else if (seed <= 41) {
					nameSelector("Grape Seed");
				} else if (seed <= 69) {
					nameSelector("Drunkard");
				} else {
					nameSelector("Winemaker");
				}
			case "sewer":
				if (seed <= 37) {
					nameSelector("Rat");
				} else if (seed <= 52){
					nameSelector("Florida Man");
				} else if (seed <= 87) {
					nameSelector("Alligator");
				} else {
					nameSelector("Upright Walking Turtles");
				}
			case "rooftop": 
				if (seed <= 19) {
					nameSelector("Call Of The Void");
				} else if (seed <= 48) {
					nameSelector("Paper Airplane");
				} else if (seed <= 79){
					nameSelector("Firefighter");
				} else {

				}
			case "":

		}
	}
	public void nameSelector(String n) {
		name=n;
		switch(name) {
			case "Squirrel":
				maxhp=(int)(30+1.9*(Math.pow(Floor.level-1,1.3)));
				atk=(int)(90+7.2*(Floor.level-1));
				def=(int)(100+1.6*Math.pow(Floor.level-1,1.4));
				setWeight(1);
				break;
			case "Branch Manager":
				maxhp=(int)(65+5*Math.pow(Floor.level,1.09));
				atk=(int)(107+1.1*Floor.level);
				def=(int)(90+3*Floor.level);
				setWeight(2);
				break;
			case "Cardboard Deer":
				maxhp=(int)(5+3*Math.pow(Floor.level,1.08));
				atk=(int)(87+9.1*Math.pow(Floor.level,0.9));
				def=(int)(93+0.5*Floor.level);
				setWeight(1);
				break;
			case "Semi-Sentient Tree":
				maxhp=(int)(220+15.5*Math.pow(Floor.level,1.2));
				atk=(int)(60+3.2*Floor.level);
				def=(int)(100+0.35*Floor.level);
				setWeight(4);
				break;
			case "Poacher":
				maxhp=(int)(109+0.6*Floor.level);
				atk=(int)(100+1.5*Math.pow(Floor.level,1.44));
				def=(int)(103-0.7*Math.pow(Floor.level,1.21));
				setWeight(4);
				break;
			case "Clown":
				maxhp=(int)(133+3.25*Math.pow(Floor.level,1.34));
				atk=(int)(107+0.9*Floor.level);
				def=(int)(77+15*Math.sqrt(Floor.level));
				setWeight(3);
			case "Defective Clown":
				maxhp=1;
				atk=(int)(112+3.6*Math.pow(Floor.level, 1.15));
				def=100;
				setWeight(2);
			case "Assembly Line Machine":
				maxhp=(int)(300+10*Math.pow(Floor.level,1.45));
				atk=(int)(90+1.7*Math.pow(Floor.level-1,1.03));
				def=(int)(107+Floor.level);
				setWeight(5);
			case "Weaponized Circus Drone":
				maxhp=(int)(40+2.7*Math.pow(Floor.level-1,1.28));
				atk=(int)(100+2.5*Floor.level);
				def=(int)(85+4*Floor.level);
				setWeight(2);
			case "Polar Camel":
				maxhp=(int)(133+3.3*Math.pow(Floor.level,1.2));
				atk=(int)(80+2.9*Math.pow(Floor.level,1.05));
				def=(int)(93+1.1*Floor.level);
				setWeight(3);
			case "Psychrophilic Amphibious Jaguar":
				maxhp=(int)(95+5*Math.pow(1.3*Floor.level, 1.05));
				atk=(int)(100+0.5*Floor.level);
				def=(int)(97+0.4*Floor.level);
				setWeight(3);
			case "Cheese-Coated Tourist":
				maxhp=(int)(38+Math.pow(1.2*Floor.level, 1.09));
				atk=(int)(95+2.4*(Floor.level-1));
				def=(int)(100+Floor.level);
				setWeight(1);
			case "Completely Normal Shark":
				maxhp=(int)(96+Floor.level);
				atk=(int)(66+17*Math.sqrt(Floor.level));
				def=(int)(98+1.2*Floor.level);
				setWeight(2);
			case "Lawyer":
				maxhp=(int)(65+1.23*Math.pow(1.23*Floor.level,1.23));
				atk=(int)(100+Math.sqrt(3*Floor.level));
				def=(int)(93);
				setWeight(2);
			case "Consul":
				maxhp=(int)(80+3*Floor.level);
				atk=(int)(91+1.1*Floor.level);
				def=(int)(93+Floor.level);
				setWeight(2);
			case "Consulate Janitor":
				maxhp=(int)(107+1.8*Floor.level);
				atk=(int)(80+1.25*Math.pow(Floor.level,1.25));
				def=(int)(97+0.9*Floor.level);
				setWeight(3);
			case "Protestor":
				maxhp = (int) (50+2.2 * Math.pow(Floor.level, 1.8));
				atk = (int) (20 + 5 * Floor.level);
				def = (int) (97 + 0.9 * Floor.level);
				setWeight(1);
			case "Sapient Grapevine":
				maxhp=(int)(90+1.05*Math.pow(Floor.level,1.4));
				atk=(int)(77+1.13*Math.pow(Floor.level,1.23));
				def=(int)(107+1.1*Floor.level);
				setWeight(2);
			case "Grape Seed":
				maxhp = (int) (40 + 8.2 * Floor.level);
				atk = (int) (31 + 4 * Math.pow(Floor.level, 1.3));
				def = (int) (50 + 2 * Math.pow(Floor.level, 1.2));
				setWeight(1);
			case "Drunkard":
				maxhp = (int) (100 + 4 * Floor.level);
				atk = (int) (120 + Math.pow(Floor.level, 1.6));
				def = (int) (96 + Floor.level);
				setWeight(3);
			case "Winemaker": 
				maxhp = (int) (97 + 3 * Floor.level);
				atk = (int) (111 + 4 * Floor.level);
				def = (int) (78 + 2 * Floor.level);
				setWeight(2);
			case "Rat":
				maxhp = (int) (40 + 2 * Floor.level);
				atk = (int) (54 + 4 * Floor.level);
				def = (int) (124 + Math.pow(Floor.level, 1.36));
				setWeight(1);
			case "Alligator":
				maxhp = (int) (102 + 2 * Floor.level);
				atk = (int) (109 + 3 * Floor.level);
				def = (int) (104 + 6 * Floor.level);
				setWeight(2);
			case "Florida Man":
				maxhp = (int) (94 + Math.pow(Floor.level, 1.46));
				atk = (int) (127 + 4 * Floor.level);
				def = (int) (82 + Floor.level);
				setWeight(2);
			case "Upright Walking Turtles":
				maxhp = (int) (90 + 2 * Floor.level);
				atk = (int) (50 + 3 * Math.pow(Floor.level, 1.5));
				def = (int) (184 + Floor.level);
				setWeight(3);
			case "Call Of The Void":
				maxhp = (int) (167 + 2 * Floor.level);
				atk = (int) (40 + 7 * Floor.level);
				def = (int) (95 + 2 * Math.pow(Floor.level, 1.2));
				setWeight(2);
			case "Paper Airplane":
				maxhp = (int) (67 + Floor.level);
				atk = (int) (112 + 1.2 * Floor.level);
				def = (int) (40 + 10 * Floor.level);
				setWeight(2);
			case "Firefighter":
				maxhp = (int) (100 + 3 * Floor.level);
				atk = (int) (98 + 1.8 * Floor.level);
				def = (int) (152 + 1.4 * Floor.level);
				setWeight(3);
			case "":


		}
		hp=maxhp;
	}
	public void setDead(boolean isDead) {
		dead=isDead;
	}
	public void checkDead() {
		if(hp<=0) {
			dead=true;
		}
	}
	public boolean getDead() {
		return dead;
	}
	public void damage(int i) {
		int damage;
		damage=(int)(i*(100.0/def));
		if(getMarkForDeath()>0&&damage>0) {
			damage*=1.35;
		}
		if(getIntang()>0&&damage>0) {
			damage=1;
		}
		if(getInvuln()>0&&damage>0) {
			damage=0;
		}
		if(damage>0) {
			System.out.println("Did "+damage+" damage.");
		}
		else if(damage<0) {
			System.out.println("Enemy "+name+" healed "+(-damage)+" health.");
		}
		else {
			System.out.println("Didn't do any damage.");
		}
		if(hp<=0) {
			dead=true;
			System.out.println("Enemy "+name+" died.");
		}
		hp-=damage;
		if(hp>maxhp) {
			hp=maxhp;
		}
	}
	public void damage(double i) {
		int damage;
		damage=(int)(i*(100.0/def));
		if(getMarkForDeath()>0&&damage>0) {
			damage*=1.35;
		}
		if(getIntang()>0&&damage>0) {
			damage=1;
		}
		if(getInvuln()>0&&damage>0) {
			damage=0;
		}
		if(damage>0) {
			System.out.println("Did "+damage+" damage.");
		}
		else if(damage<0) {
			System.out.println("Enemy "+name+" healed "+(-damage)+" health.");
		}
		else {
			System.out.println("Didn't do any damage.");
		}
		if(hp<=0) {
			dead=true;
			System.out.println("Enemy "+name+" died.");
		}
		hp-=damage;
		if(hp>maxhp) {
			hp=maxhp;
		}
	}
	public void setAtk(int increment) {
		atk+=increment;
	}
	public void setDef(int increment) {
		def+=increment;
	}
	public int getAtk() {
		return atk;
	}
	public int getDef() {
		return def;
	}
	public int getHp() {
		return hp;
	}
	public int getMaxHp() {
		return maxhp;
	}
	public String getName() {
		return name;
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
	public void setIntang(int increment) {
		intang+=increment;
	}
	public int getIntang() {
		return intang;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void statusTick() {
		if(afterburn>0) {
			afterburn--;
			hp-=3;
			atk--;
		}
		if(poison>0) {
			poison--;
			hp-=4;
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
}
