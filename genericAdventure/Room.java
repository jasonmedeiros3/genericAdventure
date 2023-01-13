package genericAdventure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Room {
	private final byte xCoord;
	private final byte yCoord;
	private final boolean itemRoom;
	private boolean currentLocation=false;
	private String biome;
	private final boolean exitRoom;
	private boolean seen;
	private boolean inspected;
	private boolean fought;
	private final int offclassItemChance;
	public static int turn;
	public Room(byte x,byte y,boolean item,int biomeSeed,boolean exit) {
		xCoord=x;
		yCoord=y;
		itemRoom=item;
		exitRoom=exit;
		seen=false;
		fought=false;
		inspected=false;
		boolean offclassItemChanceUp=false;
		for(int i=0;i<Inventory.size();i++) {
			if(Inventory.get(i).getName().equals("Multiclass Manual")) {
				offclassItemChanceUp=true;
				break;
			}
		}
		if(offclassItemChanceUp) {
			offclassItemChance=80;
		}
		else {
			offclassItemChance=25;
		}
		switch(biomeSeed) {
			case 0:
				biome="forest";
				break;
			case 1:
				biome="clown factory";
				break;
			case 2:
				biome="tundra";
				break;
			case 3:
				biome="consulate";
				break;
			case 4:
				biome="vineyard";
				break;
			case 5:
				biome="sewer";
				break;
			case 6:
				biome="rooftop";
				break;
			case 7:
				biome="fake beach";
				break;
			case 8:
				biome="hell";
				break;
			case 9:
				biome="flight";
				break;
			case 10:
				biome="ireland";
				break;
		}
	}
	public byte getX() {
		return xCoord;
	}
	public byte getY() {
		return yCoord;
	}
	public boolean getItemRoom() {
		return itemRoom;
	}
	public boolean getCurrentLocation() {
		return currentLocation;
	}
	public String getBiome() {
		return biome;
	}
	public boolean getExit() {
		return exitRoom;
	}
	public boolean getSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen=seen;
	}
	public boolean getInspected() {
		return inspected;
	}
	public void setInspected(boolean inspected) {
		this.inspected=inspected;
	}
	public boolean getFought() {
		return fought;
	}
	public void setFought(boolean fought) {
		this.fought=fought;
	}
	public Room display(Floor floor,Player player,ItemPool itempool) throws Exception {
		char input;
		Random rand=new Random();
		int displaySeed=rand.nextInt(100);
		System.out.println(welcomeMessage(displaySeed));
		if(itemRoom||displaySeed<=2) {
			System.out.println("You get the feeling that something mildly important is here.");
		}
		if(seen) {
			System.out.println("You've already been here before.");
		}
		else {
			System.out.println("You haven't been here yet.");
		}
		System.out.println("Coordinates: ("+xCoord+","+yCoord+")");
		seen=true;
		boolean canLeft=false,canRight=false,canDown=false,canUp=false;
		if(xCoord>0) {
			canLeft=true;
		}
		if(xCoord<floor.map.size()-1) {
			canRight=true;
		}
		if(yCoord>0) {
			canDown=true;
		}
		if(yCoord<floor.map.get(xCoord).size()-1) {
			canUp=true;
		}
		while(true) {
			try {
				input=roomChoice(floor);
			}
			catch(Exception e) {
				System.out.println("That isn't a valid input.");
				continue;
			}
			if((input=='L'||input=='l')&&canLeft) {
				System.out.println("You moved left.");
				return floor.map.get(xCoord-1).get(yCoord);
			}
			if((input=='R'||input=='r')&&canRight) {
				System.out.println("You moved right.");
				return floor.map.get(xCoord+1).get(yCoord);
			}
			if((input=='D'||input=='d')&&canDown) {
				System.out.println("You moved down.");
				return floor.map.get(xCoord).get(yCoord-1);
			}
			if((input=='U'||input=='u')&&canUp) {
				System.out.println("You moved up.");
				return floor.map.get(xCoord).get(yCoord+1);
			}
			if(input=='S'||input=='s') {
				File quicksave=new File("quicksave.txt");
				File mapQuicksave=new File("mapQuicksave.txt");
				int intInput;
				if(quicksave.createNewFile()||mapQuicksave.createNewFile()) {
					System.out.println("A save file already exists. Overwrite save?");
					System.out.println("1. Yes");
					System.out.println("2. No");
					intInput=getIntInput(1,2);
					if(intInput==2) {
						continue;
					}
				}
				try {
					BufferedWriter bw=new BufferedWriter(new FileWriter(quicksave));
					quicksave.createNewFile();
					bw.write(Encryption.encryptln(Boolean.toString(player.getHayFever())));
					bw.write(Encryption.encryptln(player.getClassName().toLowerCase()));
					bw.write(Encryption.encryptln(Integer.toString(player.getMaxHp())));
					bw.write(Encryption.encryptln(Integer.toString(player.getHp())));
					bw.write(Encryption.encryptln(Integer.toString(player.getAtk())));
					bw.write(Encryption.encryptln(Integer.toString(player.getDef())));
					bw.write(Encryption.encryptln(Integer.toString(player.getAfterburn())));
					bw.write(Encryption.encryptln(Integer.toString(player.getPoison())));
					bw.write(Encryption.encryptln(Integer.toString(player.getMarkForDeath())));
					bw.write(Encryption.encryptln(Integer.toString(player.getUnaware())));
					bw.write(Encryption.encryptln(Integer.toString(player.getFreeze())));
					bw.write(Encryption.encryptln(Integer.toString(player.getInvuln())));
					bw.write(Encryption.encryptln(Integer.toString(player.getIntang())));
					bw.write(Encryption.encryptln(Integer.toString(xCoord)));
					bw.write(Encryption.encryptln(Integer.toString(yCoord)));
					bw.write(Encryption.encryptln(Integer.toString(Floor.level)));
					for(int i=0;i<Inventory.size();i++) {
						bw.write(Encryption.encryptln(Inventory.get(i).getName().toLowerCase()+">"+Inventory.get(i).getDurability()));
					}
					bw.flush();
					BufferedWriter bw2=new BufferedWriter(new FileWriter(mapQuicksave));
					bw2.write(Encryption.encryptln(Integer.toString(floor.getXBound())));
					for(ArrayList<Room> subList:floor.map) {
						for(Room r:subList) {
							bw2.write(Encryption.encryptln(r.biome+">"+r.seen+">"+r.fought+">"+r.inspected+">"+r.itemRoom+">"+r.exitRoom));
						}
						bw2.write(Encryption.encryptln("."));
					}
					bw2.flush();
					System.out.println("Quicksave created.");
				}
				catch(Exception e) {
					System.out.println("Error creating quicksave.");
				}
			}
			if(input=='1'&&!itemRoom&&!fought) {
				battle(floor,player);
				int seed=rand.nextInt(100);
				if(seed>=Math.pow(Floor.level,2)) {
					while(true) {
						int seed2=rand.nextInt(itempool.size());
						int seed3=rand.nextInt(100);
						byte type=itempool.get(seed2).getType();
						switch(player.getClassName()) {
							case "Soldier":
								if((type==0||type==1||type==6)||seed3<=offclassItemChance) {
									break;
								}
								continue;
							case "Wizard":
								if((type==0||type==2||type==7)||seed3<=offclassItemChance) {
									break;
								}
								continue;
							case "Lawyer":
								if((type==0||type==3)||seed3<=offclassItemChance) {
									break;
								}
								continue;
							case "Paladin":
								if((type==0||type==4||type==7)||seed3<=offclassItemChance) {
									break;
								}
								continue;
							case "Spy":
								if((type==0||type==5||type==6)||seed3<=offclassItemChance) {
									break;
								}
								continue;
						}
						Inventory.add(itempool.get(rand.nextInt(itempool.size())),player);
					}
				}
			}
			else if(input=='1'&&itemRoom) {
				while(true) {
					int seed=rand.nextInt(itempool.size());
					int seed2=rand.nextInt(100);
					byte type=itempool.get(seed).getType();
					switch(player.getClassName()) {
						case "Soldier":
							if((type==0||type==1||type==6)||seed2<=offclassItemChance) {
								break;
							}
							continue;
						case "Wizard":
							if((type==0||type==2||type==7)||seed2<=offclassItemChance) {
								break;
							}
							continue;
						case "Lawyer":
							if((type==0||type==3)||seed2<=offclassItemChance) {
								break;
							}
							continue;
						case "Paladin":
							if((type==0||type==4||type==7)||seed2<=offclassItemChance) {
								break;
							}
							continue;
						case "Spy":
							if((type==0||type==5||type==6)||seed2<=offclassItemChance) {
								break;
							}
							continue;
					}
					Inventory.add(itempool.get(rand.nextInt(itempool.size())),player);
				}
			}
			else if(input=='1'){
				if(!inspected) {
					System.out.println("There's nothing here.");
					inspected=true;
				}
				else {
					System.out.println("There's still nothing here.");
				}
			}
			if(input=='2') {
				System.out.println("You successfully didn't do anything.");
			}
			if(input=='3'&&exitRoom) {
				if(Floor.level!=3&&Floor.level!=6&&Floor.level!=10) {
					return null;
				}
				bossBattle(floor,player);
				return null;
			}
		}
	}
	public static int getIntInput() throws Exception {
		Scanner s=new Scanner(System.in);
		return s.nextInt();
	}
	public static int getIntInput(int lowerBound,int upperBound) throws Exception {
		int input;
		do {
			input=getIntInput();
		} while(lowerBound>input||input>upperBound);
		return input;
	}
	public void displayPlayer(Player player) {
		System.out.println(player.getClassName()+": (HP: "+player.getHp()+"/"+player.getMaxHp()+")"+player.stringStatus());
	}
	public void displayEnemies(ArrayList<Enemy> enemies) {
		for(Enemy e:enemies) {
			System.out.println(e.getName()+": (HP: "+e.getHp()+"/"+e.getMaxHp()+")"+e.stringStatus());
		}
	}
	public void displayNumberedEnemies(ArrayList<Enemy> enemies) {
		int counter=0;
		for(Enemy e:enemies) {
			System.out.println(++counter+". "+e.getName()+": (HP: "+e.getHp()+"/"+e.getMaxHp()+")"+e.stringStatus());
		}
	}
	public void battle(Floor floor,Player player) {
		int maxEnemyWeight=(int)(6+Math.sqrt(2*(Floor.level-1)));
		int enemyWeight=0;
		int input;
		int turn=0;
		boolean hasActiveItem=false;
		Scanner s=new Scanner(System.in);
		Random rand=new Random();
		ArrayList<Enemy> enemies=new ArrayList<Enemy>();
		for(int i=0;i<5;i++) {
			enemies.add(new Enemy(biome,false));
			enemyWeight+=enemies.get(i).getWeight();
			if(enemyWeight>maxEnemyWeight) {
				break;
			}
		}
		for(int i=0;i<Inventory.size();i++) {
			if(Inventory.get(i).isPassive()) {
				try {
					Inventory.get(i).doEffect("battleStart",player,enemies,null,(byte) 0);
				} catch (Exception e) {
				}
			}
		}
		while(true) {
			turn++;
			int deadEnemyCounter=0;
			for(Enemy e:enemies) {
				e.checkDead();
			}
			while(true) {
				try {
					if(enemies.get(deadEnemyCounter).getDead()) {
						enemies.remove(deadEnemyCounter);
					}
					else {
						deadEnemyCounter++;
					}
				}
				catch(Exception e) {
					break;
				}
			}
			if(enemies.size()<1) {
				break;
			}
			if(player.getHp()<=0) {
				System.out.println("You died.");
				System.out.println("You made it "+Floor.level+" floors as the "+player.getClassName()+".");
				System.out.println("Your final item count was "+Inventory.size()+".");
			}
			while(true) {
				displayPlayer(player);
				displayEnemies(enemies);
				for(int i=0;i<Inventory.size();i++) {
					if(Inventory.get(i).isPassive()) {
						try {
							Inventory.get(i).doEffect("turnStart",player,enemies,null,(byte) 0);
						} catch (Exception e1) {
						}
					}
					else {
						hasActiveItem=true;
					}
				}
				System.out.println("1. Struggle");
				System.out.println("2. Open Inventory");
				System.out.println("3. Run Away");
				try {
					input=getIntInput(1,3);
					System.out.println("OK!");
					break;
				}
				catch(Exception e) {
				}
			}
			if(input==1) {
				displayNumberedEnemies(enemies);
				System.out.println("Select a target.");
				try {
					input=getIntInput(1,5);
				} catch (Exception e) {
				}
				enemies.get(input).damage(15*player.getAtk()/100.0);
				player.damage(5*player.getAtk()/100.0);
			}
			else if(input==2&&hasActiveItem) {
				battleInventory(player,enemies,(byte)input);
			}
			else if(input==2) {
				System.out.println("No usable items.");
			}
		}
	}
	public void bossBattle(Floor floor,Player player) {
		int maxEnemyWeight=(int)(6+Math.sqrt(2*(Floor.level-1)));
		int enemyWeight=0;
		int input;
		int turn=0;
		boolean hasActiveItem=false;
		Scanner s=new Scanner(System.in);
		Random rand=new Random();
		ArrayList<Enemy> enemies=new ArrayList<Enemy>();
		for(int i=0;i<5;i++) {
			enemies.add(new Boss(null));
			enemyWeight+=enemies.get(i).getWeight();
			if(enemyWeight>maxEnemyWeight) {
				break;
			}
		}
		for(int i=0;i<Inventory.size();i++) {
			if(Inventory.get(i).isPassive()) {
				try {
					Inventory.get(i).doEffect("battleStart",player,enemies,null,(byte) 0);
				} catch (Exception e) {
				}
			}
		}
		while(true) {
			turn++;
			int deadEnemyCounter=0;
			for(Enemy e:enemies) {
				e.checkDead();
			}
			while(true) {
				try {
					if(enemies.get(deadEnemyCounter).getDead()) {
						enemies.remove(deadEnemyCounter);
					}
					else {
						deadEnemyCounter++;
					}
				}
				catch(Exception e) {
					break;
				}
			}
			if(enemies.size()<1) {
				break;
			}
			if(player.getHp()<=0) {
				System.out.println("You died.");
				System.out.println("You made it "+Floor.level+" floors as the "+player.getClassName()+".");
				System.out.println("Your final item count was "+Inventory.size()+".");
			}
			while(true) {
				displayPlayer(player);
				displayEnemies(enemies);
				for(int i=0;i<Inventory.size();i++) {
					if(Inventory.get(i).isPassive()) {
						try {
							Inventory.get(i).doEffect("turnStart",player,enemies,null,(byte) 0);
						} catch (Exception e1) {
						}
					}
					else {
						hasActiveItem=true;
					}
				}
				System.out.println("1. Struggle");
				System.out.println("2. Open Inventory");
				System.out.println("3. Run Away");
				try {
					input=getIntInput(1,3);
					System.out.println("OK!");
					break;
				}
				catch(Exception e) {
				}
			}
			if(input==1) {
				displayNumberedEnemies(enemies);
				System.out.println("Select a target.");
				try {
					input=getIntInput(1,5);
				} catch (Exception e) {
				}
				enemies.get(input).damage(15*player.getAtk()/100.0);
				player.damage(5*player.getAtk()/100.0);
			}
			else if(input==2&&hasActiveItem) {
				battleInventory(player,enemies,(byte)input);
			}
			else if(input==2) {
				System.out.println("No usable items.");
			}
		}
	}
	public void battleInventory(Player player,ArrayList<Enemy> enemies,byte target) {
		int counter=0;
		int input=0;
		ArrayList<Item> battleInv=new ArrayList<Item>();
		for(int i=0;i<Inventory.size();i++) {
			if(!Inventory.get(i).isPassive()) {
				System.out.println(++counter+". "+Inventory.get(i).getName()+"("+Inventory.get(i).getDurability()+" Durability)");
				battleInv.add(Inventory.get(i));
			}
		}
		System.out.println("Select an item to use.");
		try {
			input=getIntInput(1,battleInv.size());
		} catch (Exception e) {
		}
		try {
			Inventory.get(input-1).doEffect("placeholder",player,enemies,new Integer[]{0},target);
		} catch (Exception e) {
		}
	}
	public char roomChoice(Floor floor) {
		char input;
		if(xCoord>0) {
			System.out.println("L. Go left");
		}
		if(xCoord<floor.map.size()-1) {
			System.out.println("R. Go right");
		}
		if(yCoord>0) {
			System.out.println("D. Go down");
		}
		if(yCoord<floor.map.get(xCoord).size()-1) {
			System.out.println("U. Go up");
		}
		System.out.println("S. Create quicksave");
		Scanner s=new Scanner(System.in);
		System.out.println("1. Inspect");
		System.out.println("2. Do nothing");
		if(exitRoom) {
			System.out.println("3. Next floor");
		}
		input=s.nextLine().charAt(0);
		return input;
	}
	public String welcomeMessage(int displaySeed) {
		switch(biome) {
			case "forest":
				if(displaySeed<=25) {
					return("It seems to be some kind of forest, but you're not sure. It might be a scone.");
				}
				else if(displaySeed<=50) {
					return("You're in a forest now. You turn 40 degrees to the left. You feel proud of yourself.");
				}
				else if(displaySeed==59) {
					return("It's a forest. It looks the same as the other thousand forests.");
				}
				else {
					return("It's a forest. You kill and eat a squirrel. It had a family, you monster.");
				}
			case "clown factory":
				if(displaySeed<=15) {
					return("It's a factory. It makes clowns. You put on a clown nose and pretend you're a lawyer.");
				}
				else if(displaySeed<=25) {
					return("You're in a factory that makes clowns, and also lawyers, which are basically the same thing.");
				}
				else if(displaySeed<=50) {
					return("You're in a clown-making factory. It smells of rubber.");
				}
				else if(displaySeed<=80) {
					return("This factory makes clowns. The floor is littered with oversized shoes.");
				}
				else {
					return("This factory manufactures clowns. It's mostly not on fire.");
				}
			case "tundra":
				if(displaySeed<=21) {
					return("It's an ice-cold tundra. It could be the headquarters of a popsicle mining ring.");
				}
				else if(displaySeed<=42) {
					return("You're in a freezing tundra. What's more, you aren't very well dressed for the weather.");
				}
				else {
					return("This tundra is too cold to adequately describe with words.");
				}
			case "consulate":
				if(displaySeed<=45) {
					return("You're in a consulate. It's full of white-collar criminals.");
				}
				else if(displaySeed<=56) {
					return("You're in a consulate. It's extremely Swedish.");
				}
				else {
					return("It's a very clinical-looking consulate. You steal classified government documents and eat them.");
				}
			case "vineyard":
				if(displaySeed<=25) {
					return("You're in a vineyard. The grapes are exhibiting thigmotropism. You jump twice for no reason.");
				}
				else if(displaySeed<=50) {
					return("It's a vineyard full of grapes. Cheap, low-quality wine is made here.");
				}
				else if(displaySeed<=75) {
					return("There are grapes everywhere. This must be a vineyard for making cheap wines.");
				}
				else {
					return("Nothing here but rows on rows of haphazardly cultivated grapevines.");
				}
			case "sewer":
				if(displaySeed<=7) {
					return("It's a fetid sewer. A red balloon is floating over the sewage.");
				}
				else if(displaySeed<=28) {
					return("It's a sewer. It smells of trash and designer perfume; that is, it smells like trash.");
				}
				else {
					return("This is a sewer. Why are you in a sewer?");
				}
			case "rooftop":
				if(displaySeed<=15) {
					return("You walk through a door onto a rooftop despite just having been on ground level.");
				}
				else if(displaySeed<=40) {
					return("You're on the roof of a building. You remind yourself not to drink acid.");
				}
				else if(displaySeed<=75) {
					return("It's the rooftop of some building. There's a \"NO TRESPASSING\" sign. You lick the sign.");
				}
				else {
					return("You somehow wind up on a building's rooftop again.");
				}
			case "fake beach":
				if(displaySeed<=25) {
					return("You're on a beach. It's very obviously made of plastic. You swim in the plastic ocean.");
				}
				else if(displaySeed<=60) {
					return("It's a fake beach made of glass. You play dead in case there are bears watching.");
				}
				else {
					return("You're on a beach. It's actually just a 2D cardboard cutout, but it's good enough to fool you.");
				}
			case "hell":
				if(displaySeed<=6) {
					return("You're in hell. You only sinned a few thousand times, so this must be a mistake.");
				}
				else if(displaySeed<=30) {
					return("It's hell. The fire is papier mach\u0039, but the screaming is real.");
				}
				else if(displaySeed<=54) {
					return("You are surrounded by a lake of fire. People are roasting marshmallows.");
				}
				else if(displaySeed<=78) {
					return("This is hell, complete with brimstone. You mistake it for Florida.");
				}
				else {
					return("It's hell. You joke about having a hell of a time. The crowd boos you off the stage.");
				}
			case "flight":
				if(displaySeed<=40) {
					return("You're on a commercial airliner. You didn't buy a ticket first. You are now a hardened criminal.");
				}
				else if(displaySeed<=80) {
					return("You're on a commercial airliner kilometres off the ground, somehow.");
				}
				else {
					return("It's a commercial airliner. You indulge in airplane food, then vomit it into a toilet.");
				}
			case "ireland":
				if(displaySeed<=27) {
					return("Through some strange sequence of events, you find yourself in Ireland.");
				}
				else if(displaySeed<=33) {
					return("You're in Ireland. How did you end up in Ireland?");
				}
				else if(displaySeed<=66) {
					return("You're in Ireland now. You can't tell because there aren't enough clovers and angry people.");
				}
				else {
					return("It's Ireland. You blink five times in thirty seconds, slightly below average.");
				}
			default:
				return("You're in some kind of strange state of limbo. Please file a bug report.");
		}
	}
	public void displayMap(Floor floor) {
		for (int x = 0; x < floor.map.size(); x++) {
			for (int y = 0;y < floor.map.get(0).size(); y++) {
           		if (x == xCoord && y == yCoord){
       				System.out.print(";;");
           		}
       			else {
                	System.out.print("::");
           		}
          	}
         	System.out.println();
 		}
 	}	
}
