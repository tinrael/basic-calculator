package com.mycompany.calculator.basic_calculator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(
				DatabaseConfiguration.getDatabaseUrl(), 
				DatabaseConfiguration.getDatabaseUsername(), 
				DatabaseConfiguration.getDatabasePassword());
	}
}
