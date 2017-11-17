package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import bean.Admin;
import bean.Employee;
import bean.ProductionManager;
import bean.Supervisor;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;

public class LoginActionListener implements ActionListener {

	LoginScreen loginScreen;
	public LoginActionListener(LoginScreen ls)
	{
		this.loginScreen= ls;
		
	}
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			
			if(!loginScreen.user.getText().equals(null))
			{
				EmployeeDAO emp=new EmployeeDAOImpl(loginScreen.launchClass.dB);
			
				Employee searchEmp=emp.searchEmployee(loginScreen.user.getText());
				
					
				
				if(searchEmp == null )
				{
					
					JOptionPane.showMessageDialog(loginScreen.panel,"NO SUCH USER EXISTS!","Credential Issues",JOptionPane.ERROR_MESSAGE);
					
				}
				else if(new String(loginScreen.pass.getPassword()).equals(null) || (!new String(loginScreen.pass.getPassword()).equals(String.valueOf(searchEmp.getUserPass()))))//!((Integer.parseInt(new String(loginScreen.pass.getPassword())))== searchEmp.getUserPass()))
				{
					JOptionPane.showMessageDialog(loginScreen.panel,"!!!!WRONG PASSWORD!!!","Credential Issues",JOptionPane.ERROR_MESSAGE);
					
				}
				else
				{	
				
										
					switch(searchEmp.getRole())
					{
					case "ADMIN" :	Admin admin=new Admin(loginScreen.launchClass);
									loginScreen.frm.setVisible(false);
									new HomeScreen(loginScreen.launchClass,"admin",admin);
									break;

									
					case "PRODUCTIONMANAGER" : 	
												ProductionManager prod=new ProductionManager(searchEmp.getName(),searchEmp.getGender(),searchEmp.getDob(),searchEmp.getAddr(),searchEmp.getEmail(),searchEmp.getPhoneNo(),searchEmp.getRole());
												prod.setUserId(searchEmp.getUserId());
												prod.setUserPass(searchEmp.getUserPass());
												prod.databaseCon=loginScreen.launchClass.dB;
												loginScreen.frm.setVisible(false);
												new HomeScreen(loginScreen.launchClass,"product manager",prod);
												break;

					case "SUPERVISOR" : 
									
										Supervisor sup=new Supervisor(searchEmp.getName(),searchEmp.getGender(),searchEmp.getDob(),searchEmp.getAddr(),searchEmp.getEmail(),searchEmp.getPhoneNo(),searchEmp.getRole());
										sup.setUserId(searchEmp.getUserId());
										sup.setUserPass(searchEmp.getUserPass());
										sup.databaseCon=loginScreen.launchClass.dB;
										loginScreen.frm.setVisible(false);
										new HomeScreen(loginScreen.launchClass,"supervisor",sup);
										break;
					default : System.out.println("-----------SOME ERROR-----------");
					}
					
				}
				
				
				
			
			}	
		
	}

	
}
