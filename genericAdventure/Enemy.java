package genericAdventure;

import java.util.ArrayList;
import java.util.Random;

public class Enemy {
	protected String name;
	protected int maxhp;
	protected int hp;
	protected int atk;
	protected int def;
	protected boolean dead;
	protected final int infinity=2147483647;
	protected int afterburn=0;
	protected int poison=0;
	protected int markedForDeath=0;
	protected int unaware=0;
	protected int weight;
	protected int freeze=0;
	protected int invuln=0;
	protected int intang=0;
	public final boolean isBoss;
	public Enemy(String biome,boolean isBoss) {
		Random rand=new Random();
		int seed=rand.nextInt(101);
		this.isBoss=isBoss;
		if(!isBoss) {
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
					break;
				case "sewer":
					if (seed <= 37) {
						nameSelector("Rat");
					} else if (seed <= 52){
						nameSelector("Florida Man");
					} else if (seed <= 87) {
						nameSelector("Alligator");
					} else {
						nameSelector("Bipedal Turtles");
					}
					break;
				case "rooftop": 
					if (seed <= 19) {
						nameSelector("Call Of The Void");
					} else if (seed <= 48) {
						nameSelector("Paper Airplane");
					} else if (seed <= 79){
						nameSelector("Firefighter");
					} else {
						nameSelector("Brick");
					}
					break;
				case "fake beach":
					if (seed <= 27) {
						nameSelector("Sand");
					} else if (seed <= 57) {
						nameSelector("Real Sun");
					} else if (seed <= 96) {
						nameSelector("Water");
					} else {
						nameSelector("Willify");
					}
					break;
				case "hell":
					if (seed <= 30) {
						nameSelector("Spirit");
					} else if (seed <= 49) {
						nameSelector("Three Headed Dog");
					} else if (seed <= 85) {
						nameSelector("Pointy Rock");
					} else {
						nameSelector("Uncool Satan");
					}
					break;
				case "flight":
					if (seed <= 30){
						nameSelector("Pilot");
					} else if (seed <= 60) {
						nameSelector("Flight Attendant");
					} else if (seed <= 70) {
						nameSelector("I Sell Soap");
					} else {
						nameSelector("Flying Ostrich");
					}
					break;
				case "ireland":
					if (seed <= 25){
						nameSelector("Another Drunkard");
					} else if (seed <= 36) {
						nameSelector("Doom Guy Protestant");
					} else if (seed <= 51) {
						nameSelector("Catholic Mob");
					} else if (seed <= 69){
						nameSelector("Leprechaun");
					} else if (seed <= 83) {
						nameSelector("Potato Vendor");
					} else {
						nameSelector("Blight Immigrant");
					}
					break;
			}
		}
		else if(biome!=null) {
			nameSelector(biome);
		}
		else {
			switch(Floor.level) {
				case 3:
					if(seed<=25) {
						nameSelector("The Sniper");
					}
					else if(seed<=50) {
						nameSelector("The Druid");
					}
					else if(seed<=75) {
						nameSelector("The Trash Compactor");
					}
					else if(seed<=90) {
						nameSelector("The Cube of Gelatin");
					}
					else {
						nameSelector("The British Guy");
					}
					break;
				case 6:
					if(seed<=25) {
						nameSelector("The Engineer");
					}
					else if(seed<=50) {
						nameSelector("The Fighter");
					}
					else if(seed<=75) {
						nameSelector("The Supercomputer");
					}
					else if(seed<=90) {
						nameSelector("The Ophan");
					}
					else {
						nameSelector("The Abstract Concept");
					}
					break;
				case 10:
					nameSelector("The Devil");
					break;
				case 0:
					nameSelector("The Truth");
					break;
			}
		}
	}
	public void nameSelector(String n) {
		name=n;
		if(isBoss) {
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
					break;
				case "Defective Clown":
					maxhp=1;
					atk=(int)(112+3.6*Math.pow(Floor.level, 1.15));
					def=100;
					setWeight(2);
					break;
				case "Assembly Line Machine":
					maxhp=(int)(300+10*Math.pow(Floor.level,1.45));
					atk=(int)(90+1.7*Math.pow(Floor.level-1,1.03));
					def=(int)(107+Floor.level);
					setWeight(5);
					break;
				case "Weaponized Circus Drone":
					maxhp=(int)(40+2.7*Math.pow(Floor.level-1,1.28));
					atk=(int)(100+2.5*Floor.level);
					def=(int)(85+4*Floor.level);
					setWeight(2);
					break;
				case "Polar Camel":
					maxhp=(int)(133+3.3*Math.pow(Floor.level,1.2));
					atk=(int)(80+2.9*Math.pow(Floor.level,1.05));
					def=(int)(93+1.1*Floor.level);
					setWeight(3);
					break;
				case "Psychrophilic Amphibious Jaguar":
					maxhp=(int)(95+5*Math.pow(1.3*Floor.level, 1.05));
					atk=(int)(100+0.5*Floor.level);
					def=(int)(97+0.4*Floor.level);
					setWeight(3);
					break;
				case "Cheese-Coated Tourist":
					maxhp=(int)(38+Math.pow(1.2*Floor.level, 1.09));
					atk=(int)(95+2.4*(Floor.level-1));
					def=(int)(100+Floor.level);
					setWeight(1);
					break;
				case "Completely Normal Shark":
					maxhp=(int)(96+Floor.level);
					atk=(int)(66+17*Math.sqrt(Floor.level));
					def=(int)(98+1.2*Floor.level);
					setWeight(2);
					break;
				case "Lawyer":
					maxhp=(int)(65+1.23*Math.pow(1.23*Floor.level,1.23));
					atk=(int)(100+Math.sqrt(3*Floor.level));
					def=(int)(93);
					setWeight(2);
					break;
				case "Consul":
					maxhp=(int)(80+3*Floor.level);
					atk=(int)(91+1.1*Floor.level);
					def=(int)(93+Floor.level);
					setWeight(2);
					break;
				case "Consulate Janitor":
					maxhp=(int)(107+1.8*Floor.level);
					atk=(int)(80+1.25*Math.pow(Floor.level,1.25));
					def=(int)(97+0.9*Floor.level);
					setWeight(3);
					break;
				case "Protestor":
					maxhp = (int) (50+2.2 * Math.pow(Floor.level, 1.8));
					atk = (int) (20 + 5 * Floor.level);
					def = (int) (97 + 0.9 * Floor.level);
					setWeight(1);
					break;
				case "Sapient Grapevine":
					maxhp=(int)(90+1.05*Math.pow(Floor.level,1.4));
					atk=(int)(77+1.13*Math.pow(Floor.level,1.23));
					def=(int)(107+1.1*Floor.level);
					setWeight(2);
					break;
				case "Grape Seed":
					maxhp = (int) (40 + 8.2 * Floor.level);
					atk = (int) (31 + 4 * Math.pow(Floor.level, 1.3));
					def = (int) (50 + 2 * Math.pow(Floor.level, 1.2));
					setWeight(1);
					break;
				case "Drunkard":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (120 + Math.pow(Floor.level, 1.6));
					def = (int) (50 + Floor.level);
					setWeight(2);
					break;
				case "Winemaker": 
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (111 + 4 * Floor.level);
					def = (int) (102 + 2 * Floor.level);
					setWeight(3);
					break;
				case "Rat":
					maxhp = (int) (40 + 2 * Floor.level);
					atk = (int) (54 + 4 * Floor.level);
					def = (int) (124 + Math.pow(Floor.level, 1.36));
					setWeight(1);
					break;
				case "Alligator":
					maxhp = (int) (102 + 2 * Floor.level);
					atk = (int) (109 + 3 * Floor.level);
					def = (int) (104 + 6 * Floor.level);
					setWeight(2);
					break;
				case "Florida Man":
					maxhp = (int) (94 + Math.pow(Floor.level, 1.46));
					atk = (int) (127 + 4 * Floor.level);
					def = (int) (82 + Floor.level);
					setWeight(2);
					break;
				case "Bipedal Turtles":
					maxhp = (int) (90 + 2 * Floor.level);
					atk = (int) (50 + 3 * Math.pow(Floor.level, 1.5));
					def = (int) (184 + Floor.level);
					setWeight(3);
					break;
				case "Call Of The Void":
					maxhp = (int) (167 + 2 * Floor.level);
					atk = (int) (40 + 7 * Floor.level);
					def = (int) (95 + 2 * Math.pow(Floor.level, 1.2));
					setWeight(2);
					break;
				case "Paper Airplane":
					maxhp = (int) (67 + Floor.level);
					atk = (int) (112 + 1.2 * Floor.level);
					def = (int) (40 + 10 * Floor.level);
					setWeight(2);
					break;
				case "Firefighter":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (98 + 1.8 * Floor.level);
					def = (int) (152 + 1.4 * Floor.level);
					setWeight(3);
					break;
				case "Brick":
					maxhp = (int) (50 + 2 * Floor.level);
					atk = (int) (23 + 4 * Floor.level);
					def = (int) (140 + Floor.level);
					setWeight(1);
					break;
				case "Sand":
					maxhp = (int) (40 + 2 * Floor.level);
					atk = (int) (101 + 2.4 * Floor.level);
					def = (int) (100);
					setWeight(2);
					break;
				case "Real Sun":
					maxhp = (int) (100 + 2 * Floor.level);
					atk = (int) (10 + 15 * Floor.level);
					def = (int) (95);
					setWeight(2);
					break;
				case "Water":
					maxhp = (int) (50 + 2.8 * Floor.level);
					atk = (int) (70 + Floor.level);
					def = (int) (120 + Floor.level);
					setWeight(1);
					break;
				case "Willify":
					maxhp = (int) (130 + 2.3 * Floor.level);
					atk = (int) (19 + 1.2 * Floor.level);
					def = (int) (100 + Floor.level);
					setWeight(3);
					break;
				case "Spirit":
					maxhp = (int) (40 + 2 * Floor.level);
					atk = (int) (40 + 2 * Floor.level);
					def = (int) (40 + 2 * Floor.level);
					setWeight(1);
					break;
				case "Three Headed Dog":
					maxhp = (int) (102 + 4 * Floor.level);
					atk = (int) (114 + 5 * Floor.level);
					def = (int) (84 + Floor.level);
					setWeight(3);
					break;
				case "Pointy Rock":
					maxhp = (int) (87 + Floor.level);
					atk = (int) (70 + 2 * Floor.level);
					def = (int) (120 + Floor.level);
					setWeight(2);
					break;
				case "Uncool Satan":
					maxhp = (int) (95 + 3 * Floor.level);
					atk = (int) (105 + 1.3 * Floor.level);
					def = (int) (100 + Floor.level);
					setWeight(3);
					break;
				case "Pilot":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (119 + 1.9 * Floor.level);
					def = (int) (90 + Floor.level);
					setWeight(2);
					break;
				case "Flight Attendant":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (45 + 2 * Math.pow(Floor.level, 1.4));
					def = (int) (50 + 5 * Floor.level);
					setWeight(1);
					break;
				case "I Sell Soap":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (90 + 1.3 * Floor.level);
					def = (int) (100 + 2 * Math.pow(Floor.level, 1.6));
					setWeight(2);
					break;
				case "Flying Ostrich":
					maxhp = (int) (112 + Floor.level);
					atk = (int) (120 + Floor.level);
					def = (int) (100 + 2 * Floor.level);
					setWeight(3);
					break;
				case "Potato Vendor":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (40 + 5 * Floor.level);
					def = (int) (90 + Floor.level);
					setWeight(1);
					break;
				case "Catholic Mob":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (75 + Math.pow(Floor.level, 1.6));
					def = (int) (55 + 6 * Floor.level);
					setWeight(1);
					break;
				case "Blight Immigrant":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (99 + Floor.level);
					def = (int ) (143 + Floor.level);
					setWeight(2);
					break;
				case "Another Drunkard":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (120 + Math.pow(Floor.level, 1.6));
					def = (int) (50 + Floor.level);
					setWeight(2);
					break;
				case "Leprechaun":
					maxhp = (int) (150 + 2 * Floor.level);
					atk = (int) (79 + Floor.level);
					def = (int) (15 + 2 * Math.pow(Floor.level, 1.9));
					setWeight(2);
					break;
				case "Doom Guy Protestant":
					maxhp = (int) (80 + 2 * Floor.level);
					atk = (int) (112 + 3 * Floor.level);
					def = (int) (170 + 3 * Floor.level);
					setWeight(3);
					break;
			}
			hp = maxhp;
		}
		else {
			switch(name) {
				case "The Sniper":
					maxhp=375;
					atk=107;
					def=100;
					break;
				case "The Druid":
					maxhp=500;
					atk=93;
					def=110;
					break;
				case "The Trash Compactor":
					maxhp=575;
					atk=80;
					def=117;
					break;
				case "The Cube of Gelatin":
					maxhp=400;
					atk=93;
					def=107;
					break;
				case "The British Guy":
					maxhp=480;
					atk=100;
					def=95;
					break;
				case "The Engineer":
					maxhp=500;
					atk=100;
					def=109;
					break;
				case "The Fighter":
					maxhp=780;
					atk=112;
					def=101;
					break;
				case "The Supercomputer":
					maxhp=675;
					atk=93;
					def=108;
					invuln=10;
					break;
				case "The Ophan":
					maxhp=777;
					atk=107;
					def=97;
					break;
				case "The Abstract Concept":
					maxhp=15;
					atk=100;
					def=100;
					intang=infinity;
					break;
				case "Sentry Gun":
					maxhp=150;
					atk=80;
					def=100;
					break;
				case "Dispenser":
					maxhp=150;
					atk=20;
					def=100;
					break;
				case "The Beast":
					maxhp=666;
					atk=66;
					def=66;
				case "The Devil":
					maxhp=1998;
					atk=66;
					def=66;
					break;
				case "The Truth":
					maxhp=1;
					atk=1;
					def=1;
					break;
				default:
					n="File A Bug Report Please";
					maxhp=100;
					atk=100;
					def=100;
			}
		}
		hp=maxhp;
	}
	public void doMove(Player player,ArrayList<Enemy>enemyList) {
		Random rand=new Random();
		int seed1=rand.nextInt(100);
		int seed2=rand.nextInt(100);
		if(!isBoss) {
			switch(name) {
				case "Squirrel":
					if(seed1<=50||player.getHp()<=(int)(10*atk/100.0)) {
						squirrelBite(player);
					}
					else {
						acorn();
					}
				case "Branch Manager":
					if(seed1<=70) {
						branchAttack(player);
					}
					else {
						citation(player);
					}
			}
		}
		else {
			doBossMove(player,enemyList);
		}
	}
	private void branchAttack(Player player) {
		System.out.println(name+" hits you with a branch.");
		player.damage(24*atk/100.0);
	}
	private void citation(Player player) {
		System.out.println(name+" issues you a citation.");
		System.out.println("You are damaged emotionally.");
		player.damage(39);
	}
	private void squirrelBite(Player player) {
		System.out.println(name+" bites you.");
		player.damage(10*atk/100.0);
	}
	private void acorn() {
		damage(-5-Floor.level);
	}
	public void doBossMove(Player player,ArrayList<Enemy>bossList) {
	}
	public void setDead(boolean isDead) {
		dead=isDead;
	}
	public boolean checkDead() {
		if(hp<=0) {
			dead=true;
		}
		return dead;
	}
	public boolean getDead() {
		return dead;
	}
	public boolean damage(int i) {
		int damage=i;
		if(damage>0) {
			damage=(int)(i*(100.0/def));
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
		if(damage>0) {
			System.out.println("Did "+damage+" damage.");
		}
		else if(damage<0) {
			System.out.println("Enemy "+name+" healed "+(-damage)+" health.");
		}
		else {
			System.out.println("Didn't do any damage.");
		}
		hp-=damage;
		if(hp<=0) {
			dead=true;
			System.out.println("Enemy "+name+" died.");
			return true;
		}
		if(hp>maxhp) {
			hp=maxhp;
		}
		return false;
	}
	public boolean damage(double i) {
		int damage=(int)i;
		if(damage>0) {
			damage=(int)(i*(100.0/def));
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
		if(damage>0) {
			System.out.println("Did "+damage+" damage.");
		}
		else if(damage<0) {
			System.out.println("Enemy "+name+" healed "+(-damage)+" health.");
		}
		else {
			System.out.println("Didn't do any damage.");
		}
		hp-=damage;
		if(hp<=0) {
			dead=true;
			System.out.println("Enemy "+name+" died.");
			return true;
		}
		if(hp>maxhp) {
			hp=maxhp;
		}
		return false;
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
