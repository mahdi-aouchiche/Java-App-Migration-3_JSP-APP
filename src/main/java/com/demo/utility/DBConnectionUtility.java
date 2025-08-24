package com.demo.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtility {

	private static final String URL = "jdbc:mysql://localhost:3306/company_hr_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "3306";
	
	public Connection getJDBC_Connection() { 	
		Connection connection = null;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
