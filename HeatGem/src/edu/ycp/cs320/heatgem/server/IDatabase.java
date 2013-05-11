package edu.ycp.cs320.heatgem.server;


import java.sql.SQLException;

import edu.ycp.cs320.heatgem.shared.User;
import edu.ycp.cs320.heatgem.shared.UserProfile;

public interface IDatabase {
	/**
	 * Look up a User object by username and password.
	 * 
	 * @param username the username
	 * @param password the password
	 * @return the User object, or null if there is no such user (or password is incorrect)
	 * @throws SQLException 
	 */
	public User logIn(String username, String password) throws SQLException;
	
	public boolean addUser(String username, String password, String confirmPassword,
			String email) throws SQLException;
	
	public UserProfile getUserProfile(String username);
	
	public boolean updateUserProfile(String username, UserProfile updatedProfile) throws SQLException;

	UserProfile findUserProfileByUserId(int id) throws SQLException;

	Integer getAmountUsers() throws SQLException;

	public boolean deleteUser(String username);

	public UserProfile[] updateLeaderboard() throws SQLException;

}
