package ui;



import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.Request;
import dao.DatabaseCon;
import dao.RequestDaoImpl;

public class ManagerRequestPage {

	
	
	 static DatabaseCon db;
	 String managerId;
	 JPanel requestPanel,tablePanel,labelPanel,buttonPanel;
	 JLabel requestLabel;
	 JTable reqTable;
	 DefaultTableModel dtmTable; 
	 JScrollPane scrollTable;
	 JButton approve ,cancel;
	 static int approvedRowIndex=-1;
	 
	 public ManagerRequestPage() throws SQLException
	 {
		 init();
	 }
	public ManagerRequestPage(DatabaseCon db,String managerId) throws SQLException
	{
		
		this.db = db;
		this.managerId=managerId;
		init();
	}
	
	
	public void init() throws SQLException
	{
//		JFrame frame = new JFrame();
//		frame.setLayout(new CardLayout());
		
		requestPanel=new JPanel(new BorderLayout());//requestPanel.setBackground(Color.black);
		tablePanel=new JPanel(new CardLayout());//tablePanel.setBackground(Color.black);
		buttonPanel=new JPanel(new GridLayout(1,2));
		requestLabel=new JLabel();
		reqTable =new JTable();
		scrollTable=new JScrollPane();
		dtmTable=new DefaultTableModel();
		
		reqTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        reqTable.setModel(dtmTable);
        reqTable.setDefaultRenderer(Object.class, new MyRenderer());
        
        JTextField tf = new JTextField();
        tf.setEditable(false);
        DefaultCellEditor editor=new  DefaultCellEditor (tf);
        reqTable.setDefaultEditor(Object.class, editor);
        
        //reqTable.setEnabled(false);
       	scrollTable = new JScrollPane(reqTable);
	    dtmTable.addColumn("FROM");
	    dtmTable.addColumn("DATE");
	    dtmTable.addColumn("ORDERID");
	    dtmTable.addColumn("REQUEST");
//	    dtmTable.addColumn("APPROVE REQUEST");
//	    dtmTable.addColumn("CANCEL REQUEST");
	    createRow();
	    tablePanel.add(scrollTable);
	    tablePanel.setBackground(Color.GRAY);
	    
	    
	    labelPanel=new JPanel();
	    labelPanel.setLayout(new java.awt.GridBagLayout());

	    requestLabel.setFont(new java.awt.Font("SANS_SERIF", 1, 20));
	    requestLabel.setText(" PENDING REQUEST ");
	    requestLabel.setForeground(Color.WHITE);
	    labelPanel.setBackground(Color.DARK_GRAY);     
	       
	  
	        labelPanel.add(requestLabel, setGBConstraints(0,0,GridBagConstraints.LINE_START,new java.awt.Insets(5, 5, 5, 5)));
	        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
	        gridBagConstraints.gridx = 1;
	        gridBagConstraints.gridy = 0;
	        gridBagConstraints.gridwidth = 3;
	        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
	        gridBagConstraints.weightx = 1.0;
	        labelPanel.add(new javax.swing.JSeparator(), gridBagConstraints);
	       
	        Icon aproveIcon = new ImageIcon("accept.png");
			Image aproveImg= ((ImageIcon)aproveIcon).getImage();
			//aproveImg = signoutImg.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
			aproveIcon = new ImageIcon(aproveImg);
			
			//signout = new JButton(signoutIcon);
	        
	        
	    approve=new JButton(aproveIcon);
	    approve.setContentAreaFilled(false);
	    Icon rejectIcon = new ImageIcon("reject.png");
		Image rejectImg= ((ImageIcon)rejectIcon).getImage();
		//aproveImg = signoutImg.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		rejectIcon = new ImageIcon(rejectImg);
	    
	    cancel=new JButton(rejectIcon);
	    cancel.setContentAreaFilled(false);
	    approve.addActionListener(new RequestActionListener(this));
	    cancel.addActionListener(new RequestActionListener(this));
	    buttonPanel.add(approve);
	    buttonPanel.add(cancel);
	    buttonPanel.setBackground(Color.DARK_GRAY);
	    
	    requestPanel.add(labelPanel,BorderLayout.NORTH);
	    requestPanel.add(tablePanel,BorderLayout.CENTER);
	    requestPanel.add(buttonPanel,BorderLayout.SOUTH);
	    
//	    frame.add(requestPanel);
//	    frame.setSize(500, 800);
//	    frame.setVisible(true);
	}
	
	
	public JPanel getRequestPanel() {
		return requestPanel;
	}
	public void setRequestPanel(JPanel requestPanel) {
		this.requestPanel = requestPanel;
	}
	public void createRow() throws SQLException
	{
		RequestDaoImpl req=new RequestDaoImpl(db.getCon());
		ArrayList<Request> reqRow=req.receiveRequest(managerId);
		

		for(Request pendingReq:reqRow)
		{
			Object row[]={pendingReq.getSender(),pendingReq.getOnDate(),pendingReq.getOrderId(),pendingReq.getMessage()};
			dtmTable.addRow(row);
		}
		
		
	}
	

	
	static GridBagConstraints setGBConstraints(int gridx,int gridy,int anchor,Insets in)
	{
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = in;
		
		return gbc;
	}
	
	static GridBagConstraints setGBConstraints(int gridx,int gridy,int gridwidth,int fill,int anchor,double weightx,Insets in)
	{
		GridBagConstraints gbc=setGBConstraints(gridx,gridy,anchor,in);
		
		gbc.gridwidth=gridwidth;
		gbc.fill=fill;
		gbc.weightx=weightx;
		
		return gbc;
	}
	
}

