
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Commands{
	static ArrayList <Player> players;
	InfoPanel infoPanelPanel;
	BoardAndCommandBox commandPanel;
	Roll roll;
	Board board = new Board();
	Property property;
	public Commands(ArrayList <Player> player){
		Commands.players = player;
	}
	int value = 0;
	int doubleRoll = 0;
	boolean rollDone = false;
	boolean rentOwed = false;
	boolean rentPaid = false;
	static boolean rolls = false;
	int rent_paid = 0;
	public void bankrupt() throws InterruptedException{
		InfoPanel.displayString(players.get(GameRunner.user%players.size()).getPlayerName() + " is now bankrupt");
		for(Property a: players.get(GameRunner.user%players.size()).properties){
			a.setOwner(-1);
			a.DemolishHouses(a.getHouseNumber());
		}
		players.remove(players.get(GameRunner.user%players.size()));
		if (players.size()==1) {
			decideWinner();
		}
		InfoPanel.displayString("Your game is now over it will close in 30 seconds");
		Thread.sleep(30000);
	}

	public void Command(String string) throws InterruptedException {
		GameRunner.user = GameRunner.user%players.size()%players.size();
		MainWindow.displayString(""+ players.get(GameRunner.user%players.size()).getPlayerName()+ ">> "+ MainWindow.getCommand() + "");
		if(!players.get(GameRunner.user%players.size()).inJail()){
			if(string.equals("help")||string.equals("redeem")||string.equals("mortgage")||string.equals("brupt")||string.equals("move o")||string.equals("cheat p")||string.equals("roll")||string.equals("demolish")||string.equals("balance")||string.equals("buy")||string.equals("done")||string.equals("property")||string.equals("build") || string.equals("quit")){
				switch(string.toLowerCase())
				{
				case "roll":
					if(players.get(GameRunner.user).getPlayerBalance() > 0){
						if (!rollDone) {
							if (!rentOwed) {
								Roll.roll();
								value = Roll.getTotal();
								InfoPanel.displayString(players.get(GameRunner.user%players.size()).getPlayerName() + " rolled " + value);
								players.get(GameRunner.user%players.size()).move(value);
								if(Board.isProperty(players.get(GameRunner.user%players.size()).getPosition())){
									if(Board.getProperty(players.get(GameRunner.user%players.size()).getPosition()).getOwner() == -1){
										InfoPanel.displayString("The owner of " + Board.getProperty(players.get(GameRunner.user%players.size()).getPosition()).getName() + " is " +
												" currently nobody " + "");
									}
									else{
										InfoPanel.displayString("The owner of " + Board.getProperty(players.get(GameRunner.user%players.size()).getPosition()).getName() + " is " +
												players.get(Board.getProperty(players.get(GameRunner.user%players.size()%players.size()).getPosition()).getOwner()%players.size()).getPlayerName() + "");
									}
								}
								else{
									InfoPanel.displayString(players.get(GameRunner.user%players.size()).getPlayerName() + "" + players.get(GameRunner.user%players.size()).currentLocation());
								}
								if(Board.isChest(players.get(GameRunner.user%players.size()).getPosition())){
									ChestCard.drawChestCard(players.get(GameRunner.user%players.size()));
								}
								if(Board.isChance(players.get(GameRunner.user%players.size()).getPosition())){
									ChanceCard.drawChanceCard(players.get(GameRunner.user%players.size()));
								}	
								if (Board.isProperty(players.get(GameRunner.user%players.size()).getPosition()) && 
										Board.getProperty(players.get(GameRunner.user%players.size()).getPosition()).isOwned() &&
										!(Board.getProperty(players.get(GameRunner.user%players.size()%players.size()).getPosition()).getOwner()%players.size() == players.get(GameRunner.user%players.size()).playerCount) &&
										!(Board.getProperty(players.get(GameRunner.user%players.size()).getPosition()).isMortgaged())) {

									rentOwed = true;
									property = Board.getProperty(players.get(GameRunner.user%players.size()).position%40);
									if (players.get(GameRunner.user%players.size()).getPlayerBalance() >= (property.getRent(property, players.get(property.getOwner()%players.size()),value)))
									{
										players.get(GameRunner.user%players.size()).withdraw(property.getRent(property, players.get(property.getOwner()%players.size()),value));
										players.get(property.getOwner()%players.size()).addToBalance(property.getRent(property, players.get(property.getOwner()%players.size()),value));
										InfoPanel.displayString(players.get(GameRunner.user%players.size()).getPlayerName()+  " has paid " + players.get(property.getOwner()%players.size()).getPlayerName() +"  "+  property.getRent(property, players.get(property.getOwner()%players.size()),value));
										rentPaid = true;	
										rentOwed = false;
									} else {
										InfoPanel.displayString("Insufficient funds");	
										InfoPanel.displayString("You have been declared as bankrupt ");
										bankrupt();
										players.remove(players.get(GameRunner.user%players.size()));
									}
								}
								else {
									rentOwed = false;
								}
								if (!(Roll.dieValue1 == Roll.dieValue2)) {
									doubleRoll = 0;
									rollDone = true;
								}else {
									doubleRoll++;
									InfoPanel.displayString("You rolled a double");
									if(doubleRoll == 3)
									{
										players.get(GameRunner.user%players.size()).sendToJail();
										InfoPanel.displayString("You rolled three doubles you have been sent to jail");
										doubleRoll = 0;
										rollDone = true;
										break;
									}
								}
							}
						}else{
							InfoPanel.displayString("You already rolled");
						}
					}else {
						InfoPanel.displayString("Your balance is " + players.get(GameRunner.user%players.size()).getPlayerBalance() +" you can not move");
						if(players.get(GameRunner.user%players.size()).getAssets() >= 0 || players.get(GameRunner.user%players.size()).getAssets() <= 0){
							int sellableHouses = 0;
							int mortgagableProp = 0;
							if(players.get(GameRunner.user%players.size()).properties.size() >= 0){
								for(Property p: players.get(GameRunner.user%players.size()).properties)
								{
									if(!p.isMortgaged()){
										mortgagableProp++;
									}
								}
								if(mortgagableProp > 0){
									InfoPanel.displayString("You can mortage up to " + mortgagableProp + " property");
								}
								for(Property p: players.get(GameRunner.user%players.size()).properties)
								{
									sellableHouses+= p.getHouseNumber();
								}
								if(sellableHouses > 0){
									InfoPanel.displayString("You can demolish up to " + sellableHouses + " houses");
								}
							}						
							if(sellableHouses == 0 && mortgagableProp ==0){
								InfoPanel.displayString("There is nothing you can do. you have been declared as bankrupt");
								bankrupt();
								players.remove(players.get(GameRunner.user%players.size()));
							}
						}
					}
					break;
				case "buy" :
					if (Board.isProperty(players.get(GameRunner.user%players.size()).getPosition())) {
						Property property = Board.getProperty(players.get(GameRunner.user%players.size()).getPosition());
						if (!property.isOwned()) {
							if (players.get(GameRunner.user%players.size()).getPlayerBalance() >= property.getValue()) {				
								players.get(GameRunner.user%players.size()).withdraw(property.getValue());
								InfoPanel.displayString( property.getValue() + " has been withdrawn from " + players.get(GameRunner.user%players.size()).getPlayerName());
								players.get(GameRunner.user%players.size()).boughtProperty(property);
							} else {
								InfoPanel.displayString("Insufficient funds");	
							}
						} else {
							InfoPanel.displayString("This property is already owned");	
						}
					} else {
						InfoPanel.displayString("This is not a property");									
					}
					break;
				case "balance" :
					InfoPanel.displayString(players.get(GameRunner.user%players.size()).getPlayerName() + " current balance is " + players.get(GameRunner.user%players.size()).getPlayerBalance());
					break;
				case "property" :
					InfoPanel.displayString(players.get(GameRunner.user%players.size()).getPlayerName() 
							+ " currently owns: " +players.get(GameRunner.user%players.size()).getProperties());
					break;
				case "help" :
					InfoPanel.displayString("Your commands available are: roll, end turn, help, mortgage, bankrupt, buy, quit ");
					break;
				case "done" :
					if(players.get(GameRunner.user).getPlayerBalance() > 0){
						if (rollDone) {
							InfoPanel.displayString( players.get(GameRunner.user%players.size()%players.size()).getPlayerName() +" has ended turn");
							GameRunner.user++;
							MainWindow.displayString(" It is " + players.get(GameRunner.user%players.size()).getPlayerName() + " " + "turn" + "\n");
						}else if(players.get(GameRunner.user%players.size()).isBankrupt){
							players.remove(players.get(GameRunner.user%players.size()));
						} else {
							InfoPanel.displayString("You have not rolled");
						}
					}else {
						InfoPanel.displayString("Your balance is " + players.get(GameRunner.user%players.size()).getPlayerBalance() +" you can not move");
						if(players.get(GameRunner.user%players.size()).getAssets() >= 0 || players.get(GameRunner.user%players.size()).getAssets() <= 0){
							int sellableHouses = 0;
							int mortgagableProp = 0;
							if(players.get(GameRunner.user%players.size()).properties.size() >= 0){
								for(Property p: players.get(GameRunner.user%players.size()).properties)
								{
									if(!p.isMortgaged()){
										mortgagableProp++;
									}
								}
								if(mortgagableProp > 0){
									InfoPanel.displayString("You can mortage up to " + mortgagableProp + " property");
								}
								for(Property p: players.get(GameRunner.user%players.size()).properties)
								{
									sellableHouses+= p.getHouseNumber();
								}
								if(sellableHouses > 0){
									InfoPanel.displayString("You can demolish up to " + sellableHouses + " houses");
								}
							}						
							if(sellableHouses == 0 && mortgagableProp ==0){
								InfoPanel.displayString("There is nothing you can do. you have been declared as bankrupt");
								bankrupt();
								players.remove(players.get(GameRunner.user%players.size()));
							}
						}
					}
					rollDone = false;
					break;
				case "build" : 
					Build(players.get(GameRunner.user%players.size()));
					break;
				case "demolish":
					Demolish(players.get(GameRunner.user));
					break;
				case "quit":
					InfoPanel.displayString( players.get(GameRunner.user%players.size()%players.size()).getPlayerName() +" has quit the game");
					decideWinner();	
					InfoPanel.displayString("This will close in 30 seconds");
					Thread.sleep(30000);
					System.exit(0);
					break;
				case "mortgage":
					Mortgage(players.get(GameRunner.user%players.size()));
					break;
				case "redeem":
					Reedem(players.get(GameRunner.user%players.size()));
					break;
				}
			}else{
				InfoPanel.displayString("Not a valid command");
			}
		}else{
			if(string.equals("card")||string.equals("help")||string.equals("roll")||string.equals("balance")||string.equals("done")||string.equals("pay")){
				if(	players.get(GameRunner.user).turnsJail == 3){
					InfoPanel.displayString("You have spent 3 turns in jail 50 will be deducted from your account");
				}
				switch(string.toLowerCase())
				{
				case "roll":
					if (!rollDone) {
						Roll.roll();
						if ((Roll.dieValue1 == Roll.dieValue2)) {
							players.get(GameRunner.user%players.size()).freeJail();
							InfoPanel.displayString("You rolled a double you are now free from jail");
							InfoPanel.displayString("You rolled " + Roll.getTotal());
							players.get(GameRunner.user%players.size()).move(Roll.getTotal());
						}
						else{
							GameRunner.user%=players.size();
							InfoPanel.displayString("You have rolled you and failed to get out, your turn in now over");
							rollDone = true;
							InfoPanel.displayString("It is now " + players.get(GameRunner.user+1%players.size()).getPlayerName() + " turn");
							players.get(GameRunner.user%players.size()).turnsJail++;
							GameRunner.user++;
							rollDone = false;

						}
					}	
					rollDone = false;
					break;
				case "pay":
					if(players.get(GameRunner.user).turnsJail != 3){
						players.get(GameRunner.user).withdraw(50);
						InfoPanel.displayString("50 has been withdrawn from your balance you are now free to roll");
						players.get(GameRunner.user%players.size()).freeJail();
						players.get(GameRunner.user).turnsJail = 0;
					}
					else if(players.get(GameRunner.user).turnsJail == 3){
						players.get(GameRunner.user).withdraw(50);
						players.get(GameRunner.user%players.size()).freeJail();
						players.get(GameRunner.user).turnsJail = 0;
					}
					break;
				case "done":
					InfoPanel.displayString( players.get(GameRunner.user%players.size()%players.size()).getPlayerName() +" has ended turn");
					GameRunner.user++;
					MainWindow.displayString(" It is " + players.get(GameRunner.user%players.size()).getPlayerName() + " " + "turn" + "\n");
					rollDone = false;
					break;
				case "help" :
					InfoPanel.displayString("You are currently in jail commands available are: roll, pay or card ");
					break;
				case "card" :
					if(players.get(GameRunner.user).hasGOOJ()){
						players.get(GameRunner.user).takeGOOJ();
						InfoPanel.displayString("You are now free from jail");
						players.get(GameRunner.user).freeJail();
						players.get(GameRunner.user).turnsJail = 0;
						GameRunner.user++;
						break;
					}
					else{
						InfoPanel.displayString("You do not have GOOJ card");
						break;
					}
				case "balance" :
					InfoPanel.displayString(players.get(GameRunner.user%players.size()).getPlayerName() + " current balance is " + players.get(GameRunner.user%players.size()).getPlayerBalance());
					break;
				}
			}
			else{
				InfoPanel.displayString("Valid commands in jail are : help, card, done ,balance ,roll and pay");
			}
		}
	}

	private void Reedem(Player player) {
		ArrayList<Property> propertyList = player.properties;
		int size=propertyList.size();
		Property prop;
		String[] choices=new String[size];
		for(int count=0;count<size;count++)
		{
			if(propertyList.get(count).isMortgaged()){
				choices[count]=propertyList.get(count).getName();
			}
		}
		if(propertyList.size()>0)
		{
			String chosen=JOptionPane.showInputDialog(null, "Which property would you like to build on?","The Choice", JOptionPane.OK_OPTION, null,choices, choices[0]).toString();
			prop=propertyList.get(0);
			for(int count1=0;count1<size;count1++)
			{
				Boolean found = chosen.equals(propertyList.get(count1).getName());
				if(found)
				{
					prop=propertyList.get(count1);
					break;
				}
			}
			if (prop.isMortgaged()) {
				int price = prop.getMortgageRemptionPrice();
				if (players.get(GameRunner.user%players.size()).getPlayerBalance() >= price) {
					prop.setNotMortgaged();
					players.get(GameRunner.user%players.size()).withdraw(price);
					InfoPanel.displayString(players.get(GameRunner.user%players.size()).getPlayerName() + " redeemed " + prop.getName() );
				} else {
					InfoPanel.displayString("Insufficient funds ");
				}
			} else {
				InfoPanel.displayString("Property is not mortgaged ");
			}
		}
	}

	public void Mortgage(Player player) {
		ArrayList<Property> propertyList = player.properties;
		int size=propertyList.size();
		Property prop;
		String[] choices=new String[size];
		for(int count=0;count<size;count++)
		{
			choices[count]=propertyList.get(count).getName();
		}
		if(propertyList.size()>0)
		{
			String chosen=JOptionPane.showInputDialog(null, "Which property would you like to build on?","The Choice", JOptionPane.OK_OPTION, null,choices, choices[0]).toString();
			prop=propertyList.get(0);
			for(int count1=0;count1<size;count1++)
			{
				Boolean found = chosen.equals(propertyList.get(count1).getName());
				if(found)
				{
					prop=propertyList.get(count1);
					break;
				}
			}
			if (!prop.isMortgaged()) {
				if(prop.getHouseNumber()== 0){
					prop.setMortgaged();
					players.get(GameRunner.user%players.size()).addToBalance(prop.getMortgageValue());
					InfoPanel.displayString("You have mortgaged " + prop.getName() + " for " + prop.getMortgageValue() + " ");
				}
				else{
					InfoPanel.displayString("This property has houses so you will have to demolish them first");
				}
			}else {
				InfoPanel.displayString("This has already been mortgaged");
			}
		}
	}

	public void Build(Player inPlayer){
		ArrayList<Property> propertyList = inPlayer.properties;
		int size=propertyList.size();
		Property prop;
		String[] choices=new String[size];
		for(int count=0;count<size;count++)
		{
			choices[count]=propertyList.get(count).getName();
		}
		if(propertyList.size()>0)
		{
			String chosen=JOptionPane.showInputDialog(null, "Which property would you like to build on?","The Choice", JOptionPane.OK_OPTION, null,choices, choices[0]).toString();
			prop=propertyList.get(0);
			for(int count1=0;count1<size;count1++)
			{
				Boolean found = chosen.equals(propertyList.get(count1).getName());
				if(found)
				{
					prop=propertyList.get(count1);
					break;
				}
			}
			int n = 0; 
			Property.assigncolour();
			if(Property.colourcollection(prop, propertyList))
			{
				players.get(GameRunner.user%players.size());
				if(prop.getOwner() == Player.playerNumber){
					InfoPanel.displayString("How Many Would you like to build");
					BoardAndCommandBox.inputString();
					String numberToBuild = BoardAndCommandBox.getString();
					InfoPanel.displayString(numberToBuild);
					n = Integer.parseInt(numberToBuild);
					InfoPanel.displayString("It cost " + prop.getHousePrice()+ " to build on "+ chosen);
				}
				else{
					InfoPanel.displayString("This is not your property");
				}
				if(n<=4&&prop.getHouseNumber()+n<=4)
				{
					prop.BuildHouse(n);
					inPlayer.withdraw(n*prop.getHousePrice());
					InfoPanel.displayString("You have built "+n+" houses on your property: "+prop.getName());
				}
				else if(n==5 ||prop.getHouseNumber()+n==5) // RENT IS DONE ON NUMBER OF HOUSES SO DO NOT DEMOLISH HOUSES WHEN BUILDING HOTEL
				{
					if(n==5)
					{
						prop.BuildHouse(n);
						if(inPlayer.getPlayerBalance()>n*prop.getHousePrice())
						{
							inPlayer.addToBalance(n*prop.getHousePrice());
							InfoPanel.displayString("You now have a hotel on your property");
						}
						else{
							InfoPanel.displayString("Not enough money to buy property");
						}
					}
				}
				else{
					InfoPanel.displayString("You have reached build limit");
				}
			}
			else{
				InfoPanel.displayString("You do not have all the colours");
			}
		}
		else{
			InfoPanel.displayString("You do not own any properties");
		}
	}

	public void Demolish(Player inPlayer){
		ArrayList<Property> propertyList = inPlayer.properties;

		int size=propertyList.size();
		Property prop;
		String[] choices=new String[size];
		int[] num = new int[size];
		for(int count=0;count<size;count++)
		{
			prop = propertyList.get(count);
			num[count] = prop.getHouseNumber();
			choices[count]=propertyList.get(count).getName()+" " + num[count] + " houses built";
		}
		if(propertyList.size()>0)
		{
			prop=propertyList.get(0);
			int i1 = 0;
			String chosen=JOptionPane.showInputDialog(null, "Which property would you like to demolish properties on?","The Choice", JOptionPane.OK_OPTION, null,choices, choices[0] +  " (houses)").toString();
			if(propertyList.get(i1).getOwner() == Player.playerNumber){
				prop=propertyList.get(0);
				for(int count1=0;count1<size;count1++)
				{
					boolean found = chosen.contains(propertyList.get(count1).getName());
					if(found)
					{
						prop=propertyList.get(count1);
						i1=count1;
						break;
					}
				}	
			}
			else
			{
				InfoPanel.displayString("Error: You dont own this property so you cannot build houses here");
			}
			InfoPanel.displayString("How many would you like to demolish");
			BoardAndCommandBox.inputString();
			String numberToDemolish = BoardAndCommandBox.getString();
			InfoPanel.displayString(numberToDemolish);
			int n = Integer.parseInt(numberToDemolish);
			if(n > prop.getHouseNumber()){
				InfoPanel.displayString("You do not have that many house on " + prop.getName());
			}
			else if(n ==prop.getHouseNumber() ){
				prop.DemolishHouses(n);
				InfoPanel.displayString("You have demolished " + n + " houses from " + prop.getName());
				players.get(GameRunner.user%players.size()).addToBalance(((int) (n*(0.5*prop.getHousePrice()))));
				InfoPanel.displayString("You have recieved " + ((int) (n*(0.5*prop.getHousePrice()))));
			}
			else{
				prop.DemolishHouses(n);
				InfoPanel.displayString("You have demolished " + n + " houses from " + prop.getName());
				players.get(GameRunner.user%players.size()).addToBalance(((int) (n*(0.5*prop.getHousePrice()))));
				InfoPanel.displayString("You have recieved " + ((int) (n*(0.5*prop.getHousePrice()))));
			}
		}
	}

	public void decideWinner() {
		if (players.size() == 1) {
			InfoPanel.displayString(players.get(GameRunner.user%players.size()).getPlayerName());			
		} else {
			ArrayList<Player> playersWithMostAssets = new ArrayList<Player>();
			int mostAssets = players.get(0).getAssets();
			for (Player player : players) {
				if (player.getAssets() > mostAssets) {
					playersWithMostAssets.clear(); 
					playersWithMostAssets.add(player);
				} else if (player.getAssets() == mostAssets) {
					playersWithMostAssets.add(player);
				}
			}
			if (playersWithMostAssets.size() == 1) {
				InfoPanel.displayString("There was a winner is:");
				InfoPanel.displayString(playersWithMostAssets.get(0).getPlayerName());
			} else {
				InfoPanel.displayString("There was a draw between :");
				for (Player p : playersWithMostAssets) {
					InfoPanel.displayString(p.getPlayerName() + "  " + p.getPlayerBalance());
				}
			}
		}
		return;
	}
	
	public void processDone(){
		if (rollDone) {
			InfoPanel.displayString( players.get(GameRunner.user%players.size()%players.size()).getPlayerName() +" has ended turn");
			GameRunner.user++;
			MainWindow.displayString(" It is " + players.get(GameRunner.user%players.size()).getPlayerName() + " " + "turn" + "\n");
		}
	}
}
