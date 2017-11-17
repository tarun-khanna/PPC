package bean;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JPanel;

import dao.DatabaseCon;
import ui.AddProject;
import ui.ManagerRequestPage;
import ui.MonthlyRepManager;
import ui.ReportGeneration;
import ui.Resource_Management_Screen;
import ui.SelectSupervisor;

public class ProductionManager extends Employee
{

	public DatabaseCon databaseCon;

	public ProductionManager()
	{
		super();
		this.role="PRODUCTIONMANAGER";
	}
	public ProductionManager(String name, String gen, String dob, String addr, String email, long phone, String role) {
		super(name, gen, dob, addr, email, phone, "PRODUCTIONMANAGER");

	}
	
	public JPanel resourceManagement()
	{
		Resource_Management_Screen rms;
		rms = new Resource_Management_Screen(databaseCon,this.getUserId());
		return rms.getResource_panel();
	
		
	}
	public JPanel reportGeneration() 
	{
		
			try {
				ReportGeneration rg=new ReportGeneration(databaseCon,this.getUserId());
				return rg.getReport();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
		
	}
	public JPanel addProject() 
	{
		AddProject ap;
		try {
			ap = new AddProject(databaseCon,this.getUserId());
			return ap.getPanel();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public JPanel selectSupervisor() 
	{
		SelectSupervisor ss;
		try {
			ss = new SelectSupervisor(databaseCon,this.getUserId());
			return ss.getPanel();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public JPanel viewStatus()  {
		MonthlyRepManager monRep = new MonthlyRepManager(databaseCon,this.getUserId());
		System.out.println("inside view status of product manager");
		try {
			return monRep.display();
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public JPanel processRequest() {
		
		try {
			
			return new ManagerRequestPage(databaseCon,this.getUserId()).getRequestPanel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
