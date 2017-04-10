
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BoardAndCommandBox extends JPanel{

	private static final long serialVersionUID = 1L;
	static JTextField commandBox = new JTextField();
	JFrame MainWindow;
	private static LinkedList<String> commandBuffer = new LinkedList<String>();
	JPanel Board = new JPanel();
	private static String string;
	public void AddBoardToFrame(JPanel LeftPanel){
		
		//Importing and Scaling of image
		Image monopolyBoard = new ImageIcon(getClass().getResource("/resources/monopoly.png")).
											getImage().getScaledInstance(800,800, Image.SCALE_SMOOTH);
		
		ImageIcon monopoly = new ImageIcon(monopolyBoard);
		JLabel label = new JLabel();
		
		
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setIcon(monopoly);
		

		LeftPanel.setLayout(new BorderLayout ());
		LeftPanel.add(label, BorderLayout.NORTH);
		
		LeftPanel.add(commandBox);	
		
		LeftPanel.setVisible(true);
		
		
		//sizing of commandBox
		commandBox.setPreferredSize(new Dimension(100,28));
		
		//borders around each component
		commandBox.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public BoardAndCommandBox(JFrame MainWindow,ArrayList<Player> players) {
		this();
		this.MainWindow = MainWindow;
		JPanel Board = new JPanel();
		AddBoardToFrame(Board);
		MainWindow.add(Board,BorderLayout.WEST);

	}

	BoardAndCommandBox () {

		class AddActionListener implements ActionListener {
			   public void actionPerformed(ActionEvent event)	{
				   synchronized (commandBuffer) {
					   commandBuffer.add(commandBox.getText());
					   commandBox.setText("");
					   commandBuffer.notify();
				   }
		           return;
			   }
		   }
		
		ActionListener listener = new AddActionListener();
		commandBox.addActionListener(listener);
		setLayout(new BorderLayout());
		add(commandBox, BorderLayout.CENTER);
		return;
		

}
	
	public static void inputString() {
		synchronized (commandBuffer) {
			while (commandBuffer.isEmpty()) {
				try {
					commandBuffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			string = commandBuffer.pop();
		}
		return;
	}
	
	public static String getString() {
		return string;
	}
	
}