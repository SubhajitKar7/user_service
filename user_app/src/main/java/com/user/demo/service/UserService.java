package com.user.demo.service;

import java.util.List;

import com.user.demo.model.User;

public interface UserService {
	
	public User addUser(User user);// user registration
	
	public boolean loginUser(String loginId, String password);// login
	
	public List<User> getAllUsers();// will be visible only if you are logged in
	
	public User getUserById(String loginId);// will be visible only if you are logged in

	public boolean deleteUser(String loginId);
	public User updatePassword(User user,String loginId);

	
	
}
