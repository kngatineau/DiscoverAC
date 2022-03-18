package com.group3.capstone.beans;

import java.util.List;
import java.util.UUID;

public class Bulletin {
	
	private UUID bulletinId;
	private String bulletinName;
	private String bulletinDescription;
	private UUID parentBulletinId;
	private List<Integer> checkBulletinId;
	private List<Post> postRegistry;

	public Bulletin(UUID bulletinId, String bulletinName){
		this.bulletinId = bulletinId;
		this.bulletinName = bulletinName;
	}

	private void viewPostByDate() {
		
	}

	
	public UUID getBulletinID() {
		return bulletinId;
	}


	public void setBulletinID(UUID bulletinId) {
		this.bulletinId = bulletinId;
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


	public UUID getParentBulletinID() {
		return parentBulletinId;
	}


	public void setParentBulletinID(UUID parentBulletinId) {
		this.parentBulletinId = parentBulletinId;
	}


	public List<Integer> getCheckBulletinID() {
		return checkBulletinId;
	}


	public void setCheckBulletinID(List<Integer> checkBulletinId) {
		this.checkBulletinId = checkBulletinId;
	}

	
	public List<Post> getPostRegistry() {
		return postRegistry;
	}

	
	public void setPostRegistry(List<Post> postRegistry) {
		this.postRegistry = postRegistry;
	}

	
}
