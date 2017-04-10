public class Board {
	
	// Board data from http://monopoly.wikia.com/wiki/Monopoly
	// Note, a property is a site, utility or station
	
	public static final int NUM_SQUARES = 40;
		
	static Square[] squares = new Square[NUM_SQUARES];
	
	Board () {
		squares[0] = new Square("Go");
		squares[1] = new Property("Cupboard Under The Stairs",1, 60, new int[] {2,10,30,90,160,250},30,50,50,0,false);
		squares[2] = new ChestCard("Ministry of Magic","Community Chest");
		squares[3] = new Property("Shrieking Shack",3, 60, new int[] {4,20,60,180,320,450}, 30, 50, 50,0,false);
		squares[4]  = new Square("Attacked By Fluffy");
		squares[5] = new Property("Hogwarts Express",5, 200, new int[] {25,50,100,200,200,200}, 50,50, 50,-1,false);
		squares[6] = new Property("Hagrids Hut",6, 100,new int[] {6,30,90,270,400,550}, 50, 50, 50,1,false);
		squares[7] = new ChanceCard("Deathly Hallow","Chance");
		squares[8] = new Property("Hog's Head Inn",8, 100, new int[] {6,30,90,270,400,550}, 50, 50, 50,1,false);
		squares[9] = new Property("Honey Dukes",9,120, new int[] {8,40,100,300,450,600}, 60, 50, 50,1,false);
		squares[10] = new Square("Azkaban");
		squares[11] = new Property("The Burrow",11, 140, new int[] {10,50,150,450,625,750}, 70, 100, 100,2,false);
		squares[12] = new Property("Owl Post",12, 150, new int[] {4,10,0,0,0,0}, 75, 0, 0,-1,false);
		squares[13] = new Property("The Leaky Cauldron",13, 140, new int[] {10,50,150,450,625,750}, 70, 100, 100,2,false);
		squares[14] = new Property("Quidditch Pitch",14, 160, new int[] {12,60,180,500,700,900}, 80, 100, 100,2,false);
		squares[15] = new Property("Durmstrang Ship",15, 200, new int[] {25,50,100,200,200,200}, 0, 0, 0,-1,false);
		squares[16] = new Property("Weasly Wizard Wheezes",16, 180, new int[] {14,70,200,550,750,950}, 90, 100, 100,3,false);
		squares[17] = new ChestCard("Ministry of Magic","Community Chest");
		squares[18] = new Property("Shell Cottage",18, 180, new int[] {14,70,200,550,750,950}, 90, 100, 100,3,false);
		squares[19] = new Property("Great Hall",19, 200, new int[] {16,80,220,600,800,1000}, 100, 100, 100,3,false);
		squares[20] = new Square("Free Socks");
		squares[21] = new Property("The Three Broomsticks",21, 220, new int[] {18,90,250,700,875,1050}, 110, 150, 150,4,false);
		squares[22] = new ChanceCard("Deathly Hallow","Chance");
		squares[23] = new Property("Armstrong Tower",23, 220, new int[] {18,90,250,700,875,1050}, 110, 150, 150,4,false);
		squares[24]  = new Property("Flourish And Blotts",24, 240, new int[] {20,100,300,750,925,1100}, 120, 150, 150,4,false);
		squares[25] = new Property("Flying Car",25, 200, new int[] {25,50,100,200,200,200}, 0, 0, 0,-1,false);
		squares[26] = new Property("Ollivanders Wand Shop",26, 260, new int[] {22,110,330,800,975,1150}, 130, 150, 150,5,false);
		squares[27] = new Property("12 Grimmauld Place",27, 260, new int[] {22,110,330,800,975,1150}, 130, 130, 150,5,false);
		squares[28] = new Property("Floo Network",28, 150, new int[] {4,10,0,0,0,0}, 0, 0, 0,-1,false);
		squares[29] = new Property("Chamber Of Secrets",29, 280, new int[] {22,120,360,850,1025,1200}, 140, 150, 150,5,false);
		squares[30] = new Square("Go To Azkaban");
		squares[31] = new Property("LeStrange Vault",31, 300, new int[] {26,130,390,900,1100,1275}, 150, 200, 200,6,false);
		squares[32] = new Property("Hogwarts Library",32, 300, new int[] {26,130,390,900,1100,1275}, 150, 200, 200,6,false);
		squares[33] = new ChestCard("Ministry of Magic","Community Chest");
		squares[34] = new Property("Dumbledore's Office",34, 320, new int[] {28,150,450,1000,1200,1400}, 160, 200, 200,6,false);
		squares[35] = new Property("Knight Bus",35, 200, new int[] {25,50,100,200,200,200}, 0, 0, 0,-1,false);
		squares[36] = new ChanceCard("Deathly Hallows","Chance");
		squares[37] = new Property("Room of Requirement",37, 350, new int[] {35,175,500,1100,1300,1500}, 175, 200, 200,7,false);
		squares[38] = new Square("Crashed into Whomping Willow");
		squares[39] = new Property("Gringotts Wizarding Bank",39, 400, new int[] {50,200,600,1400,1700,2000}, 200, 200, 200,7,false);
		return;
	}
	
	public static Square getSquare (int index) {
		return squares[index%40];
	}

	public static Property getProperty (int index) {
		return (Property) squares[index%40];
	}
	
	public static boolean isProperty (int index) {
		return squares[index%40] instanceof Property;
	}
	
	public static boolean isChance(int index){
		return squares[index%40] instanceof ChanceCard;
	}
	
	public static boolean isChest(int index){
		return squares[index%40] instanceof ChestCard;
	}
	
	
	
}