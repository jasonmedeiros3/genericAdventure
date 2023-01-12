package genericAdventure;

import java.util.ArrayList;
import java.util.Random;

public class Boss {
	private String name;
	private int maxhp;
	private int hp;
	private int atk;
	private int def;
	private int afterburn=0;
	private int poison=0;
	private int markedForDeath=0;
	private int unaware=0;
	private int freeze=0;
	private int invuln=0;
	private int sniperCharge=0;
	private int compactorCounter=9;
	private int metal=200;
	private int buildingLevel=1;
	private boolean huntingShotgun=false;
	public Boss(String biome) {
		Random rand=new Random();
		int seed=rand.nextInt(101);
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
		}
	}
	public void nameSelector(String n) {
		name=n;
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
				break;
			case "The Ophan":
				maxhp=777;
				atk=107;
				def=97;
				break;
			case "The Abstract Concept":
				maxhp=10;
				atk=100;
				def=100;
				break;
			case "Sentry Gun":
				maxhp=150;
				atk=80;
				def=100;
			case "Dispenser":
				maxhp=150;
				atk=20;
				def=100;
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
		hp=maxhp;
	}
	public void damage(int i) {
		int damage;
		damage=(int)(i*(100.0/def));
		if(getMarkForDeath()>0&&damage>0) {
			damage*=1.35;
		}
		if(damage>0) {
			System.out.println("Did "+damage+" damage to "+name+".");
		}
		else if(damage<0) {
			System.out.println(name+" healed "+(-damage)+" health.");
		}
		else {
			System.out.println("Didn't do any damage.");
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
		if(damage>0) {
			System.out.println("Did "+damage+" damage to "+name+".");
		}
		else if(damage<0) {
			System.out.println(name+" healed "+(-damage)+" health.");
		}
		else {
			System.out.println("Didn't do any damage.");
		}
		hp-=damage;
		if(hp>maxhp) {
			hp=maxhp;
		}
	}
	public void doMove(Player player,ArrayList<Boss>bossList) {
		Random rand=new Random();
		int seed1=rand.nextInt(100);
		int seed2=rand.nextInt(100);
		if(freeze>0) {
			System.out.println(name+" is frozen.");
		}
		else {
			switch(name) {
				case "The Sniper":
					if(seed1<=20||player.getHp()<=(40*atk/103)||(seed1<=90&&sniperCharge>=3)) {
						noScope(player,seed2);
					}
					else if((seed1<=49||player.getMarkForDeath()>0&&sniperCharge<=0)||seed1<=22) {
						bushwacka(player);
					}
					else if(seed2<=2||(seed1<=60&&player.getMarkForDeath()<=0&&sniperCharge<=0)||(seed1<=30&&sniperCharge==1)) {
						jarate(player);
					}
					else if((seed1<=87&&unaware<=0&&sniperCharge<=0)||seed2==69) {
						razorback();
					}
					else {
						charge();
					}
				case "The Druid":
					if(seed1<=90&&(poison>0||markedForDeath>0)) {
						aromatherapy(seed2);
					}
					else if(seed1<=15||(seed2<=80&&hp<=maxhp/8.5&&player.getHp()>=30)) {
						smokeWeed(seed2);
					}
					else if(seed1<=40||(seed1<=75&&!huntingShotgun)) {
						huntingShotgun(player,seed2);
					}
					else if(seed1<=80||(seed1<=99&&player.getHp()<35)) {
						beehive(player);
					}
					else {
						poisonOak(player);
					}
				case "The Trash Compactor":
					if((seed1<=20&&hp<=300)||afterburn>0||poison>0||markedForDeath>0||(seed1<=50&&hp<=125)) {
						selfRepair();
					}
					else {
						compact(player);
					}
				case "The Cube of Gelatin":
					if(seed1<=45) {
						gelatinPunch(player);
					}
					else if(seed1<=70||(seed1+(25-Room.turn*Room.turn)>=99)) {
						gelatinHarden();
					}
					else {
						gelatinTherapy();
					}
				case "The British Guy":
					if(Room.turn%5==0) {
						teaBreak(player);
					}
					else if(seed1<=40) {
						shivUp(player);
					}
					else if(seed1<=65) {
						godSaveTheKing(player);
					}
					else if(seed1<=80&&player.getFreeze()<=0) {
						ateTheWeatherSimpleAs(player,seed2);
					}
					else {
						lunch(player);
					}
				case "The Engineer":
					Boss building=null;
					boolean buildingDamaged=false;
					boolean buildingUpgradable=false;
					for(Boss b:bossList) {
						if((b.name.equals("Sentry Gun")||b.name.equals("Dispenser"))&&b.hp<maxhp-40) {
							building=b;
							buildingDamaged=true;
						}
					}
					if(!buildingDamaged) {
						for(Boss b:bossList) {
							if((b.name.equals("Sentry Gun")||b.name.equals("Dispenser"))&&b.buildingLevel<3) {
								building=b;
								buildingUpgradable=true;
							}
						}
					}
					if(bossList.size()<=5&&metal>=130&&(seed1<=15||(seed1<=85&&bossList.size()<2))) {
						sentryGoingUp(bossList,seed2);
					}
					else if(bossList.size()<=5&&metal>=100&&(seed1<=25||(seed2<=85&&bossList.size()<=2))) {
						dispenserGoingUp(bossList,seed1);
					}
					else if(building!=null&&buildingDamaged&&seed1<=50) {
						buildingRepair(building);
					}
					else if(building!=null&&buildingUpgradable&&seed1<=50&&metal>=50) {
						
					}
					else if((bossList.size()>=3&&seed1<=70)||seed1<=60) {
						engiShotgun(player);
					}
					else if(seed1<=90&&metal<100) {
						pickUpAmmo();
					}
					else {
						wrench(player);
					}
				case "Sentry Gun":
					if(buildingLevel<3||seed1<=60) {
						sentryShot(player);
					}
					else {
						
					}
			}
		}
	}
	private void sentryShot(Player player) {
		System.out.println(name+" opens fire.");
		if(buildingLevel<2) {
			player.damage(16*atk/100);
		}
		else {
			player.damage(32*atk/100);
		}
	}
	private void sentryRocket(Player player) {
		System.out.println(name+" fires a salvo of rockets.");
		player.damage(65*atk/100);
	}
	private void sentryGoingUp(ArrayList<Boss>bossList,int seed) {
		if(seed<=90) {
			System.out.println(name+" builds a Sentry.");
		}
		else {
			System.out.println(name+" is erecting a Sentry.");
		}
		bossList.add(new Boss("Sentry Gun"));
		metal-=130;
	}
	private void dispenserGoingUp(ArrayList<Boss>bossList,int seed) {
		if(seed<=90) {
			System.out.println(name+" builds a Dispenser.");
		}
		else {
			System.out.println(name+" is erecting a Dispenser.");
		}
		bossList.add(new Boss("Dispenser"));
		metal-=100;
	}
	private void buildingRepair(Boss building) {
		System.out.println(name+" repairs a "+building.name+".");
		building.damage(-60);
		metal-=20;
	}
	private void buildingUpgrade(Boss building) {
		System.out.println(name+" upgrades a "+building.name+".");
		building.buildingLevel++;
		building.maxhp*=1.2;
		building.hp*=1.2;
		metal-=50;
	}
	private void engiShotgun(Player player) {
		System.out.println(name+" uses a shotgun.");
		player.damage(60*atk/100);
	}
	private void pickUpAmmo() {
		System.out.println(name+" picks up ammo.");
		metal+=100;
		if(metal>200) {
			metal=200;
		}
	}
	private void wrench(Player player) {
		System.out.println(name+" swings a wrench.");
		player.damage(49*atk/100);
	}
	private void teaBreak(Player player) {
		System.out.println(name+" calls a tea break.");
		System.out.println("Tea. Earl Grey. Hot.");
		player.damage(-10);
		damage(-10);
	}
	private void shivUp(Player player) {
		System.out.println(name+" shivs you up.");
		System.out.println("The knife is so rusty that you get poisoned.");
		player.damage(40*atk/100);
		player.setPoison(1);
	}
	private void godSaveTheKing(Player player) {
		System.out.println(name+" plays the British national anthem.");
		player.damage(50*atk/100);
	}
	private void ateTheWeatherSimpleAs(Player player,int seed) {
		System.out.println(name+" says \"'ate the weather, simple as.\"");
		System.out.println("On cue, a frigid rainstorm rolls in.");
		if(seed>=70) {
			System.out.println("This is what London is like all the time.");
		}
		player.setFreeze(2);
		player.damage(3);
	}
	private void lunch(Player player) {
		System.out.println(name+" shares a traditional British delicacy with you.");
		System.out.println("It's stale toast with canned beans.");
		player.damage(25);
		damage(-25);
	}
	private void gelatinPunch(Player player) {
		System.out.println("Somehow, "+name+" punches you.");
		player.damage(40*atk/100);
	}
	private void gelatinHarden() {
		System.out.println(name+" hardened.");
		System.out.println("Its defense went up.");
		def+=6;
	}
	private void gelatinTherapy() {
		System.out.println(name+" attended cognitive behavioural therapy.");
		System.out.println(name+" healed on the inside.");
		damage(-37);
	}
	private void selfRepair() {
		damage(-25);
		if(afterburn>0) {
			afterburn--;
		}
		if(poison>0) {
			poison--;
		}
		if(markedForDeath>0) {
			markedForDeath--;
		}
	}
	private void compact(Player player) {
		compactorCounter--;
		System.out.println("COMPACTION COUNTER: "+compactorCounter);
		if(compactorCounter<=0) {
			System.out.println("COMPACTING- VACATE COMPACTION AREA IMMEDIATELY");
			player.damage(10000);
			compactorCounter=6;
		}
	}
	private void aromatherapy(int seed) {
		if(seed<=42) {
			System.out.println(name+" used aromatherapy despite not being a licensed practitioner.");
		}
		else {
			System.out.println(name+" used aromatherapy.");
		}
		poison=0;
		markedForDeath=0;
	}
	private void smokeWeed(int seed) {
		if(seed<=84) {
			System.out.println(name+" smokes some weed.");
		}
		else {
			System.out.println(name+" imbibes in the worldly pleasures of marijuana usage.");
		}
		if(seed>=77) {
			System.out.println("This is probably illegal.");
		}
		damage(-20);
		unaware+=2;
	}
	private void huntingShotgun(Player player,int seed) {
		if(!huntingShotgun) {
			System.out.println(name+" picks up a double barrel hunting shotgun.");
			System.out.println(name+" has poor trigger discipline.");
			huntingShotgun=true;
		}
		else {
			System.out.println(name+" fires the hunting shotgun. The recoil is significant.");
			player.damage(90*atk/100.0);
			damage(30);
		}
	}
	private void beehive(Player player) {
		System.out.println(name+" throws a beehive at you. No bees, but it still does damage.");
		player.damage(39*atk/97.0);
	}
	private void poisonOak(Player player) {
		System.out.println(name+" swings a piece of poison oak.");
		System.out.println("You become poisoned by unrelated circumstances.");
		player.damage(44*atk/100);
		player.setPoison(3);
	}
	private void noScope(Player player,int seed) {
		System.out.println(name+" used the 180 No-Scope.");
		if(seed<=31) {
			System.out.println("Despite the gun facing away from you, you get hit anyway.");
		}
		else if(seed<=35) {
			System.out.println("Despite the gun facing away from you, you get hit anyway...?");
		}
		player.damage((45+sniperCharge*15)*atk/102.0);
		sniperCharge=0;
	}
	private void bushwacka(Player player) {
		sniperCharge=0;
		System.out.println(name+" used the Bushwacka.");
		if(player.getMarkForDeath()>0) {
			System.out.println("A critical hit.");
			player.damage(93*atk/100.0);
		}
		else {
			player.damage(31*atk/100.0);
		}
	}
	private void jarate(Player player) {
		sniperCharge=0;
		System.out.println(name+" used the Jarate.");
		if(player.getMarkForDeath()<=0) {
			System.out.println("You are now covered in piss.");
			System.out.println("Will to live went down.");
			player.setMarkForDeath(3);
		}
		else {
			System.out.println("The number of piss on you increased by one.");
			player.setMarkForDeath(1);
		}
	}
	private void razorback() {
		sniperCharge=0;
		System.out.println(name+" used the Razorback.");
		System.out.println(name+" became invulnerable for 1 turn and unaware for 2 turns.");
		invuln++;
		unaware+=2;
	}
	private void charge() {
		System.out.println(name+" is charging up a shot.");
		sniperCharge++;
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
		return status;
	}
}