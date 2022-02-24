package com.group3.capstone.services;

import java.util.List;
import java.util.Map;

import com.group3.capstone.beans.Bulletin;
import com.group3.capstone.beans.Post;

public interface ApplicationService {
	
	//view entire AC bulletin, integer stands for our ID 
	public Map<Integer, Bulletin> readACBulletin();
	
	//view child AC bulletin
	public List<Post> readBulletin(int bulletinID);
	
	//for individual Posts
	public Post readPost(String id);
	
	public void writePost(Post post);
	
	public void deletePost(String id);
	
}
