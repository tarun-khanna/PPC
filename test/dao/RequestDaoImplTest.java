package dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import bean.Request;

public class RequestDaoImplTest {

	Connection c;
	RequestDaoImpl req;
	Calendar cal=Calendar.getInstance();
	Date currDate=cal.getTime();
	Request testRequest= new Request("o-24","C4_408","C4_498","just 4 testinng.",currDate,0);
	
	 void connection(){
			try {
				c= new DatabaseCon().getDBConnection();
			} catch (ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
	@Test
	public void testSendRequest() {
		connection();
		req=new RequestDaoImpl(c);
		try {
		
			
			System.out.println(testRequest.getOnDate()+"\n "+cal.getTime());
			int success=req.sendRequest(testRequest.getOrderId(),testRequest.getSender(),testRequest.getReceiver(),testRequest.getMessage(),cal);
			assertEquals(success,1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testReceiveRequest() {
		connection();
		req=new RequestDaoImpl(c);
		ArrayList<Request> expected;
		try {
			expected = req.receiveRequest("C4_498");

			for(Request r:expected )
			{
				assertEquals(r.getOrderId(),testRequest.getOrderId());
				assertEquals(r.getSender(),testRequest.getSender());
				assertEquals(r.getReceiver(),testRequest.getReceiver());
				assertEquals(r.getMessage(),testRequest.getMessage());
				assertEquals(r.getAdressed(),testRequest.getAdressed());
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testUpdateRequest() {
		connection();
		req=new RequestDaoImpl(c);
		int success;
		try {
			success=req.updateRequest(1,"o-23","just for testing");
			assertEquals(success,0);
			
			success=req.updateRequest(1,testRequest.getOrderId(),testRequest.getMessage());
			assertEquals(success,1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
