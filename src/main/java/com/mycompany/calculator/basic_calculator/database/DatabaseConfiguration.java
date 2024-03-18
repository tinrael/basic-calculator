package com.mycompany.calculator.basic_calculator.database;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class DatabaseConfiguration {
	private final static Properties properties = new Properties();
	
	static {
		InputStream inputStream = DatabaseConfiguration.class.getClassLoader().getResourceAsStream("database.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getDatabaseUrl() {
		return properties.getProperty("database.url");
	}
	
	public static String getDatabaseUsername() {
		return properties.getProperty("database.username");
	}
	
	public static String getDatabasePassword() {
		return properties.getProperty("database.password");
	}
}
