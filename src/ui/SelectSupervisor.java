package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import bean.Orders;
import dao.DatabaseCon;
import dao.OrdersDaoImpl;

public class SelectSupervisor {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	JLabel supLabel;
	JPanel pmain;
	JPanel innerPanel;
	JPanel psecond;
	JFrame fmain;
	
	JRadioButton rb;
	JButton select;
	String str;
	JLabel namesOfOrders;
	Orders orders;
	OrdersDaoImpl pImpl;
	ButtonGroup b;
	DatabaseCon db;
	String managerId;
	
	DialogBox dialog;
	String title;
	String message;
	
	JComboBox<String> sup;
	JButton assign;
	//private String[] supervisor={"ASHOK","RAHUL","RAJ"} ;
	ArrayList<String> supervisors;
	private static final String NOT_SELECTABLE_OPTION = "Assign your supervisor... ";
    
    public SelectSupervisor(DatabaseCon db, String managerId) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated constructor stub
    	pImpl=new OrdersDaoImpl(db);
    	this.managerId=managerId;
    	pmain=new JPanel();
    	init();  
    	
    	}
    
   public JPanel getPanel()
	{
		return pmain;
	}
   
    public void displaySecond() 
    {
    	pmain.removeAll(); 
    	pmain.repaint();
    	
    	pmain.setLayout(new BorderLayout(50,200));
    	//psecond.setBackground(new Color(200,200,255));
    	supLabel=new JLabel("Supervisor of the order");
    	supLabel.setHorizontalAlignment(JLabel.CENTER);
    	supLabel.setFont(new Font("Calibri (Body)", Font.BOLD, 30));
    	supLabel.setForeground(Color.black);
    	
    	JPanel titlePanel=new JPanel();
    	titlePanel.setBackground(Color.GRAY);
    	titlePanel.add(supLabel);
    	
       	sup=new JComboBox<String>();
       	assign=new JButton();
       	
       	sup.setFont(new Font("Footlight MT Light", Font.BOLD, 20));
       	((JLabel)sup.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);        //for center alignment of text in combobox
   
       	sup.setModel(new DefaultComboBoxModel<String>() {
    		private static final long serialVersionUID = 1L;
    		boolean selectionAllowed = true;
    		
    		@Override
    		public void setSelectedItem(Object anObject) {
    			if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
    				super.setSelectedItem(anObject);
    			} else if (selectionAllowed) {
    				// Allow this just once
    				selectionAllowed = false;
    				super.setSelectedItem(anObject);
    			}	
    		}
    	});

    	sup.addItem(NOT_SELECTABLE_OPTION);
    	for(String s:supervisors)
    		sup.addItem(s);
    	
    	assign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String str=sup.getSelectedItem().toString();
					if(str.equals(NOT_SELECTABLE_OPTION))
					{
						dialog=new DialogBox(pmain, "Select a Supervisor", "INVALID");
					}
					else
					{
						pImpl.updateSupervisor(str, b.getSelection().getActionCommand());
						dialog=new DialogBox(pmain, "Assigned Successfully", "SUCCESS");
						pmain.removeAll();
						pmain.revalidate();
						pmain.repaint();
						init();
					
					}
					
					
				} catch (SQLException | ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 			}
		});
       	
       	
    	JPanel buttonPanel=new JPanel();
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.add(assign);
		
		Icon button=new ImageIcon("assignSup.png");
		Image buttonIcon=((ImageIcon) button).getImage();
		button=new ImageIcon(buttonIcon);
		assign.setIcon(button);
		assign.setContentAreaFilled(false);
		
    	pmain.add(titlePanel, BorderLayout.NORTH);
    	pmain.add(sup,BorderLayout.CENTER);
    	pmain.add(buttonPanel, BorderLayout.SOUTH);
    	pmain.revalidate();
		}
    public void init() throws SQLException, ClassNotFoundException, IOException
    {
    	pmain.setLayout(new BorderLayout());
		pmain.setBackground(Color.darkGray);
		
		namesOfOrders=new JLabel("Following Orders have unassigned supervisor");
		namesOfOrders.setFont(new Font("Calibri (Body)", Font.BOLD, 30));
		namesOfOrders.setForeground(Color.black);
		
		JPanel titlePanel=new JPanel();
    	titlePanel.setBackground(Color.GRAY);
    	titlePanel.add(namesOfOrders);
    	    	
		b=new ButtonGroup();
		ArrayList<String> tempArray=new ArrayList<String>();
		tempArray=pImpl.getNullSupervisorData(managerId);
		supervisors=pImpl.getSupervisors();
		
		innerPanel=new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
		innerPanel.setBackground(Color.DARK_GRAY);
		for(String str:tempArray)
		{	
			rb=new JRadioButton(str);
			rb.setActionCommand(str);
			rb.setContentAreaFilled(false);
			rb.setFont(new Font("Calibri (Body)", Font.ITALIC, 20));
			rb.setForeground(Color.white);
			b.add(rb);
			innerPanel.add(rb);
		}		
		select=new JButton();
		select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					if(b.getSelection()==null)
					{
						dialog=new DialogBox(pmain, "Select an Order", "ERROR");
					}
					else
					{
					displaySecond();
					}
			}
			
		});
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.add(select,setGBConstraints(0,0,GridBagConstraints.BASELINE,new Insets(10,5,10,5)));
		
		Icon button=new ImageIcon("select.png");
		Image buttonIcon=((ImageIcon) button).getImage();
		button=new ImageIcon(buttonIcon);
		select.setIcon(button);
		select.setContentAreaFilled(false);
		
		JScrollPane spCenter=new JScrollPane(innerPanel);
		pmain.add(titlePanel,BorderLayout.NORTH);
   		pmain.add(spCenter, BorderLayout.CENTER);
   		pmain.add(buttonPanel,BorderLayout.SOUTH);
		
	/*	fmain=new JFrame();
		fmain.add(pmain);
    	fmain.setSize(600, 400);
    	fmain.setVisible(true);
  */
   		}
    private GridBagConstraints setGBConstraints(int gridx,int gridy,int anchor,Insets in)
	{
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = in;
		
		return gbc;
	}
	
	private GridBagConstraints setGBConstraints(int gridx,int gridy,int gridwidth,int fill,int anchor,double weightx,Insets in)
	{
		GridBagConstraints gbc=setGBConstraints(gridx,gridy,anchor,in);
		
		gbc.gridwidth=gridwidth;
		gbc.fill=fill;
		gbc.weightx=weightx;
		
		return gbc;
	}
/*public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException
	{
		DatabaseCon db= new DatabaseCon();
		db.getDBConnection();
		new SelectSupervisor(db, "C4_11");
		
		     
	}
*/}
