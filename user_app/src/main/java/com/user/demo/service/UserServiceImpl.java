package com.user.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		
		if(userRepository.findUserByLoginId(user.getLoginId())==null && userRepository.findUserByEmail(user.getEmail())==null)
		{
			return userRepository.saveAndFlush(user);
			
		}
		return null;
	
	}

	@Override
	public boolean loginUser(String loginId, String password) {
		System.out.println("****");
		User user1 = userRepository.validateUser(loginId, password);
		System.out.println(user1);
		System.out.println("User: ");
		if(user1!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<User> getAllUsers() {
	List<User> userList = userRepository.findAll();

		System.out.println(userList);
		if(userList!=null)
		{
			if(userList.size() >0)
			return userList;
			else
				return null;
		}
		else
			return null;
	}

	@Override
	public User updatePassword(User user, String loginId) {
		
		
		
		if(userRepository.findById(loginId).isPresent())
		{
			
			User user2=userRepository.findById(loginId).get();
			
			if(user2.getQuestion().equals(user.getQuestion()) && user.getAnswer().equals(user2.getAnswer()))
			{
				user2.setPassword(user.getPassword());
				userRepository.saveAndFlush(user2);
				
				return user2;
			}
			else {
				return null;
			}
		
			
			
		}
		else {
			return null;
		}
		}



	@Override
	public User getUserById(String loginId) {
		Optional<User> userOptional = userRepository.findById(loginId);

		if (userOptional != null) {
			if (userOptional.isPresent())
				return userOptional.get();
			else
				return null;
		} else
			return null;

	}

	@Override
	public boolean deleteUser(String loginId) {
		User user=userRepository.getById(loginId);
		if(user!=null)
		{
			userRepository.deleteById(loginId);
			return true;
		}
		else {
			return false;
		}
	}


}
