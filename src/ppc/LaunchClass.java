package ppc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.DatabaseCon;
import ui.LoginScreen;

	public class LaunchClass {

	public Connection con;
	public DatabaseCon dB;

	
	public LaunchClass()
	{
		 dB= new DatabaseCon();
		try {
			con=dB.getDBConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args ) throws SQLException ,ClassNotFoundException
	{
		LaunchClass lc=new LaunchClass();
		new LoginScreen(lc);

		
		
	}

}
