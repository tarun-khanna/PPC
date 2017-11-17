package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.RequestDaoImpl;

public class RequestActionListener implements ActionListener {
	ManagerRequestPage managerReq;
	
	public RequestActionListener(ManagerRequestPage managerReq)
	{
		this.managerReq=managerReq;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
	
		
		if(ae.getSource() == managerReq.approve)
		{
			int selectedRowIndex = managerReq.reqTable.getSelectedRow();
			
		
			if(selectedRowIndex>-1)
			{
				
			System.out.println(managerReq.dtmTable.getValueAt(selectedRowIndex,0).toString()+" "+managerReq.dtmTable.getValueAt(selectedRowIndex,1).toString()+" "+managerReq.dtmTable.getValueAt(selectedRowIndex,2).toString()+" "+managerReq.dtmTable.getValueAt(selectedRowIndex,3).toString());
			RequestDaoImpl req=new RequestDaoImpl(managerReq.db.getCon());
					try {
						int success= req.updateRequest(1,managerReq.dtmTable.getValueAt(selectedRowIndex,2).toString(),managerReq.dtmTable.getValueAt(selectedRowIndex,3).toString());
						if(success == 1)
						{
							JOptionPane.showMessageDialog(managerReq.requestPanel ," Request Approved  " , "Request", JOptionPane.INFORMATION_MESSAGE);
							//ManagerRequestPage.approvedRowIndex=selectedRowIndex;
							managerReq.dtmTable.removeRow(selectedRowIndex);
							//managerReq.reqTable.setSelectionBackground(Color.GREEN);
							
							 //TableModel row=managerReq.reqTable.getModel().get
							
						}
						else
							JOptionPane.showMessageDialog( managerReq.requestPanel," Request Not Approved " , "Request", JOptionPane.ERROR_MESSAGE);
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		}
		if(ae.getSource() == managerReq.cancel)
		{
			int selectedRowIndex = managerReq.reqTable.getSelectedRow();
			RequestDaoImpl req=new RequestDaoImpl(managerReq.db.getCon());
			if(selectedRowIndex>-1)
			{
				try {
					int success= req.updateRequest(-1,managerReq.dtmTable.getValueAt(selectedRowIndex,2).toString(),managerReq.dtmTable.getValueAt(selectedRowIndex,3).toString());
					if(success == 1)
					{
						JOptionPane.showMessageDialog(managerReq.requestPanel ," Request Canceled  " , "Request", JOptionPane.INFORMATION_MESSAGE);
						managerReq.dtmTable.removeRow(selectedRowIndex);
					}
					else
						JOptionPane.showMessageDialog( managerReq.requestPanel," Request Not Canceled " , "Request", JOptionPane.ERROR_MESSAGE);
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}

}

