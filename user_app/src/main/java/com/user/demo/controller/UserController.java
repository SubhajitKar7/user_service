package com.user.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.user.demo.model.User;
import com.user.demo.service.UserService;

import jakarta.validation.constraints.Null;

@RestController
@RequestMapping("api/v1.0")
@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin")
public class UserController
{
	@Autowired
	private UserService uService;
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers()
	{
		List<User> userlist = uService.getAllUsers();
		
		if(userlist!=null)
		{
			return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
		}
		return new ResponseEntity<String>("userlist is empty", HttpStatus.NO_CONTENT);
	}

	@DeleteMapping ("/deleteUserById/{loginId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("loginId") String loginId)
	{
		System.out.println(loginId);
		Map<String,String> map=new HashMap<>();
		System.out.println(uService.deleteUser(loginId));
		if(uService.deleteUser(loginId))
		{
			map.put("msg","User got deleted");
			return new ResponseEntity<Map<String,String>>(map ,HttpStatus.OK);
		}
		map.put("msg","User is not  deleted");
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	}




}
