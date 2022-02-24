package com.group3.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.group3.capstone.beans.Bulletin;
import com.group3.capstone.beans.Post;
import com.group3.capstone.services.ApplicationService;

public class ApplicationDao implements ApplicationService {
	
	//see entire AC Bulletin
	@Override
	public Map<Integer, Bulletin> readACBulletin() {
		
		Bulletin bulletin = null;
		Map<Integer, Bulletin> posts = new LinkedHashMap<Integer, Bulletin>();
		
		try {
			//get connection to DB
			Connection connection = DBConnection.getConnectionToDatabase();
			
			//write select query
			String sql = "select * from bulletins;";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			//execute query
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				bulletin = new Bulletin();
				bulletin.setBulletinID(set.getInt("bulletinID"));
				bulletin.setBulletinName(set.getString("bulletinName"));
				//put each set into list 
				posts.put(bulletin.getBulletinID(), bulletin);
				
			}
		}catch (SQLException exception) {
			exception.printStackTrace();	
		}
		return posts;
	}
	
	//view a single bulletins registry
	@Override
	public List<Post> readBulletin(int bulletinID) {
		
		Bulletin bulletin = null;
		List<Post> postRegistry = null;
		
		try {
			//get connection to DB
			Connection connection = DBConnection.getConnectionToDatabase();
			
			//write select query
			String sql = "select * from bulletins where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			//set to id given in parameter
			statement.setInt(1, bulletinID);
			
			//execute query
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				bulletin = new Bulletin();
				bulletin.setBulletinID(set.getInt("bulletinID"));
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
	

}
