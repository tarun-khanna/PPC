package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Orders;
import dao.DatabaseCon;
import dao.OrdersDaoImpl;
public class ProdManStart implements ActionListener{
	
	JFrame mainFrame;
	JPanel addPanel,pQuantity;
	JTextField nameField,startField,endField,quantField,compField,idField,managerIdField,manpowerField;
	JLabel enterLabel,nameLabel,startLabel,endLabel,quantLabel,compLabel,idLabel,managerIdLabel,manpowerLabel;
	private JLabel validateName;
	JComboBox<String> comboUnit;
	private String[] strUnit={"UNITS","Kg","litre"} ;
    private static final String NOT_SELECTABLE_OPTION = "in...";
    
    
   
    DateChooser dc,dc1;
    Date d;
    
    JButton create;
	Orders orders;
	OrdersDaoImpl pImpl;
	DatabaseCon db;
	String managerId;
	String orderId;
	
	DialogBox dialog;
	String message;
	String title;
	boolean addEnable=false;
	
	public ProdManStart(JPanel mainPanel,DatabaseCon db,String  managerId) throws SQLException, ClassNotFoundException, IOException
	{
		//this.mainFrame=mainFrame;
		this.managerId=managerId;
		this.addPanel=mainPanel;
		this.db=db;
		pImpl=new OrdersDaoImpl(db);
		
		init();
	
	}
	
	private void init() throws ClassNotFoundException, SQLException
	{
		//addPanel = new JPanel(new GridBagLayout());
		addPanel.setLayout(new GridBagLayout());
		addPanel.setBackground(Color.DARK_GRAY);
	 	addPanel.setBorder(new EmptyBorder(new Insets(20,20,20,20)));
	 	pQuantity=new JPanel();		
	 	managerIdField=new JTextField(managerId);managerIdField.setEnabled(false);
	 	managerIdField.setForeground(Color.black);
	 	managerIdLabel=new JLabel("Manger ID:");
	 	int orderCount=pImpl.calcOrderId();
		orderId="o-"+orderCount;
	 	idField=new JTextField(orderId);idField.setEnabled(false);
	 	idField.setForeground(Color.black);
	 	idLabel=new JLabel("Order ID of the project:");
	 	nameField=new JTextField(10);
	 	nameLabel=new JLabel("Name of the project:");
	 	validateName=new JLabel("*Mendatory Field"); 
	 	validateName.setFont(new Font("Serif",Font.PLAIN,11));
	 	validateName.setForeground(Color.GRAY);
	    endField=new JTextField(10);
        endLabel=new JLabel("Deadline of the project:");
        compField=new JTextField(10);
        compLabel=new JLabel("Ordering Company:");
        manpowerField=new JTextField();
        manpowerLabel=new JLabel("Manpower:");
        quantField=new JTextField(10);
        quantLabel=new JLabel("Quantity of the product:");
        comboUnit=new JComboBox<String>(strUnit);
   
        comboUnit.setModel(new DefaultComboBoxModel<String>() {
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

    	comboUnit.addItem(NOT_SELECTABLE_OPTION);
    	for(String s:strUnit)
    		comboUnit.addItem(s);

    	pQuantity.add(quantField);
    	pQuantity.add(comboUnit);
		pQuantity.setBackground(Color.DARK_GRAY);

        enterLabel=new JLabel("ENTER THE FOLLOWING DETAILS-->");
        enterLabel.setFont(new Font("Calibri (Body)", Font.BOLD,30 ));
		enterLabel.setForeground(Color.BLACK);
		
		managerIdLabel.setForeground(Color.WHITE);
	    idLabel.setForeground(Color.WHITE);
	    nameLabel.setForeground(Color.WHITE);
	    compLabel.setForeground(Color.WHITE);
	    quantLabel.setForeground(Color.WHITE);
	    endLabel.setForeground(Color.WHITE);
	    manpowerLabel.setForeground(Color.WHITE);
	    
	    create=new JButton();
        
        dc=new DateChooser();
        dc.datePicker=dc.getDatePicker();
        
        Date currDate=new Date();
        
        dc.datePicker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 d = (Date) dc.datePicker.getModel().getValue();
			       
				if(d.before(currDate))
				{
					dialog=new DialogBox(addPanel, "enter valid deadline", "INVALID");
					dc.datePicker.getJFormattedTextField().setText("");
					create.setEnabled(false);
				}
				else 
				{
					create.setEnabled(true);
				}
			}
		});
        
        
        
        
        JPanel titlePanel=new JPanel();
        titlePanel.setLayout(new GridBagLayout());
    	titlePanel.setBackground(Color.GRAY);
    	titlePanel.add(enterLabel);
    	addPanel.add(titlePanel,setGBConstraints(0,0,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.LINE_START,1.0,new Insets(10,0,50,0)));
    		
    	    
		addPanel.add(managerIdLabel,setGBConstraints(0,1,GridBagConstraints.BASELINE_LEADING,new Insets(10,5,10,5) ));
		addPanel.add(managerIdField,setGBConstraints(1,1,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(10,5,10,5)));
	
