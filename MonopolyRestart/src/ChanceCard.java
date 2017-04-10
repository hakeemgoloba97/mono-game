
public class ChanceCard extends Square {
	static int numberOfHouses = 0;
	static int numberOfHotels = 0;
	String cardType = "";
	static int ChanceCardIndex = 0;
	
	public ChanceCard(String name, String type) {
		super(name);
		this.cardType = type;
	}
	
	public static void drawChanceCard(Player p) throws InterruptedException{
		switch(((int) (Math.random() * 16))+1){
		case 0:
			InfoPanel.displayString("You drew:\nAdvance to Go");
			while(p.position%40 != 0){
				p.safeMove(1);
			}
			break;
		case 1:
			InfoPanel.displayString("You drew:\nGo To Jail");
			p.sendToJail();
			break;
		case 2:
			InfoPanel.displayString("You drew:\nAdvance To The Burrow");
			while(p.position%40 != 11){
				p.safeMove(1);
			}
			break;
		case 3:
			InfoPanel.displayString("You drew:\nTake a trip on Durmstrang's Ship");
			while(p.position%40 != 15){
				p.safeMove(1);
				if(p.position%40 == 0){
					InfoPanel.displayString("You passed Go here's 200");
					p.addToBalance(200);
				}
			}
			break;
		case 4:
			InfoPanel.displayString("You drew:\nGo To Flourish And Blott's");
			while(p.position%40 != 24){
				p.safeMove(1);
				if(p.position%40 == 0){
					InfoPanel.displayString("You passed Go here's 200");
					p.addToBalance(200);
				}
			}
			break;	
		case 5:
			InfoPanel.displayString("You drew:\nGo To Gringotts Wizarding Bank");
			while(p.position%40 != 39){
				p.safeMove(1);
				if(p.position%40 == 0){
					InfoPanel.displayString("You passed Go here's 200");
					p.addToBalance(200);
				}
			}
			break;
		case 6:
			InfoPanel.displayString("You drew:\nGo Back 3 Spaces");
			p.safeMoveBack(3);
			break;
		case 7:
			InfoPanel.displayString("You have to make repairs on all of your houses");
			for(Property a: p.properties){
				numberOfHouses+= a.getHouseNumber();
				numberOfHotels+= (a.getHouseNumber()%4);
			}
			p.withdraw((numberOfHotels*100) + (numberOfHouses*25));
			break;
		case 8:
			InfoPanel.displayString("You have to pay street repairs ");
			for(Property a: p.properties){
				numberOfHouses+= a.getHouseNumber();
				numberOfHotels+= (a.getHouseNumber()%4);
			}
			p.withdraw((numberOfHotels*115) + (numberOfHouses*40));			
			break;
		case 9:
			InfoPanel.displayString("Pay Hogwarts fees: 150 has been deducted from your account");
			p.withdraw(150);
			break;
		case 10:
			InfoPanel.displayString("Fine: Caught stealing polyjuice potion 20 has been deducted from your account");
			p.withdraw(20);
			break;
		case 11:
			InfoPanel.displayString("Fine: Caught speeding on broom 15 has been deducted from your account");
			p.withdraw(15);
			break;
		case 12:
			InfoPanel.displayString("You owl delivered you money 150 has been added to your account");
			p.addToBalance(150);
		case 13:
			InfoPanel.displayString("You won some money");
			p.withdraw(100);
			break;
		case 14:
			InfoPanel.displayString("Bank pays you money");
			p.withdraw(50);
			break;
		case 15:
			InfoPanel.displayString("You have received word you can be broken out of Azkaban when you wish");
			p.GetGOOJ();
			break;
		}
	}

}
