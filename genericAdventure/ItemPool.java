package genericAdventure;

import java.util.ArrayList;
import java.util.Random;

public class ItemPool {
	private ArrayList<Item> pool=new ArrayList<Item>();
	public ItemPool() {
		byte b;
		short s;
		pool.add(new ActiveItem("Rocket Launcher",s=10,20,b=1,false)); //0
		pool.add(new ActiveItem("Staff of Lower Back Pain",s=6,125,b=2,false)); //1
		pool.add(new ActiveItem("Lawsuit",s=0,26,b=3,false)); //2
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
		pool.add(new PassiveItem("Holy Mantle",s=1,14,b=4)); //18
		pool.add(new PassiveItem("Synthol",s=1,256,b=0)); //19
		pool.add(new PassiveItem("Brass Knuckles",s=4,160,b=0)); //20
		pool.add(new ActiveItem("Red Flask",s=3,5,b=7,true)); //21
		pool.add(new ActiveItem("M1911",s=6,20,b=3,false)); //22
		pool.add(new ActiveItem("Staple Gun",s=10,30,b=3,false)); //23
		pool.add(new ActiveItem("Conniver Kunai",s=7,70,b=5,false)); //24
		pool.add(new ActiveItem("Direct Hit",s=10,20,b=1,false)); //25
		pool.add(new PassiveItem("Pot Lid",s=4,30,b=4)); //26
		pool.add(new ActiveItem("Syrup of Ipecac",s=1,3,b=0,true)); //27
		pool.add(new PassiveItem("Immune Deficiency",s=0,100,b=0)); //28
		pool.add(new PassiveItem("Solar Panel Armour",s=20,125,b=4)); //29
		pool.add(new ActiveItem("Weighted Coin",s=1,3,b=5,false)); //30
		pool.add(new ActiveItem("Taunton Dart Gun",s=5,7,b=5,false)); //31
		pool.add(new PassiveItem("Booster Shot",s=0,25,b=0)); //32
		pool.add(new ActiveItem("Blowgun",s=2,1,b=0,false)); //33
		pool.add(new ActiveItem("Shotgun",s=8,38,b=1,false)); //34
		pool.add(new ActiveItem("Poison Pen",s=1,6,b=8,false)); //35
		pool.add(new PassiveItem("Law License",s=1,256,b=3)); //36
		pool.add(new ActiveItem("$15 Spear",s=18,96,b=4,false)); //37
		pool.add(new PassiveItem("Balaclava",s=2,125,b=5)); //38
		pool.add(new ActiveItem("Sewing Needle",s=1,9,b=5,false)); //39
		pool.add(new ActiveItem("Plan C",s=1,1,b=0,false)); //40
		pool.add(new PassiveItem("Nested Universe",s=0,1,b=0)); //41
		pool.add(new PassiveItem("Swedish Passport",s=1,100,b=0)); //42
		pool.add(new ActiveItem("Pinot Noir",s=5,5,b=8,false)); //43
		pool.add(new PassiveItem("Thick Skin",s=0,256,b=0)); //44
		pool.add(new PassiveItem("Heart Container",s=0,1,b=7)); //45
		pool.add(new ActiveItem("Whiskey",s=1,10,b=1,true)); // 46
	}
	public Item get(int index) {
		if(pool.get(index).isPassive()) {
			return new PassiveItem(pool.get(index));
		}
		else {
			return new ActiveItem(pool.get(index));
		}
	}
	public int size() {
		return pool.size();
	}
}
