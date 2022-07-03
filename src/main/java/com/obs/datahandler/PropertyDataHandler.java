package com.obs.datahandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyDataHandler {

	public static final String currentDir = System.getProperty("user.dir");
	public static final String filePath = currentDir + "/src/main/resources/";
	
	public Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(filePath+fileName);
			prop = new Properties();
			prop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			fis.close();
		}
		return prop;
	}
}
