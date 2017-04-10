
public class ChestCard extends Square {
	String cardType = "";
	static int ChestCardIndex = 0;
	static String command = "";
	public ChestCard(String name, String type) {
		super(name);
		this.cardType = type;
	}
	
	public static void drawChestCard(Player p) throws InterruptedException{
		switch(((int) (Math.random() * 16))+1){
		case 0:
			InfoPanel.displayString("You drew:\nAdvance to Go");
			while(p.position%40 != 0){
				p.safeMove(1);
			}
			break;
		case 1:
			InfoPanel.displayString("You drew:\nGo back to The Cupboard under the stairs");
			while(p.position%40 != 1){
				p.safeMoveBack(1);
			}
			break;
		case 2:
			InfoPanel.displayString("You drew:\nGo To Jail");
			p.sendToJail();
			break;
		case 3:
			InfoPanel.displayString("You drew:\nPay Hospital fee");
			p.withdraw(100);
			break;
		case 4:
			InfoPanel.displayString("You drew:\nPay doctors fee");
			p.withdraw(50);
			break;	
		case 5:
			InfoPanel.displayString("You drew:\nPay Insurance fee");
			p.withdraw(50);
			break;
		case 6:
			InfoPanel.displayString("You drew:\nBank made a mistake, you have recieved 200");
			p.addToBalance(200);
			break;
		case 7:
			InfoPanel.displayString("You drew:\nAnnuity matures");
			p.addToBalance(100);
			break;
		case 8:
			InfoPanel.displayString("You drew:\nYou inherit 100 from Dumbledore !!!Spoiler!!!");
			p.addToBalance(100);		
			break;
		case 9:
			InfoPanel.displayString("You made a sale from stock");
			p.addToBalance(50);
			break;
		case 10:
			InfoPanel.displayString("Receive interest on 7% preference shares: 25.");
			p.addToBalance(25);
			break;
		case 11:
			InfoPanel.displayString("Income tax refund. Collect 20");
			p.addToBalance(20);
			break;
		case 12:
			InfoPanel.displayString("You second prize in a beauty contest. Collect 10");
			p.addToBalance(10);
		case 13:
			InfoPanel.displayString("Collect 10 from each player");
			p.addToBalance(10*(Commands.players.size()-1));
			break;
		case 14:
			InfoPanel.displayString("You have received word you can be broken out of Azkaban when you wish");
			p.GetGOOJ();
			
			break;
		case 15:
			InfoPanel.displayString("Would you like to pay a fine of 10 or draw a chance");
			BoardAndCommandBox.inputString();
			command = BoardAndCommandBox.getString();
			command = command.toLowerCase();
			if(command.equals("fine")){
				p.withdraw(10);
			}
			else if(command.equals("chance")){
				ChanceCard.drawChanceCard(p);
			}
			break;
		}
	}

}
