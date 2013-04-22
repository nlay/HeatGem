package edu.ycp.cs320.heatgem.shared;

import java.io.Serializable;

public class UserProfile implements Serializable {

	// fields
	private int userId;
	private String name;
	private int level;
	private int experience;
	// etc.
	
	private int photoId;
	
	// methods
	
	public UserProfile() {
		
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

}