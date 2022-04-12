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
	public User getUser(String userName);
	public boolean verifyUser(UUID userId);
	public boolean updateUser(User user);
	public boolean updateUserPassword(User user, String newPassword);
	public boolean verifyUserEmail(String email);
	
	// To Manage User Sessions
	public void createSession(UserSession session);
	public UserSession getSession(UUID sessionId);
	public boolean verifySession(UUID sessionId);
	public boolean verifyUserNamePassword(String userName, String password);
	public boolean verifyUserName(String userName);

	public boolean deleteSession(UUID sessionId);

}
