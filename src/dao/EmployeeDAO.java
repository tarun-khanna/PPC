package dao;

import java.util.ArrayList;

import bean.Employee;
import bean.ProductionManager;
import bean.Supervisor;

public interface EmployeeDAO {

	boolean addEmployee(Employee newEmp);
	boolean updateEmployee(Employee newEmp);
	boolean removeEmployee(String username) ;
	ArrayList<Employee> listEmployee();
	Employee searchEmployee(String username);
	ArrayList<Supervisor> listSupervisor();
	ArrayList<ProductionManager> listProductionManager();
	
}
