package ui;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.TimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import bean.Machine;
import bean.Orders;
import dao.DatabaseCon;
import dao.MachineDaoImpl;
import dao.MyTaskDaoImpl;
import dao.OrdersDaoImpl;
import bean.MyTask;


 

public class SupervisorScreen2 {
	
	IntervalCategoryDataset dataset;
	JFreeChart chart;
	CategoryPlot plot ;
	CategoryItemRenderer renderer;
	ChartPanel chartPanel = new ChartPanel(chart);
	ArrayList<Machine> machineList = new ArrayList<Machine>();
	ArrayList<Orders> orderList = new ArrayList<Orders>();
	ArrayList<Orders> pendingOrders = new ArrayList<Orders>();

	JComboBox<String> myOrders;

	
	
	
	
	//JSlider slider;
	
	JComboBox<String> myMachines;
	private static final String NOT_SELECTABLE_OPTION = "Select The product to plan ";
	private static final String NOT_SELECTABLE_OPTION_2 = "Select The Machine to assign ";
	JPanel mainFrm;
	JPanel top;
	JPanel center;
	JButton submit;
	DateChooser startDate;
	DateChooser endDate;
	//JComboBox myProducts;
	//JDialog myProducts;
	Orders selectedOrder;
	  JDialog productsDialog;
	  String supId;
	
	  ArrayList<Machine> machinesOfOrder;
	  

		Calendar currCal = Calendar.getInstance();
		
		Calendar cStart = Calendar.getInstance();
		
		Calendar cEnd = Calendar.getInstance();
	  
	  
	  
	  
	  ////////////DB CONNECTION
	


Connection con;
PreparedStatement pstmt,pstmt2,pstmt3;
ResultSet rs,rs2,rs3;
DatabaseCon db;
public SupervisorScreen2(DatabaseCon db,String supId) throws SQLException , ClassNotFoundException, ParseException, IOException
{
	
con=db.getCon();
this.supId = supId;
//String url = "jdbc:oracle:thin:@localhost:1521:XE";
//String user = "system";
//String pwd = "ayushi";
//Class.forName("oracle.jdbc.driver.OracleDriver");
//con = DriverManager.getConnection(url,user,pwd);
System.out.println("Connection Established Successfully!!!");

myMachines = new JComboBox();
//myMachines.setName("SELECT THE MACHINE...");
myMachines.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN, 20));
((JLabel)myMachines.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);        //for center alignment of text in combobox

myMachines.setModel(new DefaultComboBoxModel<String>() {
private static final long serialVersionUID = 1L;
boolean selectionAllowed = true;

@Override
public void setSelectedItem(Object anObject) {
	if (!NOT_SELECTABLE_OPTION_2.equals(anObject)) {
		super.setSelectedItem(anObject);
	} else if (selectionAllowed) {
		// Allow this just once
		selectionAllowed = false;
		super.setSelectedItem(anObject);
	}	
}
});
myMachines.addItem(NOT_SELECTABLE_OPTION_2);



myOrders = new JComboBox<String>();

machinesOfOrder = new ArrayList<Machine>();


	myOrders.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN, 20));
	((JLabel)myOrders.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);        //for center alignment of text in combobox

	myOrders.setModel(new DefaultComboBoxModel<String>() {
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
myOrders.addItem(NOT_SELECTABLE_OPTION);






OrdersDaoImpl oImpl = new OrdersDaoImpl(db);
orderList = oImpl.getOrdersOfSupervisor(supId);


for(Orders o : orderList)
{
if(o.getAllocated()==0 && o.getGotResources()==1)
{
	pendingOrders.add(o);
	myOrders.addItem(o.getNameOfProject());
}


MachineDaoImpl mImpl = new MachineDaoImpl(db);

machinesOfOrder = mImpl.getMachineData(o.getOrderId());
machineList.addAll(machinesOfOrder);

}
//
for(Machine m : machineList)
{
myMachines.addItem(m.getMachineNo());
//	machineList.add(m);


}

createChart();





mainFrm = new JPanel();
top = new JPanel();
center = new JPanel();



submit = new JButton("ADD");

////////////
submit.addActionListener(new ActionListener()
{

@Override
public void actionPerformed(ActionEvent arg0)  {
Date cStart_d = date(cStart.get(Calendar.DAY_OF_MONTH), cStart.get(Calendar.MONTH), cStart.get(Calendar.YEAR));
Date cEnd_d= date(cEnd.get(Calendar.DAY_OF_MONTH), cEnd.get(Calendar.MONTH), cEnd.get(Calendar.YEAR));

System.out.println("!!!!! " + selectedOrder.getOrderId());

OrdersDaoImpl oImpl;
try {
	oImpl = new OrdersDaoImpl(db);
	oImpl.updateAllocation(selectedOrder);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

System.out.println("machine List " + machineList.size());

for(Machine m : machineList)
{
if(m.getMachineNo() == myMachines.getSelectedItem())
{
System.out.println("SELECTED MACHINE : " + m.getMachineNo());	

MyTask mt = new MyTask(m.getMachineNo(),selectedOrder.getOrderId(), cStart_d, cEnd_d);
m.assignTask(mt);




/////////////


MyTaskDaoImpl mtImpl = new MyTaskDaoImpl(con);

try {
	mtImpl.addTaskInDB(mt);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}
}
	
createChart();
SwingUtilities.updateComponentTreeUI(mainFrm);

}
});


//ArrayList<Product> unallocatedProducts = new ArrayList<Product>();



top.add(myOrders);



myOrders.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent ie) {
//
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					int index = myOrders.getSelectedIndex();
					
					System.out.println("int index = " + index);
					
					
					selectedOrder=pendingOrders.get(index-1);
					
					String s = "Order ID : " +selectedOrder.getOrderId() + "\nOrder Name : " + selectedOrder.getNameOfProject() + "\nQuantity : " + selectedOrder.getQuantity() + " " + selectedOrder.getUnit() ;
					JOptionPane.showMessageDialog(top, s, "ORDER INFO", JOptionPane.INFORMATION_MESSAGE);
					
				}	
			
			}
		});



