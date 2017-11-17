package ppc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class loadProperties {

	private  Properties loadProps;
	public void loadproperties() throws IOException
	{
		loadProps =new Properties();
		try {
			FileInputStream fin=new FileInputStream("config.properties");
			loadProps.load(fin);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return loadProps;
	}
	
	public String returnProperty(String key)
	{
		
		Enumeration<?> names = loadProps.propertyNames();
		while(names.hasMoreElements())
		{
			String k=(String)names.nextElement();
			if(k.equals(key))
			{
				System.out.println(loadProps.getProperty(key));
				return loadProps.getProperty(key);
			}
			
		
			
		}
		
		return null;
	
	}
	

}
