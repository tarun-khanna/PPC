package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.MyTask; 

public class MyTaskDaoImpl implements MyTaskDao {

	DatabaseCon databaseCon;
	Connection con;
	PreparedStatement pst;
	public ResultSet res;
	
	public MyTaskDaoImpl(Connection con)
	{
		this.con = con;
	}
	
		
	@Override
	public boolean addTaskInDB(MyTask mt) throws SQLException
	{
		
		if(mt==null)
			return false;
		pst = con.prepareStatement("insert into tasks values(?,?,?,?)");
		pst.setString(1,mt.getMachineNo());
		
		pst.setString(2, mt.getOrderId());

		pst.setDate(3, new java.sql.Date(mt.getStartDate().getTime()));

		pst.setDate(4, new java.sql.Date(mt.getEndDate().getTime()));
		
		int i = pst.executeUpdate();
		pst.close();
		if(i<1)
			return false;
		
		
		return true;
		
	}

	
	@Override
	public ArrayList<MyTask> getAllocatedTasks(String machineNo) throws SQLException
	{ System.out.println(machineNo);
	ArrayList<MyTask> taskList = new ArrayList<MyTask>();	
		pst = con.prepareStatement("Select * from tasks where machineno = ?");
		pst.setString(1,machineNo);
		res =pst.executeQuery();
		
		
		
		while(res.next())
		{	MyTask mt = new MyTask(res.getString(1),res.getString(2), res.getDate(3), res.getDate(4));
			taskList.add(mt);
		}
		
		pst.close();
	
	
	return taskList;
		
	}
	
	@Override
	public ArrayList<MyTask> getTasksOfOrder(String oId) throws SQLException
	{
		
		ArrayList<MyTask> allTasks = new ArrayList<MyTask>();
		
		pst = con.prepareStatement("Select * from tasks where orderId = ?");
		pst.setString(1, oId);
		res = pst.executeQuery();
		while(res.next())
		{
		MyTask mt = new MyTask(res.getString(1),res.getString(2), res.getDate(3), res.getDate(4));
			
		allTasks.add(mt);
	
		}
		pst.close();
		return allTasks;
		}
		
	
	
}
	
	

