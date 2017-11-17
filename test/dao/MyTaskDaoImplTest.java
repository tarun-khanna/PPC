package dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import bean.MyTask;

public class MyTaskDaoImplTest {

	DatabaseCon db;
	Connection con;
	
	String machineNo = "M-test";
	String orderNo = "O-test";
	Date startTest = new Date();
	Date endTest = new Date();
	@Test
	public void testAddTaskInDB() throws SQLException {
		
		db=new DatabaseCon();
		try {
			db.getDBConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		MyTaskDaoImpl mtImpl = new MyTaskDaoImpl(db.getCon());

		MyTask mt = new MyTask(machineNo,orderNo,startTest,endTest);
	
		boolean b = mtImpl.addTaskInDB(mt);
		
		assertEquals(true, b);
		
		
		
		
		//	fail("Not yet implemented");
	}

	@Test
	public void testGetAllocatedTasks() throws SQLException {
		
		db=new DatabaseCon();
		try {
			db.getDBConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		MyTaskDaoImpl mtImpl = new MyTaskDaoImpl(db.getCon());
		
		
		ArrayList<MyTask> taskList = new ArrayList<MyTask>();
		
		taskList = mtImpl.getAllocatedTasks(machineNo);
		
		for(MyTask mt : taskList)
		{
			assertEquals(machineNo, mt.getMachineNo());
			assertEquals(orderNo, mt.getOrderId());
			assertEquals(machineNo + orderNo, mt.getTaskName());
			assertEquals(startTest, mt.getStartDate());
			assertEquals(endTest, mt.getEndDate());
			
		}
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetTasksOfOrder() throws SQLException {
	
		
		db=new DatabaseCon();
		try {
			db.getDBConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		MyTaskDaoImpl mtImpl = new MyTaskDaoImpl(db.getCon());
		
		

		ArrayList<MyTask> taskList = new ArrayList<MyTask>();
		
		taskList = mtImpl.getAllocatedTasks(orderNo);
	
		for(MyTask mt : taskList)
		{	assertEquals(machineNo, mt.getMachineNo());
			assertEquals(orderNo, mt.getOrderId());
			assertEquals(machineNo + orderNo, mt.getTaskName());
			assertEquals(startTest, mt.getStartDate());
			assertEquals(endTest, mt.getEndDate());
			
		}
		
		
		//	fail("Not yet implemented");
	}

}
