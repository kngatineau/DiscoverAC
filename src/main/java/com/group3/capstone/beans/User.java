package com.group3.capstone.beans;

import java.awt.image.BufferedImage;
import java.util.UUID;

public abstract class User {
	
	private UUID userID;
	private String userName;
	private String firstName;
	private String lastName;
	private BufferedImage avatar;
	private String email;
	private String password;
	private Role role; // SignedUserRole? 
	
	/**
	 * Constructors here
	 */
	
	public UUID getUserID() {
		return userID;
	}
	public void setUserID(UUID userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	} 
	public BufferedImage getAvatar() {
		return avatar;
	}
	public void setAvatar(BufferedImage avatar) {
		this.avatar = avatar;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
	
	/**
	 * 'role' getters and setters
	 */
}
