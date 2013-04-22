package edu.ycp.cs320.heatgem.shared;

import java.io.Serializable;

//comment

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private int highScore;
	
	public User() {
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
}
