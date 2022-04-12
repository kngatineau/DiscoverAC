package com.group3.capstone.dao;

import java.util.List;
import java.util.UUID;

import com.group3.capstone.beans.Bulletin;
import com.group3.capstone.beans.Post;
import com.group3.capstone.services.ApplicationService;
import com.group3.capstone.user.User;
import com.group3.capstone.usersession.UserSession;

public class ApplicationDaoProxy implements ApplicationService {
	//give proxy reference to real Application dao and controls access to it
	private ApplicationService daoReal = new ApplicationDao();
	//constructor
	public ApplicationDaoProxy() {
	
	}
	
	public boolean verifySession(UUID sessionId) {
		return daoReal.verifySession(sessionId);
	}
	
	public UserSession getSession(UUID sessionId) {
		return daoReal.getSession(sessionId);
	}
	
	public List<Post> getBulletinPosts(UUID bulletinId) {
		return daoReal.getBulletinPosts(bulletinId);
	}

	public Bulletin getBulletin(UUID bulletinId) {
		return daoReal.getBulletin(bulletinId);
	}
	
	public User getUser(UUID authorId) {
		return daoReal.getUser(authorId);
	}

	@Override
	public Post readPost(String id) {
		return daoReal.readPost(id);
	}

	@Override
	public void writePost(Post post) {
		daoReal.writePost(post);
		
	}

	@Override
	public void deletePost(String id) {
		daoReal.deletePost(id);
		
	}

	@Override
	public void createUser(User user) {
		daoReal.createUser(user);
		
	}

	@Override
	public boolean verifyUser(UUID userId) {
		return daoReal.verifyUser(userId);
	}

	@Override
	public boolean updateUser(User user) {
		return daoReal.updateUser(user);
	}
	public boolean updateUserPassword(User user, String newPassword) {
		return daoReal.updateUserPassword(user, newPassword);
	}

	@Override
	public void createSession(UserSession session) {
		daoReal.createSession(session);
	}

	@Override
	public User getUser(String userName) {
		return daoReal.getUser(userName);
	}

	@Override
	public boolean verifyUserNamePassword(String userName, String password) {
		return daoReal.verifyUserNamePassword(userName, password);
	}

	@Override
	public boolean verifyUserEmail(String email) {
		return daoReal.verifyUserEmail(email);
	}

	@Override
	public boolean verifyUserName(String userName) {
		return daoReal.verifyUserName(userName);
	}

	@Override
	public boolean deleteSession(UUID sessionId) {
		return daoReal.deleteSession(sessionId);
	}
}
