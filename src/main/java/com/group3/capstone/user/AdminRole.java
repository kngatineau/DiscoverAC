package com.group3.capstone.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.group3.capstone.dao.DBConnection;

public class AdminRole extends Role {
	
	private static final String roleName = "ADMIN";
	
	/**
	 * Constructors
	 */
	
	// may not all return void
	
	public void readUser() {
		
	}
//	public User createUser(User user) {
//		try {
//			//get connection to DB
//			Connection connection = DBConnection.getConnectionToDatabase();
//			//write insert query for new user
//			String sql = "INSERT INTO user (firstName, lastName, userName, password, email, userId) values (?,?,?,?,?,?);";
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, user.getFirstName());
//			statement.setString(2, user.getLastName());
//			statement.setString(3, user.getUserName() );
//			statement.setString(4, user.getPassword());
//			statement.setString(5, user.getEmail());
//			
//			//get UUID as string to pass to DB
//			UUID string = user.getUserID();
//			String uuiD = string.toString();
//			
//			statement.setString(6,  uuiD);
//			
//			//execute query and update result set
//			statement.execute();
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//			
//		}
//		return user;
		
//	}
	public void updateUser() {
		
	}
	public void deleteUser() {
		
	}
	public void readPost() {
		
	}
	public void createPost() {
		
	}
	public void updatePost() {
		
	}
	public void deletePost() {
		
	}
}
