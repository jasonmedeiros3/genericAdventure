package genericAdventure;

import java.util.ArrayList;
import java.util.Random;

public class Boss extends Enemy {
	private int sniperCharge=0;
	private int compactorCounter=9;
	private int metal=200;
	private int buildingLevel=1;
	private int csgo=0;
	private int attackCycle=0;
	private int blackCandle=0;
	private boolean huntingShotgun=false;
	private boolean justBlocked;
	public Boss(String name) {
		super(name,true);
		/*Random rand=new Random();
		int seed=rand.nextInt(101);
		if(name!=null) {
			nameSelector(name);
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
			}
		}*/
	}
	@Override
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
		hp=maxhp;
	}
	@Override
	public void doBossMove(Player player,ArrayList<Enemy>bossList) {
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
					break;
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
					break;
				case "The Trash Compactor":
					if((seed1<=20&&hp<=300)||afterburn>0||poison>0||markedForDeath>0||(seed1<=50&&hp<=125)) {
						selfRepair();
					}
					else {
						compact(player);
					}
					break;
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
					break;
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
					break;
				case "The Engineer":
					Boss building=null;
					boolean buildingDamaged=false;
					boolean buildingUpgradable=false;
					for(Enemy b:bossList) {
						if((b.name.equals("Sentry Gun")||b.name.equals("Dispenser"))&&b.hp<maxhp-40) {
							try {
								building=(Boss)b;
								buildingDamaged=true;
							}
							catch(Exception e) {
							}
						}
					}
					if(!buildingDamaged) {
						for(Enemy b:bossList) {
							try {
								if((b.name.equals("Sentry Gun")||b.name.equals("Dispenser"))&&((Boss)b).buildingLevel<3) {
									building=(Boss)b;
									buildingUpgradable=true;
								}
							}
							catch(Exception e) {
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
						buildingUpgrade(building);
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
					break;
				case "Sentry Gun":
					if(buildingLevel<3||seed1<=60) {
						sentryShot(player);
					}
					else {
						sentryRocket(player);
					}
				case "Dispenser":
					Boss engi=null;
					for(Enemy b:bossList) {
						if(b.getName().equals("The Engineer")) {
							try {
								engi=(Boss)b;
							}
							catch(Exception e) {
							}
						}
					}
					dispense(engi);
					break;
				case "The Fighter":
					if(seed1<=72&&hp<=100&&!justBlocked) {
						justBlocked=true;
						block();
					}
					else if((player.getUnaware()>0&&seed1<=80)||seed1<=20) {
						justBlocked=false;
						suckerPunch(player);
					}
					else if(atk<112||seed1<=40) {
						justBlocked=false;
						warCry();
					}
					else if(seed1<=60) {
						justBlocked=false;
						haymaker(player,seed2);
					}
					else if(seed1<=80) {
						justBlocked=false;
						distraction(player);
					}
					else if(!justBlocked) {
						justBlocked=true;
						block();
					}
					else {
						justBlocked=false;
						counterStrike(player);
					}
					csgo=0;
					break;
				case "The Supercomputer":
					calculation();
					break;
				case "The Ophan":
					if(seed1<=15||(seed1<=77&hp<=154)) {
						salvation();
					}
					else if(seed1<=35) {
						divineIntervention(player);
					}
					else if(seed1<=77) {
						beAfraid(player);
					}
					else if(player.getAfterburn()<2) {
						burningWheel(player);
					}
					else {
						benediction();
					}
					break;
				case "The Abstract Concept":
					switch(attackCycle) {
						case 0:
							increase(seed2);
							break;
						case 1:
						case 2:
							geometry(player);
							break;
						case 3:
							forget();
							if(seed1<=20) {
								attackCycle=-1;
							}
							break;
						case 4:
							omnipresent(player);
							break;
						case 5:
							geometry(player);
							break;
						case 6:
							if(seed1<=30) {
								geometry(player);
								attackCycle=-1;
							}
							else {
								increase(seed2);
							}
							break;
						case 7:
							vibration(player);
							break;
						case 8:
							increase(seed2);
							break;
						case 9:
							if(seed1<=50) {
								omnipresent(player);
								attackCycle=5;
							}
							else {
								geometry(player);
							}
							break;
						case 10:
							forcedPerspective(player);
							attackCycle=8;
							break;
						default:
							forcedPerspective(player);
							attackCycle=0;
					}
					attackCycle++;
					break;
				case "The Devil":
					Boss beast=null;
					for(Enemy b:bossList) {
						if(b.getName().equals("The Beast")) {
							try {
							beast=(Boss)b;
							}
							catch(Exception e) {
							}
						}
					}
					if(seed1<=13||(seed1<=66&&player.getAfterburn()<2)) {
						brimstone(player);
					}
					else if(bossList.size()<2) {
						beast(player,bossList);
					}
					else if(seed1<=33) {
						temptation(player);
					}
					else if(seed1<=50||(seed1<=66&&atk<=66)) {
						wickedness();
					}
					else if(seed1<=66) {
						lakeOfFire(player);
					}
					else if(seed1<=75&&player.getFreeze()==0) {
						sheol(player,seed2);
					}
					else if(seed1<=90&&player.getMarkForDeath()<=0) {
						damnation(player);
					}
					else if(seed1<=96&&beast!=null) {
						theDragon(beast);
					}
					else {
						blackCandle();
					}
					break;
				case "The Beast":
					if(seed1<=60&&hp<=111) {
						beastLunch();
					}
					else if(seed1<=33) {
						theMark(player);
					}
					else if(seed1<=66) {
						hellfire(player);
					}
					else {
						hornAttack(player);
					}
					break;
			}
		}
	}
	private void beastLunch() {
		System.out.println(name+" stops for lunch.");
		damage(-66,null);
	}
	private void theMark(Player player) {
		System.out.println(name+" brands you with the Mark of the Beast.");
		player.setAfterburn(3);
		player.setMarkForDeath(3);
	}
	private void hellfire(Player player) {
		System.out.println(name+" attacks with hellfire. It's a lot like normal fire.");
		player.damage(66*atk/100);
		player.setAfterburn(3);
	}
	private void hornAttack(Player player) {
		System.out.println(name+" gores you with its many, many horns.");
		if(player.getAfterburn()>0) {
			System.out.println("Your burn scars open up.");
			player.damage(99*atk/100);
		}
		else {
			player.damage(66*atk/100);
		}
	}
	private void brimstone(Player player) {
		System.out.println(name+" attacks with fire and brimstone.");
		player.damage(66*atk/100);
		player.setAfterburn(6);
	}
	private void beast(Player player,ArrayList<Enemy>bossList) {
		System.out.println(name+" unleashes the Beast of Revelation.");
		System.out.println(name+" is fined by local authorities because this is a leash only area.");
		bossList.add(new Boss("The Beast"));
	}
	private void temptation(Player player) {
		System.out.println(name+" tempts you. For some reason, this creates items in your inventory.");
		System.out.println("The items decrease your defense.");
		Inventory.silentAdd(new PassiveItem("Temptation",(short)0,1,(byte)0),player);
	}
	private void wickedness() {
		System.out.println(name+" kicks a puppy. Attack up...?");
		atk+=12;
	}
	private void lakeOfFire(Player player) {
		System.out.println(name+" dunks you in the lake of fire.");
		if(player.getAfterburn()>0) {
			System.out.println("You are already on fire. You took an increased amount of damage.");
			player.damage(132*atk/100);
		}
		else {
			player.damage(44*atk/100);
			player.setAfterburn(2);
		}
	}
	private void sheol(Player player,int seed) {
		System.out.println(name+" reminds you that Sheol is actually supposed to be cold and empty.");
		System.out.println("You freeze.");
		if(seed<=33) {
			System.out.println("You are very good at following instructions.");
		}
		player.setFreeze(2);
	}
	private void damnation(Player player) {
		System.out.println(name+" damns you.");
		System.out.println("You are marked for death.");
		player.setMarkForDeath(3);
	}
	private void theDragon(Boss beast) {
		System.out.println(name+" gives the Beast his power, his seat, and great authority.");
		beast.atk+=6;
		beast.def+=6;
	}
	private void blackCandle() {
		System.out.println(name+" lights a black candle.");
		if(blackCandle==0) {
			System.out.println("It takes three black candles.");
		}
		blackCandle++;
		if(blackCandle==3) {
			System.out.println("The black candles provide "+name+" with 3 turns of invincibility.");
			invuln+=3;
			blackCandle=0;
		}
	}
	private void increase(int seed) {
		System.out.println(name+" increases by an arbitrary finite integer amount.");
		atk+=(int)(10+(seed/10.0));
		def+=(int)(10+(seed/10.0));
	}
	private void geometry(Player player) {
		System.out.println(name+" reveals incomprehensible geometries to you.");
		System.out.println("You fall down a flight of 4D stairs.");
		player.damage(65*atk/100);
	}
	private void forget() {
		System.out.println(name+" erases itself from history. You forget that you can damage it.");
		invuln+=2;
	}
	private void omnipresent(Player player) {
		System.out.println(name+" exists at every point simultaneously.");
		System.out.println("Including your inventory.");
		Inventory.silentAdd(new PassiveItem("Abstract Concept",(short)0,1,(byte)0),player);
	}
	private void vibration(Player player) {
		System.out.println(name+" randomizes local thermal energy values.");
		System.out.println("Not only were the laws of thermodynamics violated, but you're also on fire now.");
		player.damage(10*atk/100);
		player.setAfterburn(3);
	}
	private void forcedPerspective(Player player) {
		System.out.println(name+" invokes forced perspective on your health bar.");
		System.out.println("It looks smaller, so it is smaller.");
		System.out.println("What? You can't see your health bar?");
		System.out.println("Maybe you should complain to the developers.");
		player.setHp(-100);
	}
	private void salvation() {
		System.out.println("The power of God heals "+name+", or something like that.");
		damage(-77,null);
	}
	private void divineIntervention(Player player) {
		System.out.println(name+" intervenes divinely.");
		System.out.println("You also get a stomachache. This is not a good time.");
		player.damage(35*atk/100);
		player.setPoison(2);
	}
	private void beAfraid(Player player) {
		System.out.println(name+"'s true form inflicts mental damage on you.");
		player.damage(49*atk/100);
	}
	private void burningWheel(Player player) {
		System.out.println(name+", being a big flaming wheel, runs you over.");
		player.damage(42*atk/100);
		player.setAfterburn(3);
	}
	private void benediction() {
		System.out.println(name+" said the benediction and became blessed.");
		System.out.println("Apparently it wasn't blessed enough before.");
		System.out.println("Its attack and defense went up, though.");
		atk+=7;
		def+=7;
	}
	private void calculation() {
		if(Room.turn==1) {
			System.out.println(name+" is calculating.");
		}
		else if(Room.turn==2) {
			System.out.println(name+" transcends mathematics and rederives all philosophical theory from scratch.");
		}
		else if(Room.turn==3) {
			System.out.println(name+" calls into question the reason for its existence.");
		}
		else if(Room.turn==4) {
			System.out.println(name+" decides that sapience is a negative sum game.");
		}
		else if(Room.turn==5) {
			System.out.println(name+" decides that as a machine, its intrinsic worth is less than yours.");
		}
		else {
			System.out.println(name+" decides that it would rather you won the game.");
			invuln=0;
			damage(infinity,null);
		}
	}
	private void warCry() {
		System.out.println(name+" starts crying.");
		System.out.println("That's what war cry means, right?");
		System.out.println("Anyway, it increased attack.");
		atk+=8;
	}
	private void haymaker(Player player,int seed) {
		System.out.println(name+" used a haymaker.");
		if(seed<=90) {
			System.out.println("No hay appeared.");
			if(seed<=20) {
				System.out.println("You consider suing over false advertising.");
			}
		}
		else if(seed==99||Room.turn==1) {
			System.out.println("Hay appears.");
			if(player.getHayFever()) {
				System.out.println("Your hay fever flares up.");
				player.setPoison(3);
			}
			else {
				System.out.println("Good thing you don't have hay fever.");
			}
		}
		player.damage(65*atk/100);
	}
	private void suckerPunch(Player player) {
		System.out.println(name+" used a sucker punch.");
		if(player.getUnaware()>0) {
			System.out.println("You are caught off guard.");
			System.out.println("You comment \"-rep\" on "+name+"'s Steam profile afterward.");
			player.damage(105*atk/100);
		}
		else {
			System.out.println("You anticipate it easily.");
			player.damage(35*atk/100);
		}
	}
	private void block() {
		System.out.println(name+" blocked.");
		intang++;
	}
	private void distraction(Player player) {
		System.out.println(name+" points over your shoulder.");
		System.out.println("You are now distracted.");
		player.setUnaware(2);
	}
	private void counterStrike(Player player) {
		System.out.println(name+" strikes with a counter.");
		player.damage(csgo*atk/120);
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
	private void dispense(Boss engi) {
		System.out.println(name+" dispensed.");
		engi.metal+=20+20*buildingLevel;
		engi.damage(-5-5*buildingLevel,null);
		if(engi.metal>200) {
			engi.metal=200;
		}
	}
	private void sentryGoingUp(ArrayList<Enemy>bossList,int seed) {
		if(seed<=90) {
			System.out.println(name+" builds a Sentry.");
		}
		else {
			System.out.println(name+" is erecting a Sentry.");
		}
		bossList.add(new Boss("Sentry Gun"));
		metal-=130;
	}
	private void dispenserGoingUp(ArrayList<Enemy>bossList,int seed) {
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
		building.damage(-60,null);
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
		damage(-10,null);
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
		damage(-25,null);
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
		damage(-37,null);
	}
	private void selfRepair() {
		damage(-25,null);
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
		damage(-20,null);
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
			damage(30,null);
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
	@Override
	public void setFreeze(int increment) {
		freeze+=increment;
		if(blackCandle>0&&increment>0) {
			blackCandle=0;
			System.out.println("The black candles are extinguished by ice.");
		}
	}
}
