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
	static int xCoord;
	static int yCoord;
	static boolean gameLoaded=false;
	static boolean dealAccepted=false;
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
				BufferedWriter keyWriter=new BufferedWriter(new FileWriter("encryptionKey.txt"));
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
			Player player=new Player(main.classSelect(),itempool);
			while(true) {
				Floor.level++;
				while(true) {
					try {
						System.out.println("Floor "+(Floor.level));
						main.advent(new Floor(),player,itempool);
						break;
					}
					catch(Exception e) {
						System.out.println(e.getMessage()==null?"Something went wrong. Try again.":e.getMessage());
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
			Player player=new Player(new byte[]{hayFever,className},itempool);
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
			while((str=Encryption.decryptString(br.readLine()))!=null) {
				int i=0;
				int inventoryIndex=0;
				int chevronIndex=0;
				char[] itemNameArray=str.toCharArray();
				for(char c:itemNameArray) {
					if(c=='>') {
						chevronIndex=i;
					}
					i++;
				}
				for(i=0;i<itempool.size();i++) {
					if(str.substring(0,chevronIndex).equals(itempool.get(i).getName())) {
							Inventory.silentAdd(itempool.get(i),player);
							Inventory.get(inventoryIndex).damage(Inventory.get(inventoryIndex).getMaxDurability()-Integer.parseInt(str.substring(chevronIndex+1)),player);
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
						main.advent(new Floor(),player,itempool);
						break;
					}
					catch(Exception e) {
						System.out.println(e.getMessage()==null?"Something went wrong. Try again.":e.getMessage());
						continue;
					}
				}
				if(!dealAccepted) {
					Floor.level++;
				}
				else {
					Floor.level=0;
				}
			}
		}
	}
	public void advent(Floor floor,Player player,ItemPool itempool) throws Exception {
		Random rand=new Random();
		Room location;
		do {
			xCoord=rand.nextInt(floor.map.size()-1);
			yCoord=rand.nextInt(floor.map.get(xCoord).size()-1);
			location=floor.map.get(xCoord).get(yCoord);
		} while(location.getExit());
		while(true) {
			try {
				location=location.display(floor,player,itempool);
				if(location==null) {
					break;
				}
			}
			catch(Exception e) {
			}
		}
	}
}
