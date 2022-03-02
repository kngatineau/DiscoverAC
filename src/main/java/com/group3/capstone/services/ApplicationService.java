package com.group3.capstone.services;

import java.util.List;
import java.util.UUID;

import com.group3.capstone.beans.Bulletin;
import com.group3.capstone.beans.Post;
import com.group3.capstone.beans.User;

public interface ApplicationService {
	
	// Get Bulletin 
	public Bulletin getBulletin(UUID bulletinId);
	
	// Get all posts in a bulletin
	public List<Post> getBulletinPosts(UUID bulletinId);
	
	//for individual Posts
	public Post readPost(String id);
	
	public void writePost(Post post);
	
	public void deletePost(String id);
	
	//for Users
	public boolean verifyUser(UUID userId);
	
	public User getUser(UUID userId);
	
	public User createUser(User user);
	
}
