package genericAdventure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GenericAdventure {
	static int xCoord=0;
	static int yCoord=0;
	static boolean gameLoaded=false;
	static boolean dealRefused=false;
	public ItemPool initItems() {
		ItemPool itempool=new ItemPool();
		return itempool;
	}
	public byte[] classSelect() throws Exception {
		byte input;
		byte[] returnValues=new byte[2];
		Scanner s = new Scanner(System.in);
		System.out.println("Do you suffer from hay fever?");
		System.out.println("1. Yes\n2. No");
		input=s.nextByte();
		if(input!=1&&input!=2) {
			throw new Exception("That isn't a valid answer.");
		}
		returnValues[0]=input;
		System.out.println("Select a class.");
		System.out.println("1. Soldier\n2. Wizard\n3. Lawyer\n4. Paladin\n5. Spy");
		input=s.nextByte();
		if(input<1||input>5) {
			throw new Exception("That isn't a valid class.");
		}
		returnValues[1]=input;
		return returnValues;
	}
	public static void main(String[]args) throws Exception {
		GenericAdventure main=new GenericAdventure();
		ItemPool itempool;
		Scanner s=new Scanner(System.in);
		itempool=main.initItems();
		int input;
		try {
			File encryptionKey=new File("encryptionKey.txt");
			encryptionKey.delete();
			if(encryptionKey.createNewFile()); {
				BufferedWriter keyWriter=new BufferedWriter(new FileWriter(encryptionKey));
				keyWriter.write("1#%$\n2!%&\n3^%*\n4!$&\n5)(&\n");
				keyWriter.write("6##!\n7&#$\n8!&$\n9))%\n0%#*\n");
				keyWriter.write("u#^$\na$%$\nc@(^\ns$&%\np((#\nv%%(\n");
				keyWriter.write("b!$!\nd^%$\ne&!%\nf!!!\ng@@#\nh)!#\n");
				keyWriter.write("i*)(\nj***\nk&$&\nl@%#\nm*))\nn^^!\n");
				keyWriter.write("o*@@\nq)*^\nr#!*\nt^!^\nw&&#\n");
				keyWriter.write("x)#(\ny!@#\nz%)%\n>&**\n %%%\n-^*^\n.&!^\n$$$*\n #)$");
				keyWriter.flush();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			File itemManual=new File("itemManual.txt");
			itemManual.delete();
			if(itemManual.createNewFile()) {
				BufferedWriter bw=new BufferedWriter(new FileWriter(itemManual));
				bw.write("ROCKET LAUNCHER:\n");
				bw.write("Default weapon for the Soldier.\n");
				bw.write("Reliable 50 base damage with area of effect.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("STAFF OF LOWER BACK PAIN:\n");
				bw.write("Default weapon for the Wizard.\n");
				bw.write("High durability and lowers enemy ATK slightly.\n");
				bw.write("Low base damage of 25.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("LAWSUIT\n");
				bw.write("Default weapon for the Lawyer.\n");
				bw.write("Deals only 30 damage but marks enemies for death.\n");
				bw.write("Mark-for-death lasts 2 turns.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("$8 SPEAR\n");
				bw.write("Default weapon for the Paladin.\n");
				bw.write("65 base damage, but uses DEF to attack instead of ATK.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("BUTTER KNIFE\n");
				bw.write("Default weapon for the Spy.");
				bw.write("Only 32 base damage but deals extra damage to unaware enemies.\n");
				bw.write("Extra damage depends on the enemy's health.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("SIX SHOOTER\n");
				bw.write("Weapon for the Spy.\n");
				bw.write("32 base damage, but deals 50% more damage to unaware enemies.\n");
				bw.write("Only 6 durabiity, as the name implies.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("STAFF OF ARTHRITIS\n");
				bw.write("Weapon for the Wizard.\n");
				bw.write("Deals more damage to enemies with high DEF.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("PAPER-THIN DISGUISE\n");
				bw.write("Passive item for the Spy.\n");
				bw.write("Inflicts 1 turn of Unaware on all enemies.\n");
				bw.write("Triggers at the start of each battle.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("CARDBOARD SHIELD\n");
				bw.write("Passive item for the Paladin.\n");
				bw.write("Increases DEF by 5. Nullifies all damage less than 3.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("BLACK BOX\n");
				bw.write("Weapon for the Soldier.\n");
				bw.write("Mostly identical to the Rocket Launcher.\n");
				bw.write("Less weapon durability, but heals self on attack.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("FOUNTAIN PEN\n");
				bw.write("Weapon for the Lawyer.\n");
				bw.write("Alright base damage of 47. No special effects.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("RIOT SHIELD\n");
				bw.write("Passive item for the Paladin.\n");
				bw.write("Increases DEF by 25 and nullifies all damage less than 10.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("HEAT DEATH-PROOF VEST\n");
				bw.write("Passive item for the Soldier.\n");
				bw.write("Increases DEF by 20. Respectable 40 durability.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("SHIMMERING VEIL\n");
				bw.write("Passive item for the Wizard.\n");
				bw.write("Increases DEF by 10. Decreases DEF by 15 after breaking.\n");
				bw.write("Blocks the first hit each battle.\n");
				bw.write("Regenerates 3 durability per floor.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("WATER CANTEEN\n");
				bw.write("Weapon for all classes.\n");
				bw.write("Heals 8 health. Good for 25 uses.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("UBER CANTEEN\n");
				bw.write("Weapon for the Soldier and the Spy.\n");
				bw.write("Gives 3 turns of complete invincibility.\n");
				bw.write("Good for 3 uses.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("MULTICLASS MANUAL\n");
				bw.write("Passive item for all classes.\n");
				bw.write("Increases chance of getting off-class items.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("DEAD RINGER\n");
				bw.write("Weapon for the Spy.\n");
				bw.write("Single-use. Reduce damage of next hit by 75%.\n");
				bw.write("When triggered, makes all enemies unaware.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("HOLY MANTLE\n");
				bw.write("Passive item for the Paladin.\n");
				bw.write("Blocks the first hit each battle.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("SYNTHOL\n");
				bw.write("Passive item for all classes.\n");
				bw.write("Increases ATK by 3.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("BRASS KNUCKLES\n");
				bw.write("Passive item for all classes.\n");
				bw.write("Increases ATK by 8.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("RED FLASK\n");
				bw.write("Weapon for the Wizard and the Paladin.\n");
				bw.write("Heals 40% of max health. Good for 5 uses.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("M1911\n");
				bw.write("Weapon for the Lawyer.\n");
				bw.write("Simple and effective 60 base damage.\n");
				bw.write("Extremely strong against marked-for-death enemies.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("STAPLE GUN\n");
				bw.write("Weapon for the Lawyer.\n");
				bw.write("Deals 40 base damage.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("CONNIVER KUNAI\n");
				bw.write("Weapon for the Spy.\n");
				bw.write("Deals 40 base damage and extra damage to unaware enemies.\n");
				bw.write("Heal after killing an unaware enemy.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("DIRECT HIT\n");
				bw.write("Weapon for the Soldier.\n");
				bw.write("Deals 76 base direct damage but much lower splash damage.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("POT LID\n");
				bw.write("Passive item for the Paladin.\n");
				bw.write("Increases DEF by 10. Decreases incoming damage by 1.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("SYRUP OF IPECAC\n");
				bw.write("Active item for all classes.\n");
				bw.write("Poisons yourself for 5 turns.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("IMMUNE DEFICIENCY\n");
				bw.write("Passive item for all classes.\n");
				bw.write("If attacking while poisoned, inflict the same amount of poison on the enemy.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("SOLAR PANEL ARMOUR\n");
				bw.write("Passive item for the Paladin.\n");
				bw.write("Increases DEF by 4.\n");
				bw.write("Taking burn damage increases ATK by 1 permanently.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("WEIGHTED COIN\n");
				bw.write("Weapon for the Spy.\n");
				bw.write("Inflicts 2 turns of Unaware on all enemies.\n");
				bw.write("You get 3 coins.");
				bw.write("--------------------------------------------------\n");
				bw.write("TAUNTON DART GUN\n");
				bw.write("Weapon for the Spy.\n");
				bw.write("Emetic Mode: Inflict 5 turns of Poison.\n");
				bw.write("Sedative Mode: Inflict 2 turns of Freeze.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("BOOSTER SHOT\n");
				bw.write("Passive item for all classes.\n");
				bw.write("Grants immunity to Poison. 25 durability.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("BLOWGUN\n");
				bw.write("Weapon for all classes.\n");
				bw.write("Emetic Mode: Inflict 5 turns of Poison.\n");
				bw.write("Sedative Mode: Inflict 2 turns of Freeze.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("SHOTGUN\n");
				bw.write("Weapon for the Soldier.\n");
				bw.write("90 base damage with 8 base recoil.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("POISON PEN\n");
				bw.write("Weapon for the Lawyer and the Spy.\n");
				bw.write("Pitiful 5 base damage. Inflicts 1 Poison.\n");
				bw.write("If the enemy is unaware, inflicts 5 Poison instead.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("LAW LICENSE\n");
				bw.write("Passive item for the Lawyer.\n");
				bw.write("When the player inflicts Mark-For-Death, increases it by 1.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("BALACLAVA\n");
				bw.write("Passive item for the Spy.\n");
				bw.write("Causes all player attacks to inflict 1 Unaware.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("SEWING NEEDLE\n");
				bw.write("Weapon for the Spy.\n");
				bw.write("Strongest attack of any knife-type weapon.\n");
				bw.write("Only 9 durability.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("PLAN C\n");
				bw.write("Weapon for all classes.\n");
				bw.write("Deal 199 damage to every enemy.\n");
				bw.write("If the battle does not end within 3 turns, you die.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("NESTED UNIVERSE\n");
				bw.write("Passive item for all classes.\n");
				bw.write("Revive instantly on death with full health.\n");
				bw.write("Clears all status effects on revive.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("SWEDISH PASSPORT\n");
				bw.write("Passive item for all classes.\n");
				bw.write("Makes Consulate enemies less difficult.\n");
				bw.write("--------------------------------------------------\n");
				bw.write("PINOT NOIR\n");
				bw.write("Weapon for the Lawyer and the Spy.\n");
				bw.write("Increases ATK by 10 permanently.\n");
				bw.write("Self-inflicts 5 Unaware and 3 Mark-For-Death.\n");
				bw.write("--------------------------------------------------\n");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.print("GENERIC");
		Thread.sleep(1080);
		System.out.print(" ADVENTURE");
		Thread.sleep(1080);
		System.out.println(" GAME");
		Thread.sleep(1080);
		System.out.println("1. New Game");
		Thread.sleep(1080);
		System.out.println("2. Load Game");
		Thread.sleep(256);
		while(true) {
			try {
				input=s.nextInt();
				if(input!=1&&input!=2) {
					continue;
				}
				break;
			}
			catch(Exception e) {
			}
		}
		if(input==1) {
			Player player=new Player(main.classSelect(),itempool,true);
			Room.printscroll("You have to get the Artifact.");
			Thread.sleep(1000);
			Room.printscroll("Name is uncreative but I assure you, it's very important.");
			Thread.sleep(1000);
			Room.printscroll("Go on and get this over with.");
			Thread.sleep(2250);
			while(true) {
				Floor.level++;
				while(true) {
					try {
						System.out.println("Floor "+(Floor.level));
						main.advent(new Floor(),player,itempool,false);
						break;
					}
					catch(Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		}
		else {
			File quicksave=new File("quicksave.txt");
			BufferedReader br=new BufferedReader(new FileReader(quicksave));
			String str;
			byte className;
			byte hayFever;
			if(Boolean.parseBoolean(Encryption.decryptString(str=br.readLine()))) {
				hayFever=1;
			}
			else {
				hayFever=2;
			}
			switch(Encryption.decryptString(str=br.readLine())) {
				case "soldier":
					className=1;
					break;
				case "wizard":
					className=2;
					break;
				case "lawyer":
					className=3;
					break;
				case "paladin":
					className=4;
					break;
				case "spy":
					className=5;
					break;
				default:
					className=66;
			}
			Player player=new Player(new byte[]{hayFever,className},itempool,false);
			player.setMaxHp(Integer.parseInt(Encryption.decryptString(br.readLine()))-player.getMaxHp());
			player.setHp(Integer.parseInt(Encryption.decryptString(br.readLine()))-player.getHp());
			player.setAtk(Integer.parseInt(Encryption.decryptString(br.readLine()))-player.getAtk());
			player.setDef(Integer.parseInt(Encryption.decryptString(br.readLine()))-player.getDef());
			player.setAfterburn(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setPoison(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setMarkForDeath(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setUnaware(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setFreeze(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setInvuln(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setIntang(Integer.parseInt(Encryption.decryptString(br.readLine())));
			xCoord=Integer.parseInt(Encryption.decryptString(br.readLine()));
			yCoord=Integer.parseInt(Encryption.decryptString(br.readLine()));
			Floor.level=Integer.parseInt(Encryption.decryptString(br.readLine()));
			int inventoryIndex=0;
			while((str=Encryption.decryptString(br.readLine()))!=null) {
				int i=0;
				int chevronIndex=0;
				char[] itemNameArray=str.toCharArray();
				for(char c:itemNameArray) {
					if(c=='>') {
						chevronIndex=i;
					}
					i++;
				}
				for(i=0;i<itempool.size();i++) {
					String compactSaveName=str.substring(0,chevronIndex).toLowerCase();
					String compactPoolName=itempool.get(i).getName().toLowerCase();
					compactSaveName=compactSaveName.replaceAll("  "," ");
					compactPoolName=compactPoolName.replaceAll("  "," ");
					if(compactSaveName.equals(compactPoolName)) {
						Inventory.silentAdd(itempool.get(i),player);
						Inventory.get(inventoryIndex).damage(Inventory.get(inventoryIndex).getMaxDurability()-Integer.parseInt(str.substring(chevronIndex+1)),player);
						break;
					}
				}
				inventoryIndex++;
			}
			File mapQuicksave=new File("mapQuicksave.txt");
			BufferedReader br2=new BufferedReader(new FileReader(mapQuicksave));
			int xBound=Integer.parseInt(Encryption.decryptString(br2.readLine()));
			ArrayList<String> quicksaveData=new ArrayList<String>();
			Floor floor=new Floor();
			while((str=Encryption.decryptString(br2.readLine()))!=null) {
				int counter=0;
				char[] roomNameArray=str.toCharArray();
				int[] roomNameChevronIndices=new int[5];
				int currentChevronIndex=0;
				int previousChevronIndex=0;
				for(char c:roomNameArray) {
					if(c=='>') {
						roomNameChevronIndices[currentChevronIndex++]=counter;
					}
					counter++;
				}
				for(int i:roomNameChevronIndices) {
					if(i>=1) {
						quicksaveData.add(str.substring(previousChevronIndex,i));
					}
					previousChevronIndex=i;
				}
				floor.reloadFloor(quicksaveData, xBound);
			}
			gameLoaded=true;
			while(true) {
				while(true) {
					try {
						System.out.println("Floor "+(Floor.level));
						main.advent(new Floor(),player,itempool,gameLoaded);
						gameLoaded=false;
						break;
					}
					catch(Exception e) {
						System.out.println(e.getMessage()==null?"Something went wrong. Try again.":e.getMessage());
						continue;
					}
				}
				if(!dealRefused) {
					Floor.level++;
				}
				else {
					Floor.level=0;
				}
			}
		}
	}
	public void advent(Floor floor,Player player,ItemPool itempool,boolean useExistingCoords) throws Exception {
		Random rand=new Random();
		Room location;
		location=floor.map.get(xCoord).get(yCoord);
		if(!useExistingCoords) {
			do {
				xCoord=rand.nextInt(floor.map.size());
				yCoord=rand.nextInt(floor.map.get(xCoord).size());
				location=floor.map.get(xCoord).get(yCoord);
			} while(location.getExit());
		}
		if(Floor.level==0) {
			location=floor.map.get(0).get(0);
		}
		while(true) {
			try {
				location=location.display(floor,player,itempool);
				if(location==null) {
					break;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
