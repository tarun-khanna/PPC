package ui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.DatabaseCon;
import dao.OrdersDao;
import dao.OrdersDaoImpl;

public class ProgressChart {

	JPanel mainPanel;
	String managerId;
	DatabaseCon db;
	ArrayList<String> allOrderIds ;
	
	public ProgressChart(String managerId,DatabaseCon db) throws ClassNotFoundException, SQLException, IOException, ParseException
	{
		this.managerId = managerId;
		this.db = db;
		mainPanel = new JPanel();
	
	
	JFreeChart barChart = ChartFactory.createBarChart(
	         "EXPECTED PROGRESS REPORT",           
	         "PRODUCTS",            
	         "PERCENT",            
	         createDataset(),          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	         
	      ChartPanel chartPanel = new ChartPanel( barChart );        
	      //chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ));        
	      mainPanel.add(chartPanel,BorderLayout.CENTER);
	      //mainPanel.setVisible(true);
	}	
	      
	      public CategoryDataset createDataset( ) throws ClassNotFoundException, SQLException, IOException, ParseException {
	    	  DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
	    	  OrdersDao order = new OrdersDaoImpl(db);
	    	  allOrderIds = order.getOrderIDs(managerId);
	    	  Double progress;
	    	  for(String oId : allOrderIds)
	    	  {
	    		 progress = order.getProgress(oId);
	    		  dataset.addValue(progress, oId , "Orders");
	    	  }
	    	  	
	          return dataset; 
	       }


	      public JPanel getProgressPanel()
	      {
	    	  return mainPanel;
	      }
	      
	      //
//public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException
//{
//new ProgressChart("C4_164");
//}
}
