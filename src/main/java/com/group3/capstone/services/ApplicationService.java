package com.group3.capstone.services;

import java.util.List;
import java.util.UUID;

import com.group3.capstone.beans.Bulletin;
import com.group3.capstone.beans.Post;
import com.group3.capstone.user.User;
import com.group3.capstone.usersession.UserSession;

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
	public void createUser(User user);
	public User getUser(UUID userId);
	public boolean verifyUser(UUID userId);
	
	// To Manage User Sessions
	public void createSession(UserSession session);
	public UserSession getSession(UUID sessionId);
	public boolean verifySession(UUID sessionId);
}
