package bean;

import javax.swing.JPanel;

import ppc.LaunchClass;
import ui.AddForm;
import ui.AdminEdit;
import ui.ViewTables;

public class Admin {
	
	private LaunchClass launchClass ;
	private AddForm addForm;
	private AdminEdit adminEdit;
	private ViewTables viewTables;
	public Admin(LaunchClass  lc)
	{
		launchClass=lc;
	}
	
	public JPanel addEmp()
	{
		addForm= new AddForm(launchClass);
		addForm.displayAddForm();
		return addForm.getAddPanel();
		
	}
	public JPanel editEmp()
	{
		adminEdit =new AdminEdit(launchClass);
		return adminEdit.getEditpanel();
	}

	public JPanel viewEmp()
	{
		viewTables=new ViewTables(launchClass);
		return viewTables.getMainPanel();
		
	}
	

}

