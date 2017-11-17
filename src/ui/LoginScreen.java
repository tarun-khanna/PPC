package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.jfree.ui.RefineryUtilities;

import ppc.LaunchClass;

public class LoginScreen implements FocusListener {

	
	
	
	JFrame frm;
	JPanel panel;
	JTextField user ;
	JPasswordField pass;
	JButton loginButton;
	JLabel signUp;
	JLabel forgotPass;
	JPanel upperPanel;
	JLabel gifLabel;
	JDialog d;
	
	LaunchClass launchClass;
	
	
	public LoginScreen(LaunchClass lc) throws ClassNotFoundException, SQLException 
	{
		prepareGui();
		new DialogBox();
		this.launchClass=lc;
		
	}
	
	public void prepareGui()
	{
		frm = new JFrame("C4 FACTORY");
		
		
		panel = new JPanel(new GridLayout(3,1,10,10));

		user = new JTextField();
		String s = new String("Enter password");
		pass = new JPasswordField(s,20);
		
		Icon login = new ImageIcon("login.png");
		Image loginIcon= ((ImageIcon)login).getImage();
		//gifIcon = gifIcon.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		login = new ImageIcon(loginIcon);
		
		loginButton = new JButton();
		loginButton.setIcon(login);
		loginButton.setContentAreaFilled(false);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.setBorder(null);
		
		signUp = new JLabel(" SIGN UP ",SwingConstants.LEFT);
		forgotPass = new JLabel(" Forgot Password? ",SwingConstants.RIGHT);
		signUp.setForeground(Color.white);
		forgotPass.setForeground(Color.white);
		
		
		Font fontObj = new Font("Calibri(body)",Font.BOLD,20);
		
		
	

		pass.setEchoChar((char)0);
		
		user.addFocusListener(this);	
		pass.addFocusListener(this);
	
		user.setText("Enter username");
		user.setFont(fontObj);

		pass.setFont(fontObj);

		user.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pass.setBorder(new BevelBorder(BevelBorder.LOWERED));


		loginButton.addActionListener(new LoginActionListener(this));

		panel.setBorder(new EmptyBorder(5, 10, 30, 10));
		
		
		
		panel.add(user);
		panel.add(pass);
		panel.add(loginButton);

		
		
		panel.setBackground(Color.black);
		
		gifLabel=new JLabel();
		upperPanel=new JPanel();
		Icon gif = new ImageIcon("myGif4.gif");
		Image gifIcon= ((ImageIcon)gif).getImage();
	
		gif = new ImageIcon(gifIcon);
		gifLabel.setIcon(gif);
		upperPanel.add(gifLabel);
		upperPanel.setBackground(Color.black);
		
		JPanel centerPanel = new JPanel();
		JLabel loginImgLabel=new JLabel();
		Icon loginImg = new ImageIcon("loginImg.jpg");
		Image loginImgIcon= ((ImageIcon)loginImg).getImage();
		loginImg = new ImageIcon(loginImgIcon);
		loginImgLabel.setIcon(loginImg);
		centerPanel.add(loginImgLabel);
		centerPanel.setBackground(Color.black);
		
		frm.setLayout(new BorderLayout());
		frm.setSize(400, 690);
		frm.setResizable(false);
		frm.setBackground(Color.decode("#119796"));
		
	
		frm.add(upperPanel,BorderLayout.NORTH);
		frm.add(centerPanel, BorderLayout.CENTER);
		frm.add(panel, BorderLayout.SOUTH);
		
		RefineryUtilities.centerFrameOnScreen(frm);
		frm.setVisible(true);
		frm.addWindowListener(new WindowAdapter()
				{
					@Override
					public void windowClosing(WindowEvent e)
					{
						try {
							launchClass.dB.closeAll();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						frm.dispose();
					    System.exit(0);
					}
					
					
				});
		
	
	}
	@Override
	public void focusGained(FocusEvent fe) {
		
		if(fe.getSource() == user) 
		{
				
				if(new String(pass.getPassword()).equals("")  || new String(pass.getPassword()).equals("Enter password"))
				{		pass.setEchoChar((char)0);
					pass.setText("Enter password");
				}
					if(user.getText().equalsIgnoreCase("Enter Username"))
					user.setText("");
		}
		if(fe.getSource() == pass )
		{	
				
					if(user.getText().equals(""))
						user.setText("Enter username");
					pass.setEchoChar('*');
					pass.setText("");
	}}

	@Override
	public void focusLost(FocusEvent fe) {
	
	}
	

}
