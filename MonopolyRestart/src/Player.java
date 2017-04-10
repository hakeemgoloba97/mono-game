
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Player {

	final public JLabel players;
	int playerCount = 0;
	String name;
	static int playerNumber= 0;
	int position = 0;
	private boolean hasGOOJ = false;
	String[] choices = {"2", "3", "4", "5", "6" };
	int turnsJail = 0;
	int xCoOrdinate;
	int yCoOrdinate;
	int balance;
	private String[] TokenImage = {"/resources/Token1.png","/resources/Token2.png","/resources/Token3.png",
			"/resources/Token4.png","/resources/Token5.png","/resources/Token6.png"	};
	boolean isBankrupt;

	//Constructor
	ArrayList<Property> properties = new ArrayList<Property>();
	private boolean isJailed;
	public Player()
	{
		name = "";
		Image token = new ImageIcon(getClass().getResource(TokenImage[playerNumber])).getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH);
		ImageIcon tokenPicture = new ImageIcon(token);
		players = new JLabel();
		players.setIcon(tokenPicture);
		position = 0;
		balance = 1500;
		xCoOrdinate = 750;
		yCoOrdinate = 750;
		playerNumber++;
		playerNumber = playerNumber%5;
		playerCount = playerNumber;
		isJailed = false;
		isBankrupt = false;
		return;
	}

	public void setName(String n)
	{
		name = n;
	}
	public int getNumberOfPlayers()
	{
		String input = (String) JOptionPane.showInputDialog(null, "How many are playing",
				"Enter Number Of Players", JOptionPane.QUESTION_MESSAGE, null,         
				choices, // Array of choices
				choices[0]); // Initial choice
		return playerCount = Integer.parseInt(input);
	}

	public boolean inJail(){
		return isJailed;
	}
	public void assignPlayerBalance(int money)
	{
		balance = money;
	}
	public int getPlayerBalance(){
		return balance;
	}
	public String getPlayerName() {
		return name;
	}

	public void addToBalance(int value){
		balance = balance + value;
	}
	public void payRent(){
			balance = balance - Board.getProperty(getPosition()).getRent();
	}
	public void withdraw(int value){
		balance = balance - value;
	}

	public void move( int i) throws InterruptedException{

		for(int j = 0; j < i; j++){
			position++;
			if(xCoOrdinate > 65 && yCoOrdinate  == 750){ //bottom row works
				xCoOrdinate = xCoOrdinate - 70;	
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);
			}
			else if(yCoOrdinate > 75 && xCoOrdinate < 750){
				yCoOrdinate = yCoOrdinate - 70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);
			}
			else if (xCoOrdinate < 750 && yCoOrdinate == 50 ){//moves along top row
				xCoOrdinate = xCoOrdinate +70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);

			}
			else if(xCoOrdinate == 750 && yCoOrdinate < 750){
				yCoOrdinate = yCoOrdinate + 70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);

			}
			Thread.sleep(150);
			if(!isJailed){
			if(position%40 == 0){
				addToBalance(200);
				InfoPanel.displayString("You passed GO, 200 has been added to your account");
			}
			}
		}
		if(!isJailed){
		if(position%40 == 4){
			withdraw(200); 
			InfoPanel.displayString("You were attacked by fluffy 200 has been deducted from you account");
		}
		if(position%40 == 38){
			withdraw(75); 
			InfoPanel.displayString("You crashed into the whomping willow 75 has been deducted from you account");
		}
		if(position%40 == 30){
			InfoPanel.displayString("You have been sent to Azkaban");
			sendToJail();
		}
		}
	}

	public void moveBack( int i) throws InterruptedException{

		for(int j = 0; j < i; j++){
			position--;
			if(xCoOrdinate > 65 && yCoOrdinate  == 750){ //bottom row works
				xCoOrdinate = xCoOrdinate + 70;	
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);
			}
			else if(yCoOrdinate > 75 && xCoOrdinate < 750){
				yCoOrdinate = yCoOrdinate + 70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);
			}
			else if (xCoOrdinate < 750 && yCoOrdinate == 50 ){//moves along top row
				xCoOrdinate = xCoOrdinate -70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);

			}
			else if(xCoOrdinate == 750 && yCoOrdinate < 750){
				yCoOrdinate = yCoOrdinate - 70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);

			}
			Thread.sleep(150);
			if(!isJailed){
			if(position%40 == 0){
				addToBalance(200);
				InfoPanel.displayString("You passed GO, 200 has been added to your account");
			}
			}
		}
		if(!isJailed){
		if(position%40 == 4){
			withdraw(200); 
			InfoPanel.displayString("You were attacked by fluffy 200 has been deducted from you account");
		}
		if(position%40 == 38){
			withdraw(75); 
			InfoPanel.displayString("You crashed into the whomping willow 75 has been deducted from you account");
		}
		if(position%40 == 30){
			InfoPanel.displayString("You have been sent to Azkaban");
			sendToJail();
		}
		}

	}
	
	public void boughtProperty (Property property) {
		property.setOwner(playerCount);
		properties.add(property);
		return;
	}

	public String currentLocation(){
		if(Board.isProperty(getPosition())){
			return  "You have Arrived at: " 
					+ Board.getProperty(getPosition()).getName() + "\n"
					+ "Rent is : " +
					+ Board.getProperty(getPosition()).getRent() + "\n"
					+ "This is valued at : " +
					+ Board.getProperty(getPosition()).getValue();
		}
		else{
			return " you have arrived at: " +
					Board.getSquare(getPosition()).getName();
		}
	}
	public Property getLatestProperty () {
		return properties.get(properties.size()-1);
	}

	public ArrayList<Property> getProperties () {
		return properties;
	}

	public int getPosition(){
		return position;
	}

	public void sendToJail() throws InterruptedException {
			isJailed = true;
			while(position%40 != 10){
			safeMove(1);
		}
	}

	public boolean jailed(){
		return isJailed;
	}

	public void freeJail(){
		if(isJailed == true){
			isJailed = false;
		}
	}

	public int getAssets () {
		int assets = balance;
		for (Property property: properties) {
			assets = assets + property.getValue();
		}
		return assets;
	}

	public boolean hasGOOJ(){
		return hasGOOJ;
	}

	public void takeGOOJ(){
		hasGOOJ = false;
	}
	
	public void GetGOOJ(){
		hasGOOJ = true;
	}
	
	public void safeMove( int i) throws InterruptedException{

		for(int j = 0; j < i; j++){
			position++;
			if(xCoOrdinate > 65 && yCoOrdinate  == 750){ //bottom row works
				xCoOrdinate = xCoOrdinate - 70;	
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);
			}
			else if(yCoOrdinate > 75 && xCoOrdinate < 750){
				yCoOrdinate = yCoOrdinate - 70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);
			}
			else if (xCoOrdinate < 750 && yCoOrdinate == 50 ){//moves along top row
				xCoOrdinate = xCoOrdinate +70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);

			}
			else if(xCoOrdinate == 750 && yCoOrdinate < 750){
				yCoOrdinate = yCoOrdinate + 70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);

			}
			Thread.sleep(150);
		}
	}

	public void safeMoveBack(int i) throws InterruptedException {
		
		for(int j = 0; j < i; j++){
			position--;
			if(xCoOrdinate > 65 && yCoOrdinate  == 750){ //bottom row works
				xCoOrdinate = xCoOrdinate + 70;	
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);
			}
			else if(yCoOrdinate > 75 && xCoOrdinate < 750){
				yCoOrdinate = yCoOrdinate + 70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);
			}
			else if (xCoOrdinate < 750 && yCoOrdinate == 50 ){//moves along top row
				xCoOrdinate = xCoOrdinate -70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);

			}
			else if(xCoOrdinate == 750 && yCoOrdinate < 750){
				yCoOrdinate = yCoOrdinate - 70;
				players.setBounds(playerNumber*1 + xCoOrdinate,playerNumber*1 +yCoOrdinate, 15,15);

			}
			Thread.sleep(150);
		}
	}

}
