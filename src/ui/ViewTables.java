package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXCollapsiblePane;

import ppc.LaunchClass;

public class ViewTables {
	static Color hd_color = Color.decode("#119796");
	Color bg_color = Color.DARK_GRAY;	
	JFrame frame;
	JPanel mainPanel;
	JPanel viewLoginDetails,loginPanel;
	JButton loginButton,refreshLogin;
	JXCollapsiblePane cpLogin;
	JPanel viewProductionManager,productionPanel;
	JButton productionButton,refreshProduction;
	JXCollapsiblePane cpProduction;
	JPanel viewSupervisors,supervisorPanel;
	JButton supervisorButton,refreshSupervisor;
	JXCollapsiblePane cpSupervisor;
	JTable loginTable,productionTable,supervisorTable;
	
	DefaultTableModel loginDtm,productionDtm,supervisorDtm;
	JScrollPane loginJsp,productionJsp,supervisorJsp;
	LaunchClass lc;
	
	public ViewTables(LaunchClass lc){
	   this.lc=lc;
		
		frame=new JFrame();
	   mainPanel=new JPanel();
	   mainPanel.setLayout(new GridBagLayout());
	   mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	   mainPanel.setBackground(bg_color);
	   
	   //toggleIcon 
	    Icon downKeyIcon = new ImageIcon("downArrow.png");
		Image downKeyImg= ((ImageIcon)downKeyIcon).getImage();
		downKeyImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		downKeyIcon = new ImageIcon(downKeyImg);
		
		Icon upKeyIcon = new ImageIcon("upArrow.png");
		Image upKeyImg= ((ImageIcon)upKeyIcon).getImage();
		upKeyIcon = new ImageIcon(upKeyImg);

		
	  // ------firstpanel
	   loginDtm=new DefaultTableModel();
	   loginTable=new JTable();
	   loginTable.setDefaultRenderer(Object.class, new MyRenderer());
	   loginPanel=new JPanel(new GridBagLayout());

	 	loginTable.setPreferredScrollableViewportSize(new Dimension(400,100));
     
	 	loginTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        loginTable.setModel(loginDtm);
       	loginJsp = new JScrollPane(loginTable);
	    createColumns(loginTable, loginDtm);
	    cpLogin=new JXCollapsiblePane();
	    loginButton=new JButton(cpLogin.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
	    loginButton.setText("");
		loginButton.setContentAreaFilled(false);
	    Action logintoggleAction = cpLogin.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
	    logintoggleAction.putValue(JXCollapsiblePane.COLLAPSE_ICON,upKeyIcon);
	    logintoggleAction.putValue(JXCollapsiblePane.EXPAND_ICON,downKeyIcon);
	    loginPanel.add(loginJsp,setGBConstraints(0,0,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
	  
	   		
		  
		    
	    //Refreshbuttons
	    
	    Icon refreshIcon = new ImageIcon("sync.png");
		Image refreshImg= ((ImageIcon)refreshIcon).getImage();
		refreshIcon = new ImageIcon(refreshImg);
		
		refreshLogin=new JButton(refreshIcon);
		refreshLogin.setContentAreaFilled(false);
	    refreshProduction=new JButton(refreshIcon);
	    refreshProduction.setContentAreaFilled(false);
	    refreshSupervisor=new JButton(refreshIcon);
	    refreshSupervisor.setContentAreaFilled(false);
	    
	    refreshLogin.addActionListener(new AdminActionListener(this,"VIEW"));
	    refreshProduction.addActionListener(new AdminActionListener(this,"VIEW"));
	    refreshSupervisor.addActionListener(new AdminActionListener(this,"VIEW"));
	    
	    //  loginPanel.add();
	   loginPanel.setBackground(hd_color);
	   cpLogin.add(loginPanel);
	   //-----adding table
	   
	   viewLoginDetails=new JPanel();  
	   viewLoginDetails.setLayout(new GridBagLayout());
	  
	   viewLoginDetails.add(createSeparator("VIEW LOGIN DETAILS ",refreshLogin,loginButton),setGBConstraints(0,0,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
	  // viewLoginDetails.add(refreshLogin,setGBConstraints(0,1,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(1,1,1,1)));
	   viewLoginDetails.add(cpLogin,setGBConstraints(0,2,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(1,1,1,1)));
	   viewLoginDetails.setBackground(bg_color);
	   //mainPanel.add(viewLoginDetails,setGBConstraints(0,0,1,GridBagConstraints.BOTH,GridBagConstraints.NORTHWEST,1.0,new Insets(0,0,0,0)));
	   mainPanel.add(viewLoginDetails);
	   //--------------------secondpanel
	   productionDtm=new DefaultTableModel();
	   productionTable=new JTable();
	   productionPanel=new JPanel(new GridBagLayout());
       productionTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	   productionTable.setModel(productionDtm);
	   productionTable.setDefaultRenderer(Object.class, new MyRenderer());
	   productionTable.setPreferredScrollableViewportSize(new Dimension(400,150));
	   productionJsp = new JScrollPane(productionTable);
	   createColumns(productionTable, productionDtm);
	   cpProduction=new JXCollapsiblePane();
	   productionButton=new JButton(cpProduction.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
	   productionButton.setText("");
	   productionButton.setContentAreaFilled(false);
	    Action prodtoggleAction = cpProduction.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
	    prodtoggleAction.putValue(JXCollapsiblePane.COLLAPSE_ICON,upKeyIcon);
	    prodtoggleAction.putValue(JXCollapsiblePane.EXPAND_ICON,downKeyIcon);
	    productionPanel.add(productionJsp,setGBConstraints(0,0,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
	   productionPanel.setBackground(hd_color);
	   cpProduction.add(productionPanel);
	   viewProductionManager=new JPanel();
	   viewProductionManager.setLayout(new GridBagLayout());
	   viewProductionManager.add(createSeparator("VIEW PRODUCTION MANAGER ",refreshProduction,productionButton),setGBConstraints(0,0,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
	   viewProductionManager.add(cpProduction,setGBConstraints(0,1,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
	   viewProductionManager.setBackground(bg_color);
	   mainPanel.add(viewProductionManager);
	  // mainPanel.add(viewProductionManager,setGBConstraints(0,1,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(0,0,0,0)));
	  //----third panel
	   cpSupervisor=new JXCollapsiblePane();
	   supervisorButton=new JButton(cpSupervisor.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
	   supervisorButton.setText("");
	   supervisorButton.setContentAreaFilled(false);
	   Action supervisortoggleAction = cpSupervisor.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
	   supervisortoggleAction.putValue(JXCollapsiblePane.COLLAPSE_ICON,upKeyIcon);
	   supervisortoggleAction.putValue(JXCollapsiblePane.EXPAND_ICON,downKeyIcon);
	   //--------------------------------------

	   supervisorDtm=new DefaultTableModel();
	   supervisorTable=new JTable();
	   supervisorPanel=new JPanel(new GridBagLayout());
	   supervisorTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	   supervisorTable.setModel(supervisorDtm);
	   supervisorTable.setDefaultRenderer(Object.class, new MyRenderer());
	   supervisorTable.setPreferredScrollableViewportSize(new Dimension(400,150));
	   supervisorJsp = new JScrollPane(supervisorTable);
	   createColumns(supervisorTable, supervisorDtm);
	   supervisorPanel.add(supervisorJsp,setGBConstraints(0,0,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
	   supervisorPanel.setBackground(hd_color);
	   
	 
	   cpSupervisor.add(supervisorPanel);
	   viewSupervisors=new JPanel();
	   viewSupervisors.setLayout(new GridBagLayout());
	   viewSupervisors.add(createSeparator("VIEW SUPERVISORS ",refreshSupervisor,supervisorButton),setGBConstraints(0,0,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
	   viewSupervisors.add(cpSupervisor,setGBConstraints(0,1,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
	   viewSupervisors.setBackground(bg_color);
	   //mainPanel.add(viewSupervisors,setGBConstraints(0,2,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(0,0,0,0)));
	  mainPanel.add(viewSupervisors);
	   
	 
	   
//		frame.add(mainPanel);
//		frame.setSize(1000,800);
//		frame.setVisible(true);
	}
	
	
	public JPanel getMainPanel() {
		return mainPanel;
	}


	private void createColumns(JTable table,DefaultTableModel dtm)
	{ //get table model
		
		dtm=(DefaultTableModel) table.getModel();
		if(table==productionTable && dtm==productionDtm)
		{
			dtm.addColumn("Username");
			dtm.addColumn("Name");
			dtm.addColumn("Gender");
			dtm.addColumn("DOB");
			dtm.addColumn("Address");
		
			dtm.addColumn("Email");
			dtm.addColumn("Phone number");		
		}
		if(table==loginTable && dtm==loginDtm)
		{
			dtm.addColumn("Username");
			dtm.addColumn("Password");
			dtm.addColumn("Role");
		}
		if(table==supervisorTable && dtm==supervisorDtm)
		{

			dtm.addColumn("Username");
			dtm.addColumn("Name");
			dtm.addColumn("Gender");
			dtm.addColumn("DOB");
			dtm.addColumn("Address");
		
			dtm.addColumn("Email");
			dtm.addColumn("Phone number");
		}
		
	}
	
	

	public static void main(String[] args) {
		//new ViewTables(lc);
	}

	static	JPanel createSeparator(String title , JButton refresh,JButton toggle)
	{
		JPanel jPanel1 = new javax.swing.JPanel();
		JLabel  loginLabel = new javax.swing.JLabel();
		JSeparator  loginSeparator = new javax.swing.JSeparator();

		 jPanel1.setLayout(new java.awt.GridBagLayout());

	     loginLabel.setFont(new java.awt.Font("SANS_SERIF", 1, 14));
	     loginLabel.setText(title);
	     loginLabel.setForeground(Color.WHITE);
	     jPanel1.setBackground(hd_color);     
	       
	  
	        jPanel1.add(loginLabel, setGBConstraints(0,0,GridBagConstraints.LINE_START,new java.awt.Insets(5, 5, 5, 5)));
	        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
	        gridBagConstraints.gridx = 1;
	        gridBagConstraints.gridy = 0;
	        gridBagConstraints.gridwidth = 1;
	        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
	        gridBagConstraints.weightx = 1.0;
	        jPanel1.add(loginSeparator, gridBagConstraints);
	        jPanel1.add(refresh,setGBConstraints(2,0,GridBagConstraints.LINE_END,new Insets(5,0,5,0)));
	        jPanel1.add(toggle,setGBConstraints(3,0,GridBagConstraints.LINE_END,new Insets(5,0,5,0)));
	        return jPanel1;
	
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