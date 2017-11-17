package dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import bean.Employee;
import bean.ProductionManager;
import bean.Supervisor;

public class EmployeeDAOImplTest {

	DatabaseCon db;
	
	
	void dBConnection()
	{
		db=new DatabaseCon();
		try {
			db.getDBConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testAddEmployee() {
		
		EmployeeDAOImpl emp;
		
		dBConnection();
			emp=new EmployeeDAOImpl(db);
		
		Employee employee =new Employee("AROHI","FEMALE","24/09/1996","ash,askh,akdh-398,dsgh","qwerty249@abc.com",Long.valueOf(9999999999l),"PRODUCTIONMANAGER");
		employee.setUserId(employee.generateID());
		employee.setUserPass(employee.generatePass());
		
		boolean expected=emp.addEmployee(employee);
		assertEquals(expected,false);
		
		//null employee
		 expected=emp.addEmployee(null);
		assertEquals(expected,false);
		
		//fail("Not yet implemented");
	}

	@Test
	public void testRemoveEmployee() {
		
	
		
		EmployeeDAOImpl emp;
		dBConnection();
		
			
			emp=new EmployeeDAOImpl(db);
		//non existing username 
		boolean expected =emp.removeEmployee("C4_180");
		assertEquals(expected,false);
		
		//null username 
		expected =emp.removeEmployee("");
		assertEquals(expected,false);
		
		//existing username
		expected =emp.removeEmployee("C4_962");
		assertEquals(expected,false);
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdateEmployee() {
		
		EmployeeDAOImpl emp;
		dBConnection();
		
			
		emp=new EmployeeDAOImpl(db);
		
		Employee employee =new Employee("ruhi","FEMALE","24/09/1996","ash,askh,akdh-398,dsgh","qsdf@dfjh.sfjk",Long.valueOf(9999999999l),"PRODUCTIONMANAGER");
		employee.setUserId(employee.generateID());
		employee.setUserPass(employee.generatePass());
		
		boolean expected=emp.updateEmployee(employee);
		assertEquals(expected,false);
		
		//null employee
		expected=emp.addEmployee(null);
		assertEquals(expected,false);
		
		//fail("Not yet implemented");
	}

	@Test
	public void testListSupervisor()
	{
		
		EmployeeDAOImpl emp;
		dBConnection();
			
			emp=new EmployeeDAOImpl(db);
			
			ArrayList<Supervisor> expected=emp.listSupervisor();
			
			ArrayList<Supervisor> actual=new ArrayList<>();
			Supervisor sup=new Supervisor();
			addSupervisor(sup);
			actual.add(sup);
			
			assertEquals(expected.get(0).toString(),actual.get(0).toString());
			
			
	}
	
	public void addSupervisor(Employee sup)
	{
		sup.setAddr("HGJHGhgj,hgjhjkg,gjjhjk-5665878,india");
		sup.setDob("14/07/1993");
		sup.setEmail("ayushi@gmail.com");
		sup.setGender("FEMALE");
		sup.setName("Ayushi");
		sup.setPhoneNo(8989898989l);
		sup.setRole("SUPERVISOR");
		sup.setUserId("C4_480");
		
		
		
		
	}
	
	@Test
	public void testSearchEmployee()
	{
		dBConnection();
		EmployeeDAOImpl emp;
		emp=new EmployeeDAOImpl(db);
		Employee employee=emp.searchEmployee("");
		assertEquals(employee,null);
		
		employee=emp.searchEmployee("C4_480");
		Employee actualEmp=new Employee();
		addSupervisor(actualEmp);
		assertEquals(employee.getUserId(),actualEmp.getUserId());
		
		employee=emp.searchEmployee("C4_499");
		assertEquals(employee,null);
	}

	@Test
	public void testListProductionManager()
	{
		
		EmployeeDAOImpl emp;
		dBConnection();
			
			emp=new EmployeeDAOImpl(db);
			
			ArrayList<ProductionManager> expected=emp.listProductionManager();
			
		//	ArrayList<ProductionManager> actual=new ArrayList<>();
			
			
			assertEquals(expected.size(),5);
			
			
	}
	public void testListEmployee()
	{
		
		EmployeeDAOImpl emp;
		dBConnection();
			
			emp=new EmployeeDAOImpl(db);
			
			ArrayList<Employee> expected=emp.listEmployee();
			
		//	ArrayList<ProductionManager> actual=new ArrayList<>();
			
			
			assertEquals(expected.size(),9);
			
			
	}

}
