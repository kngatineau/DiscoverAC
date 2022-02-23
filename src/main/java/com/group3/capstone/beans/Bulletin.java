package com.group3.capstone.beans;

import java.util.List;

public class Bulletin {
	
	private int bulletinID;
	private String bulletinName;
	private String bulletinDescription;
	private int parentBulletinID;
	private List<Integer> checkBulletinID;
	private List<Post> postRegistry;
	
	// may not actually return void
	private void viewPostByDate() {
		
	}


	public int getBulletinID() {
		return bulletinID;
	}


	public void setBulletinID(int bulletinID) {
		this.bulletinID = bulletinID;
	}


	public String getBulletinName() {
		return bulletinName;
	}


	public void setBulletinName(String bulletinName) {
		this.bulletinName = bulletinName;
	}


	public String getBulletinDescription() {
		return bulletinDescription;
	}


	public void setBulletinDescription(String bulletinDescription) {
		this.bulletinDescription = bulletinDescription;
	}


	public int getParentBulletinID() {
		return parentBulletinID;
	}


	public void setParentBulletinID(int parentBulletinID) {
		this.parentBulletinID = parentBulletinID;
	}


	public List<Integer> getCheckBulletinID() {
		return checkBulletinID;
	}


	public void setCheckBulletinID(List<Integer> checkBulletinID) {
		this.checkBulletinID = checkBulletinID;
	}


	public List<Post> getPostRegistry() {
		return postRegistry;
	}


	public void setPostRegistry(List<Post> postRegistry) {
		this.postRegistry = postRegistry;
	}

	
}
