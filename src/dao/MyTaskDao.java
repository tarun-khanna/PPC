package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.MyTask;

public interface MyTaskDao {

	public boolean addTaskInDB(MyTask mt) throws SQLException;
	public ArrayList<MyTask> getAllocatedTasks(String machineNo) throws SQLException;
	public ArrayList<MyTask> getTasksOfOrder(String oId) throws SQLException;
		
	
}
