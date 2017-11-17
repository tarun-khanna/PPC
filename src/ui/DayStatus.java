package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import bean.MyTask;
import bean.Orders;
import dao.DatabaseCon;
import dao.MyTaskDaoImpl;




////////////////////////////////////////////////////////////////////////////



public class DayStatus {
	

	
	int month;// = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH); 
	int year;// = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);; 
	JLabel l = new JLabel("", JLabel.CENTER); 
	String day = ""; 
	JDialog d; 
	JButton[] button = new JButton[49];
	
	
	
	ArrayList<Orders> allocatedOrders;
	
	ArrayList<MyTask> allTasksOfOrder;
	ArrayList<MyTask> allTasks;

Connection con;
PreparedStatement pstmt,pstmt2;
ResultSet rs,rs2;
DatabaseCon db;


	

	public DayStatus (int month,int year,ArrayList<Orders> allocatedOrders,Connection con) throws SQLException
	{
		this.month = month;
		this.year = year;
		this.allocatedOrders=allocatedOrders;
		this.con = con;
		
		allTasks = new ArrayList<MyTask>();
		allTasksOfOrder = new ArrayList<MyTask>();
		
		
		MyTaskDaoImpl mtImpl = new MyTaskDaoImpl(con);
		
		for(Orders o : allocatedOrders)
		{
			allTasksOfOrder = mtImpl.getTasksOfOrder(o.getOrderId());
			allTasks.addAll(allTasksOfOrder);
		}
		
		
	}
	
public void displayDate() throws ClassNotFoundException, SQLException, ParseException { 
	
	
//	System.out.println("p.start get calendar month : " + p.start.get(Calendar.MONTH));

	
	
	JLabel pl = new JLabel("p1");
		
	
for (int x = 7; x < button.length; x++) 
button[x].setText(""); 
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(" MMMM yyyy "); 
java.util.Calendar cal = java.util.Calendar.getInstance(); 
cal.set(year, month, 1); 
//System.out.println("cal.get calendar month : " + cal.get(Calendar.MONTH) );

int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK); 
int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); 
for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
{
//	System.out.println("cal.get calendar month : " + cal.get(Calendar.MONTH) );

button[x].setText("<html> " + day); 
button[x].setToolTipText(button[x].getText());

//LOGIC FOR PRINTING PROJUCT IN PARTICULAR MONTHS
for(MyTask mt : allTasks)
{
	//p.setDuration();
	

Calendar taskStart = Calendar.getInstance();
taskStart = mt.getStartCalendarDate();

Calendar taskEnd = Calendar.getInstance();
taskEnd = mt.getEndCalendarDate();
	
if(taskStart.get(Calendar.MONTH) == taskEnd.get(Calendar.MONTH))
{
	if(day >=  taskStart.get(Calendar.DAY_OF_MONTH) && (day <= taskEnd.get(Calendar.DAY_OF_MONTH)) && month== taskStart.get(Calendar.MONTH) )
			{
	//	button[x].setText(button[x].getText() + "\n" + mt.getTaskName());	
		String s = new String( button[x].getText() + " \n " + mt.getTaskName());
		System.out.println("in if day =" + day +" s=" + s);
		button[x].setText(s.replaceAll("\\n", "<br>") );	
		button[x].setToolTipText(button[x].getText());
			}

//System.out.println("p.start.get(Calendar.MONTH) == p.end.get(Calendar.MONTH)");
}
else
{
	
	///System.out.println("ELSE-----p.start.get(Calendar.MONTH) == p.end.get(Calendar.MONTH)");
	

	if(day>= taskStart.get(Calendar.DAY_OF_MONTH ) && month== taskStart.get(Calendar.MONTH))
	{
		String s = new String(button[x].getText() + " \n " + mt.getTaskName());
		System.out.println("in else1 day =" + day +" s=" + s);

		//button[x].get
		button[x].setText(s.replaceAll("\\n", "<br>"));	
		button[x].setToolTipText(button[x].getText());

		//button[x].setText(button[x].getText() + "\n" + mt.getTaskName());	
		
	}
	
	else if(day <= taskEnd.get(Calendar.DAY_OF_MONTH) && month== taskEnd.get(Calendar.MONTH))
	{
		String s = new String(button[x].getText() + " \n " + mt.getTaskName());
		System.out.println("in else2 day =" + day +" s=" + s);

		button[x].setText(s.replaceAll("\\n", "<br>"));	
		button[x].setToolTipText(button[x].getText());

		//button[x].setText(button[x].getText() + "\n" + mt.getTaskName());	
		
		
	}
	
	else if(month>taskStart.get(Calendar.MONTH) && month<taskEnd.get(Calendar.MONTH))
	{
		String s = new String( button[x].getText() + " \n " + mt.getTaskName());
		System.out.println("in else3 day =" + day +" s=" + s);

		button[x].setText(s.replaceAll("\\n", "<br>") );	
		button[x].setToolTipText(button[x].getText());

		//button[x].setText(button[x].getText() + "\n" + mt.getTaskName());	

	}
}

}
/*
if(day >=  p.start.get(Calendar.DAY_OF_MONTH) && (day <= p.end.get(Calendar.DAY_OF_MONTH)) && (cal.get(Calendar.MONTH) == p.start.get(Calendar.MONTH)))
{
button[x].setText(day + "\n" + p.productName);	
}
*/

button[x].setText(button[x].getText() + "</html>");
}
l.setText(sdf.format(cal.getTime())); 
//d.setTitle("Date Picker"); 
} 
		



