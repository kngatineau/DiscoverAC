package com.group3.capstone.beans;

import java.sql.Date;

public class Post {
	private String postAuthor;
	private Date postDate;
	private String discordLink;
	private String descriptionText;
	//does Post also need an ID
	private int postID;
	

	/**
	 * Constructors
	 */
	
	private String postAuthor() {
		return postAuthor;
		
	}
	
	private Date postDate() {
		return postDate;
		
	}
	
	// 'discordLink in UML'
	private String addDiscordLink() {
		return discordLink;
			
	}
	
	
	// 'descriptionText' in UML
	private String addDescription() {
		return descriptionText;
		
	}

}
	