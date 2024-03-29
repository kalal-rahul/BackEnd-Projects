package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.model.DeleteUserResponseBody;
import com.bookstore.model.LoginForm;
import com.bookstore.model.ResponseMessage;
import com.bookstore.model.SignUpForm;
import com.bookstore.service.UserService;


//CORS Error : This is to enable req from only this source to hit the end point
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
	

	@Autowired
	UserService userService;

	
	@PostMapping("/signup")
	public SignUpForm signUp(@RequestBody SignUpForm signUpData) {
		return userService.storeUserData(signUpData);
	}
	
	@PostMapping("/login")
	public ResponseMessage login(@RequestBody LoginForm loginData) {
		return userService.loginUser(loginData);
	}
	
	@DeleteMapping("/delete/{email}")
	public DeleteUserResponseBody deleUser(@PathVariable String email) {
		return userService.removeUserData(email);
	}
	
}