package dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import bean.RawMaterials;
import dao.DatabaseCon;
import dao.RawMaterialsDaoImpl;

public class RawMaterialsDaoImplTest  {
	

boolean bool;
DatabaseCon dbc=new DatabaseCon();
RawMaterialsDaoImpl rmdi;

RawMaterials rm=new RawMaterials();
String orderId="testOrder";

ArrayList<RawMaterials> rawAl=new ArrayList<RawMaterials>();

@Test
	public void testCurrentOrderId() {
        Connection();
       rmdi=new RawMaterialsDaoImpl(dbc);

        bool=rmdi.currentOrderId(orderId);
    assertEquals(true, bool);

	}

public void Connection(){
	try {
		dbc.getDBConnection();
	} catch (ClassNotFoundException | SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

@Test
	public void testSetRawData() {
	Connection();
	rmdi=new RawMaterialsDaoImpl(dbc);

	RawMaterials rm1=new RawMaterials();
	rm1.setRawNo("r-1");
	rm1.setRawName("sugar testing");
	rm1.setRawQuantity(20);
	rawAl.add(rm1);
	
	    rmdi.currentOrderId(orderId);
		bool=rmdi.setRawData(rawAl);
	
		assertEquals(true, bool);
	}

	@Test
	public void testGetRawData() {
		Connection();
		rmdi=new RawMaterialsDaoImpl(dbc);

		ArrayList<RawMaterials> rawAl2=new ArrayList<RawMaterials>();
		rawAl2=rmdi.getRawData(orderId);
		for(RawMaterials m1:rawAl2){
		assertEquals("r-1",m1.getRawNo() );
		assertEquals("sugar testing",m1.getRawName() );
		assertEquals(20,m1.getRawQuantity() );
		
		}
		
	
	} 
	

}
