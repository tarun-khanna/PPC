package ui;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Test {

	JFrame frame;
	JPanel panel1,panel2;
	JTextArea area;
	JScrollPane jsp;
	Test()
	{
		frame = new JFrame("test");
		panel1=new JPanel();
		panel2=new JPanel();
		area=new JTextArea(100, 100);
		jsp=new JScrollPane(panel1);
		
		panel1.add(area);
		panel1.add(new JButton("hello"));
		
		//panel2.add(jsp);
		frame.add(jsp);
		frame.setLayout(new CardLayout());
		frame.setSize(500,500);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		new Test();
		
	}

}
