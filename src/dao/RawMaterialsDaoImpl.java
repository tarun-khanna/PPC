package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.RawMaterials;


public class RawMaterialsDaoImpl  implements RawMaterialsDao  {

	PreparedStatement ps,ps1;
	DatabaseCon databaseCon;
	public ResultSet rs,rs1;
	public String OrderId;
	String rawno;
	String selectedid;
	public RawMaterialsDaoImpl(DatabaseCon databaseCon)
	{
		this.databaseCon=databaseCon;
	}
@Override
	public boolean currentOrderId(String OrderId){
	boolean bool=false;	
	if(OrderId!=null){
	this.OrderId=OrderId;
	bool=true;
	}
	else bool=false;
	return bool;
	}

	
	public boolean setRawData(ArrayList<RawMaterials> rawAl) {
	boolean bool=false;
	if(!rawAl.isEmpty()){
		for(RawMaterials i:rawAl){
	          			
			
			try {
				ps=databaseCon.con.prepareStatement("insert into rawmaterials values(?,?,?,?)");
				ps.setString(1,i.getRawNo());
				ps.setString(2,i.getRawName());	
				ps.setInt(3,i.getRawQuantity());
				ps.setString(4,OrderId);
			    ps.executeQuery();
			    bool=true;
			} catch (SQLException e) {
			 bool=false;
				e.printStackTrace();
			}
			
	}
	}
		System.out.println("bool="+bool);
	return bool;
	}
	@Override
	public ArrayList<RawMaterials> getRawData(String orderid) {
	
		ArrayList<RawMaterials> als=new ArrayList<RawMaterials>();
	
		ArrayList<String> al=new ArrayList<String>();
		try {
			ps=databaseCon.con.prepareStatement("select rawno from rawmaterials where orderId =? ");
			ps.setString(1,orderid);
			rs=ps.executeQuery();
		System.out.println("from raw table orderid="+orderid);
			while(rs.next())
			{  
System.out.println("inside");

rawno=rs.getString(1);
al.add(rawno);
System.out.println("rawno="+rawno);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		for(String i:al){
			try {
				ps=databaseCon.con.prepareStatement("select * from rawmaterials where rawno =? ");
				ps.setString(1,i);
				
				rs=ps.executeQuery();
				while(rs.next())
				{ RawMaterials rm=new RawMaterials();
					rm.setRawNo(rs.getString(1));
					rm.setRawName(rs.getString(2));
					rm.setRawQuantity(rs.getInt(3));
					als.add(rm);
			}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			}
			
		
	return als;
	   }

	}
/*	@Override
	public void getRawData(ArrayList<RawMaterials> rawAl) {
		// TODO Auto-generated method stub
		
	} */
	


