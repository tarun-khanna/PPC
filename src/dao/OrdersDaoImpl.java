package dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.Orders;


public class OrdersDaoImpl implements OrdersDao{

	Orders p;
	ArrayList<String> nameOfOrders;
	ArrayList<String> tempArray;
	
	
	Connection con;
	
	
	DatabaseCon db;
	
	PreparedStatement pst,ps3;
	ResultSet res,rs4,rs5;

	String  managerid,supervisorid,managername,supervisorname;

	
	public OrdersDaoImpl(DatabaseCon db)
	{
		this.db=db;
		this.con=db.getCon();
	}

	@Override
	public boolean setTableData(Orders p) throws SQLException, ClassNotFoundException, IOException {
		boolean bool=false;
		PreparedStatement pst;
		
		if(p!=null){
		
			
		
       System.out.println(p);
	pst=db.con.prepareStatement("INSERT INTO orders " + 
                "VALUES (?,?,?,?,?,?,?,'null',?,?,?,?,?)");
			System.out.println("pst="+pst);
			
		pst.setString(1, p.getOrderId());
		pst.setString(2, p.getNameOfProject());
		//if(p.getStartDate()!=null)
		pst.setDate(3,new java.sql.Date(p.getStartDate().getTime()));
		System.out.println("new java.sql.Date(p.getStartDate().getTime())"+new java.sql.Date(p.getStartDate().getTime()));
		//if(p.getEndDate()!=null)
		pst.setDate(4, new java.sql.Date(p.getEndDate().getTime()));
		pst.setString(5, p.getCompany());
		pst.setInt(6, p.getQuantity());
		pst.setString(7, p.getUnit());
		pst.setString(8, p.getManagerId());
		pst.setInt(9, 0);
		pst.setInt(10, 0);
		pst.setString(11, p.getManpower());
        if(p.getDueDate()!=null)
		pst.setDate(12, new java.sql.Date(p.getDueDate().getTime()));
        else pst.setDate(12,null);
        pst.executeUpdate();
        pst.close();
		bool=true;
		}
		else bool=false;
		return bool;
		
	}
	@Override
	public ArrayList<String> getReportOrders(String managerId)
	{System.out.println("GET Report ORDERS");
    
		ArrayList<String> al=new ArrayList<String>();
		try {
			ps3=db.con.prepareStatement("select ORDERID from orders where managerId=?");
			ps3.setString(1,managerId);
			 rs4=ps3.executeQuery();
			 System.out.println("query executing");
			 while(rs4.next())
			 {
				 al.add(rs4.getString(1));
				 System.out.println(rs4.getString(1));
			 }
			 ps3.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	
		System.out.println("getReportOrders Implemented Successfully");
	return al;
	}

	@Override
	public int calcOrderId() throws SQLException,ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(pst);
		pst=db.con.prepareStatement("select * from orders");
		System.out.println(pst);
		res=pst.executeQuery();
		int count=1;
		while(res.next())
		{	++count;
			System.out.println(res.getString(1)+"\t"+res.getString(2)+"\t"+res.getString(3)+"\t"+res.getString(4)+"\t"+res.getString(5)+"\t"
		+res.getString(6)+"\t"+res.getString(7)+"\t"+res.getString(8)+"\t"+res.getString(9)+"\t"+res.getString(10)+"\t"+res.getString(11)+"\t"+res.getString(12)+"\t"+res.getString(13));
		}		
		 pst.close();
		return count;
	}
	
