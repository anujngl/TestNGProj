package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
	Properties pro;
	public ConfigurationReader(){
		try{
			File src = new File("./src/test/resources/configuration.properties");
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
			}catch(Exception e){
				System.out.println("Exception is "+e.getMessage());
			}
	}
	
	public String getChromeDriver(){
		String value = pro.getProperty("chrome_driver");
		return value;
	}
	
	public String getFirefoxDriver(){
		String value = pro.getProperty("firefox_driver");
		return value;
	}
	
	public String getIEDriver(){
		String value = pro.getProperty("ie_driver");
		return value;
	}

}
