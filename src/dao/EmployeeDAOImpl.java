package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.Employee;
import bean.ProductionManager;
import bean.Supervisor;

public class EmployeeDAOImpl implements EmployeeDAO{
	DatabaseCon dB;
	
	public EmployeeDAOImpl(DatabaseCon databaseCon)
	{
		this.dB=databaseCon;
		
	}

	@Override
	public
	boolean addEmployee(Employee newEmp)
	{
		PreparedStatement ps=null;
		int rs =-1;
		int rs1=-1;
		try {
			
			ps=dB.getCon().prepareStatement("insert into LoginDetails values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			System.out.println("Statement created");
			ps.setString(1, newEmp.getUserId());
			ps.setInt(2,newEmp.getUserPass());
			ps.setString(3,newEmp.getRole());
			try
			{
				rs =ps.executeUpdate();
				
			
			}
			catch(SQLException s)
			{
				s.printStackTrace();	
				return false;
			}
			
			
			ps.close();
			
			ps=dB.getCon().prepareStatement("insert into "+newEmp.getRole()+" values(?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					
			System.out.println("Statement created");
			ps.setString(1,newEmp.getUserId());
			ps.setString(2,newEmp.getName());
			ps.setString(3,newEmp.getGender());
			
			ps.setString(5,newEmp.getAddr());
			ps.setString(6,newEmp.getEmail());
			ps.setLong(7,newEmp.getPhoneNo());
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			Date dob = (Date)formatter.parse(newEmp.getDob()); 
			
			ps.setDate(4,new java.sql.Date(dob.getTime()));
			
			rs1=ps.executeUpdate();
			ps.close();
			
			
		
		} catch (SQLException e) {
			if(rs>0 && rs1<1 )
			{
				try {
					ps.close();
					ps = dB.getCon().prepareStatement("Delete from logindetails where username=? ",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ps.setString(1,newEmp.getUserId());
					ps.executeUpdate();
					ps.close();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
		if(!(rs>0 && rs1>0))
		return false;
		
		return true;
		
	}
	
	@Override
	public
	boolean removeEmployee(String username)
	{
		
		PreparedStatement ps;
		int rs=-1;
		try {
			ps = dB.getCon().prepareStatement("Delete from logindetails where username='"+username+"'",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		System.out.println("Statement created ");
		try
		{
			rs = ps.executeUpdate();
			System.out.println(rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if(rs<1)
			return false;
		
		return true;
		
	}
	@Override
	public
	boolean updateEmployee(Employee newEmp)
	{
		

		PreparedStatement ps=null;
		int result=-1;
		try {
			
			ps=dB.getCon().prepareStatement("update "+newEmp.getRole()+" set name = ?,gender = ?,dob=?,address=?,phone= ?   where username = '"+newEmp.getUserId()+"' ",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					
			System.out.println("Statement created");
			//ps.setString(1,newEmp.getUserId());
			ps.setString(1,newEmp.getName());
			ps.setString(2,newEmp.getGender());
			
			ps.setString(4,newEmp.getAddr());
			ps.setLong(5,newEmp.getPhoneNo());
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			Date dob = (Date)formatter.parse(newEmp.getDob()); 
			
			ps.setDate(3,new java.sql.Date(dob.getTime()));
			
			result=ps.executeUpdate();
			ps.close();
			
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if(!(result>0))
			return false;
		
		return true;
	
		
	}
	
	@Override
	public ArrayList<Employee> listEmployee()
	{
		ArrayList<Employee> emp = new ArrayList<>();
		try {
			PreparedStatement ps = dB.getCon().prepareStatement("Select * from logindetails");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Employee employee=new Employee();
				employee.setUserId(rs.getString(1));
				employee.setUserPass(rs.getInt(2));
				employee.setRole(rs.getString(3));
				emp.add(employee);
			}
			rs.close();
			  ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emp;
		
	}
	
	@Override
	public Employee searchEmployee(String username){
			Employee emp=null;
		try {
			PreparedStatement ps = dB.getCon().prepareStatement("Select * from logindetails where username='"+username+"'",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			ResultSet res =ps.executeQuery();
			String role = null ;
			if(res.next())
			{
				
				role = res.getString(3);
				
				if(role.equalsIgnoreCase("admin"))
				{	emp=new Employee();
					emp.setUserId(res.getString(1));
					emp.setUserPass(res.getInt(2));
					emp.setRole("ADMIN");
					return emp;
				}
			}
			else
			{
				return  null;
			}
			res.close();
			ps.close();
			
			ps = dB.getCon().prepareStatement("Select * from logindetails t1, "+role+" t2 where t1.username='"+username+"' and t2.username='"+username+"'",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet res1 =ps.executeQuery();
			if(!res1.next())
			{
				return null;
			}
			else
			{
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
				Date dob = new java.util.Date(res1.getDate(7).getTime()); 
				String empDob=formatter.format(dob);
		    	emp=new Employee(res1.getString(5),res1.getString(6),empDob,res1.getString(8),res1.getString(9),res1.getLong(10),role);
		    	emp.setUserId(res1.getString(1));
		    	emp.setUserPass(res1.getInt(2));
			}
			ps.close();
			res1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
		
	}

	@Override
	public ArrayList<Supervisor> listSupervisor() {
		ArrayList<Supervisor> supervisors=new ArrayList<Supervisor>();
		ResultSet rs=null;
		try {
			PreparedStatement ps = dB.getCon().prepareStatement("Select * from supervisor");
		    rs = ps.executeQuery();
				
		    while(rs.next())
		    {
		    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
				Date dob = new java.util.Date(rs.getDate(4).getTime()); 
				String supDob=formatter.format(dob);
		    	Supervisor s=new Supervisor(rs.getString(2),rs.getString(3),supDob,rs.getString(5),rs.getString(6),rs.getLong(7),"SUPERVIOSR");
		    	s.setUserId(rs.getString(1));
		    	supervisors.add(s);
		    }	
		    rs.close(); 
		    ps.close();
		    return supervisors;
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return supervisors;
	}

	@Override
	public ArrayList<ProductionManager> listProductionManager() {
		ArrayList<ProductionManager> productionmanager=new ArrayList<ProductionManager>();
		ResultSet rs=null;
		try {
			PreparedStatement ps = dB.getCon().prepareStatement("Select * from productionmanager");
		    rs = ps.executeQuery();
		   
		    while(rs.next())
		    {
		    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
				Date dob = new java.util.Date(rs.getDate(4).getTime()); 
				String supDob=formatter.format(dob);
				ProductionManager p=new ProductionManager(rs.getString(2),rs.getString(3),supDob,rs.getString(5),rs.getString(6),rs.getLong(7),"SUPERVIOSR");
		    	p.setUserId(rs.getString(1));
		    	productionmanager.add(p);
		    }	
		    
		    rs.close();
		    ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productionmanager;
	}
}
