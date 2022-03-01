package com.group3.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.group3.capstone.beans.Bulletin;
import com.group3.capstone.beans.Post;
import com.group3.capstone.beans.User;
import com.group3.capstone.services.ApplicationService;

public class ApplicationDao implements ApplicationService {
	
	private Connection connection = null;

	// Make connection upon DAO instantiation.
    public ApplicationDao() {
    	this.connection = DBConnection.getConnectionToDatabase();;

    }
// Commented out for future use.
//
//	//see entire AC Bulletin
//	@Override
//	public Map<Integer, Bulletin> readACBulletin() {
//		
//		Bulletin bulletin = null;
//		Map<Integer, Bulletin> posts = new LinkedHashMap<Integer, Bulletin>();
//		
//		try {
//			//write select query
//			String sql = "select * from bulletin;";
//			PreparedStatement statement = connection.prepareStatement(sql);
//			
//			//execute query
//			ResultSet set = statement.executeQuery();
//			while (set.next()) {
//				bulletin = new Bulletin();
//				bulletin.setBulletinID(set.getInt("bulletinId"));
//				bulletin.setBulletinName(set.getString("bulletinName"));
//				//put each set into list 
//				posts.put(bulletin.getBulletinID(), bulletin);
//				
//			}
//		}catch (SQLException exception) {
//			exception.printStackTrace();	
//		}
//		return posts;
//	}
	
	//view a single bulletins registry
	
	public List<Post> readBulletin(UUID bulletinID) {
		
		Bulletin bulletin = null;
		List<Post> postRegistry = null;
		
		try {
			//write select query
			String sql = "select * from bulletin where bulletinId = " + bulletinID.toString();
			
			// HJ: Wondering if the below prepareStatement will be superceded by JSP approach.
			PreparedStatement statement = connection.prepareStatement(sql);
			//set to id given in parameter
			statement.setString(1, bulletinID.toString());
			
			//execute query
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				bulletin = new Bulletin();
				bulletin.setBulletinID(UUID.fromString(set.getString("bulletinID")));
				bulletin.setBulletinName(set.getString("bulletinName"));
				//use method from Bulletin to show registry
			}
		}catch (SQLException exception) {
			exception.printStackTrace();	
		}
		postRegistry = bulletin.getPostRegistry();
		return postRegistry;
	}

	@Override
	public Post readPost(String id) {
		
		return null;
	
	}

	@Override
	public void writePost(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePost(String id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<Integer, Bulletin> readACBulletin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> readBulletin(int bulletinID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//all CRUD operations for USER table
	//method to verify if user is in database of registered users
	public boolean verifyUser(String x, String y) {
	boolean match = false;
	String sql = "SELECT * FROM user WHERE username=? AND password=?;";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, x);
		statement.setString(2, y);
		
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			match = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return match ;
	}
	
	public boolean verifyUser(UUID userId) {
	boolean match = false;
	String sql = "SELECT * FROM user WHERE userId = '"+userId.toString()+"';";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			match = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return match ;
	}
	
	public User getUser(String userName) {
	User retrievedUser = null;
	String sql = "SELECT * FROM user WHERE username= '"+userName+"';";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			retrievedUser = new User(set.getString("userId"), set.getString("firstName"), set.getString("lastName"), 
					set.getString("userName"), set.getString("email"), set.getString("password"));
		} else {			
			retrievedUser = new User("NullFirsName", "NullLastName", "NullUserName", "NullEmail", "NullPassword");
		}

		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return retrievedUser;
	}
	
	
	public User createUser(User user) {
		try {
			//write insert query for new user
			String sql = "INSERT INTO user (firstName, lastName, userName, password, email, userId) values (?,?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(0, user.getFirstName());
			statement.setString(1, user.getLastName());
			statement.setString(2, user.getUserName() );
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			//get UUID as string to pass to DB
			UUID string = user.getUserID();
			String uuiD = string.toString();
			statement.setString(5,  uuiD);
			
			statement.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}	
}
