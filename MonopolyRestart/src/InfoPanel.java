import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel infoPanelPanel = new JPanel();
	static JTextArea infoPanel = new JTextArea(18 ,25);

	JScrollPane scrollPane = new JScrollPane(infoPanel);
	DefaultCaret caret = (DefaultCaret)infoPanel.getCaret();
	
	InfoPanel () {
		infoPanel.setEditable(false);
		infoPanel.setLineWrap(true);
		infoPanel.setWrapStyleWord(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		return;
	}

	public static void displayString (String text) {
		infoPanel.setText(infoPanel.getText()+"\n"+text);
	}

}