		addPanel.add(idLabel,setGBConstraints(0,2,GridBagConstraints.BASELINE_LEADING,new Insets(10,5,10,5) ));
		addPanel.add(idField,setGBConstraints(1,2,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(10,5,10,5)));
				
		addPanel.add(nameLabel,setGBConstraints(0,3,GridBagConstraints.BASELINE_LEADING,new Insets(10,5,10,5) ));
		addPanel.add(nameField,setGBConstraints(1,3,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(10,5,10,5)));
		
		addPanel.add(endLabel,setGBConstraints(0,4,GridBagConstraints.BASELINE_LEADING,new Insets(10,5,10,5) ));
		addPanel.add(dc.datePicker,setGBConstraints(1,4,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(10,5,10,5)));
		
		
		addPanel.add(compLabel,setGBConstraints(0,5,GridBagConstraints.BASELINE_LEADING,new Insets(10,5,10,5) ));
		addPanel.add(compField,setGBConstraints(1,5,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(10,5,10,5)));
			
		addPanel.add(quantLabel,setGBConstraints(0,6,GridBagConstraints.BASELINE_LEADING,new Insets(10,5,10,5) ));
		addPanel.add(pQuantity,setGBConstraints(1,6,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(10,5,10,5)));
			
		addPanel.add(manpowerLabel,setGBConstraints(0,7,GridBagConstraints.BASELINE_LEADING,new Insets(10,5,10,5) ));
		addPanel.add(manpowerField,setGBConstraints(1,7,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(10,5,10,5)));

		quantField.addKeyListener(new KeyAdapter() 
		{ 
		public void keyTyped(KeyEvent ke) 
		{ 
		char c = ke.getKeyChar(); 
		if((!(Character.isDigit(c))) && // Only digits 
		(c!=('\b')) ) // For backspace 
		{ 
		ke.consume(); 
		} 
		} 
		public void keyReleased(KeyEvent e){} 
		public void keyPressed(KeyEvent e){} 
		});
		
		manpowerField.addKeyListener(new KeyAdapter() 
		{ 
		public void keyTyped(KeyEvent ke) 
		{ 
		char c = ke.getKeyChar(); 
		if((!(Character.isDigit(c))) && // Only digits 
		(c!=('\b')) ) // For backspace 
		{ 
		ke.consume(); 
		} 
		} 
		public void keyReleased(KeyEvent e){} 
		public void keyPressed(KeyEvent e){} 
		});
		
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.add(create,setGBConstraints(0,0,GridBagConstraints.BASELINE,new Insets(10,5,10,5)));
		addPanel.add(buttonPanel,setGBConstraints(0,8,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.PAGE_END,1.0,new Insets(10,0,0,0)));
		
		Icon button=new ImageIcon("add.png");
		Image buttonIcon=((ImageIcon) button).getImage();
		button=new ImageIcon(buttonIcon);
		create.setIcon(button);
		create.setContentAreaFilled(false);
		
		create.addActionListener(this);
		/*	mainFrame=new JFrame();
		mainFrame.pack();
		 mainFrame.setSize(600,400);

         mainFrame.getContentPane().add(addPanel);
         mainFrame.setVisible(true);
	*/ }


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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		if(e.getSource()==create)
		{
			try {
				if(managerIdField.getText().equals("")|| idField.getText().equals("")|| nameField.getText().equals("")||
						dc.datePicker.getJFormattedTextField().getText().equals("")||compField.getText().equals("")||
						quantField.getText().equals("")||comboUnit.getSelectedItem().toString().equals("in...")||manpowerField.getText().equals(""))
				{
					message="All fields are MANDATORY!";
					title="ERROR";
				}
				
				else
				{
					orders=new Orders();
					pImpl=new OrdersDaoImpl(db);
					int temp=Integer.parseInt(quantField.getText());
					
					orders.setOrders(managerIdField.getText(), idField.getText(), nameField.getText(), dc.datePicker.getJFormattedTextField().getText(),
							 compField.getText(), temp,comboUnit.getSelectedItem().toString(), null,manpowerField.getText());
					
					pImpl.setTableData(orders);
					//pImpl.getTableData();
					
					message="Added Successfully";
					title="SUCCESS";
					
					addPanel.removeAll();
					addPanel.revalidate();
					addPanel.repaint();
					init();
				
				}
				
				dialog=new DialogBox(addPanel,message,title);
			} catch (SQLException | ParseException | ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		
	}
/*public static void main(String args[])
{
	DatabaseCon db=new DatabaseCon();
	try {
		
		db.getDBConnection();
		
		new ProdManStart(new JPanel(), db, "C4_11");
	} catch (ClassNotFoundException | SQLException | IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}*/
}


