package bean;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JPanel;

import dao.DatabaseCon;
import ui.MonthlyReport;
import ui.SupervisorRequestPage;
import ui.SupervisorScreen2;

public class Supervisor extends Employee {

	public DatabaseCon databaseCon;
	
	public Supervisor()
	{
		super();
		this.role="SUPERVISOR";
	}
	public Supervisor(String n, String gen, String dob, String addr, String email, long phone, String role) {
		super(n, gen, dob, addr, email, phone, "SUPERVISOR");
		
	}
	public JPanel planSchedule()  {
		try {
			SupervisorScreen2 sc=new SupervisorScreen2(databaseCon,this.getUserId());
			return sc.getMainFrm();
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public JPanel viewStatus()  {
		MonthlyReport monRep = new MonthlyReport(databaseCon,this.getUserId());
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

	public JPanel sendRequest() {
	
		return new SupervisorRequestPage(databaseCon,this.getUserId()).getRequestPanel();
		
	}

	
}
