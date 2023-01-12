package genericAdventure;

import java.util.ArrayList;
import java.util.Random;

public class Floor {
	public ArrayList<ArrayList<Room>> map=new ArrayList<ArrayList<Room>>();
	public static int level=0;
	public Floor() {
		Random rand=new Random();
		int xBound =rand.nextInt(4)+5;
		int yBound=xBound+rand.nextInt(4)-2;
		int itemX=rand.nextInt(xBound);
		int itemY=rand.nextInt(yBound);
		int exitX=rand.nextInt(xBound);
		int exitY=rand.nextInt(yBound);
		boolean itemRoom=false;
		boolean exitRoom=false;
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
				map.get(i).add(new Room((byte)i,(byte)j,(short)rand.nextInt(5),itemRoom,rand.nextInt(11),exitRoom));
				itemRoom=false;
				exitRoom=false;
			}
		}
	}

}
