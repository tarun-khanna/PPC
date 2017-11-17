package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dao.DatabaseCon;
import dao.OrdersDao;
import dao.OrdersDaoImpl;
import dao.RequestDaoImpl;

public class SupervisorRequestPage implements ActionListener, FocusListener   {

	 DatabaseCon db;
	 String supervisorId;
	 JPanel requestPanel;
	 JPanel outerPanel;
	 JLabel label;
	 JComboBox<String> order;
	 private static final String NOT_SELECTABLE_OPTION = " Select ORDER ID ... ";
	 JTextArea message;
	 JButton send;
	 
	 public SupervisorRequestPage()
	 {
		 init();
	 }
	public SupervisorRequestPage(DatabaseCon db,String supervisorId)
	{
		
		this.db = db;
		this.supervisorId=supervisorId;
		init();
	}
	
	
//	public JPanel getPanel()
//	{
//	
//		
//		//return mainPanel;
//		
//	}
//	
	
	
	

	public void init()
	{
//		JFrame fram=new JFrame("request");
		outerPanel = new JPanel();
		requestPanel=new JPanel();
		label = new JLabel();
		
		label.setText("REQUEST  FORM");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Calibri (Body)", Font.PLAIN, 40));
		label.setForeground(Color.BLACK);
		outerPanel.setBackground(Color.GRAY);
		message = new JTextArea(5,25);
		message.setText("Enter your request here.....");
		message.addFocusListener(this);
		send=new JButton();
		
		Icon sendImg = new ImageIcon("sendrequest.png");
		Image sendIcon= ((ImageIcon)sendImg).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		sendImg = new ImageIcon(sendIcon);
		send.setContentAreaFilled(false);
		send.setBorderPainted(false);
		send.setIcon(sendImg);
		
		order = new <String>JComboBox();
		
		send.addActionListener(this);
		 order.setModel(new DefaultComboBoxModel<String>() {
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

	     order.addItem(NOT_SELECTABLE_OPTION);
	    
	     OrdersDao orders=null;
		orders = new OrdersDaoImpl(db);
	     
	     ArrayList<String> orderList=null;
		try {
			orderList = orders.getOrders(supervisorId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     
	     for(String s:orderList)
	    	 order.addItem(s);
	     
	     //order.addItemListener(this);
	     outerPanel.setLayout(new BorderLayout());
	     
	     requestPanel.setBorder(new EmptyBorder(80, 80, 80, 80));
	     requestPanel.setLayout(new BorderLayout(10,50));
	     
	     
	     requestPanel.setBackground(Color.DARK_GRAY);
	     requestPanel.add(order,BorderLayout.NORTH);
	     requestPanel.add(message,BorderLayout.CENTER);
	     requestPanel.add(send,BorderLayout.SOUTH);
	     
	     outerPanel.add(label, BorderLayout.NORTH);
	     outerPanel.add(requestPanel, BorderLayout.CENTER);
//	     fram.add(requestPanel);
//	     fram.setLayout(new CardLayout());
//	     fram.setSize(500, 500);
//	     fram.setVisible(true);
		
	}
	public JPanel getRequestPanel() {
		return outerPanel;
	}
	public void setRequestPanel(JPanel outerPanel) {
		this.outerPanel = outerPanel;
	}
//	public static void main(String ...strings )
//	{
//		new SupervisorRequestPage(new DatabaseCon(),"C4_643");
//		
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== send)
		{
			String orderId=(String) order.getSelectedItem();
			String reqMessage=message.getText();
			
			if(order.getSelectedIndex()==0)
			{

				JOptionPane.showMessageDialog(requestPanel, "YOU MUST SELECT THE ORDER", "NO ORDER SELECTED", JOptionPane.ERROR_MESSAGE);
				
			}
			else if(reqMessage.isEmpty())
			{
				JOptionPane.showMessageDialog(requestPanel, "REQUEST MESSAGE CANNOT BE BLANK", "INVALID REQUEST", JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
			
			 OrdersDao orders=null;
				orders = new OrdersDaoImpl(db);
				String receiverId=null;
				try {
					receiverId=	orders.getProductionManager(orderId);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				RequestDaoImpl req=new RequestDaoImpl(db.getCon());
				try {
					int success=req.sendRequest(orderId,supervisorId, receiverId, reqMessage,Calendar.getInstance());
					if(success == 1)
					{
						JOptionPane.showMessageDialog( requestPanel," Request Sent to Respective Production manager " , "Request", JOptionPane.INFORMATION_MESSAGE);
						message.setText("");
					}
					else
						JOptionPane.showMessageDialog( requestPanel," Request Not Send...Try again!! " , "Request", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}	
				
			
		}
		
		
	}
	@Override
	public void focusGained(FocusEvent fe) {
		if(fe.getSource()==message)
		{
			message.setText("");
		}
	}
	@Override
	public void focusLost(FocusEvent fe) {
		// TODO Auto-generated method stub
		
	}

}
