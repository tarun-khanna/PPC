package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import bean.Admin;
import bean.ProductionManager;
import bean.Supervisor;
import ppc.LaunchClass;

public class HomeScreen {

	Color bg_color = Color.decode("#119796");
	JPanel toolPanel;
	JToolBar toolbar;
	JButton home;
	JButton signout;
	
	GridBagLayout gbl;
	
	JMenuBar mBar;
	JMenuItem exit;
	JPanel sidePanel;
	JPanel mainPanel;
	JPanel homePanel;
	JPanel[] mainPanels;
	JFrame mainFrame;
	JMenu file;
	JMenu edit;
	JMenu settings;
	JMenuItem file1;
	JMenuItem file2;
	JMenuItem edit1;
	JLabel func , welcomeMessage;
	JPanel funcPanel;
	int funcRows;
	ArrayList < JButton > funcButtons = new ArrayList<JButton>();
	String role;
	CardLayout cardlayout;
	//HomeScreen Home;
	Admin admin;
	ProductionManager productManager;
	Supervisor supervisor;
	
	LaunchClass lc;
	HomeScreen(LaunchClass lc,String role,Object obj)
	{
		this.lc=lc;
		this.role = role;
		if(role.equalsIgnoreCase("admin"))
			this.admin=(Admin)obj;
		if(role.equalsIgnoreCase("product manager"))
			this.productManager=(ProductionManager)obj;
		if(role.equalsIgnoreCase("supervisor"))
			this.supervisor=(Supervisor)obj;
		prepareGui();
	
	}

	public void prepareGui()
	{
		
		gbl = new GridBagLayout();
		mainFrame = new JFrame();
		toolbar = new JToolBar();
		
		Icon homeIcon = new ImageIcon("homeicon.png");
		Image homeImg= ((ImageIcon)homeIcon).getImage();
		homeImg = homeImg.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		homeIcon = new ImageIcon(homeImg);
				
		
		home = new JButton(homeIcon);
		home.addActionListener(new HomeScreenListener(this));
		
		Icon signoutIcon = new ImageIcon("signout.png");
		Image signoutImg= ((ImageIcon)signoutIcon).getImage();
		signoutImg = signoutImg.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		signoutIcon = new ImageIcon(signoutImg);
		
		signout = new JButton(signoutIcon);
		toolPanel = new JPanel();
		toolPanel.setLayout(new BoxLayout(toolPanel,BoxLayout.X_AXIS));
		signout.addActionListener(new HomeScreenListener(this));
		
	
		toolPanel.add(home);
		
		Icon heading = new ImageIcon("heading.png");
		Image headingIcon= ((ImageIcon)heading).getImage();
		
		heading = new ImageIcon(headingIcon);
		
		
		
		Box.Filler hgap = new Box.Filler(new Dimension(50,43), new Dimension(300,43), new Dimension(450,43));
		hgap.setOpaque(true);
		hgap.setBackground(Color.DARK_GRAY);
		
		Box.Filler hgap1 = new Box.Filler(new Dimension(50,43), new Dimension(300,43), new Dimension(450,43));
		hgap1.setOpaque(true);
		hgap1.setBackground(Color.DARK_GRAY);
		
		
		toolPanel.add(hgap);
		JLabel temp = new JLabel();
		temp.setIcon(heading);
		
		toolPanel.add(temp);
	
		toolPanel.add(hgap1);
		toolPanel.add(signout);
		
		
		Icon set = new ImageIcon("set.png");
		Image img = ((ImageIcon) set).getImage();
		Image newimg = img.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		Icon newSet = new ImageIcon(newimg);
		
		 settings = new JMenu();
		 settings.setIcon(newSet);
		  exit = new JMenuItem("SIGN OUT");
		 settings.add(exit); 
		 
		
		 funcButtons = new ArrayList<JButton>();
		 sidePanel = new JPanel();
		 sidePanel.setLayout(new BorderLayout());
		 func = new JLabel("! WELCOME !");
		
		 
		 cardlayout = new CardLayout();
		 
		 mainPanel = new JPanel(cardlayout);
		 
		 homePanel  =new JPanel();
		 homePanel.setBackground(Color.DARK_GRAY);
		 welcomeMessage=new JLabel();
		 welcomeMessage.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 25));
		 welcomeMessage.setForeground(Color.WHITE);
		 homePanel.add(welcomeMessage);
		 
	//	func.setForeground(Color.white);
		
		func.setFont(new Font("Calibri (body)", Font.BOLD, 25));
		func.setHorizontalAlignment(func.CENTER);
		//func.setBorder(new EmptyBorder(20, 30, 30, 30));
		func.setForeground(Color.WHITE);
	//	funcPanel = new JPanel();
		funcPanel = getFuncPanel();
		funcPanel.setBackground(Color.lightGray);
		
		
		if(funcRows>4)
		{
			funcPanel.setLayout(new GridLayout(funcRows,1,0,20));
			funcPanel.setBorder(new EmptyBorder(30,0,20,0));

		}
		else
		{
		funcPanel.setLayout(new GridLayout(funcRows,1,0,80));
		funcPanel.setBorder(new EmptyBorder(50,0,100,0));
		}
		sidePanel.add(func,BorderLayout.NORTH);
		sidePanel.add(funcPanel,BorderLayout.CENTER);
		
		sidePanel.setBorder(BorderFactory.createLoweredBevelBorder());
	
		
		
	
		home.setBackground(Color.DARK_GRAY);
		signout.setBackground(Color.DARK_GRAY);
	
		
		toolPanel.setBackground(bg_color);
		
		
		
		toolPanel.setBorder(new EmptyBorder(20,0,20,0));
		funcPanel.setBackground(bg_color);
		sidePanel.setBackground(bg_color);
		
	
		mainFrame.add(toolPanel,BorderLayout.NORTH);
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		mainFrame.add(sidePanel,BorderLayout.WEST);
		mainFrame.setSize(600, 600);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
	}

