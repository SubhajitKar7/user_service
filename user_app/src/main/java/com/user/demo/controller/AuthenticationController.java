package com.user.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Soundbank;

import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.model.User;
import com.user.demo.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;

@RestController
@RequestMapping("auth/v1.0")
@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin")
public class AuthenticationController {

	
	private Map<String, String> mapObj = new HashMap<String, String>();
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("moviebooking/getUserById/{loginId}")
	public ResponseEntity<?> getUserById(@PathVariable("loginId") String loginId)
	{
		User user=userService.getUserById(loginId);
		if(user!=null)
		{
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<String>("null", HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/moviebooking/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		if(userService.addUser(user)!=null)
	{
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
		return new ResponseEntity<String>("user registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//method to generate token inside login API
	public String generateToken(String loginId, String password,String role) throws ServletException, Exception
	{
		System.out.println("loginId for login"+loginId);
		System.out.println("password for login"+password);
		System.out.println();
		String jwtToken;
		if(loginId ==null || password ==null)
		{
			throw new ServletException("Please enter valid credentials");
		}
		
		System.out.println(userService.loginUser(loginId, password));
		boolean flag = userService.loginUser(loginId, password);
		
		if(!flag)
		{
			throw new ServletException("Invalid credentials");
		}
		
		else
		{
			Map<String, Object> claims = new HashMap<>();
			/*jwtToken= Jwts.builder().setClaims(claims).setSubject(loginId).setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis()+300000))
			.signWith(SignatureAlgorithm.HS512, "secret key").compact();*/
			
				
			jwtToken= Jwts.builder().setId(role).setSubject(loginId).setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+300000))
					.signWith(SignatureAlgorithm.HS512, "secret key").compact();
			
			
			System.out.println("role= "+role);
			
					
		}
		System.out.println(jwtToken);
		return jwtToken;
		
	}
	
	@PostMapping("/moviebooking/login")
	public ResponseEntity<?> logiUser(@RequestBody User user)
	{
		String role="";
	
		try
		{
			
		if(user.getRole().equals("admin"))
			role="admin";
		if(user.getRole().equals("customer"))
			role="user";
			
			
			String jwtToken = generateToken(user.getLoginId(), user.getPassword(),role);
			mapObj.put("Message", "User successfully logged in");
			mapObj.put("Token:", jwtToken);
		
			
		}
		catch(Exception e)
		{
			mapObj.put("Message", "User not logged in");
			mapObj.put("Token:", null);
			return new ResponseEntity<>(mapObj, HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(mapObj, HttpStatus.OK);
	}
	
	
	@GetMapping("forgot/{loginId}")
	public ResponseEntity<?> forgetPassword(@PathVariable("loginId") String loginId) // check if user exists or not
	{
		System.out.println(loginId);
	User user=userService.getUserById(loginId);
		System.out.println(user);
		if(user!=null)
		{
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<String>("No user found", HttpStatus.OK);
		}
		
		
	}
	
	@PutMapping("forgot/updatepassword/{loginId}")
	public ResponseEntity<?> question(@RequestBody User user,@PathVariable("loginId") String loginId)
	{
		User upUser=userService.updatePassword(user,loginId);
		
		if(upUser!=null){
			return new ResponseEntity<User>(upUser, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Password Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
					
		}
	}
	

	@GetMapping("forgot/getUser/{loginId}")
	public ResponseEntity<?> getUser(@PathVariable("loginId") String loginId)
	{
		User user=userService.getUserById(loginId);
		if(user!=null)
		{
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<String>("null", HttpStatus.NO_CONTENT);
	}
	
}

