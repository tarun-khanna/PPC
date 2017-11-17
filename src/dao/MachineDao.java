package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Machine;



public interface MachineDao {
	public boolean setMachineData(ArrayList<Machine> machineAl);

		
	
	public boolean updateOrderId(String selectedOrderId);

	ArrayList<Machine> getMachineData(String orderid) throws SQLException, ClassNotFoundException;


	boolean currentOrderId(String OrderId);


	ArrayList<String> getOrders(String managerId);
	
}
