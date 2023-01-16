package genericAdventure;

import java.security.InvalidAlgorithmParameterException;
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
	protected Player tempPlayer;
	protected boolean bearTrap=false;
	protected boolean clownSaw=false;
	private static boolean droneRepair=false;
	private static boolean canDroneRepair=true;
	private static boolean charge = false;
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
					if(seed<=18||(seed<=25&&Floor.level<3)) {
						nameSelector("Clown");
					}
					else if(seed<=40||(seed<=60&&Floor.level<3)) {
						nameSelector("Defective Clown");
					}
					else if((seed<=54&&Floor.level>=4)||(seed<=45&&Floor.level==3)) {
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
		if(!isBoss) {
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
					maxhp=(int)(300+10*Math.pow(Floor.level,1.4));
					atk=(int)(90+1.7*Math.pow(Floor.level-1,1.03));
					def=(int)(107+Floor.level);
					setWeight(5);
					break;
				case "Weaponized Circus Drone":
					maxhp=(int)(60+3*Math.pow(Floor.level-1,1.4));
					atk=(int)(77+2.5*Floor.level);
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
					def = (int) (100 + 2 * Math.pow(Floor.level, 1.55));
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
					atk = (int) (75 + Math.pow(Floor.level, 1.5));
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
					name="File A Bug Report Please";
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
					break;
				case "Branch Manager":
					if(seed1<=70) {
						branchAttack(player);
					}
					else {
						citation(player);
					}
					break;
				case "Cardboard Deer":
					if (seed1 <= 60 || (player.getHp() > 80 && seed2 > 90)) {
						deerKick(player,seed2);
					} else {
						deerCharge(player);
					}
					break;
				case "Semi-Sentient Tree":
					boolean hasSquirrel=false;
					for(Enemy e:enemyList) {
						if(e.name.equals("Squirrel")) {
							hasSquirrel=true;
						}
					}
					if(seed1<=40&&hasSquirrel) {
						acornDrop(enemyList);
					}
					else if(seed1<=30||(def<104&&seed1<=55)) {
						ingrain();
					}
					else if(seed1<=70) {
						branchDrop(player);
					}
					else {
						leafBlow(player);
					}
					break;
				case "Poacher":
					if(seed1<=25) {
						bearTrap(player,enemyList);
					}
					else if(seed1<=50&&Floor.level>6) {
						doubleBarrel(player);
					}
					else if(seed1<=60||(seed1<=90&&player.getPoison()<=0)) {
						huntingMachete(player);
					}
					else {
						handgun(player);
					}
					break;
				case "Clown":
					if(seed1<=25||(seed1<=99&&clownSaw)) {
						chainsaw(player);
					}
					else if(seed1<=50||Room.turn==1) {
						noiseMaker(enemyList,seed2);
					}
					else if(seed1<=60||(seed1<=99&&hp<=30)) {
						creamPie();
					}
					else {
						aNewBat(player);
					}
					break;
				case "Defective Clown":
					if (player.getHp() <= 25) {
						clownFinisher(player);
					} else if(seed1<=40) {
						handSaw(player,seed2);
					}
					else if(seed1<=69||(seed1<=85&&player.getPoison()<=0)) {
						expiredPie(player);
					}
					else {
						shortCircuit();
					}
					break;
				case "Assembly Line Machine":
					if(seed1<=25) {
						armSwing(player);
					}
					else if(seed1<=65&&enemyList.size()<5) {
						clownAssembly(enemyList);
					}
					else if(seed1<=30) {
						bsod();
					}
					else if(seed1<=70) {
						disassemble(player);
					}
					else {
						clownRepair(enemyList);
					}
					break;
				case "Weaponized Circus Drone":
					if((hp<=maxhp/2.5||droneRepair)&&canDroneRepair) {
						swarmRepair(enemyList);
					}
					else if(seed1<=85) {
						canDroneRepair=false;
						droneGun(player);
					}
					else {
						canDroneRepair=false;
						uplink(enemyList);
					}
					break;
				case "Polar Camel":
					if (seed1 >= 25 && player.getHp() >= 60) {
						freezingSpit(player);
					} else if (seed2 <= 90 || hp > 50) {
						humpAbsorption(player);
					} else {
						deerKick(player, seed2);
					}
				case "Psychrophilic Amphibious Jaguar": 
					if (charge) {
						pounce(player);
						charge = false;
					} else if (hp > 30 || seed1 >= 20) {
						System.out.println(name + " gets ready to pounce.");
						charge = true;
					} else {
						swipe(player);
					}
				case "Cheese-Coated Tourist":
					if (seed1 >= 50) {
						photoshoot(player);
					} else if (seed2 >= 50) {
						cheetoTouch(player);
					} else {
						handgun(player);
					}
				case "Completely Normal Shark":
					if (seed1 >= 80) {
						System.out.println(name + " shocks you with how normal looking it is");
						confuse(player);
					} else if (atk <= 115 && seed2 >= 70) {
						atkIncrement();				
					} else {
						chomp(player);
					}
				case "Lawyer":
					if (player.getHp() >= 50 && seed1 >= 20) {
						System.out.println(name + " shows you some documents to look over leaving you confused. ");
						confuse(player);
					} else if (seed1 > 50) {
						hurtfulLies(player);
					} else {
						createsProblems();
					}
				case "Consul":
					if (charge) {
						handgun(player);
						charge = false;
					} else if (seed1 >= 40) {
						bombing();
					} else if (hp <= 40) {
						lastStand();
					} else {
						nothing();
					}
				case "Consulate Janitor":
					if (enemyList.size() == 1) {
						boost();
					} else if (hp <= 30){
						healingStim();
					} else {
						mopping(player);
					}
				case "Protestor": 
					if (enemyList.size() > 1 && seed1 >= 50) {
						grenade(player,enemyList);
					} else if (seed1 > 75) {
						annoyance(player);
					} else {
						noiseMaker(enemyList, seed2);
					}
				case "Sapient Grapevine":
					if (enemyList.size() > 1 && seed1 >= 60 || (enemyList.size() > 1 && charge)){
						healingWine(enemyList);
						charge = false;
					} else if (charge == false && hp > 20) {
						ferment();
					} else if (seed1 >= 15) {
						grapeShot(player, seed2);
					} else {
						ingrain();
					}
				case "Grape Seed":
					if (seed1 >= 95) {
						evolve();
						//please complete this charles
					} else if (def < 60) {
						ingrain();
					} else if (seed2 >= 70) {
						hide();
					} else {
						nothing();
					}
				case "Drunkard":
				
			}
		}
		else {
			doBossMove(player,enemyList);
		}
	}

	private void evolve () {

	}

	private void hide () {
		intang(2);
		System.out.println(name + " hides from you. ");
	}

	private void ferment () {
		System.out.println(name + " fermented to create better wine");
		charge = true;
	}

	private void grapeShot(Player player, int seed2) {
		int counter = 0;
		System.out.println(name + " put grapes in a cannon and shot you with it");
		for (int x = 0; x < 6; x++) {
			if (seed2 >= Math.pow(x, 3) - 30) {
				counter++;
			}
		}
		player.damage(5 * counter * atk/ 100);

	}

	private void healingWine (ArrayList<Enemy>enemyList) {
		int counter=0;
		for(Enemy e:enemyList) {
			if(e.name.equals("Drunkard") || e.name.equals("Winemaker")) {
				if (charge) {
					e.damage(-5- 4 *Floor.level,null);
				} else {
					e.damage(-3 - Floor.level,null);
				}
				counter++;
			}
		}
		charge = false;
		System.out.println(name+" healed "+counter+" enemies with healing wine.");
	}

	private void annoyance (Player player){
		System.out.println(name + " annoys you with their preaching.");
		setMarkForDeath(2);
		player.damage(10 * atk/ 100);
	}

	private void grenade (Player player,ArrayList<Enemy>enemyList){
		System.out.println(name + " threw a grenade at you. He is not good at throwing.");
		for (Enemy e: enemyList) {
			e.damage(5, null);
		}
		hp -= 5;
		player.damage(10 * atk/100);
	}


	private void mopping (Player player){
		System.out.println(name + " puts a dirty mop in your eyes.");
		player.setPoison(2);
		player.damage(15 * atk/ 100);
	}

	private void boost (){
		atk += 10;
		def += 15;
		hp += 5;
	}

	private void healingStim (){
		hp += 10;
	}

	private void nothing (){
		System.out.println(name + " does nothing.");
	}

	private void lastStand(){
		System.out.println(name + "makes a last stand.");
		hp = 10;
		atk += 50;
		charge = true;
	}

	private void bombing(){
		System.out.println("Someone tried to bomb the " + name);
		hp -= 5;
		def -= 5;
		atk += 10;
		setMarkForDeath(2);
	}

	private void createsProblems (){
		System.out.println(name + "creates problems.");
		hp += 10;
	}
	private void hurtfulLies(Player player){
		System.out.println(name + " lies to you. You are poisoned emotionally.");
		player.setPoison(2);
		player.damage(5 * atk/100);
	}

	private void chomp(Player player){
		System.out.println(name + "takes a bite out of you.");
		player.damage(25 * atk/100);
	}

	private void atkIncrement () {
		System.out.println(name + "gets ready to attack.");
		atk += 5;
	}

	private void confuse (Player player) {
		player.setMarkForDeath(2);
	}

	private void cheetoTouch (Player player) {
		System.out.println(name + " touches you with their disgusting cheeto stained fingers.");
		player.setPoison(2);
		player.damage(5 * atk/100);
	}

	private void photoshoot (Player player) {
		System.out.println(name + " blinds you with flash photography.");
		player.setFreeze(2);
		player.setUnaware(2);
	}

	private void swipe (Player player){
		System.out.println(name + "swipes at you");
		player.damage(15 * atk/100);
	}

	private void pounce(Player player) {
		System.out.println(name + " pounced on you and tried to maul you to death.");
		player.damage(40 * atk/100);
	}

	private void humpAbsorption (Player player) {
		System.out.println(name + " absorbed a hump. Who knew they could do that.");
		def += 30;
	}

	private void freezingSpit(Player player) {
		System.out.println(name + " used freezing spit...?");
		player.damage(5 * atk/100.0);
		player.setFreeze(2);
	}

	private void clownFinisher(Player player) {
		System.out.println(name + " noticed you're low on hp and is going for a theatrical finisher involving a rubber chicken.");
		player.damage(99 * atk/100.0);
	}

	private void swarmRepair(ArrayList<Enemy>enemyList) {
		if(!droneRepair) {
			System.out.println(name+" initiates a swarm repair.");
		}
		else {
			System.out.println(name+" joins the swarm repair.");
		}
		for(Enemy e:enemyList) {
			if(!droneRepair) {
				e.damage(-20*atk/100,null);
			}
			else {
				e.damage(-25*atk/100,null);
			}
		}
		droneRepair=true;
	}
	private void droneGun(Player player) {
		System.out.println(name+" opens fire with a mounted gun.");
		player.damage(32*atk/100);
	}
	private void uplink(ArrayList<Enemy>enemyList) {
		System.out.println(name+" establishes an uplink to import damage-dealing strategies.");
		for(Enemy e:enemyList) {
			e.setAtk(5);
		}
	}
	private void armSwing(Player player) {
		System.out.println(name+" swings a robot arm at you.");
		player.damage(65*atk/100);
	}
	private void clownAssembly(ArrayList<Enemy>enemyList) {
		Enemy e;
		int counter=3;
		do {
			e=new Enemy("clown factory",false);
			counter--;
		} while(!e.name.equals("Clown")&&!e.name.equals("Defective Clown")&&counter!=0);
		if(counter!=0) {
			enemyList.add(e);
			System.out.println(name+" manufactured a "+e.name+".");
		}
		else {
			System.out.println(name+" failed to manufacture anything.");
		}
	}
	private void bsod() {
		System.out.println(name+" crashes unexpectedly for 3 turns.");
		freeze+=3;
	}
	private void disassemble(Player player) {
		System.out.println(name+" attempts to disassemble you.");
		player.damage(50+atk/10);
	}
	private void clownRepair(ArrayList<Enemy>enemyList) {
		Random rand=new Random();
		boolean successful=false;
		System.out.println(name+" attempts to repair a clown.");
		for(Enemy e:enemyList) {
			if(rand.nextInt(100)<=70&&e.hp<e.maxhp&&(e.name.equals("Clown")||e.name.equals("Defective Clown"))) {
				successful=true;
				e.damage(-40*atk/100,null);
			}
		}
		if(!successful) {
			System.out.println("It failed to repair anything.");
		}
	}
	private void handSaw(Player player,int seed) {
		System.out.println(name+" cuts into you with an old hand saw.");
		if(seed<=11) {
			System.out.println("At least you are vaccinated for tetanus.");
		}
		player.damage(37*atk/100);
	}
	private void expiredPie(Player player) {
		System.out.println(name+" assaults you with a very expired cream pie. You get sick instantly.");
		player.setPoison(3);
	}
	private void shortCircuit() {
		System.out.println(name+" short circuits.");
		damage(15*atk/100,null);
	}
	private void chainsaw(Player player) {
		if(!clownSaw) {
			System.out.println(name+" revs up a chainsaw.");
			clownSaw=true;
		}
		else {
			System.out.println(name+" attacks you with the revved-up chainsaw.");
			player.damage(75*atk/100);
			clownSaw=false;
		}
	}
	private void noiseMaker(ArrayList<Enemy>enemyList,int seed) {
		System.out.println(name+" uses a noisemaker to encourage its teammates.");
		if(seed<=45) {
			System.out.println("It's the thought that counts.");
		}
		for(Enemy e:enemyList) {
			if(e.hashCode()!=this.hashCode()) {
				e.atk+=3;
			}
		}
	}
	private void creamPie() {
		System.out.println(name+" eats an entire cream pie.");
		damage(-15,null);
	}
	private void aNewBat(Player player) {
		System.out.println(name+" whacks you with a multicoloured baseball bat wrapped in streamers.");
		player.damage(28*atk/100);
	}
	private void bearTrap(Player player,ArrayList<Enemy>enemyList) {
		Random rand=new Random();
		int trapSeed=rand.nextInt(enemyList.size());
		tempPlayer=player;
		enemyList.get(trapSeed).bearTrap=true;
	}
	private void doubleBarrel(Player player) {
		System.out.println(name+" fires a double barrel at you. There is a bit of recoil.");
		player.damage(80*atk/100);
		damage(12*atk/100,null);
	}
	private void handgun(Player player) {
		System.out.println(name+" opens fire with a handgun.");
		player.damage(40*atk/100);
	}
	private void huntingMachete(Player player) {
		System.out.println(name+" shivs you with a rusty machete. You get infected.");
		player.damage(32*atk/100);
		player.setPoison(3);
	}
	private void acornDrop(ArrayList<Enemy>enemyList) {
		int counter=0;
		for(Enemy e:enemyList) {
			if(e.name.equals("Squirrel")) {
				e.damage(-5-Floor.level,null);
				counter++;
			}
		}
		System.out.println(name+" healed "+counter+" squirrels with acorns.");
	}
	private void ingrain() {
		System.out.println(name+" roots deeper into the ground.");
		def+=10;
	}
	private void branchDrop(Player player) {
		System.out.println(name+" drops a branch on your head.");
		player.damage(50*atk/100);
	}
	private void leafBlow(Player player) {
		System.out.println("The wind blows "+name+"'s leaves into your face.");
		player.damage(4*atk/100);
		player.setUnaware(2);
	}
	private void deerCharge(Player player) {
		System.out.println(name + " charges you with it's cardboard antlers. ");
		System.out.println(name + " cardboard deer is disorientated from their charge. ");
		player.damage(40 * atk /100.0);
		unaware+= 2;
		markedForDeath += 2;
	}
	private void deerKick(Player player,int seed) {
		System.out.println(name + " kicks you in the face." );
		if(seed<=22) {
			System.out.println("You lost a tooth.");
		}
		player.damage(20 * atk/ 100.0);
	}

	private void branchAttack(Player player) {
		System.out.println(name  +" hits you with a branch.");
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
		damage(-5-Floor.level,null);
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
	public boolean damage(int i,Player player) {
		int damage=i;
		ArrayList<Enemy> tempList=new ArrayList<Enemy>();
		tempList.add(this);
		if(damage>0) {
			damage=(int)(i*(100.0/def));
			if(player!=null) {
				Inventory.eventFlagHandler("attack",player,tempList,new Integer[]{0});
			}
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
		if(damage>0&&bearTrap) {
			tempPlayer.damage(25*tempPlayer.getAtk()/100);
			bearTrap=false;
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
			if(Floor.level!=0) {
				System.out.println(name+" died.");
			}
			else {
				System.out.println("The game is over.");
			}
			return true;
		}
		if(hp>maxhp) {
			hp=maxhp;
		}
		return false;
	}
	public boolean damage(double i,Player player) {
		int damage=(int)i;
		ArrayList<Enemy> tempList=new ArrayList<Enemy>();
		tempList.add(this);
		if(damage>0) {
			damage=(int)(i*(100.0/def));
			if(player!=null) {
				Inventory.eventFlagHandler("attack",player,tempList,new Integer[]{0});
			}
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
		if(damage>0&&bearTrap) {
			tempPlayer.damage(25*tempPlayer.getAtk()/100);
			bearTrap=false;
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
			if(Floor.level!=0) {
				System.out.println(name+" died.");
			}
			else {
				System.out.println("The game is over.");
			}
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
		if(PassiveItem.getLawLicense()) {
			markedForDeath++;
		}
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
		droneRepair=false;
		canDroneRepair=true;
		bearTrap=false;
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
