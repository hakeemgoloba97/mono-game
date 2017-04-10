
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameRunner{
	private static ArrayList<Player> players = new ArrayList<Player>();
     static Roll newRoll= new Roll();
    Commands playerCommands = new Commands(players);
	static int user = 0;
	GameRunner() throws InterruptedException {
		new MainWindow(players);
		getPlayerNames();
		determineFirstPlayer();
		echo();
	}
	
	public void getPlayerNames()
	{
		JTextField[] textField = new JTextField[6];
	    JPanel myPanel = new JPanel(new GridLayout(0,1));
		for(int i = 0; i < players.size(); i++){
			textField[i] = new JTextField(5);
		    myPanel.add(new JLabel("Player " + (i+1) + ":"));
		    myPanel.add(textField[i]);
		}
		JOptionPane.showConfirmDialog(null, myPanel, "Please enter player Names", JOptionPane.DEFAULT_OPTION);
		for(int i = 0; i < players.size(); i++){
			players.get(i).setName(textField[i].getText());
		}	
		for(int i = 0; i < players.size(); i++){
			InfoPanel.displayString(players.get(i).getPlayerName() + " recieved " + players.get(i).getPlayerBalance() + " from bank\n");
		}	
		
	}
	static int number = players.size();
	
	void echo() throws InterruptedException {
		String command = "";
		do {
			BoardAndCommandBox.inputString();
			command = BoardAndCommandBox.getString();
			command = command.toLowerCase();
			command = command.trim();
			command = command.replaceAll("( )+", " ");
			playerCommands.Command(command);
		} while (!command.equals("quit"));
		return;
	}
	static int roll;
	public static int[] dice_total;

	public void determineFirstPlayer(){
		int playerRolls[] = new int[players.size()];
		int max = 0;
		int playerIndex = 0;
		String s = "";
		for(int i = 0; i < players.size() ; i++){
			playerRolls[i] = Roll.roll();
			s = s +  players.get(i).getPlayerName() + " rolled " +  playerRolls[i] +"\n";
		}

		MainWindow.displayString(s);

		for (int counter = 0; counter < playerRolls.length; counter++)
		{
			if (playerRolls[counter] > max)
			{
				max = playerRolls[counter];
				playerIndex = counter;
			}
		}

		MainWindow.displayString("The highest roll was " + max);
		MainWindow.displayString(players.get(playerIndex).getPlayerName() + "  will go first");
		user = playerIndex;

	}
}