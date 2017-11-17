package ui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.Machine;
import bean.Orders;
import bean.RawMaterials;
import dao.DatabaseCon;
import dao.MachineDaoImpl;
import dao.OrdersDaoImpl;
import dao.RawMaterialsDaoImpl;

public class ReportGeneration implements ActionListener,ItemListener {
   JFrame f;
   JPanel panel;
   JPanel orderP,reportP;
   JLabel enter,report,orderId,orderName,company,quantity,startdate,enddate,managerAssigned,supervisorAssigned,rawMaterialsUsed,machinesUsed,manpower,currentProgress;
   JLabel datel,orderIdl,orderNamel,companyl,quantityl,startdatel,enddatel,managerAssignedl,supervisorAssignedl,rawMaterialsUsedl,machinesUsedl,manpowerl,currentProgressl;
   JTextField ordertf;
   JButton submit;
    //Date date;
   Date today;
   Orders o;
   JComboBox<String> selectOrder;
   ArrayList<String> orderAl;
   MachineDaoImpl mdi;
   String selectedorder;
   JTable rawTable,machineTable;
   DefaultTableModel rawModel,machineModel;
   JScrollPane rawPane,machinePane;
   DatabaseCon dB;
   OrdersDaoImpl odi;
   String managerid,supervisorid;
   ArrayList<Machine> m;
   ArrayList<RawMaterials> rm;
   RawMaterialsDaoImpl rmdi;
   JInternalFrame internalFrame;
   Double percent;
   JPanel reportPanel;
   static String managerId;
   static Color hd_color = Color.LIGHT_GRAY;
    public ReportGeneration(DatabaseCon dB,String managerId ) throws SQLException
	{   this.managerId=managerId;
		this.dB=dB;
		
		Report();
	}

