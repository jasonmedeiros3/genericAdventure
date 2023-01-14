package genericAdventure;

import java.util.ArrayList;
import java.util.Random;

public final class Floor {
	public final ArrayList<ArrayList<Room>> map=new ArrayList<ArrayList<Room>>();
	public static int level=1;
	public Floor() {
		Random rand=new Random();
		int xBound=rand.nextInt(2,8)+3;
		int yBound=xBound+rand.nextInt(-2,3);
		int itemX=rand.nextInt(xBound);
		int itemY=rand.nextInt(yBound);
		int exitX=rand.nextInt(xBound);
		int exitY=rand.nextInt(yBound);
		int homeCounter=0;
		boolean itemRoom=false;
		boolean exitRoom=false;
		if(Floor.level==0) {
			xBound=1;
			yBound=3;
			itemX=99;
			itemY=99;
			exitX=0;
			exitY=2;
			Inventory.removeAll();
		}
		for(int i=0;i<=xBound;i++) {
			map.add(new ArrayList<Room>());
			for(int j=0;j<=yBound;j++) {
				if(i==itemX&&j==itemY) {
					itemRoom=true;
				}
				if(i==exitX&&j==exitY) {
					exitRoom=true;
				}
				while (itemX == exitX) {
       				itemX = rand.nextInt(xBound);
    			}
				if(Floor.level!=0) {
					map.get(i).add(new Room((byte)i,(byte)j,itemRoom,rand.nextInt(11),exitRoom));
				}
				else {
					map.get(i).add(new Room((byte)i,(byte)j,itemRoom,--homeCounter,exitRoom));
				}
				itemRoom=false;
				exitRoom=false;
			}
		}
	}
	public void reloadFloor(ArrayList<String>quicksaveData,int xBound) {
		int counter=0;
		byte xCoord=0;
		byte yCoord=0;
		int biome=0;
		boolean seen=false;
		boolean fought=false;
		boolean inspected=false;
		boolean itemRoom=false;
		boolean exitRoom=false;
		for(String s:quicksaveData) {
			switch(counter) {
				case 0:
					switch(s) {
						case "forest":
							biome=0;
							break;
						case "clown factory":
							biome=1;
							break;
						case "tundra":
							biome=2;
							break;
						case "consulate":
							biome=3;
							break;
						case "vineyard":
							biome=4;
							break;
						case "sewer":
							biome=5;
							break;
						case "rooftop":
							biome=6;
							break;
						case "fake beach":
							biome=7;
							break;
						case "hell":
							biome=8;
							break;
						case "flight":
							biome=9;
							break;
						case "ireland":
							biome=10;
					}
					break;
				case 1:
					seen=Boolean.parseBoolean(s);
					break;
				case 2:
					fought=Boolean.parseBoolean(s);
					break;
				case 3:
					inspected=Boolean.parseBoolean(s);
					break;
				case 4:
					itemRoom=Boolean.parseBoolean(s);
					break;
				case 5:
					exitRoom=Boolean.parseBoolean(s);
					map.get(xCoord).set(yCoord,new Room(xCoord,yCoord,itemRoom,biome,exitRoom));
					map.get(xCoord).get(yCoord).setSeen(seen);
					map.get(xCoord).get(yCoord).setInspected(inspected);
					map.get(xCoord).get(yCoord).setFought(fought);
					if(yCoord==map.get(0).size()) {
						yCoord=0;
						xCoord++;
					}
					else {
						yCoord++;
					}
			}
			counter++;
			if(counter>=5)counter=0;
		}
	}
	public int getXBound() {
		return map.size();
	}
	public int getYBound() {
		return map.get(0).size();
	}
}
