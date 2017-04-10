
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainWindow {

	JFrame mainWindow;
	Player player = new Player();
	private InfoPanel infoPanel = new InfoPanel();
	private int number = 0;
	public MainWindow(ArrayList<Player> players){
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLayout(new BorderLayout ());
		number = player.getNumberOfPlayers();
		for(int i = 0; i < number; i++){
			players.add(new Player());
		}
		for(int i = 0; i < number; i++){
			players.get(i).players.setBounds(735 +(i*10), 745 + (i * 5), 15, 15);
		}
		//Add Tokens to mainWindow
		for(int i = 0; i < number; i++) mainWindow.getContentPane().add(players.get(i).players);
		
		mainWindow.add(new BoardAndCommandBox(mainWindow,players));
		mainWindow.add(infoPanel);
		mainWindow.setResizable(false);
		mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
		return;
	}
	
	public int getNumber(){
		return number;
	}
	public static String getCommand () {
		return BoardAndCommandBox.getString();
	}
	
	public static void displayString (String string) {
		InfoPanel.displayString(string);
		return;
	}
	
}