	void Report()
	{  //basic 
		odi=new OrdersDaoImpl(dB);
		//f=new JFrame();
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		orderP=new JPanel(new GridBagLayout());
	    reportP=new JPanel(new GridBagLayout());
	    orderAl=new ArrayList<String>();
        orderAl=odi.getReportOrders(managerId);
        mdi=new MachineDaoImpl(dB);
        reportPanel=new JPanel(new GridBagLayout());
        
		submit=new JButton("Submit");
		submit.addActionListener(this);
		//----
	    today = new Date();
	    
	    datel=new JLabel(today.toString());
	    selectOrder=new JComboBox<String>();
	    selectOrder.addItem("Select your order");
        for(String s : orderAl)
        {
     	   selectOrder.addItem(s);
        System.out.println("s="+s);
        }
        selectOrder.addItemListener(this);
	
	    //adding into orderP
	     enter=new JLabel("REPORT");
	     enter.setFont(new Font("Calibri (Body)",Font.BOLD,40));
	     setfont(selectOrder);
	     setfont(submit);
	     
	     orderP.add(enter,setGBConstraints(0,0,0,GridBagConstraints.NONE,GridBagConstraints.CENTER,1.0,new Insets(0,0,0,0)));
		orderP.add(selectOrder,setGBConstraints(0,1,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
		orderP.add(submit,setGBConstraints(1,1,0,GridBagConstraints.NONE,GridBagConstraints.EAST,1.0,new Insets(5,5,5,5)));
        
		//getting data
	//rawtable
		rawModel=new DefaultTableModel();
		 rawTable=new JTable(rawModel);
		 rawTable.setEnabled(false);
	      	createColumns(rawTable,rawModel);
	      	rawTable.setPreferredScrollableViewportSize(new Dimension(200, 50));
	      	rawTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	      	rawTable.setModel(rawModel);
	      	rawPane = new JScrollPane(rawTable);
	      	rmdi=new RawMaterialsDaoImpl(dB);
	    
	   //machinetable
	      	machineModel=new DefaultTableModel();
			 machineTable=new JTable(machineModel);
			 machineTable.setEnabled(false);
		      	createColumns(machineTable,machineModel);
		      machineTable.setPreferredScrollableViewportSize(new Dimension(50, 50));
		      	machineTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		      	machineTable.setModel(machineModel);
		      	machinePane = new JScrollPane(machineTable);
		      	
	      	
	      	
	 	      	
		//report=new JLabel("REPORT");
		        orderId=new JLabel("OrderId:");
			    setfont(orderId);
				orderIdl=new JLabel();
			    
		      	orderName=new JLabel("Order Name:");
		  setfont(orderName);
		orderNamel=new JLabel();
		 
		company=new JLabel("Ordering Company:");
		setfont(company);	
		companyl=new JLabel();
		quantity=new JLabel("Quantity:");
		setfont(quantity);	
			
		quantityl=new JLabel();
		startdate=new JLabel("Start Date:");
		setfont(startdate);
		startdatel=new JLabel();
		enddate=new JLabel("End Date:");
		setfont(enddate);
		enddatel=new JLabel();
		managerAssigned=new JLabel("Manager Assigned:");
		setfont(managerAssigned);	
		managerAssignedl=new JLabel();
		supervisorAssigned=new JLabel("Supervisor Assigned:");
	    setfont(supervisorAssigned);		
		supervisorAssignedl=new JLabel();
		rawMaterialsUsed=new JLabel("Raw Materials Used:");
		setfont(rawMaterialsUsed);
		rawMaterialsUsedl=new JLabel();
		machinesUsed=new JLabel("Machines Used:");
		setfont(machinesUsed);
		machinesUsedl=new JLabel();
		manpower=new JLabel("Man Power:");
		setfont(manpower);
		manpowerl=new JLabel();
		currentProgress=new JLabel("Current Progress:");
		setfont(currentProgress);
		currentProgressl=new JLabel();
		
		//adding into reportP
		reportP.add(datel,setGBConstraints(0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,1.0,new Insets(5,5,5,5)));
	    reportP.add(orderId,setGBConstraints(0,1,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
		reportP.add(orderIdl,setGBConstraints(1,1,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
	    reportP.add(orderName,setGBConstraints(2,1,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
	    reportP.add(orderNamel,setGBConstraints(3,1,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
	    reportP.add(company,setGBConstraints(0,4,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
		reportP.add(companyl,setGBConstraints(1,4,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		reportP.add(quantity,setGBConstraints(0,5,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
		reportP.add(quantityl,setGBConstraints(1,5,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		reportP.add(startdate,setGBConstraints(0,6,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
		reportP.add(startdatel,setGBConstraints(1,6,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		reportP.add(enddate,setGBConstraints(2,6,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
		reportP.add(enddatel,setGBConstraints(3,6,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		
		reportP.add(managerAssigned,setGBConstraints(0,7,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
		reportP.add(managerAssignedl,setGBConstraints(1,7,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		reportP.add(supervisorAssigned,setGBConstraints(2,7,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
		reportP.add(supervisorAssignedl,setGBConstraints(3,7,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		reportP.add(manpower,setGBConstraints(0,8,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
		reportP.add(manpowerl,setGBConstraints(1,8,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
	
		reportP.add(rawMaterialsUsed,setGBConstraints(0,9,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
	    reportP.add(rawPane,setGBConstraints(1,9,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
	//reportP.add(rawPane,setGBConstraints(1,7,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
       reportP.add(machinesUsed,setGBConstraints(0,10,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
	   reportP.add(machinePane,setGBConstraints(1,10,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
	   reportP.add(currentProgress,setGBConstraints(0,11,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
	   reportP.add(currentProgressl,setGBConstraints(1,11,2,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		//frme details
	   orderP.setBackground(hd_color);
	   reportP.setBackground(hd_color);
	panel.add(orderP,setGBConstraints(0,0,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(0,0,0,0)));
	panel.add(reportP,setGBConstraints(0,1,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(0,0,0,0)));
	reportPanel.add(panel,setGBConstraints(0,0,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(0,0,0,0)));
	reportPanel.setBackground(Color.DARK_GRAY);
	reportPanel.setBorder(new EmptyBorder(10,20,10,20));
	//f.add(reportPanel);	
	//f.setSize(400, 600);	
	//f.setVisible(true);
	
	}
	void setfont(Component l)
	{
		l.setFont(new Font("arial",Font.BOLD,15)); 
		l.setForeground(Color.DARK_GRAY); 
		
			
	}
	void setfontdata(Component l)
	{
		l.setFont(new Font("Calibri (body)",Font.PLAIN,20));
		
		l.setForeground(Color.BLACK);	
	}
	void clearRows(DefaultTableModel dm)
	{
		int rowCount = dm.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==submit)
		{
			try {
				show();
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    try {clearRows(machineModel);
		    clearRows(rawModel);
		    	addMachine(m,machineModel);
				addMaterials(rm,rawModel);
				selectOrder.setSelectedIndex(0);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 
		}
	}

	public void itemStateChanged(ItemEvent e) {
		 
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if((String) selectOrder.getSelectedItem()=="Select your order")
		    {
			 System.out.println("Select ur order");
			}
	        else {
			selectedorder=(String) selectOrder.getSelectedItem();

			try {
				o=odi.getTableData(selectedorder);
				managerid=odi.managerId(selectedorder);
				supervisorid=odi.supervisorId(selectedorder);
		        m=mdi.getMachineData(selectedorder);
		     
		      rm=rmdi.getRawData(selectedorder);
		      percent=odi.getProgress(selectedorder);
		     				System.out.println("Selectted order:"+selectedorder);
			} catch (ClassNotFoundException | SQLException | ParseException ep) {
				// TODO Auto-generated catch block
				ep.printStackTrace();
			
	        }	
			
	        }
		}
	}
	
	private void createColumns(JTable table,DefaultTableModel dtm)
	{ //get table model
		dtm=(DefaultTableModel) table.getModel();
		 if(dtm==rawModel)
	     	dtm.addColumn("Material No");
	     	else if(dtm==machineModel)
	         	dtm.addColumn("Machine No");
		dtm.addColumn("Name");
     	dtm.addColumn("Quantity");
     	
		
	}
	
	
	
	void show() throws ParseException{
		orderIdl.setText(selectedorder);
		orderNamel.setText(o.getNameOfProject());
		System.out.println("NAme="+o.getNameOfProject());
		companyl.setText(o.getCompany());
		System.out.println("company="+o.getCompany());
		quantityl.setText(String.valueOf(o.getQuantity()));
		
		SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/yyyy");
		Date d =formatter.parse("31/12/2050");
		if(o.getStartDate().before(d))
		{
	startdatel.setText(String.valueOf(o.getStartDate()));
		}
		else
			startdatel.setText("NOT ASSIGNED");
				
	
		d=formatter.parse("01/01/1970");
		if(o.getEndDate().after(d))
		{
			enddatel.setText(String.valueOf(o.getEndDate()));
				}
		else
			enddatel.setText("NOT ASSIGNED");
			
	
	managerAssignedl.setText(managerid);
	supervisorAssignedl.setText(supervisorid);
	rawMaterialsUsedl.setText("");
	machinesUsedl.setText("");
	manpowerl.setText(o.getManpower());
	currentProgressl.setText(String.format("%.02f", percent)+"%");
	
	setfontdata(orderIdl);
	setfontdata(orderNamel);
	setfontdata(companyl);
	setfontdata(quantityl);
	setfontdata(startdatel);
	setfontdata(enddatel);
	setfontdata(managerAssignedl);
	setfontdata(supervisorAssignedl);
	setfontdata(rawMaterialsUsedl);
	setfontdata(machinesUsedl);
	setfontdata(manpowerl);
	setfontdata(currentProgressl);
		
	
	
	}
	
	public JPanel getReport()
	{
		return reportPanel;
	}
	private void addMaterials(ArrayList<RawMaterials> m2,DefaultTableModel table_model) throws SQLException{
		//jbc_raw.
		
	for(RawMaterials m1:m2)
	{
		String rawdata[]={m1.getRawNo(),m1.getRawName(),String.valueOf(m1.getRawQuantity())};
		System.out.println(m1.getRawNo()+" "+m1.getRawName()+" "+m1.getRawQuantity());
		table_model.addRow(rawdata);
	}
	 
	}
	
	private void addMachine(ArrayList<Machine> m2,DefaultTableModel table_model) throws SQLException{
		//jbc_raw.
		
	for(Machine m1:m2)
	{
		String machinedata[]={m1.getMachineNo(),m1.getNameOFMachine(),String.valueOf(m1.getQuantityOfMachine())};
		System.out.println(m1.getMachineNo()+" "+m1.getNameOFMachine());
		table_model.addRow(machinedata);
	}
	 
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseCon dbc=new DatabaseCon();
		try {
			dbc.getDBConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			new ReportGeneration(dbc,managerId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
