package helperPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Properties;

public class helperClass {

	public String getStringValueFromPropFile(String property) throws IOException{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("global.properties");
		prop.load(fis);
		
		return prop.getProperty(property);
			
	}
	
	public String[] getArrayValueFromPropFile(String property) throws IOException{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("global.properties");
		prop.load(fis);
		
		String stringValue = prop.getProperty(property);
		
		String[] arrayValue = stringValue.split(",");
		return arrayValue;
			
	}
	
}
