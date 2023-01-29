package com.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider {

	Properties pro;

	public ConfigDataProvider() {

		File file = new File(System.getProperty("user.dir") + "/Config/config.properties");

		try {
			FileInputStream fis = new FileInputStream(file);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read the properties file " + e.getMessage());
		}

	}

	public String getValue(String key) {
		
		return pro.getProperty(key);

	}

	public String getBrowser() {
		
		return pro.getProperty("Browser");

	}

	public String getTestingUrl() {
		
		return pro.getProperty("QAUrl");

	}

}
