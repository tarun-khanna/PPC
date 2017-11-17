package dao;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import bean.Orders;

public interface OrdersDao {


	boolean setTableData(Orders p) throws SQLException,ClassNotFoundException,IOException;
	//boolean getTableData() throws SQLException,ClassNotFoundException,IOException;
	ArrayList<String> getNameData(String managerId) throws SQLException,ClassNotFoundException,IOException;
	ArrayList<String> getNullSupervisorData(String managerId) throws SQLException,ClassNotFoundException,IOException;
	ArrayList<String> getSupervisors() throws SQLException,ClassNotFoundException,IOException;
	boolean updateSupervisor(String str1,String str2) throws SQLException,ClassNotFoundException,IOException;
	String[] displayTableData(String str) throws SQLException,ClassNotFoundException,IOException;
	public ArrayList<String> getOrders(String supId) throws SQLException;
	public String getProductionManager(String orderId) throws SQLException;
	//ResultSet getOrdersTable(String managerId);
	public Orders getTableData(String orderid) throws SQLException, ClassNotFoundException, ParseException;
	public String managerId(String orderid);
	public String supervisorId(String orderid);
	
	
	
	////////Progress Logic
	
	public double getProgress(String orderId) throws SQLException, ParseException;
	public ArrayList<String> getOrderIDs(String managerId) throws ClassNotFoundException, SQLException,IOException;
	ArrayList<String> getReportOrders(String managerId);
	boolean updateAllocation(Orders o) throws SQLException;
	int calcOrderId() throws SQLException, ClassNotFoundException;
	
}
