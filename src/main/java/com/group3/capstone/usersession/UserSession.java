package com.group3.capstone.usersession;

import java.util.UUID;

import com.group3.capstone.dao.ApplicationDao;
import com.group3.capstone.user.User;

public class UserSession {
	private User user;
	private UUID sessionId;
	
	private ApplicationDao sessionDB = new ApplicationDao();
	
	public UserSession() {
		sessionId = UUID.randomUUID();
	}

	public UUID getSessionId() {
		return sessionId;
	}
	public User getUser() {
		return user;
	}
	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setSession(UUID sessionId) {
		UserSession retrievedSession = sessionDB.getSession(sessionId);
		this.sessionId = retrievedSession.getSessionId();
		this.user = retrievedSession.getUser();
	}
	
	
}
