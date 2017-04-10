
import java.util.ArrayList;

public class Property extends Square {

	private boolean isOwned;
	private int value;
	private int rent[];
	private int owner = -1;
	private boolean isMortgaged;
	private int mortgageValue;
	private int housePrice;
	public int index;
	public int colour;
	private int numberOfHouses = 0;
	static ArrayList<Property>Brown=new ArrayList<Property>();
	static ArrayList<Property>LightBlue=new ArrayList<Property>();
	static ArrayList<Property>DarkBlue=new ArrayList<Property>();
	static ArrayList<Property>Purple=new ArrayList<Property>();
	static ArrayList<Property>Orange=new ArrayList<Property>();
	static ArrayList<Property>Red=new ArrayList<Property>();
	static ArrayList<Property>Yellow=new ArrayList<Property>();
	static ArrayList<Property>Green=new ArrayList<Property>();
	private static final float MORTGAGE_PREMIUM = 1.1f;

	Property(String name,int index, int value, int[] rent, int mortgageValue,int housePrice, int hotelPrice,int colour,boolean isMortgaged) {
		super(name);
		this.value = value;
		this.rent = rent;
		this.housePrice = housePrice;
		this.index=index;
		this.mortgageValue = mortgageValue;
		this.colour = colour;
		isOwned = false;
		isMortgaged=false;
		return;
	}

	public static void assigncolour() {
		if(Brown.size() == 0 ){
			Brown.add((Property) Board.squares[1]);
			Brown.add((Property) Board.squares[3]);
		}
		if(LightBlue.size() ==0 ){
			LightBlue.add((Property) Board.squares[6]);
			LightBlue.add((Property) Board.squares[8]);
			LightBlue.add((Property) Board.squares[9]);	
		}
		if(Purple.size() ==0 ){
			Purple.add((Property) Board.squares[11]);
			Purple.add((Property) Board.squares[13]);
			Purple.add((Property) Board.squares[14]);
		}	
		if(Orange.size() ==0 ){
			Orange.add((Property) Board.squares[16]);
			Orange.add((Property) Board.squares[18]);
			Orange.add((Property) Board.squares[19]);	
		}
		if(Red.size() ==0 ){
			Red.add((Property) Board.squares[21]);
			Red.add((Property) Board.squares[23]);
			Red.add((Property) Board.squares[24]);
		}
		if(Yellow.size() ==0 ){
			Yellow.add((Property) Board.squares[26]);
			Yellow.add((Property) Board.squares[27]);
			Yellow.add((Property) Board.squares[29]);
		}
		if(Green.size() ==0 ){
			Green.add((Property) Board.squares[31]);
			Green.add((Property) Board.squares[32]);
			Green.add((Property) Board.squares[34]);
		}

		if(DarkBlue.size() ==0 ){
			DarkBlue.add((Property) Board.squares[37]);
			DarkBlue.add((Property) Board.squares[39]);
		}

	}
	public static boolean colourcollection(Property prop,ArrayList<Property> p)	
	{
		
		System.out.println(Brown);
		System.out.println(prop.getName());
		System.out.println(prop.colour);

		if(prop.colour == 0)
		{
			return p.containsAll(Brown);
		}
		if(prop.colour == 1)
		{
			return p.containsAll(LightBlue);
		}
		if(prop.colour == 2)
		{
			return p.containsAll(Purple);
		}
		if(prop.colour == 3)
		{
			return p.containsAll(Orange);
		}
		if(prop.colour == 4)
		{
			return p.containsAll(Red);
		}
		if(prop.colour == 5)
		{
			return p.containsAll(Yellow);
		}
		if(prop.colour == 6)
		{
			return p.containsAll(Green);
		}
		if(prop.colour == 7)
		{
			return p.containsAll(DarkBlue);
		}


		return false;
	}

	public int getRent(Property prop,Player x, int dice) { 
		//check how many houses are on property
		//check if utility or train station 
		//then do mulitpliers
		int rent=0;
		int stationCount=0;
		ArrayList<Property>Stations=new ArrayList<Property>();
		Stations.add((Property) Board.squares[5]);
		Stations.add((Property) Board.squares[15]);
		Stations.add((Property) Board.squares[25]);
		Stations.add((Property) Board.squares[35]);
		ArrayList<Property>Utilities=new ArrayList<Property>();
		Utilities.add((Property)Board.squares[12]);
		Utilities.add((Property)Board.squares[28]);

		//Stations
		if(prop.getName().equals(Board.squares[5].getName())||prop.getName().equals(Board.squares[15].getName())||prop.getName().equals(Board.squares[25].getName())||prop.getName().equals(Board.squares[35].getName()))
		{
			if(x.getProperties().contains((Property)Board.squares[5]))
			{
				stationCount++;	
			}
			if(x.getProperties().contains((Property)Board.squares[15]))
			{
				stationCount++;	
			}
			if(x.getProperties().contains((Property)Board.squares[25]))
			{
				stationCount++;	
			}
			if(x.getProperties().contains((Property)Board.squares[35]))
			{
				stationCount++;
			}

			if(stationCount==1)
			{
				rent=prop.rent[0];
			}
			else if(stationCount==2)
			{
				rent=prop.rent[1];

			}
			else if(stationCount==3)
			{
				rent=prop.rent[2];
			}
			else if(stationCount==4)
			{
				rent=prop.rent[3];
			}

		}
		//Utilities
		else if(prop.getName().equals(Board.squares[12].getName())||prop.getName().equals(Board.squares[28].getName()))
		{
			if(x.getProperties().contains((Property)Board.squares[12]) && x.getProperties().contains((Property)Board.squares[28]))
			{
				rent = dice*10;
			}
			else
			{
				rent = dice*4;		 
			}
		}
		else
		{
			rent=prop.rent[numberOfHouses];	
		}
		return rent;
	}


	public int getHousePrice(){
		return housePrice;
	}


	public int getValue () {
		return value;
	}

	public int getRent () {
		return rent[numberOfHouses];
	}

	public boolean isOwned () {
		return isOwned;
	}

	public void setOwner (int user) {
		owner = user;
		isOwned = true;
		return;
	}

	public int getOwner () {
		return owner;
	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	public void BuildHouse(int n){
		numberOfHouses += n;
	}
	public int getHouseNumber()
	{
		return numberOfHouses;
	}
	public void DemolishHouses(int n){
		numberOfHouses -=n;
	}

	public void setMortgaged() {
		isMortgaged = true;
	}

	public int getMortgageValue() {
		return mortgageValue;
	}

	public int getMortgageRemptionPrice() {
		return (int) (((float) mortgageValue) * MORTGAGE_PREMIUM);
	}

	public void setNotMortgaged() {
		isMortgaged = false;		
	}

	public int propColour(){
		return colour;
	}
}

