package com.group3.capstone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 
	public static Connection getConnectionToDatabase() {
	        
		Connection connection = null;
	        
		System.out.println("Attempting to connect to database.");
		
		try {
	        	//load driver class
	        	Class.forName("com.mysql.jdbc.Driver");
	        	System.out.println("MySql JDBC Driver Registered!");
	        	
	        	//get hold of driver manager
	        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loggy", "root", "PLACEHOLDER");
	        
		}catch (ClassNotFoundException e) {
	        	System.out.println("Where is your MySqlJDBC Driver?");
	        	e.printStackTrace();
	        }
	            
	       catch(SQLException e) {
	        	System.out.println("Conection failed! Check output console");
	        	e.printStackTrace();
	        }
	        if (connection != null) {
	        	System.out.print("Connection made to db!");
	        }
	       
	        return connection;
	    }
}