startDate = new DateChooser();
endDate = new DateChooser();
startDate.datePicker = startDate.getDatePicker();




startDate.datePicker.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cStart.setTime((Date) startDate.datePicker.getModel().getValue());

if(cStart.before(currCal))
{

submit.setEnabled(false);

//JOptionPane alert = new JDialog();	
//JLabel alertText = new JLabel("SELECTED START DATE HAS ALREADY PASSED !!..Please Re-Enter the start date!");
JOptionPane.showMessageDialog(top, "SELECTED START DATE HAS ALREADY PASSED !!..Please Re-Enter the start date!", "INVALID START DATE", JOptionPane.ERROR_MESSAGE);
}
else
{
submit.setEnabled(true);	
}
			}}
);





top.add(startDate.datePicker);

endDate.datePicker = endDate.getDatePicker();


endDate.datePicker.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cEnd.setTime((Date)endDate.datePicker.getModel().getValue());
				
				if(cEnd.after(selectedOrder.getDueCalDate()))
				{
					
					
					JOptionPane.showMessageDialog(top, "Choosen allocation will cross the DUE DATE for project.CONTINUE?", "DUE DATE CROSSED", JOptionPane.WARNING_MESSAGE);
					submit.setEnabled(true);
				}
							
				else if(cEnd.before(cStart))
							{
				
								submit.setEnabled(false);
				
								JOptionPane.showMessageDialog(top, "END DATE CANNOT BE BEFORE START DATE", "INVALID END DATE", JOptionPane.ERROR_MESSAGE);
					
							}
				else
				{
					System.out.println("Theeeeeeeeek hai");
				submit.setEnabled(true);	
				}
							}}
	);
				






top.add(endDate.datePicker);

top.add(myMachines);
top.add(submit);


mainFrm.setLayout(new BorderLayout());

mainFrm.add(top,BorderLayout.NORTH);
mainFrm.add(chartPanel,BorderLayout.SOUTH);

//mainFrm.pack();
mainFrm.setVisible(true);






    

}
	
	


public JPanel getMainFrm() {
	return mainFrm;
}




public void createChart()
{
	
	dataset = createSampleDataset();
	chart = ChartFactory.createGanttChart(
	        "Planning and Scheduling",  // chart title
	        "Machines",              // domain axis label
	        "Date",              // range axis label
	        dataset,             // data
	        true,                // include legend
	        true,                // tooltips
	        false                // urls
	    );

	plot = (CategoryPlot) chart.getPlot();
//	plot.configureRangeAxes();
	
	CategoryItemRenderer renderer = plot.getRenderer();
	
	
	renderer.setSeriesPaint(0, Color.blue);
	
	chartPanel.setChart(chart);
	 chartPanel.setDomainZoomable(true);
	chartPanel.setRangeZoomable(true);
	 chartPanel.setAutoscrolls(true);
	 
	// chartPanel.add(slider,BorderLayout.SOUTH);
	
	}


TaskSeries task_series = new TaskSeries("Scheduled");
//TaskSeries task_series2 = new TaskSeries(" Available");
TaskSeriesCollection collection = new TaskSeriesCollection();

ArrayList<Task> taskList = new ArrayList<Task>();




private IntervalCategoryDataset createSampleDataset() {

	//task_series2.removeAll();
    task_series.removeAll();
    collection.removeAll();
 //   System.out.println("+++" + s1.isEmpty() + "+++" + collection.getSeriesCount());
    

	
   for(Machine m : machineList)
   {
	 //  Task avail = new Task(m.no,date(1,1,2017),date(31,12,2017));
	   Task mTask = new Task(m.getMachineNo(), date(1,1,2017),date(31,12,2017));
	  // task_series2.add(avail);
	   for(MyTask mt : m.getAllottedTask())
	   {
		// System.out.println("Inner LOop!");
	 	mTask.addSubtask(mt.getTask());
		   
	   }
	
	   
	//   System.out.println("outer LOop!");
	   if(!m.getAllottedTask().isEmpty())
	   {task_series.add(mTask);
	   }
   }
    
    

   
  
    collection.add(task_series);
    //collection.add(task_series2);
    return collection;
}	

/**
 * Utility method for creating <code>Date</code> objects.
 *
 * @param day  the date.
 * @param month  the month.
 * @param year  the year.
 *
 * @return a date.
 */
private static Date date( int day,  int month,  int year) {

	//System.out.println("***" + day + "***" + month + "***" + year);
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, day);
    Date result = cal.getTime();
  //  System.out.println("{}{}{}"+ result);
    return result;

}












	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException
{

//		Connection connect;
//		
//		String url = "jdbc:oracle:thin:@localhost:1521:XE";
//		String user = "system";
//		String pwd = "1234";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		connect = DriverManager.getConnection(url,user,pwd);
//		new SupervisorScreen2(connect,"C4_782");
}




	




}
