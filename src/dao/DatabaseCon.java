package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ppc.loadProperties;

public class DatabaseCon {

	Connection con;

	private loadProperties lp;
	public Connection getDBConnection() throws SQLException ,ClassNotFoundException, IOException
	{
		lp=new loadProperties();
		lp.loadproperties();
		String url=lp.returnProperty("url");
		String user=lp.returnProperty("username");
		String pwd=lp.returnProperty("passw");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection(url,user,pwd);
		System.out.println("Connection created suuccessfully");
		return con;
			
	}
	public Connection getCon()
	{
		
		return con;
	}
	

	public void closeAll() throws SQLException
	{System.out.println("diconnected");
		con.close();
	}
	
	
	
}
