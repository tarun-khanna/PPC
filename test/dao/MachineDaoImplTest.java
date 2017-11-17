package dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import bean.Machine;
import dao.DatabaseCon;
import dao.MachineDaoImpl;
import dao.MyTaskDaoImpl;
import java.sql.Connection;


public class MachineDaoImplTest {
	boolean bool;
	DatabaseCon dbc=new DatabaseCon();
	Connection con;
	MachineDaoImpl mdi=new MachineDaoImpl(dbc);
	MyTaskDaoImpl mtdi=new MyTaskDaoImpl(con);
	String orderId="testOrder";
	String machineNo="m-1";
	String machineName="test machine";
	
 void Connection(){
	try {
		dbc.getDBConnection();
	} catch (ClassNotFoundException | SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Test
	public void testCurrentOrderId() {
	
		   Connection();
		      bool=mdi.currentOrderId(orderId);
		    assertEquals(true, bool);
	}

	@Test
	public void testSetMachineData() {
		//fail("Not yet implemented");
		Connection();
		ArrayList<Machine> machineAl=new ArrayList<Machine>();
		
		Machine m=new Machine();
		m.setMachineNo(machineNo);
		m.setNameOFMachine(machineName);
		machineAl.add(m);
		mdi.currentOrderId(orderId);
		bool=mdi.setMachineData(machineAl);
		
		assertEquals(true, bool);
		
		
		
		
	}
	@Test
	public void testGetMachineData() throws ClassNotFoundException, SQLException {
		
		Connection();
		Machine m2=new Machine();
		ArrayList<Machine> rawAl2=new ArrayList<Machine>();
		m2.setAllottedTask(mtdi.getAllocatedTasks(machineNo));
		
		rawAl2=mdi.getMachineData(orderId);
		for(Machine m:rawAl2){
		assertEquals("m-1",m.getMachineNo() );
		assertEquals("test machine",m.getNameOFMachine() );
		assertEquals(1,m.getQuantityOfMachine());
	
		}	
	}

	@Test
	public void testGetOrders() {
		//fail("Not yet implemented");
		Connection();
		ArrayList<String> al=new ArrayList<String>();
		al=mdi.getOrders("manager-12");
		if(!al.isEmpty()){
		for(String s : al)
        { 
			assertEquals(orderId,s );
			  
        }
   }
		
		
	}

	@Test
	public void testUpdateOrderId() {
   Connection();	
   bool= mdi.updateOrderId(orderId);
    assertEquals(true, bool);
	
	}

	
}
