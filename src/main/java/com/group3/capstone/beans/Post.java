package com.group3.capstone.beans;

import java.sql.Date;
import java.util.UUID;

public class Post {
	private UUID postId;
	private UUID bulletinId;
	private UUID authorId;
	private String title;
	private String description;
	private String url;
	private Date postDate;
	
	

	public Post(UUID postId, String title, String description, String url, Date postDate, UUID bulletinId,
			UUID authorId) {
		this.postId = postId;
		this.bulletinId = bulletinId;
		this.authorId = authorId;
		this.title = title;
		this.description = description;
		this.url = url;
		this.postDate = postDate;

	}


	public UUID getPostId() {
		return postId;
	}


	public void setPostId(UUID postId) {
		this.postId = postId;
	}


	public UUID getBulletinId() {
		return bulletinId;
	}


	public void setBulletinId(UUID bulletinId) {
		this.bulletinId = bulletinId;
	}


	public UUID getAuthorId() {
		return authorId;
	}

	public void setAuthorId(UUID authorId) {
		this.authorId = authorId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Date getPostDate() {
		return postDate;
	}


	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}


}
	