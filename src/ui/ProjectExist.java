package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bean.Orders;
import dao.DatabaseCon;
import dao.OrdersDaoImpl;

public class ProjectExist {
	
	JFrame mainFrame;
	JPanel pmain;
	JPanel innerPanel;
	JPanel titlePanel;
	
	JLabel namesOfProj;
	
	JLabel extraLabel;
	//JButton buttons[];
	JRadioButton rb;
	JButton ShowProgress;
	
	OrdersDaoImpl pImpl;
	DatabaseCon db;
	Connection con;
	
	String extraStr[]=new String[13];
	
	boolean addEnable=false;
	String managerId;
	
	public ProjectExist(JPanel mainPanel,DatabaseCon db,String managerId) throws SQLException, ClassNotFoundException, IOException, ParseException
	{
		this.db=db;
		this.pmain=mainPanel;
		this.managerId=managerId;
		pImpl=new OrdersDaoImpl(db);
		init();
		
	}
	public JPanel getPanel()
	{
		return pmain;
	}

	void displayProgress() throws ParseException
	{
		pmain.removeAll();
		innerPanel=new JPanel();
		ProgressChart pc;
		try {
			pc = new ProgressChart(managerId, db);
			innerPanel=pc.getProgressPanel();
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pmain.add(innerPanel,BorderLayout.CENTER);
		pmain.revalidate();
	}

	public void init() throws SQLException, ClassNotFoundException, IOException, ParseException
	{
		//pmain=new JPanel();
		pmain.setLayout(new BorderLayout());
		pmain.setBackground(Color.DARK_GRAY);
		
		 namesOfProj=new JLabel("Existing Projects");
	     namesOfProj.setFont(new Font("Calibri (Body)", Font.BOLD,30 ));
		 namesOfProj.setForeground(Color.BLACK);
		 JPanel titlePanel=new JPanel();
	     titlePanel.setBackground(Color.GRAY);
	     titlePanel.add(namesOfProj);
		 
	    
	    innerPanel=new JPanel();
	    innerPanel.setBackground(Color.DARK_GRAY);
		
	    ArrayList<Orders> tempListOfOrders = new ArrayList<Orders>();
	    
	    tempListOfOrders=pImpl.getOrdersOfManager(managerId);
	    DefaultTableModel model = new DefaultTableModel(); 
	    JTable table = new JTable(model); 
	    model.addColumn("OrderId");	    
	    model.addColumn("OrderName"); 
	    model.addColumn("StartDate"); 
	    model.addColumn("EndDate"); 
	    model.addColumn("Company"); 
	    model.addColumn("Quantity"); 
	    model.addColumn("Unit"); 
	    model.addColumn("Supervisor"); 
	    model.addColumn("Manager"); 
	    model.addColumn("Manpower");	    
	    model.addColumn("Deadline");
	    for(Orders o : tempListOfOrders)
	    {
	  //  ArrayList<String> tempArray=new ArrayList<String>();
//		for(int i=0;i<9;++i)
//		{
//			if((i==2 && res.getString(i+1).equals("2050-12-31 00:00:00.0")) || (i==3 && res.getString(i+1).equals("1970-01-01 00:00:00.0")) )
//				{tempArray.add("NOT ASSIGNED");}
//			else
//				{tempArray.add(res.getString(i+1));}
//		
//			
//			
	    	SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/yyyy");
			Date d =formatter.parse("31/12/2050");
			Date printStart = o.getStartDate();
			String startString = printStart.toString();
			if(printStart.compareTo(d)==0)
			{
				startString="NOT ASSIGNED";
			}
				
			d=formatter.parse("01/01/1970");
			Date printEnd = o.getEndDate();
			String endString = printEnd.toString();
			if(printEnd.compareTo(d)==0)
			{
				endString= "NOT ASSIGNED";
			}
				
				
			
			
		model.addRow(new Object[]{o.getOrderId(),o.getNameOfProject(),startString,endString,o.getCompany(),o.getQuantity(),o.getUnit(),o.getSupervisor()
				,o.getManagerId(),o.getManpower(),o.getDueDate()});
	    }
	    JScrollPane sp = new JScrollPane(table);
	    
	    ShowProgress=new JButton();
		ShowProgress.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					displayProgress();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		        
		}
				
			
		});
		JPanel buttonPanel=new JPanel();
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.add(ShowProgress);
		
		Icon button=new ImageIcon("ShowProgress.png");
		Image buttonIcon=((ImageIcon) button).getImage();
		button=new ImageIcon(buttonIcon);
		ShowProgress.setIcon(button);
		ShowProgress.setContentAreaFilled(false);
		
		pmain.add(titlePanel,BorderLayout.NORTH);
		pmain.add(sp, BorderLayout.CENTER);
		pmain.add(buttonPanel,BorderLayout.SOUTH);
		
	/*	mainFrame=new JFrame();
		mainFrame.add(pmain);
		mainFrame.setSize(600, 400);
		mainFrame.setVisible(true);
	*/
		pmain.revalidate();		
	}
/*	public static void main(String args[])
	{
		DatabaseCon db=new DatabaseCon();
		
		try {
			db.getDBConnection(); 
			new ProjectExist(new JPanel(), db,"C4_164");
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}*/
 

}