public void display() throws ClassNotFoundException, SQLException, ParseException
{

	//d = new JDialog(); 
//	d.setModal(true);
	
	JDialog parent = new JDialog();
	
	String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" }; 
	JPanel p1 = new JPanel(new GridLayout(7, 7)); 
//	p1.setPreferredSize(); 

	for (int x = 0; x < button.length; x++) { 
	final int selection = x; 
	button[x] = new JButton(); 
	button[x].setFocusPainted(false); 
	button[x].setBackground(Color.white); 
	if (x < 7)
	{ 
	button[x].setText(header[x]); 
	button[x].setForeground(Color.red); 
	} 
	p1.add(button[x]); 
	}
	
	
	
	JPanel p2 = new JPanel(new GridLayout(1, 3)); 
	JButton previous = new JButton("<< Previous"); 
	previous.addActionListener(new ActionListener() { 
	public void actionPerformed(ActionEvent ae) { 
	month--; 
	try {
		displayDate();
	} catch (ClassNotFoundException | SQLException | ParseException e) {
		
		e.printStackTrace();
	} 
	} 
	}); 
	p2.add(previous); 
	p2.add(l); 
	JButton next = new JButton("Next >>"); 
	next.addActionListener(new ActionListener() { 
	public void actionPerformed(ActionEvent ae) { 
	month++; 
	try {
		displayDate();
	} catch (ClassNotFoundException | SQLException | ParseException e) {
		
		e.printStackTrace();
	} 
	} 
	}); 
	p2.add(next); 
	parent.add(p1, BorderLayout.CENTER); 
	parent.add(p2, BorderLayout.SOUTH); 
	parent.setModal(true);
	parent.setSize(700, 400); 
//	d.setLocationRelativeTo(parent); 
	displayDate(); 
	//parent.setName("Day Status");
	RefineryUtilities.centerFrameOnScreen(parent);
	parent.setVisible(true); 
	} 
	




	public static void main(String[]  args) throws ClassNotFoundException, SQLException, ParseException
	{
		//Product p = new Product();
//		
//	JFrame f = new JFrame();	
//	
//	
//	
//	Connection connect;
//	PreparedStatement ps,ps2;
//	ResultSet r,r2;
//
//	
//	
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String user = "SYSTEM";
//	String pwd = "1234";
//	Class.forName("oracle.jdbc.driver.OracleDriver");
//	connect = DriverManager.getConnection(url,user,pwd);
//	System.out.println("Connection Established Successfully!!!");
//	
//	ArrayList<Product> allProd = new ArrayList<Product>();
//	
//	ps = connect.prepareStatement("Select * fro")
//
//	
//	
//	DayStatus ds = new DayStatus(9,2017, allProd,connect);
//	ds.display();	
//		
		
		
		


		
		
	}
}