	public Orders getTableData(String orderid) throws SQLException,ClassNotFoundException, ParseException {
		// TODO Auto-generated method stub
		pst=db.con.prepareStatement("select * from orders where orderId =? ");
		System.out.println("pst"+pst);
		System.out.println("orderid="+orderid);
		pst.setString(1,orderid);
		
		res=pst.executeQuery();
		Orders o=new Orders();		
	while(res.next())
		{   
	
    o.setOrderId(res.getString(1));
    o.setNameOfProject(res.getString(2));
    if(res.getDate(3)!=null)
		try {
			o.setStartDate(new java.util.Date(res.getDate(3).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	else
		try {
			o.setStartDate(null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    if(res.getDate(4)!=null)
		try {
			o.setEndDate(new java.util.Date(res.getDate(4).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	else
		try {
			o.setEndDate(null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
   o.setManagerId(res.getString(9));
   o.setCompany(res.getString(5));
  if(res.getString(12)!=null)
   o.setManpower(res.getString(12));
  else o.setManpower(null);
   o.setQuantity(res.getInt(6));
		}
	pst.close();
		return o;		
	
	} 

	

	@Override
	public String supervisorId(String orderid)
	{
		try {
			
			ps3=db.con.prepareStatement("select supervisorid from orders where orderid=?");
			ps3.setString(1,orderid);
			rs4=ps3.executeQuery();
			while(rs4.next()){
				
			
	         supervisorid=rs4.getString(1);
			 System.out.println("supervisor id="+supervisorid);
			}
			ps3.close();
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
try {
			
			pst=db.con.prepareStatement("select name from supervisor where username=?");
			pst.setString(1,supervisorid);
			rs5=pst.executeQuery();
			while(rs5.next()){
			
			
	         supervisorname=rs5.getString(1);
			 System.out.println("supervisor name="+supervisorname);
			}
			pst.close();
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
	   if(supervisorname==null)
	   {
		   supervisorname="No Supervisor assigned for this orderno.";
	   }
		return supervisorname;
		
	}
	
	@Override
	public String[] displayTableData(String str) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
	//	con=db.getDBConnection();
		pst=db.con.prepareStatement("select * from orders where ORDERNAME=?");
		pst.setString(1, str);
		res=pst.executeQuery();
		int i=0;
		String extraStr[]=new String[13];
		while(res.next())
		{
			while(i<13)
			{
			extraStr[i]=res.getString(i+1);
			System.out.println(extraStr[i]);
			++i;
			}
		}
		pst.close();
		//closeAll();
		return extraStr;
	}

	@Override
	public ArrayList<String> getNameData(String managerId) throws ClassNotFoundException, SQLException,IOException {
		// TODO Auto-generated method stub
		//con=db.getDBConnection();	
		pst=db.con.prepareStatement("select ORDERNAME from orders where managerId=?");
		pst.setString(1, managerId);
		res=pst.executeQuery();
		ArrayList<String> nameOfOrders=new ArrayList<String>();
		while(res.next())
		{
			nameOfOrders.add(res.getString("ORDERNAME"));
		}
		pst.close();
		//closeAll();
		return nameOfOrders;
			
	}

	@Override
	public ArrayList<String> getNullSupervisorData(String managerId) throws SQLException, ClassNotFoundException ,IOException{
		// TODO Auto-generated method stub
		//con=db.getCon();
		nameOfOrders=new ArrayList<String>();
		pst=db.con.prepareStatement("select ORDERNAME from orders where SUPERVISORID='null' AND MANAGERID=?");

		pst.setString(1, managerId);
		res=pst.executeQuery();
		while(res.next())
		{
			nameOfOrders.add(res.getString("ORDERNAME"));
		}
		pst.close();
		//closeAll();
		return nameOfOrders;
	}
	
	
	@Override
	public boolean updateSupervisor(String str1,String str2) throws SQLException, ClassNotFoundException,IOException{
		// TODO Auto-generated method stub
		//con=db.getCon();
		boolean bool=false;
		if(true){
		pst=db.con.prepareStatement("update orders set SUPERVISORID=? where ORDERNAME=?");
    	pst.setString(1, str1);
    	pst.setString(2, str2);
    	pst.executeUpdate();
    //	getTableData();
		bool=true;
		}
    	//closeAll();
		else bool=false;
		pst.close();
		return bool;
	}

	@Override
	public ArrayList<String> getSupervisors() throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		tempArray=new ArrayList<String>();
		pst=db.con.prepareStatement("select username from supervisor");
		res=pst.executeQuery();
		while(res.next())
		{
			tempArray.add(res.getString("username"));
		}
		pst.close();
		return tempArray;
	}

	
	public ArrayList<String> getOrders(String supId) throws SQLException
	{System.out.println("GETORDERS");
    
		ArrayList<String> al=new ArrayList<String>();
		pst=db.con.prepareStatement("select ORDERID from orders where SupervisorId=?");
		pst.setString(1,supId);
		res=pst.executeQuery();
		System.out.println("query executing");
		while(res.next())
		{
			al.add(res.getString(1));
			System.out.println(res.getString(1));
		}
		
		pst.close();
		return al;
	}
	
	public String getProductionManager(String orderId) throws SQLException
	{
		pst=db.con.prepareStatement("select managerId from orders where orderId= ? ");
		pst.setString(1, orderId);
		res=pst.executeQuery();
		
			if(res.next())
				return res.getString(1);
			pst.close();
		return null;
	}
	
	
	@Override
	public double getProgress(String orderId) throws SQLException, ParseException
	{
		
		Date currDate = new Date();
		Date startDate=null;
		Date endDate=null;
		double startToCurr=0.0;
		double timeDiff=0.0;
		pst = db.con.prepareStatement("Select startDate,endDate from orders where orderId = ?");
		pst.setString(1, orderId);
		res = pst.executeQuery();
		res.next();
		
		
		
		SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/yyyy");
		Date d1 =formatter.parse("31/12/2050");
		Date d2 = formatter.parse("01/01/1970");
		
		Date temp = new java.util.Date(res.getDate(1).getTime());
		Date temp2 = (new java.util.Date(res.getDate(2).getTime()));
		
		if(!(temp.before(d1) || temp2.after(d2)))
			return 0;
		
		if(res.getDate(1)!=null)
		startDate = (new java.util.Date(res.getDate(1).getTime()));
		if(res.getDate(2)!=null)
	    endDate = (new java.util.Date(res.getDate(2).getTime()));
		double timeUptoCurr = currDate.getTime();
		if(startDate!=null){
		startToCurr = (timeUptoCurr - startDate.getTime());
		timeDiff = (endDate.getTime()-startDate.getTime());
		}
		pst.close();
		
		System.out.println(timeUptoCurr);
		System.out.println(startToCurr);
				System.out.println(timeDiff);
						
//int days = (int) (timeDiff / (1000*60*60*24));
double percent = ((startToCurr/timeDiff)*100);

if(percent<0)
	return 0;

return percent;


}
	
	
	
	
	@Override
	public ArrayList<String> getOrderIDs(String managerId) throws ClassNotFoundException, SQLException,IOException {

		pst=db.con.prepareStatement("select orderId from orders where managerId=?");
		
		pst.setString(1, managerId);
		res=pst.executeQuery();
		ArrayList<String> ordersList=new ArrayList<String>();
		while(res.next())
		{
			ordersList.add(res.getString(1));
		}
		//closeAll();
		pst.close();
		return ordersList;
			
	}


	@Override
	public String managerId(String orderid) {
		try {
			System.out.println("Inside Manager Id");
			ps3=db.con.prepareStatement("select managerid from orders where orderid=?");
			ps3.setString(1,orderid);
			rs4=ps3.executeQuery();
			while(rs4.next()){
				
				System.out.println("Inside Manager Iddknk");	
	         managerid=rs4.getString(1);
			 System.out.println("manager id="+managerid);
			}
			ps3.close();
			} catch (SQLException e) {
		
			e.printStackTrace();
		}
try {
			
			pst=db.con.prepareStatement("select name from ProductionManager where username=?");
			pst.setString(1,managerid);
			rs5=pst.executeQuery();
			while(rs5.next()){
				
			
	         managername=rs5.getString(1);
			 System.out.println("manager name="+managername);
			}
			pst.close();
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
if(managername==null)
{
	   managername="No Manager is assigned for this orderno.";
}
		return managername;
	}






	public boolean setDuration(Orders o) throws SQLException	//function to initialize the start , end
, ParseException
	{boolean bool=false;
		if(o!=null){
		pst =db.con.prepareStatement("select * from tasks where orderId =?");
		pst.setString(1, o.getOrderId());
		res = pst.executeQuery();
		
		Date temp1= new Date();
		Date temp2= new Date();
		
		
		while(res.next())
		{
			if(res.getDate(3)!=null && res.getDate(4)!=null)
			{
			
			temp1 = res.getDate(3);
			if(temp1.before(o.getStartDate()))
				o.setStartDate(temp1);
			temp2 = res.getDate(4);
			if(temp2.after(o.getEndDate()))
				o.setEndDate(temp2);
			}
			
		
		}
		pst.close();
		
	System.out.println("PRODUCT START DATE  : "+o.getStartDate()+" AND END DATE : " +o.getEndDate());


	int success;
	pst = db.con.prepareStatement("Update orders set startdate = ? where orderId = ?");
	if(o.getStartDate()!=null)
	pst.setDate(1, new java.sql.Date(o.getStartDate().getTime()));
	pst.setString(2, o.getOrderId());
	success = pst.executeUpdate();
	pst.close();
	pst = db.con.prepareStatement("Update orders set enddate = ? where orderId = ?");
	pst.setDate(1, new java.sql.Date(o.getEndDate().getTime()));
	pst.setString(2, o.getOrderId());
	success = pst.executeUpdate();


	
	o.setCalDates();

	pst.close();
	res.close();
	bool=true;
		}
		bool=false;
		return bool;
		}

public ArrayList<Orders> getOrdersOfSupervisor(String supId) throws SQLException, ParseException
{
	
	pst = db.con.prepareStatement("select * from orders where supervisorId=? ");		//where supervisorId is id of current supervisor
	pst.setString(1, supId);
	res=pst.executeQuery();
	
	
	ArrayList<Orders> orderList = new ArrayList<Orders>();
	
	while(res.next())
	{   
		Orders o = new Orders();
	

o.setOrderId(res.getString(1));
o.setNameOfProject(res.getString(2));
if(res.getDate(3)!=null)
o.setStartDate(new java.util.Date(res.getDate(3).getTime()));
if(res.getDate(4)!=null)
o.setEndDate(new java.util.Date(res.getDate(4).getTime()));
o.setCompany(res.getString(5));
o.setQuantity(res.getInt(6));
o.setUnit(res.getString(7));
o.setSupervisor(res.getString(8));
o.setManagerId(res.getString(9));
o.setGotResources(res.getInt(10));
o.setAllocated(res.getInt(11));
o.setManpower(res.getString(12));
o.setDueDate(new java.util.Date(res.getDate(13).getTime()));
//o.setCalDates();


orderList.add(o);
	
	}
	
	
	pst.close();
	return orderList;

}


@Override
public boolean updateAllocation(Orders o) throws SQLException
{boolean bool=false;
	if(o!=null){
o.setAllocated(1);
pst =db.con.prepareStatement("update orders set allocated = 1 where orderId = ?");
pst.setString(1, o.getOrderId());
int i = pst.executeUpdate();
bool=true;
}
	else bool=false;
	pst.close();
	return bool;
}


public ArrayList<Orders> getOrdersOfManager(String managerId) throws SQLException, ParseException
{
	
	pst = db.con.prepareStatement("select * from orders where managerId=? ");		//where supervisorId is id of current supervisor
	pst.setString(1, managerId);
	res=pst.executeQuery();
	
	
	ArrayList<Orders> orderList = new ArrayList<Orders>();
	
	while(res.next())
	{   
		Orders o = new Orders();
	

o.setOrderId(res.getString(1));
o.setNameOfProject(res.getString(2));
if(res.getDate(3)!=null)
o.setStartDate(new java.util.Date(res.getDate(3).getTime()));
if(res.getDate(4)!=null)
o.setEndDate(new java.util.Date(res.getDate(4).getTime()));
o.setCompany(res.getString(5));
o.setQuantity(res.getInt(6));
o.setUnit(res.getString(7));
o.setSupervisor(res.getString(8));
o.setManagerId(res.getString(9));
o.setGotResources(res.getInt(10));
o.setAllocated(res.getInt(11));
o.setManpower(res.getString(12));
if(res.getDate(13)!=null)
o.setDueDate(new java.util.Date(res.getDate(13).getTime()));
//o.setCalDates();


orderList.add(o);
	
	}
	
	pst.close();
	
	return orderList;

}







}
