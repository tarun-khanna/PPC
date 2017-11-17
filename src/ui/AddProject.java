package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.DatabaseCon;
import dao.OrdersDaoImpl;

public class AddProject{

	JRadioButton startNew;
	JRadioButton selectExisting;
	JLabel labelSelect;
	JPanel mainPanel;
	JPanel titlePanel;
	JPanel outerPanel;
	JPanel innerPanel;
	JLabel tempLabel;
	JPanel innerPanel1;
	JLabel imageLabel;
	JFrame mainFrame;
	JButton choose;
	JLabel hi;
	DatabaseCon db;
	OrdersDaoImpl pImpl;
	ProdManStart pms;
	ProjectExist pe;
	ButtonGroup b;
	String managerId;
	
	DialogBox dialog;
	public AddProject(DatabaseCon db,String managerId) throws ClassNotFoundException, SQLException, IOException
	{
		this.db=db;
		this.managerId=managerId;
		init();
	}
	
	public JPanel getPanel()
	{
		return mainPanel;
	}
	public void init() throws ClassNotFoundException, SQLException
	{
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.gray);
		labelSelect =new JLabel("ADD A PROJECT",SwingConstants.CENTER);
		labelSelect.setFont(new Font("Calibri (Body)", Font.BOLD,30 ));
		labelSelect.setForeground(Color.black);
		startNew =new JRadioButton("START A NEW PROJECT");
		startNew.setFont(new Font("Calibri (Body)", Font.ITALIC, 30));
		startNew.setActionCommand("START A NEW PROJECT");
		startNew.setContentAreaFilled(false);
		startNew.setForeground(Color.white);
		selectExisting=new JRadioButton("VIEW EXISTING PROJECTS");
		selectExisting.setFont(new Font("Calibri (Body)", Font.ITALIC, 30));
		selectExisting.setActionCommand("VIEW EXISTING PROJECTS");
		selectExisting.setContentAreaFilled(false);
		selectExisting.setForeground(Color.white);

		
		mainPanel=new JPanel(new BorderLayout(100,50));
		mainPanel.setBackground(Color.DARK_GRAY);
		
		choose=new JButton();
	
		titlePanel.add(labelSelect);

		innerPanel=new JPanel();
		innerPanel.setLayout(new GridLayout(1, 1));
		innerPanel.setBorder(new EmptyBorder(0, 100, 0, 100));
		
		innerPanel.add(startNew);
		innerPanel.add(selectExisting);
		innerPanel.setBackground(Color.DARK_GRAY);
		
		//innerPanel1.setBackground(Color.green);
		b=new ButtonGroup();
		
		b.add(startNew);
		b.add(selectExisting);
		
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				mainPanel.setVisible(false);
				if(b.getSelection()==null)
				{
					dialog = new DialogBox(mainPanel, "Select one from the options", "INVALID");
				}
				else
				{
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();
					if(b.getSelection().getActionCommand().equals(startNew.getText()))
						try {
							pms=new ProdManStart(mainPanel,db,managerId);
							
						} catch (ClassNotFoundException | SQLException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					else
						try {//mainPanel.setVisible(false);
							pe=new ProjectExist(mainPanel,db,managerId);
							//mainPanel=pe.getPanel();
							
						} catch (ClassNotFoundException | SQLException | IOException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				}	
			}
		});
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.add(choose);
			
		Icon button=new ImageIcon("continue.png");
		Image buttonIcon=((ImageIcon) button).getImage();
		button=new ImageIcon(buttonIcon);
		choose.setIcon(button);
		choose.setContentAreaFilled(false);
		
		//mainPanel.setBorde
		mainPanel.add(titlePanel,BorderLayout.NORTH);
		mainPanel.add(innerPanel,BorderLayout.CENTER);
		mainPanel.add(buttonPanel,BorderLayout.SOUTH);

	//	mainPanel.add(choose,BorderLayout.SOUTH);
	/*	mainFrame=new JFrame();
		mainFrame.add(mainPanel);
		
		mainFrame.setSize(600, 400);
		mainFrame.setVisible(true);
	*/
	}

	
	/*public static void main(String[] args)
	{
		DatabaseCon db=new DatabaseCon();
		try {
			new AddProject(db, null);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
