package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bean.Orders;
import dao.DatabaseCon;
import dao.OrdersDaoImpl;





/////////////////////////////////////////////////////////////////////////

public class MonthlyReport implements ActionListener {

	int year = Calendar.getInstance().get(Calendar.YEAR);
	JButton[] button = new JButton[12];
	JLabel disp; 
	int month;
	String supId;
	
	DatabaseCon db;
	
	ArrayList<Orders> orderList;
	ArrayList<Orders> allocatedOrders;
	
	

	ArrayList<Icon> yearList;

Connection con;
PreparedStatement pstmt,pstmt2;
ResultSet rs,rs2;

	
	public MonthlyReport(DatabaseCon db,String supId)
	{
		this.db=db;
		con = db.getCon();
		this.supId = supId;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException
	{
//		MonthlyReport monRep = new MonthlyReport();
//		monRep.display();
		
	}

	
	private void displayYear() throws ClassNotFoundException, SQLException, ParseException, IOException
	{
		
		
		int currMon;
		int start;
		int end;
	
	

		
		
			
		//	Calendar curr = Calendar.getInstance();
		String[] months = {"January" , "February" , "March" , "April" , "May" , "June" , "July" , 
                "August" , "September" , "October" , "November" , "December"};
		
		///////////////////Img Icons added to calendar
		yearList = new ArrayList<Icon>();
		
		Icon jan = new ImageIcon("jan.png");
		Image janIcon= ((ImageIcon)jan).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		jan = new ImageIcon(janIcon);
		yearList.add(jan);
	
		Icon feb = new ImageIcon("feb.png");
		Image febIcon= ((ImageIcon)feb).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		feb = new ImageIcon(febIcon);
		yearList.add(feb);
		
		
		Icon mar = new ImageIcon("mar.png");
		Image marIcon= ((ImageIcon)mar).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		mar = new ImageIcon(marIcon);
		yearList.add(mar);
		
		Icon april = new ImageIcon("april.png");
		Image aprilIcon= ((ImageIcon)april).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		april = new ImageIcon(aprilIcon);
		yearList.add(april);
	
		Icon may = new ImageIcon("may.png");
		Image mayIcon= ((ImageIcon)may).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		may = new ImageIcon(mayIcon);
		yearList.add(may);
		
		Icon june = new ImageIcon("june.png");
		Image juneIcon= ((ImageIcon)june).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		june = new ImageIcon(juneIcon);
		yearList.add(june);
		
		Icon july = new ImageIcon("july.png");
		Image julyIcon= ((ImageIcon)july).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		july = new ImageIcon(julyIcon);
		yearList.add(july);
	
		Icon aug = new ImageIcon("aug.png");
		Image augIcon= ((ImageIcon)aug).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		aug = new ImageIcon(augIcon);
		yearList.add(aug);
		
		
		Icon sep = new ImageIcon("sep.png");
		Image sepIcon= ((ImageIcon)sep).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		sep = new ImageIcon(sepIcon);
		yearList.add(sep);
		
		Icon oct = new ImageIcon("oct.png");
		Image octIcon= ((ImageIcon)oct).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		oct = new ImageIcon(octIcon);
		yearList.add(oct);
	
		Icon nov = new ImageIcon("nov.png");
		Image novIcon= ((ImageIcon)nov).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		nov = new ImageIcon(novIcon);
		yearList.add(nov);
		
		Icon dec = new ImageIcon("dec.png");
		Image decIcon= ((ImageIcon)dec).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		dec = new ImageIcon(decIcon);
		yearList.add(dec);
			
		
		
		
		
		for(int i = 0 ; i < 12 ; ++i)
		{	
//			button[i].setText(months[i]);
			

			//button[i].setText(months[i]);
			button[i].setText("");
			button[i].setIcon(yearList.get(i));
			button[i].setHorizontalTextPosition(JButton.CENTER);
			button[i].setVerticalTextPosition(JButton.CENTER);
			button[i].setContentAreaFilled(false);

			button[i].setForeground(Color.decode("#119796"));
			//button[i].setForeground(Color.WHITE);
			

			currMon=((year-1)*12)+(i); // i am calculating the total months upto the current months
//
//			System.out.println("For month " + (i+1) +"=");
//			System.out.println("currMon = " + currMon + " start = " + start + " end = " + end);
//			System.out.println("");
//			
			//LOGIC FOR PRINTING PROJUCT IN PARTICULAR MONTHS
		
			OrdersDaoImpl oImpl = new OrdersDaoImpl(db);
			for(Orders o : allocatedOrders)
			{
				oImpl.setDuration(o);
				start = ((o.getStartCalDate().get(Calendar.YEAR )-1)*12)+(o.getStartCalDate().get(Calendar.MONTH));
			end = ((o.getEndCalDate().get(Calendar.YEAR )-1)*12)+(o.getEndCalDate().get(Calendar.MONTH));
			
			
			if((currMon >=  start && currMon <= end) && ((year == o.getStartCalDate().get(Calendar.YEAR)) || (year == o.getEndCalDate().get(Calendar.YEAR))))
			{
			button[i].setText(button[i].getText() + " [" + o.getNameOfProject() + "] ");	
			}

			
		}
		
	}
		
		disp.setText(Integer.toString(year));
		
		
	}
	
	
	
	
	
	public JPanel display() throws ClassNotFoundException, SQLException, ParseException, IOException {
		
		JPanel parent = new JPanel();
		
		JPanel top = new JPanel();
		JPanel center = new JPanel();
		
	//////////////////////////////////////////////////////////////////////////////////////
		
//		
//		String url = "jdbc:oracle:thin:@localhost:1521:XE";
//		String user = "system";
//		String pwd = "ayushi";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		con = DriverManager.getConnection(url,user,pwd);
		System.out.println("Connection Established Successfully!!!");
		orderList = new ArrayList<Orders>();
		
		
		OrdersDaoImpl oImpl = new OrdersDaoImpl(db);
		orderList = oImpl.getOrdersOfSupervisor(supId);
		
		
		
//		allTasks = new ArrayList<MyTask>();
//		
//		
//		
//		
//		pstmt = con.prepareStatement("Select * from tasks where orderId = ?");
		
		
//		
//		
//		pstmt = con.prepareStatement("Select * from orders where supervisorId = ? ");
//		
//		System.out.println("Selected supervisor ID :" + supId);
//		pstmt.setString(1, supId);
//		rs = pstmt.executeQuery();
//		
//		while(rs.next())
//		{
//			System.out.println("MONTHLY REPORT:::::::::::::: order no : " + rs.getString(1) + "\torder name : " + rs.getString(2) + " due date : " + rs.getDate(13) + " allocated : " + rs.getInt(11));
//			
//			Product p = new Product(con);
//			
//			p.productNo = rs.getString(1);
//			p.productName = rs.getString(2);
//			p.d_dueDate = rs.getDate(13);
//			//System.out.println(" due date : " + p.d_dueDate);
//			if(rs.getInt(11)==1)
//				{p.allocated=true;																																																							
//				allProducts.add(p);}
//			else
//			{
//				p.allocated=false;
//				
//			}
//			p.setDuration();
//			
//			
//		}
//		
		allocatedOrders = new ArrayList<Orders>();
		for(Orders o : orderList)
		{
		if(o.getAllocated()==1)
			allocatedOrders.add(o);
			
			
		}
		System.out.println("ALL PRODUCTS ::::" + allocatedOrders);
		
		
	
	/////////////////////////////////////////////////////////////////////////////
		
		
		
		Icon prev = new ImageIcon("prev.png");
		Image prevIcon= ((ImageIcon)prev).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		prev = new ImageIcon(prevIcon);
		
		JButton prevButton = new JButton();
		prevButton.setContentAreaFilled(false);
		prevButton.setIcon(prev);
	
		disp = new JLabel("");
		disp.setFont(new Font("Calibri (Body)", Font.BOLD, 20));
		disp.setHorizontalAlignment(disp.CENTER);
		
		Icon next = new ImageIcon("next.png");
		Image nextIcon= ((ImageIcon)next).getImage();
		//adduserIcon = adduserIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		next = new ImageIcon(nextIcon);
		
		JButton nextButton = new JButton();
		nextButton.setContentAreaFilled(false);
		nextButton.setIcon(next);		
		prevButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				year--;
				try {
					displayYear();
				} catch (ClassNotFoundException | SQLException | ParseException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				year++;
				try {
					displayYear();
				} catch (ClassNotFoundException | SQLException | ParseException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		top.add(prevButton);
		top.add(Box.createGlue());
		top.add(disp);
		top.add(Box.createGlue());
		

		top.add(nextButton);
		top.setBackground(Color.gray);
		center.setSize(400, 400);
	
		GridLayout layout = new GridLayout(4, 3);
		layout.preferredLayoutSize(center);
		
		layout.setVgap(0);
		center.setLayout(layout);
		center.setBorder(null);
		center.setBackground(Color.DARK_GRAY);
		
		for(int i = 0 ; i < 12 ; ++i)
		{
			button[i] = new JButton();
			
//			button[i].bo
			button[i].addActionListener(this);
			//button[i].setText(months[i]);
			button[i].setContentAreaFilled(false);
			button[i].setBorder(null);
		//	button[i].setBorderPainted(false);
			button[i].setMargin(null);
			button[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			button[i].setMargin(new Insets(0,0,0,0));

			center.add(button[i]);
		}
		
		parent.setLayout(new BorderLayout());
		parent.add(top,BorderLayout.NORTH);
		parent.add(center, BorderLayout.CENTER);
		parent.setSize(400, 400);
		displayYear();
		parent.setVisible(true);
		return parent;
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		
		for(int i=0;i<12;++i)
		{
			if(ae.getSource() == button[i])
			{
				System.out.println("Month" + (i+1));
				month = i;
				
			}
		}
		
		
		DayStatus ds = null;
		try {
			ds = new DayStatus(month,year,allocatedOrders,con);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ds.display();
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
		}	
		
	}

	}
