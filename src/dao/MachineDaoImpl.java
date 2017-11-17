package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Machine;




public class MachineDaoImpl implements MachineDao{
	
	DatabaseCon databaseCon;
	PreparedStatement ps,ps1,ps2,ps3,ps4;
		Connection con;
		public ResultSet rs,rs1,rs2,rs3,rs4;
		String selectedOrderId,machineno;
		   public MachineDaoImpl(DatabaseCon databaseCon)
	{
		this.databaseCon=databaseCon;
		this.con=databaseCon.getCon();
	}
   
	//-------ADDING CURRENT ID
	@Override
		   public boolean currentOrderId(String OrderId){
				boolean bool=false;	
				if(OrderId!=null){
				this.selectedOrderId=OrderId;
				bool=true;
				}
				else bool=false;
				return bool;
				}
   public boolean setMachineData(ArrayList<Machine> machineAl)
	{ boolean bool=false;
	   if(!machineAl.isEmpty()){
		for(Machine i:machineAl){
			try {
				ps=databaseCon.con.prepareStatement("insert into machines values(?,?,?)");
				ps.setString(1,String.valueOf(i.getMachineNo()));
				ps.setString(2,i.getNameOFMachine());	
				ps.setString(3,"1");
	            			
			    ps.executeQuery();
			    bool=true;
			} catch (SQLException e) {
				 bool=false;
				e.printStackTrace();
			}
		}
			for(Machine m:machineAl){
				try {
					ps2=databaseCon.con.prepareStatement("insert into machine_allocation values(?,?,?)");
					ps2.setString(1,String.valueOf(m.getMachineNo()));
					
					ps2.setString(2,m.getNameOFMachine());	
					ps2.setString(3,selectedOrderId);
				    ps2.executeQuery();
				    bool=true;
				} catch (SQLException e) {
					 bool=false;	
					e.printStackTrace();
				}	
	}
	
	}
	   
	System.out.println("machine bool="+bool);
	   return bool;
	}
   @Override
	
	public ArrayList<String> getOrders(String managerId)
	{System.out.println("GETORDERS");
    
		ArrayList<String> al=new ArrayList<String>();
	
		try {
		
			
			ps3=databaseCon.con.prepareStatement("select ORDERID from orders where managerId ='"+managerId+"'");
			System.out.println("ps3getOrders="+ps3);
			System.out.println("executing");
			 rs3=ps3.executeQuery();
			 System.out.println("query executing");
			 while(rs3.next())
			 {
				 al.add(rs3.getString(1));
				 System.out.println("Orders of gotResources =0"+rs3.getString(1));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	//	}
}
		return al;
	}

   @Override
	
	public boolean updateOrderId(String selectedOrderId){
		boolean bool=false;
	   this.selectedOrderId=selectedOrderId;
		try {
			ps4=databaseCon.con.prepareStatement("update orders set gotResources=1,allocated=0 where orderId=?");
			ps4.setString(1,selectedOrderId);
			int i = ps4.executeUpdate();
			System.out.println("i="+i);
           bool=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			bool=false;
			e.printStackTrace();
		}
		return bool;
	}
   
@Override
public ArrayList<Machine> getMachineData(String orderid) throws SQLException, ClassNotFoundException {
	// TODO Auto-generated method stub
	
	   ArrayList<Machine> als=new ArrayList<Machine>();
		//ArrayList<ArrayList<Machine>> finalal =new ArrayList<ArrayList<Machine>>();
		ArrayList<String> al=new ArrayList<String>();

	   ps=databaseCon.con.prepareStatement("select machineno from machine_allocation where orderId =? ");
		System.out.println("orderid="+orderid);
		ps.setString(1,orderid);
				rs=ps.executeQuery();
			
while(rs.next())
		{  
System.out.println("inside");
machineno=rs.getString(1);
al.add(machineno);
System.out.println("machineno="+machineno);
//al.add(rs.getString(1));
		}
for(String i:al){
		ps=databaseCon.con.prepareStatement("select * from machines where machineno =? ");
ps.setString(1,i);
		
		rs=ps.executeQuery();
		while(rs.next())
		{ Machine m=new Machine();
			m.setMachineNo(rs.getString(1));
			m.setNameOFMachine(rs.getString(2));
			m.setQuantityOfMachine(rs.getInt(3));
			
			MyTaskDaoImpl mtImpl = new MyTaskDaoImpl(con);
			
			m.setAllottedTask(mtImpl.getAllocatedTasks(m.getMachineNo()));
			als.add(m);
		}
		
	}


return als;
}

}

