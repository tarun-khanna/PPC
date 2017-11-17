package ui;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import ppc.LaunchClass;
public class AdminEdit extends  AddForm {

	
	JFrame frm;
	JPanel editpanel;
	JPanel buttons,details,deletePanel,updatePanel,blankPanel;
	JTextField user ;
	JButton delete,update;
	JButton saveUpdate;
	JButton yes;
	JButton no;
	JLabel username,status;
	CardLayout cardLayout;
	
	
	AdminEdit()
	{
		init();
	}
	public AdminEdit(LaunchClass launchClass)
	{
		this();
		this.launchClass=launchClass;
		
	}
	private void init()
	{
		
		cardLayout=new CardLayout();
		
		editpanel = new JPanel(new BorderLayout());
		buttons=new JPanel(new GridBagLayout());
		details=new JPanel(cardLayout);
		
		deletePanel=new JPanel(new GridBagLayout());
		deletePanel.setBackground(bg_color);//new Color(240,249,254,255));
		updatePanel=new JPanel(new BorderLayout());
		updatePanel.setBackground(bg_color);//new Color(240,249,254,255));
		blankPanel=new JPanel();
		blankPanel.setBackground(bg_color);//new Color(240,249,254,255));
		
		details.add(updatePanel,"UPDATE");
		details.add(deletePanel,"DELETE");
		details.add(blankPanel,"BLANK");
		
		Font fontObj = new Font("SANS_SERIF",Font.PLAIN,15);
		user = new JTextField();
		user.setFont(fontObj);
		user.setBorder(new BevelBorder(BevelBorder.LOWERED));
		update = new JButton("UPDATE"); 
		update.setBackground(Color.cyan);
		update.addMouseListener(new MyMouseActions(this));
		delete = new JButton("DELETE");
		delete.setBackground(Color.cyan);
		delete.addMouseListener(new MyMouseActions(this));
		status = new JLabel("*Enter valid user name",SwingConstants.LEFT);
		status.setForeground(Color.white);
		username=new JLabel("Enter user name : ");
		username.setForeground(Color.WHITE);
		
		
		
		
		
		buttons.add(username,AddForm.setGBConstraints(0,0,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5)));
		buttons.add(user,AddForm.setGBConstraints(1,0,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.BASELINE,1.0,new Insets(5,5,1,5)));
		buttons.add(update,AddForm.setGBConstraints(2,0,GridBagConstraints.BASELINE,new Insets(5,5,5,5)));
		buttons.add(delete,AddForm.setGBConstraints(3,0,GridBagConstraints.BASELINE,new Insets(5,5,5,5)));
		buttons.add(status,AddForm.setGBConstraints(1,1,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(0,5,2,5)));
		buttons.setBackground(bg_color);
		
		
		delete.addActionListener(new AdminActionListener(this,"EDIT"));
		update.addActionListener(new AdminActionListener(this,"EDIT"));
		
		editpanel.add(buttons,BorderLayout.NORTH);
		editpanel.add(details,BorderLayout.CENTER);
		editpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
//		frm = new JFrame();
//		frm.setResizable(false);
//		frm.setSize(700, 700);
//		frm.add(editpanel);
//		frm.setVisible(true);
	}
	
	
	public JPanel getEditpanel() {
		return editpanel;
	}
	
	public void deleteUserDetails(String username ,String emailId,long phone)
	{
		
		Icon yesIcon = new ImageIcon("yes.png");
		Image yesImg= ((ImageIcon)yesIcon).getImage();
		yesIcon = new ImageIcon(yesImg);
		yes=new JButton(yesIcon);
		yes.setContentAreaFilled(false);
	
		Icon noIcon = new ImageIcon("no.png");
		Image noImg= ((ImageIcon)noIcon).getImage();
		noIcon = new ImageIcon(noImg);
		no=new JButton(noIcon);
		no.setContentAreaFilled(false);
		
	 	firstnameLabel=new JLabel(username);
	    emailIdLabel=new JLabel(emailId);
		phoneLabel=new JLabel(String.valueOf(phone));	
	
		JLabel name=new JLabel("NAME :");
		name.setHorizontalAlignment(JLabel.RIGHT);
		JLabel email=new JLabel("EMAIL ID : ");
		email.setHorizontalAlignment(JLabel.RIGHT);
		JLabel ph=new JLabel("PHONE NO :");
		ph.setHorizontalAlignment(JLabel.RIGHT);
		
		formattingLabels(firstnameLabel);
		formattingLabels(name);
		formattingLabels(emailIdLabel);
		formattingLabels(email);
		formattingLabels(phoneLabel);
		formattingLabels(ph);
		
		/*confirm panel */
		JPanel confirmPanel=new JPanel();
		confirmPanel.setLayout(new GridBagLayout());
		confirmPanel.setBackground(Color.GRAY);
		
		JLabel confirmLabel=new JLabel("ARE YOU SURE YOU WISH TO DELETE THIS USER ? ");
		confirmLabel.setFont(new Font("SANS_SERIF",Font.ITALIC,15));
		confirmLabel.setForeground(Color.black);
		
		confirmPanel.add(confirmLabel,AddForm.setGBConstraints(0,0,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(10,5,10,5)));
		confirmPanel.add(yes,AddForm.setGBConstraints(1,0,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		confirmPanel.add(no,AddForm.setGBConstraints(2,0,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		/*heading panel*/
		 JLabel deleteLabel=new JLabel(" DELETE USER ");
	     deleteLabel.setFont(new Font("Calibri (Body)", Font.BOLD,30 ));
	     deleteLabel.setForeground(Color.BLACK);
		 JPanel headingPanel=new JPanel();
		 headingPanel.setBackground(Color.GRAY);
		 headingPanel.setForeground(Color.WHITE);
		 headingPanel.setLayout(new GridBagLayout());
		 headingPanel.add(deleteLabel);
		
		deletePanel.add(headingPanel, setGBConstraints(0,0,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.PAGE_START,0.0,new java.awt.Insets(10,0,50, 0)));
		deletePanel.add(name,AddForm.setGBConstraints(1,1,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5)));
		deletePanel.add(firstnameLabel,AddForm.setGBConstraints(2,1,GridBagConstraints.BASELINE,new Insets(5,5,5,5)));
		deletePanel.add(email,AddForm.setGBConstraints(1,2,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5)));
		deletePanel.add(emailIdLabel,AddForm.setGBConstraints(2,2,GridBagConstraints.BASELINE,new Insets(5,5,5,5)));
		deletePanel.add(ph,AddForm.setGBConstraints(1,3,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5)));
		deletePanel.add(phoneLabel,AddForm.setGBConstraints(2,3,GridBagConstraints.BASELINE,new Insets(5,5,5,5)));
		deletePanel.add(confirmPanel,setGBConstraints(0,4,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.LINE_END,1.0,new Insets(45,0,150,0)));
	
		yes.addMouseListener(new MyMouseActions(this));
		no.addMouseListener(new MyMouseActions(this));
	}
	
	
}
