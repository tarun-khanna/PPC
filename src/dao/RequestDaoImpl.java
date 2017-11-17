package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bean.Request;

public class RequestDaoImpl {

	Connection con;
	PreparedStatement pst;
	ResultSet res;

	public RequestDaoImpl(Connection con)
	{
		this.con = con;
	
	}

	public int  sendRequest(String orderId,String senderId,String receiverId,String message,Calendar currCalDate) throws SQLException
	{
		pst=con.prepareStatement("insert into requests values (?,?,?,?,?,?)");
		pst.setString(1, orderId);
		pst.setString(2, senderId);
		pst.setString(3,receiverId);
		pst.setString(4, message);
		Date currDate = currCalDate.getTime();

		pst.setDate(5,new java.sql.Date(currDate.getTime()));
		pst.setInt(6,0);
		int i=pst.executeUpdate();
		pst.close();
		return i;
	}
	
	public ArrayList<Request> receiveRequest(String receiverId) throws SQLException
	{
		pst=con.prepareStatement("Select * from requests where receiver= ? and addressed=0");
		pst.setString(1,receiverId);
		res=pst.executeQuery();
		
		ArrayList<Request> row=new ArrayList<>();
		
		while(res.next())
		{
			Request request=new Request(res.getString(1),res.getString(2),res.getString(3),res.getString(4),new Date(res.getDate(5).getTime()),res.getInt(6));
			row.add(request);
				
		}
		return row;
		
		
	}
	
	public int updateRequest(int addressed,String orderId,String message) throws SQLException
	{
		pst=con.prepareStatement("update requests set addressed=? where orderId= ? and message = ?");
		pst.setInt(1,addressed);
		pst.setString(2, orderId);
		pst.setString(3, message);
		int i =pst.executeUpdate();
		
		return i;
	
	}
	
	
}
