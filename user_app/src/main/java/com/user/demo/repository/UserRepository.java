package com.user.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.demo.model.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String>
{
	@Query(value="select u from User u where u.loginId= :loginId and u.password= :password")
	public User validateUser(String loginId, String password);//login
	
	@Query(value="select u from User u where  u.loginId= :loginId")
	public User findUserByLoginId(String loginId);//login
	
	@Query(value="select u from User u where  u.email= :email")
	public User findUserByEmail(String email);//login
	
	
	
	

}
