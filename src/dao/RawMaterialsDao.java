package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.RawMaterials;

public interface RawMaterialsDao {
	
	public boolean setRawData(ArrayList<RawMaterials> rawAl);

	//public void getRawData(ArrayList<RawMaterials> rawAl);

	ArrayList<RawMaterials> getRawData(String orderid);
public boolean currentOrderId(String OrderId) throws SQLException;		
	

}
