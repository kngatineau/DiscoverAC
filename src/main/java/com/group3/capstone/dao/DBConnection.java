package com.group3.capstone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection dbConnection = new DBConnection();
	private static Connection connection = dbConnection.getConnectionToDatabase();
	
	private DBConnection() {
		final String dbName = "DiscoverAc";
		
		final String dbUrl = "jdbc:mysql://cst8288capstone.mysql.database.azure.com:3306/"
				+ dbName +"?useSSL=true&requireSSL=false&autoReconnect=true";
		
		final String dbUser = "Group3@cst8288capstone";
		final String dbPassword = "AlgonquinCP2022Winter";
		
		System.out.println("Attempting to connect to database.");
		
		try {
			//load driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			System.out.println("MySql JDBC Driver Registered!");
			
			//get hold of driver manager
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
		}catch (ClassNotFoundException e) {
			System.out.println("Where is your MySqlJDBC Driver?");
			e.printStackTrace();
		}
		
		catch(SQLException e) {
			System.out.println("Conection failed! Check output console");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("Connection made to db!");
		}
	}
	
	public static synchronized Connection getConnectionToDatabase() {
	        
		return connection;
	}
}
