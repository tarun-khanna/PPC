package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class HomeScreenListener implements ActionListener {
	HomeScreen layoutObj;
	
	HomeScreenListener(HomeScreen layoutObj)
	{
		this.layoutObj = layoutObj;
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		if(ae.getSource() == layoutObj.signout)
		{
			int dialogButton=JOptionPane.YES_NO_OPTION;
			int dialogResult=JOptionPane.showConfirmDialog(layoutObj.mainFrame,"ARE YOU SURE ?","SIGN OUT",dialogButton);
			if(dialogResult == 0)
			{
				layoutObj.mainFrame.dispose();
				try {
					LoginScreen loginScreen= new LoginScreen(layoutObj.lc);
				} catch (ClassNotFoundException | SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		if(layoutObj.role.equalsIgnoreCase("admin"))
		{
			
			if(ae.getSource()==layoutObj.home)
				((CardLayout) (layoutObj.mainPanel).getLayout()).show(layoutObj.mainPanel,"HOME");
			else if(ae.getSource() == layoutObj.funcButtons.get(0))
				showInternalPanels(1,layoutObj.admin.addEmp(),"ADD NEW USER ");

			else if(ae.getSource() == layoutObj.funcButtons.get(1))
				showInternalPanels(2,layoutObj.admin.editEmp(),"UPDATE / DELETE USER ");

			else if(ae.getSource() == layoutObj.funcButtons.get(2))
				showInternalPanels(3,layoutObj.admin.viewEmp(),"VIEW USER ");
		}
		
		else if(layoutObj.role.equalsIgnoreCase("supervisor"))
		{
			if(ae.getSource()==layoutObj.home)
				((CardLayout) (layoutObj.mainPanel).getLayout()).show(layoutObj.mainPanel,"HOME");
			else if(ae.getSource() == layoutObj.funcButtons.get(0))
				showInternalPanels(1,layoutObj.supervisor.planSchedule(),"PLANNING AND SCHEDULING ");
			
			else if(ae.getSource() == layoutObj.funcButtons.get(1))
				showInternalPanels(2,layoutObj.supervisor.viewStatus(),"VIEW STATUS");
			
			else if(ae.getSource() == layoutObj.funcButtons.get(2))
				showInternalPanels(3,layoutObj.supervisor.sendRequest(),"RESOURCE REQUEST");
			
		}
		
		else if(layoutObj.role.equalsIgnoreCase("product manager"))
		{
			if(ae.getSource()==layoutObj.home)
				((CardLayout) (layoutObj.mainPanel).getLayout()).show(layoutObj.mainPanel,"HOME");
			
			else if(ae.getSource() == layoutObj.funcButtons.get(0))
				showInternalPanels(1,layoutObj.productManager.addProject(),"ADD/OPEN PROJECT");
			
			else if(ae.getSource() == layoutObj.funcButtons.get(1))
				showInternalPanels(2,layoutObj.productManager.resourceManagement(),"ResourceManagement");
			
			else if(ae.getSource() == layoutObj.funcButtons.get(2))
				showInternalPanels(3,layoutObj.productManager.selectSupervisor(),"SELECT SUPERVISOR");
				
			else if(ae.getSource() == layoutObj.funcButtons.get(3))
				showInternalPanels(4,layoutObj.productManager.viewStatus(),"VIEW STATUS");
			
			else if(ae.getSource() == layoutObj.funcButtons.get(4))
				showInternalPanels(5,layoutObj.productManager.processRequest(),"PENDING REQUESTS");
			
			else if(ae.getSource() == layoutObj.funcButtons.get(5))
				showInternalPanels(6,layoutObj.productManager.reportGeneration(),"REPORT GENERATION");
				
		}
	}
	
	public void showInternalPanels(int panelNo,JPanel funcPanel,String funcName)
	{
		CardLayout cl = (CardLayout)layoutObj.mainPanel.getLayout();
		layoutObj.mainPanels[panelNo].removeAll();
		JScrollPane jsp=new JScrollPane(funcPanel);
		//jsp.add();
//		modifyColors();
//		JInternalFrame internalFrame =new JInternalFrame();
//		internalFrame.add(jsp);
//		internalFrame.setTitle(funcName);  
//		try {
//		         internalFrame.setSelected(true);
//		         
//		      } catch (java.beans.PropertyVetoException e) {}
//		layoutObj.mainPanels[panelNo].add(internalFrame);
		layoutObj.mainPanels[panelNo].add(jsp);
		layoutObj.mainPanels[panelNo].revalidate();
		cl.show(layoutObj.mainPanel,funcName);
	}
	 protected void modifyColors() {
	      try {
	         String name = UIManager.getCrossPlatformLookAndFeelClassName();//getSystemLookAndFeelClassName();
	         UIManager.setLookAndFeel(name);//UIManager.getSystemLookAndFeelClassName());
	         UIManager.put("InternalFrame.activeTitleBackground", Color.DARK_GRAY);
	         UIManager.put("InternalFrame.activeTitleForeground", Color.DARK_GRAY);
	         UIManager.put("InternalFrame.inactiveTitleBackground", Color.DARK_GRAY);
	         UIManager.put("InternalFrame.inactiveTitleForeground", Color.DARK_GRAY);
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }
	   }
	
}
