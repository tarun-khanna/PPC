package ui;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class DialogBox {

	DialogBox()
	{
		UIManager.put("OptionPane.background", Color.lightGray);
		UIManager.put("Panel.background", Color.lightGray);
		UIManager.put("OptionPane.messageForeground", Color.BLACK);

	}
	
	DialogBox(JPanel mainPanel,String message,String title)
	{
		this();
		JOptionPane.showMessageDialog(mainPanel, message, title, JOptionPane.INFORMATION_MESSAGE);

	}
}
