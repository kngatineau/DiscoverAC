package com.group3.capstone.user;

import java.awt.image.BufferedImage;
import java.util.UUID;

public class User {
	
	private UUID userID;
	private String userName;
	private String firstName;
	private String lastName;
	private BufferedImage avatar;
	private String email;
	private String password;
	//private Role role; // SignedUserRole? 
	
	/**
	 * Constructors here
	 */
	//constructor
	public User() {
		this.userID = UUID.randomUUID();
	}
	
	public User(String first, String last, String userName, String email, String password) {
		this.firstName = first;
		this.lastName = last;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userID = UUID.randomUUID();
	}
	
	public User(String userID, String first, String last, String userName, String email, String password) {
		this.userID = UUID.fromString(userID);
		this.firstName = first;
		this.lastName = last;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public UUID getUserID() {
		return userID;
	}
	public void setUserID() {
		this.userID = UUID.randomUUID();
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
	public void setEmail(String email2) {
		
	}
	public String getEmail() {
		return this.email;
	}
	
	
	
	/**
	 * 'role' getters and setters
	 */
}
