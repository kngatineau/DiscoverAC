package com.group3.capstone.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	public Bulletin getBulletin(UUID bulletinId) {
		Bulletin retrievedBulletin = null;
		
		try {
			//Search for bulletin
			String sql = "select * from bulletin where bulletinId = \'" + bulletinId.toString() +"\';";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			//execute query
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				//Retrieve bulletin if exists.
				retrievedBulletin = new Bulletin(UUID.fromString(set.getString("bulletinId")), set.getString("bulletinName"));
			}
		}catch (SQLException exception) {
			exception.printStackTrace();	
		}
		
		return retrievedBulletin;
	}

	//view a single bulletins registry
	@Override
	public List<Post> getBulletinPosts(UUID bulletinId) {
		
		List<Post> postRegistry = new ArrayList<Post>();
		
		try {
			//write select query
			String sql = "select * from post where bulletinId = \'" + bulletinId.toString() +"\'"
					+ "order by postDate desc;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			//execute query
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				//Add new post object if exists.
				postRegistry.add(
						new Post(UUID.fromString(set.getString("postId")),
								set.getString("title"),set.getString("description"),set.getString("url"),
								Date.valueOf(set.getString("postDate")),
								UUID.fromString(set.getString("bulletinId")),
								UUID.fromString(set.getString("authorId"))
								));
			}
		}catch (SQLException exception) {
			exception.printStackTrace();	
		}
		
		return postRegistry;
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
	
	@Override
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
	
	@Override	
	public User getUser(UUID userId) {
	User retrievedUser = null;
	String sql = "SELECT * FROM user WHERE userId= '"+userId.toString()+"';";
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
	
	@Override
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
