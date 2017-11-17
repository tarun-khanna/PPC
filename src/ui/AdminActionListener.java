package ui;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bean.Employee;
import bean.ProductionManager;
import bean.Supervisor;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;

public class AdminActionListener implements ActionListener
{
	AddForm addForm;
	AdminEdit adminEdit;
	ViewTables viewTables;
	String adminFunc;
	
	
	public AdminActionListener(Object obj,String adminFunc)
	{
		if(adminFunc.equals("ADD"))
		{
			this.addForm=(AddForm)obj;
			this.adminFunc=adminFunc;
		}
		if(adminFunc.equals("EDIT"))
		{
			this.adminEdit=(AdminEdit)obj;
			this.adminFunc=adminFunc;
			System.out.println("inside adminEditActionConstructor");
		}
		if(adminFunc.equals("VIEW"))
		{
			this.viewTables=(ViewTables)obj;
			this.adminFunc=adminFunc;
			System.out.println("inside adminViewActionConstructor");
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("inside action performed");
		
		if(adminFunc.equals("ADD"))
		{
		if(e.getSource()==addForm.performFunc)
		{				
			//Employee newEmp = null;
			try
			{
			if(addForm.supervisor.isSelected())
			{
				addForm.newEmp=new Supervisor();
				
			}	
			else if(addForm.productionM.isSelected())
			{    
				
				 addForm.newEmp=new ProductionManager();	
				
			}else
			{
				System.out.println("Employee not added ");
				return;
			}
			
			
			addForm.newEmp.setName(addForm.firstnameField.getText().trim());
			addForm.newEmp.setAddr(addForm.addr1Field.getText().trim()+","+addForm.cityField.getText().trim()+","+addForm.stateField.getText().trim()+"-"+addForm.pinField.getText().trim()+","+addForm.countryField.getText().trim());
			addForm.newEmp.setEmail(addForm.emailIdField.getText().trim());
			
			try
			{
			addForm.newEmp.setPhoneNo(Long.parseLong(addForm.phoneField.getText().trim()));
			}
			catch(Exception ph)
			{
				System.out.println("phone"+ph);
			}
			addForm.newEmp.setGender((String)addForm.gen.getSelectedItem());
			
			
			
			String selectedDate=AddForm.dc.datePicker.getJFormattedTextField().getText();
			
			addForm.newEmp.setDob(selectedDate);
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			
			addForm.newEmp.setUserId(addForm.newEmp.generateID());
			addForm.newEmp.setUserPass(addForm.newEmp.generatePass());
			
			//JOptionPane.showMessageDialog( addForm.addPanel,addForm.newEmp.getRole()+" added SUCCESSFULLY !!!! \nUSER NAME : "+addForm.newEmp.getUserId()+"\nUSER PASSWORD : "+addForm.newEmp.getUserPass(), "Login Credentials", JOptionPane.INFORMATION_MESSAGE);
			
			addForm.setEnableSaveToDB(true);
			
			
			Runnable saveToDataBase=new Runnable(){

				@Override
				public void run() {
					while(!addForm.isEnableSaveToDB())
					{
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					EmployeeDAO addEmp=new EmployeeDAOImpl(addForm.launchClass.dB);
					boolean addcheck = addEmp.addEmployee(addForm.newEmp);
					if(!addcheck)
					JOptionPane.showMessageDialog( addForm.addPanel,"Error adding !!!! \nUSER NAME : "+addForm.newEmp.getUserId()+"\nUSER PASSWORD : "+addForm.newEmp.getUserPass(), "Credentials Issue", JOptionPane.ERROR_MESSAGE);
					else
					{
						JOptionPane.showMessageDialog( addForm.addPanel,addForm.newEmp.getRole()+" added SUCCESSFULLY !!!! \nUSER NAME : "+addForm.newEmp.getUserId()+"\nUSER PASSWORD : "+addForm.newEmp.getUserPass(), "Login Credentials", JOptionPane.INFORMATION_MESSAGE);
						
					}
					addForm.setEnableSaveToDB(false);
					addForm.clear();
				}
				
			};  
			Thread savingtoDB=new Thread(saveToDataBase);
			savingtoDB.start();
			 
			System.out.println(addForm.newEmp.getName()+" "+addForm.newEmp.getAddr()+" "+addForm.newEmp.getDob()+" "+addForm.newEmp.getEmail()+" "+addForm.newEmp.getGender()+" "+addForm.newEmp.getRole()+" "+addForm.newEmp.getPhoneNo());

		}
		}
		if(adminFunc.equalsIgnoreCase("VIEW"))
		{
			if(e.getSource() == viewTables.refreshLogin)
			{
				EmployeeDAO empView=new EmployeeDAOImpl(viewTables.lc.dB);
				ArrayList<Employee>  emp=empView.listEmployee();
				if(!emp.isEmpty())
				{
						int rowCount =viewTables.loginDtm.getRowCount();
						for(int i=rowCount-1;i>=0;i--)
						{
							viewTables.loginDtm.removeRow(i);
						}
							
						for(Employee employee:emp)
						{
							String row[]={employee.getUserId(),String.valueOf(employee.getUserPass()),employee.getRole()};
							viewTables.loginDtm.addRow(row);
						}
						
				}
				
				
			}
			if(e.getSource() == viewTables.refreshProduction)
			{
				EmployeeDAO empView=new EmployeeDAOImpl(viewTables.lc.dB);
				ArrayList<ProductionManager> productionManager=empView.listProductionManager();
				if(!productionManager.isEmpty())
				{
					int rowCount =viewTables.productionDtm.getRowCount();
					for(int i=rowCount-1;i>=0;i--)
					{
						viewTables.productionDtm.removeRow(i);
					}
					for(ProductionManager prod:productionManager)
					{
						String row[]={prod.getUserId(),prod.getName(),prod.getGender(),prod.getDob(),prod.getAddr(),prod.getEmail(),String.valueOf(prod.getPhoneNo())};
						viewTables.productionDtm.addRow(row);
						
					}
				
				}
				
			}
			if(e.getSource() == viewTables.refreshSupervisor)
			{
				EmployeeDAO empView=new EmployeeDAOImpl(viewTables.lc.dB);
				ArrayList<Supervisor> supervisors=empView.listSupervisor();
					if(!supervisors.isEmpty())
					{
						int rowCount =viewTables.supervisorDtm.getRowCount();
						for(int i=rowCount-1;i>=0;i--)
						{
							viewTables.supervisorDtm.removeRow(i);
						}
						for(Supervisor sup:supervisors)
						{
							String row[]={sup.getUserId(),sup.getName(),sup.getGender(),sup.getDob(),sup.getAddr(),sup.getEmail(),String.valueOf(sup.getPhoneNo())};
							viewTables.supervisorDtm.addRow(row);
							
						}
					
					}
				
			}
			
		}
		if(adminFunc.equals("EDIT"))
		{
			System.out.println("inside edit action performed");
			if(e.getSource()== adminEdit.update)
			{
				System.out.println("inside edit-update action performed");
				
				String user= adminEdit.user.getText();
				if(user != null)
				{
					EmployeeDAO editEmp = new EmployeeDAOImpl(adminEdit.launchClass.dB);
					Employee foundEmp =editEmp.searchEmployee(user);
				
						if(foundEmp==null)
						{
							adminEdit.status.setText("Invalid User Name !!!");
							adminEdit.user.setText("");
							
						}
						else
						{	
							adminEdit.title="UPDATE USER DETAILS ";
							JPanel updateDetails=new JPanel();
							updateDetails.setLayout(new GridBagLayout());
							Icon saveIcon = new ImageIcon("save.png");
							Image saveImg= ((ImageIcon)saveIcon).getImage();
							saveIcon = new ImageIcon(saveImg);
							adminEdit.saveUpdate=new JButton(saveIcon);
							adminEdit.saveUpdate.setContentAreaFilled(false);
							adminEdit.init(updateDetails,adminEdit.saveUpdate);
							adminEdit.updatePanel.removeAll();
							adminEdit.updatePanel.add(updateDetails);
							adminEdit.updatePanel.repaint();
							adminEdit.saveUpdate.addMouseListener(new MyMouseActions(adminEdit));
							if(foundEmp.getRole().equalsIgnoreCase("productionmanager"))
							
								adminEdit.productionM.setSelected(true);
							
							else
								adminEdit.supervisor.setSelected(true);
							
							adminEdit.firstnameField.setText(foundEmp.getName());
							adminEdit.gen.setSelectedItem(foundEmp.getGender());
							adminEdit.emailIdField.setText(foundEmp.getEmail());
							adminEdit.phoneField.setText(String.valueOf(foundEmp.getPhoneNo()));
							
							String add[]=foundEmp.getAddr().split(",");
							if(add.length>0 && add[0] != null)
							adminEdit.addr1Field.setText(add[0]);
							if(add.length>1 && add[1] != null)
							adminEdit.cityField.setText(add[1]);
							if(add.length>2 && add[2] != null){
							String add2[]=add[2].split("-");
							if(add2.length>0 && add2[0] != null)
							adminEdit.stateField.setText(add2[0]);
							
							if(add2.length>1 && add2[1] != null)
							adminEdit.pinField.setText(add2[1]);
							}
							if(add.length>3 && add[3] != null)
							adminEdit.countryField.setText(add[3]);
							
							java.util.Date dateofbirth;
							try {
								dateofbirth = new SimpleDateFormat("dd/MM/yyyy").parse(foundEmp.getDob());
								AdminEdit.dc.getDatePicker().getModel().setDate(dateofbirth.getYear(),dateofbirth.getMonth(),dateofbirth.getDate());

							} catch (ParseException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							AdminEdit.dc.getDatePicker().getModel().setSelected(true);
							
							adminEdit.validateEmail.setText("VALID");
							adminEdit.validateName.setText("VALID");
							adminEdit.validatePhone.setText("VALID");
							adminEdit.supervisor.setEnabled(false);
							adminEdit.productionM.setEnabled(false);
							adminEdit.emailIdField.setEditable(false);
							adminEdit.addEnable=true;
							adminEdit.performFunction(adminEdit.saveUpdate);
							adminEdit.details.revalidate();
							
							CardLayout cl=(CardLayout)adminEdit.details.getLayout();
							cl.show(adminEdit.details,"UPDATE");
							
							adminEdit.saveUpdate.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {
									if (foundEmp.getRole().equalsIgnoreCase("productionmanager"))
									adminEdit.newEmp=new ProductionManager();
									else
									adminEdit.newEmp=new Supervisor();
									adminEdit.newEmp.setUserId(adminEdit.user.getText());
									adminEdit.newEmp.setName(adminEdit.firstnameField.getText().trim());
									adminEdit.newEmp.setAddr(adminEdit.addr1Field.getText().trim()+","+adminEdit.cityField.getText().trim()+","+adminEdit.stateField.getText().trim()+"-"+adminEdit.pinField.getText().trim()+","+adminEdit.countryField.getText().trim());
									adminEdit.newEmp.setEmail(adminEdit.emailIdField.getText().trim());
									
									try
									{
									adminEdit.newEmp.setPhoneNo(Long.parseLong(adminEdit.phoneField.getText().trim()));
									}
									catch(Exception ph)
									{
										System.out.println("phone"+ph);
									}
									adminEdit.newEmp.setGender((String)adminEdit.gen.getSelectedItem());
									
									
									
									String selectedDate=AddForm.dc.datePicker.getJFormattedTextField().getText();
									
									adminEdit.newEmp.setDob(selectedDate);	
									
									EmployeeDAO updateEmp=new EmployeeDAOImpl(adminEdit.launchClass.dB);
									boolean updatecheck = updateEmp.updateEmployee(adminEdit.newEmp);
									if(!updatecheck)
									JOptionPane.showMessageDialog( adminEdit.updatePanel,"Error updating!!!! \nUSER NAME : "+adminEdit.newEmp.getUserId(), "Update Issue", JOptionPane.ERROR_MESSAGE);
									else
									{
										try {
											JOptionPane.showMessageDialog( adminEdit.updatePanel," Updated SUCCESSFULLY !!!! \nUSER NAME : "+user, " UPDATE MESSAGE ", JOptionPane.INFORMATION_MESSAGE);
											CardLayout cl=(CardLayout)adminEdit.details.getLayout();
											cl.show(adminEdit.details,"BLANK");
											adminEdit.user.setText("");
											adminEdit.status.setText("Enter valid User Name !!!");
										} catch (HeadlessException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

									}
									
								}
								
							});
							
						}
				
				
			}
				
			}		
			if(e.getSource()== adminEdit.delete)
			{
			String username= adminEdit.user.getText();
			if(adminEdit.user.getText() != null)
			{
				EmployeeDAO editEmp = new EmployeeDAOImpl(adminEdit.launchClass.dB);
				Employee deleteEmp =editEmp.searchEmployee(username);
				
				if(deleteEmp == null )
				{
					adminEdit.status.setText("Invalid User Name !!!");
					adminEdit.user.setText("");

				}
				else
				{	
					
						
					adminEdit.deletePanel.removeAll();
					adminEdit.deleteUserDetails(deleteEmp.getName(),deleteEmp.getEmail(),deleteEmp.getPhoneNo());
					adminEdit.deletePanel.repaint();
					adminEdit.details.revalidate();
					CardLayout cl=(CardLayout)adminEdit.details.getLayout();
					cl.show(adminEdit.details,"DELETE");
					
					adminEdit.yes.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							
							boolean isDeleted=editEmp.removeEmployee(username);
							
							if(isDeleted)
							{
								try {
									JOptionPane.showMessageDialog( adminEdit.deletePanel," Deleted SUCCESSFULLY !!!! \nUSER NAME : "+username, "Delete User", JOptionPane.INFORMATION_MESSAGE);
									CardLayout cl=(CardLayout)adminEdit.details.getLayout();
									cl.show(adminEdit.details,"BLANK");
									adminEdit.user.setText("");
									adminEdit.status.setText("Enter valid User Name !!!");
								} catch (HeadlessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							else
							{
								try {
									JOptionPane.showMessageDialog( adminEdit.deletePanel,"Deletion Failed!!!! \nUSER NAME : "+username, "Delete User", JOptionPane.ERROR_MESSAGE);
									CardLayout cl=(CardLayout)adminEdit.details.getLayout();
									cl.show(adminEdit.details,"BLANK");
									adminEdit.user.setText("");
									adminEdit.status.setText("Enter valid User Name !!!");
									
								} catch (HeadlessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							System.out.println(isDeleted);
						}
					
					});
					
					adminEdit.no.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							
							
								try {
									JOptionPane.showMessageDialog( adminEdit.deletePanel,"Cancel Delete!!!! \nUSER NAME : "+adminEdit.user.getText(), "Delete User", JOptionPane.INFORMATION_MESSAGE);
									CardLayout cl=(CardLayout)adminEdit.details.getLayout();
									cl.show(adminEdit.details,"BLANK");
									adminEdit.user.setText("");
									adminEdit.status.setText("Enter valid User Name !!!");
									
								} catch (HeadlessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						
					});
					
					
				}
				
				
				
			}	
			
			}
		}	
	}	
	
		
	
	
	
}