public JPanel getFuncPanel() {
	
	JPanel pan = new JPanel();
	
	HomeScreenListener hs = new HomeScreenListener(this);
	if(role.equalsIgnoreCase("admin"))
	{
		
		funcRows = 3;
		func.setText(" ADMIN ");
		//JPanel homePanel=new JPanel();
		//homePanel.setLayout(new CardLayout());
		welcomeMessage.setText(" WELCOME ADMIN");
		
		mainPanels = new JPanel[funcRows+1];
		mainPanels[0]=homePanel;
		mainPanel.add(mainPanels[0],"HOME");
		
		for(int i=0;i<3;++i)
		{
			JButton b = new JButton();
			b.setContentAreaFilled(false);
			//b.setBorder(new EmptyBorder(50,50,50,50));
			
			funcButtons.add(b);
			mainPanels[i+1]=new JPanel();
			mainPanels[i+1].setLayout(new CardLayout());
			//mainPanels[i].setBackground(Color.BLACK);
						
		}
		
		String[] buttonName = {"ADD NEW USER ","UPDATE / DELETE USER ","VIEW USER "};

		int i=0;
	
		Icon adduser = new ImageIcon("adduser.png");
		Image adduserIcon= ((ImageIcon)adduser).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		adduser = new ImageIcon(adduserIcon);
		funcButtons.get(0).setIcon(adduser);
	
		Icon updateuser = new ImageIcon("updateuser.png");
		Image updateIcon= ((ImageIcon)updateuser).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		updateuser = new ImageIcon(updateIcon);
		funcButtons.get(1).setIcon(updateuser);
		
		
		Icon viewuser = new ImageIcon("viewusers.png");
		Image viewIcon= ((ImageIcon)viewuser).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		viewuser = new ImageIcon(viewIcon);
		funcButtons.get(2).setIcon(viewuser);
		
		

		
		for(JButton jb : funcButtons)
		{
		
//		jb.setIcon(defaultIcon);	
		//jb.setText(buttonName[i]);	
		pan.add(jb);
		jb.addActionListener(hs);
		
		
		mainPanel.add(mainPanels[i+1],buttonName[i]);
				
		++i;
		}
		
	}
	else if(role.equalsIgnoreCase("supervisor"))
	{
		
		funcRows = 3;
		func.setText(" SUPERVISOR ");
		

		welcomeMessage.setText(" WELCOME "+supervisor.getName());

		
		mainPanels = new JPanel[funcRows+1];
		mainPanels[0]=homePanel;
		mainPanel.add(mainPanels[0],"HOME");

		for(int i=0;i<3;++i)
		{
			JButton b = new JButton();
			b.setContentAreaFilled(false);
			funcButtons.add(b);
			mainPanels[i+1]=new JPanel();
			mainPanels[i+1].setLayout(new CardLayout());
//			mainPanels[i].setBackground(Color.BLACK);
					
		}
		
		String[] buttonName = {"PLANNING AND SCHEDULING ","VIEW STATUS","RESOURCE REQUEST"};

		int i=0;
		
		
		Icon schedule = new ImageIcon("schedule.png");
		Image scheduleIcon= ((ImageIcon)schedule).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		schedule = new ImageIcon(scheduleIcon);
		funcButtons.get(0).setIcon(schedule);
	
		Icon status = new ImageIcon("status.png");
		Image statusIcon= ((ImageIcon)status).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		status = new ImageIcon(statusIcon);
		funcButtons.get(1).setIcon(status);
		
		
		Icon request = new ImageIcon("request.png");
		Image requestIcon= ((ImageIcon)request).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		request = new ImageIcon(requestIcon);
		funcButtons.get(2).setIcon(request);		
		
		
		for(JButton jb : funcButtons)
		{
			
		//jb.setText(buttonName[i]);	
		pan.add(jb);
		jb.addActionListener(hs);
		
		mainPanel.add(mainPanels[i+1],buttonName[i]);
		++i;
		}
		
	}
	else if(role.equalsIgnoreCase("product manager"))
	{
		funcRows = 6;
		func.setText(" PRODUCTION MANAGER ");

		welcomeMessage.setText(" WELCOME "+productManager.getName());
		mainPanels = new JPanel[funcRows+1];
		mainPanels[0]=homePanel;
		mainPanel.add(mainPanels[0],"HOME");

	
		for(int i=0;i<funcRows;++i)
		{
			

			JButton b = new JButton();
			b.setContentAreaFilled(false);
			funcButtons.add(b);
		
		//	funcButtons.add(new JButton());
			mainPanels[i+1]=new JPanel();
			mainPanels[i+1].setLayout(new CardLayout());
			//mainPanels[i].setBackground(Color.BLACK);
			
		
		}
		
		
		String[] buttonName = {"ADD/OPEN PROJECT","ResourceManagement","SELECT SUPERVISOR","VIEW STATUS","PENDING REQUESTS","REPORT GENERATION"};
		
		int i=0;
		
		Icon newproject = new ImageIcon("newproject.png");
		Image newprojectIcon= ((ImageIcon)newproject).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		newproject = new ImageIcon(newprojectIcon);
		funcButtons.get(0).setIcon(newproject);
		
		Icon resource = new ImageIcon("resource.png");
		Image resourceIcon= ((ImageIcon)resource).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		resource = new ImageIcon(resourceIcon);
		funcButtons.get(1).setIcon(resource);		
		
		Icon assign = new ImageIcon("assign.png");
		Image assignIcon= ((ImageIcon)assign).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		assign = new ImageIcon(assignIcon);
		funcButtons.get(2).setIcon(assign);
		

		Icon status2 = new ImageIcon("status2.png");
		Image status2Icon= ((ImageIcon)status2).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		status2 = new ImageIcon(status2Icon);
		funcButtons.get(3).setIcon(status2);
		Icon pending = new ImageIcon("pending.png");
		Image pendingIcon= ((ImageIcon)pending).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		pending = new ImageIcon(pendingIcon);
		funcButtons.get(4).setIcon(pending);
		
		Icon report = new ImageIcon("report.png");
		Image reportIcon= ((ImageIcon)report).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		report = new ImageIcon(reportIcon);
		funcButtons.get(5).setIcon(report);		
		
				
		
		
		
		
		
		
		for(JButton jb : funcButtons)
		{
			
		//jb.setText(buttonName[i]);	
		pan.add(jb);
		jb.addActionListener(hs);
		
		mainPanel.add(mainPanels[i+1],buttonName[i]);
		++i;
		}
	}
	
	
	
	return pan;

	
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




}
