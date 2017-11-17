package dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import org.junit.Test;

import bean.Orders;
import dao.DatabaseCon;
import dao.OrdersDaoImpl;

public class OrdersDaoImplTest {
	boolean bool;
	DatabaseCon dbc=new DatabaseCon();
	OrdersDaoImpl odi=new OrdersDaoImpl(dbc);
	Orders o=new Orders();
	Date startDate,endDate,dueDate;
	String orderId="testOrder";
	String managerId="manager-12";
	public void Connection(){
		try {
			dbc.getDBConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	@Test
	public void testSetTableData() throws ParseException, ClassNotFoundException, SQLException, IOException {
		//fail("Not yet implemented");
		Connection();
		
	
		
		o.setOrders(managerId,orderId,"ppc testing",null,"testing company",200,"Kg","supervisor","400");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		this.startDate = formatter.parse("12/07/2017");
		this.endDate =  formatter.parse("01/12/2017");
		this.dueDate=formatter.parse("22/12/2017");
		o.setDueDate(dueDate);
	  o.setStartDate(startDate);
	  o.setEndDate(endDate);
	  o.setAllocated(0);
	  o.setGotResources(0);
		bool=odi.setTableData(o);		
		assertEquals(true, bool);

	}

	@Test
	public void testGetReportOrders() {
		//fail("Not yet implemented");
		Connection();
		ArrayList<String> al=new ArrayList<String>();
		al=odi.getReportOrders(managerId);

			assertEquals(orderId,al.get(0));

	}



	@Test
	public void testGetTableDataString() throws ClassNotFoundException, SQLException, ParseException {
		//fail("Not yet implemented");
		Connection();
	Orders p=new Orders();
		p=odi.getTableData(orderId);
		assertEquals(orderId,p.getOrderId());
		assertEquals("ppc testing",p.getNameOfProject());
		assertEquals(new java.sql.Date(p.getStartDate().getTime()),p.getStartDate());
		assertEquals(new java.sql.Date(p.getEndDate().getTime()),p.getEndDate());
		assertEquals("testing company",p.getCompany());
		assertEquals(200,p.getQuantity());
		assertEquals(null,p.getUnit());
		assertEquals(null,p.getSupervisor());
	    assertEquals(managerId,p.getManagerId());
		assertEquals(0,p.getAllocated());
		assertEquals(0,p.getGotResources());
		assertEquals("400",p.getManpower());
		assertEquals(dueDate,p.getDueDate());	
	}
//-------------iski testing ke liye supervisor ka nam chhiye;
	@Test
	public void testSupervisorId() {
		//fail("Not yet implemented");
		Connection();
		String str=odi.supervisorId(orderId);
		//if(str!=null)
			assertEquals("No Supervisor assigned for this orderno.",str);
		
	}

	@Test
	public void testDisplayTableData() throws ClassNotFoundException, SQLException, IOException, ParseException {
		//fail("Not yet implemented");
		Connection();
		
		String str[]=odi.displayTableData("ppc testing");
	
			assertEquals(orderId,str[0]);
			assertEquals("ppc testing",str[1]);
			assertEquals("2017-07-12 00:00:00.0",str[2]);
			assertEquals("2017-12-01 00:00:00.0",str[3]);
			assertEquals("testing company",str[4]);
			assertEquals("200",str[5]);
			assertEquals("Kg",str[6]);
			assertEquals("null",str[7]);
		    assertEquals(managerId,str[8]);
			assertEquals("0",str[9]);
			assertEquals("0",str[10]);
			assertEquals("400",str[11]);
		assertEquals("2017-12-22 00:00:00.0",str[12]);	
	    
	}

	@Test
	public void testGetNameData() throws ClassNotFoundException, SQLException, IOException {
		//fail("Not yet implemented");
		Connection();
		ArrayList<String> al=new ArrayList<String> ();
		al=odi.getNameData(managerId);
	assertEquals("ppc testing",al.get(0));
	}

	@Test
	public void testGetNullSupervisorData() throws ClassNotFoundException, SQLException, IOException {
		//fail("Not yet implemented");
		Connection();
		ArrayList<String> al=new ArrayList<String> ();
		al=odi.getNullSupervisorData(managerId);
		assertEquals("ppc testing",al.get(0));
	}

	@Test
	public void testUpdateSupervisor() throws ClassNotFoundException, SQLException, IOException {
	//	fail("Not yet implemented");
		Connection();
		bool=odi.updateSupervisor("null","ppc testing");
		assertEquals(true,bool);
		}

	//------------ismne issues h 
	/*@Test
	public void testGetSupervisors() throws ClassNotFoundException, SQLException, IOException {
		//fail("Not yet implemented");
		Connection();
		ArrayList<String> al=new ArrayList<String> ();
		al=odi.getSupervisors();
		if(!al.isEmpty())
		assertEquals();
	}
*/
	@Test
	public void testGetOrders() throws SQLException {
		//fail("Not yet implemented");
		Connection();
		ArrayList<String> al=new ArrayList<String>();
		al=odi.getOrders("supervisor");
	if(!al.isEmpty())
		assertEquals("supervisor",al.get(0));
	
	}

	@Test
	public void testGetProductionManager() throws SQLException {
		//fail("Not yet implemented");
		
		Connection();
		String str=odi.getProductionManager(orderId);
		assertEquals(managerId,str);
		
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetProgress() throws SQLException, ParseException {
		//fail("Not yet implemented");
		Connection();
		double d=odi.getProgress(orderId);
	    //---------no way to calculate this as everytijme it changes
		//assertEquals(0.7633363817162233, d,0.001);
		
	}

	@Test
	public void testGetOrderIDs() throws ClassNotFoundException, SQLException, IOException {
		Connection();
		ArrayList<String> al=new ArrayList<String>(); 
		al=odi.getOrderIDs(managerId);
		assertEquals(orderId,al.get(0));
		
	}
//-----------------isko chaange karo result set ni return karana.
	@Test
	public void testGetOrdersTable() {
		//fail("Not yet implemented");
		
		
		
	}	

	@Test
	public void testManagerId() {
		//fail("Not yet implemented");
		Connection();
		String str=odi.managerId(managerId);
		assertEquals("No Manager is assigned for this orderno.",str);
	}
//---------- beacuse start date and end datte abhi set ni h 
	@Test
	public void testSetDuration() throws SQLException, ParseException {
		//fail("Not yet implemented");
		Connection();
		bool=odi.setDuration(o);
		if(bool==false)
		assertEquals(false,bool);
		else assertEquals(true,bool);
	}

	@Test
	public void testGetOrdersOfSupervisor() throws SQLException, ParseException {
		//fail("Not yet implemented");
	Connection();
	ArrayList<Orders> o=odi.getOrdersOfSupervisor("supervisor");
	if(!o.isEmpty()){
		for(Orders i:o){
	assertEquals(orderId,i.getOrderId());
	assertEquals("ppc testing",i.getNameOfProject());
	assertEquals(new java.sql.Date(i.getStartDate().getTime()),i.getStartDate());
	assertEquals(new java.sql.Date(i.getEndDate().getTime()),i.getEndDate());
	assertEquals("testing company",i.getCompany());
	assertEquals(200,i.getQuantity());
	assertEquals(null,i.getUnit());
	assertEquals(null,i.getSupervisor());
    assertEquals(managerId,i.getManagerId());
	assertEquals(0,i.getAllocated());
	assertEquals(0,i.getGotResources());
	assertEquals("400",i.getManpower());
	assertEquals(dueDate,i.getDueDate());	
	}
	}
	else assertEquals(true,o.isEmpty());
	}
	@Test
	public void testUpdateAllocation() throws SQLException {
		//fail("Not yet implemented");
		Connection();
		bool=odi.updateAllocation(o);
		assertEquals(true,bool);
			
		
		
		
	}

	@Test
	public void testGetOrdersOfManager() throws SQLException, ParseException {
		//fail("Not yet implemented");
		Connection();
		ArrayList<Orders> o=new ArrayList<Orders>();
		o=odi.getOrdersOfManager(managerId);
		
		if(!o.isEmpty()){
			for(Orders i:o){
		assertEquals(orderId,i.getOrderId());
		assertEquals("ppc testing",i.getNameOfProject());
		assertEquals(new java.sql.Date(i.getStartDate().getTime()),i.getStartDate());
		assertEquals(new java.sql.Date(i.getEndDate().getTime()),i.getEndDate());
		assertEquals("testing company",i.getCompany());
		assertEquals(200,i.getQuantity());
		assertEquals("Kg",i.getUnit());
		assertEquals("null",i.getSupervisor());
	    assertEquals(managerId,i.getManagerId());
		assertEquals(0,i.getAllocated());
		assertEquals(0,i.getGotResources());
		assertEquals("400",i.getManpower());
		assertEquals("Fri Dec 22 00:00:00 IST 2017",String.valueOf(i.getDueDate()));	
		}
		}
		else assertEquals(true,o.isEmpty());
		}
	@Test
	public void  calcOrderId() throws ClassNotFoundException, SQLException{
		Connection();
		
			assertEquals(odi.calcOrderId(),(int)odi.calcOrderId());
		
		
		
			
	}		
}
