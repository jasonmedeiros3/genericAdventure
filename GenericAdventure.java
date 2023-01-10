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
	public ItemPool initItems() {
		ItemPool itempool=new ItemPool();
		return itempool;
	}
	public byte classSelect() throws Exception {
		byte input;
		Scanner s = new Scanner(System.in);
		System.out.println("Select a class.");
		System.out.println("1. Soldier\n2. Wizard\n3. Lawyer\n4. Paladin\n5. Spy");
		input=s.nextByte();
		if(input<1||input>5) {
			throw new Exception("That isn't a valid class.");
		}
		return input;
	}
	public static void main(String[]args) throws Exception {
		GenericAdventure main=new GenericAdventure();
		ItemPool itempool;
		Scanner s=new Scanner(System.in);
		itempool=main.initItems();
		int input;
		try {
			File encryptionKey=new File("encryptionKey.txt");
			if(encryptionKey.createNewFile()); {
				BufferedWriter keyWriter=new BufferedWriter(new FileWriter("encryptionKey.txt"));
				keyWriter.write("1#%$\n2!%&\n3^%*\n4!$&\n5)(&\n");
				keyWriter.write("6##!\n7&#$\n8!&$\n9))%\n0%#*\n");
				keyWriter.write("u#^$\na$%$\nc@(^\ns$&%\np((#\nv%%(\n");
				keyWriter.write("b!$!\nd^%$\ne&!%\nf!!!\ng@@#\nh)!#\n");
				keyWriter.write("i*)(\nj***\nk&$&\nl@%#\nm*))\nn^^!\n");
				keyWriter.write("o*@@\nq)*^\nr#!*\nt^!^\nw&&#\n");
				keyWriter.write("x)#(\ny!@#\nz%)%\n>&**\n %%%\n-^*^\n.&!^");
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
			switch(Encryption.decryptString(str=br.readLine())) {
				case "Soldier":
					className=1;
					break;
				case "Wizard":
					className=2;
					break;
				case "Lawyer":
					className=3;
					break;
				case "Paladin":
					className=4;
					break;
				case "Spy":
					className=5;
					break;
				default:
					className=66;
			}
			Player player=new Player(className,itempool);
			player.setMaxHp(Integer.parseInt(Encryption.decryptString(br.readLine()))-player.getMaxHp());
			player.setHp(Integer.parseInt(Encryption.decryptString(br.readLine()))-player.getHp());
			player.setAtk(Integer.parseInt(Encryption.decryptString(br.readLine()))-player.getAtk());
			player.setDef(Integer.parseInt(Encryption.decryptString(br.readLine()))-player.getDef());
			player.setAfterburn(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setPoison(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setMarkForDeath(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setUnaware(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setFreeze(Integer.parseInt(Encryption.decryptString(br.readLine())));
			player.setInvuln(Integer.parseInt(Encryption.decryptString(br.readLine()))-player.getMaxHp());
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
							Inventory.silentAdd(itempool.get(i));
					}
				}
				Inventory.get(inventoryIndex).damage(Inventory.get(inventoryIndex).getMaxDurability()-Integer.parseInt(str.substring(chevronIndex+1)));
				inventoryIndex++;
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
