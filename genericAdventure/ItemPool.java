package genericAdventure;

import java.util.ArrayList;

public class ItemPool {
	private ArrayList<Item> pool=new ArrayList<Item>();
	public ItemPool() {
		byte b;
		short s;
		pool.add(new ActiveItem("Rocket Launcher",s=10,20,b=1,false)); //0
		pool.add(new ActiveItem("Staff of Lower Back Pain",s=6,125,b=2,false)); //1
		pool.add(new ActiveItem("Lawsuit",s=0,10,b=3,false)); //2
		pool.add(new ActiveItem("$8 Spear",s=12,32,b=4,false)); //3
		pool.add(new ActiveItem("Butter Knife",s=2,65,b=5,false)); //4
		pool.add(new ActiveItem("Six Shooter",s=8,6,b=5,false)); //5
		pool.add(new ActiveItem("Staff of Arthritis",s=7,24,b=2,false)); //6
		pool.add(new PassiveItem("Paper-Thin Disguise",s=1,100,b=5)); //7
		pool.add(new PassiveItem("Cardboard Shield",s=8,48,b=4)); //8
		pool.add(new ActiveItem("Black Box",s=10,17,b=1,false)); //9
		pool.add(new ActiveItem("Fountain Pen",s=1,100,b=3,false)); //10
		pool.add(new PassiveItem("Riot Shield",s=20,98,b=4)); //11
		pool.add(new PassiveItem("Heat Death-Proof Vest",s=12,40,b=1)); //12
		pool.add(new PassiveItem("Shimmering Veil",s=2,16,b=2)); //13
		pool.add(new ActiveItem("Water Canteen",s=1,25,b=0,true)); //14
		pool.add(new ActiveItem("Uber Canteen",s=9,3,b=6,true)); //15
		pool.add(new PassiveItem("Multiclass Manual",s=3,256,b=0)); //16
		pool.add(new ActiveItem("Dead Ringer",s=6,1,b=5,false)); //17
	}
	public Item get(int index) {
		return pool.get(index);
	}
	public int size() {
		return pool.size();
	}
}