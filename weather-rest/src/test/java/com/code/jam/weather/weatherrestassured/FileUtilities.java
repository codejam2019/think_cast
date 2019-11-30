package com.code.jam.weather.weatherrestassured;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtilities {

	public String getPropertyValue(String propertyKey) {
		String propertyValue = null;
		try {
			// Getting value of given property key from property file.
			propertyValue = loadPropertyFile().getProperty(propertyKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}

	public Properties loadPropertyFile() throws IOException {
		Properties prop = new Properties();
		InputStream is = FileUtilities.class.getResourceAsStream("/application.properties");
		// Loading property file
		prop.load(is);
		return prop;
	}
}
