package com.bookstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bookstore.model.LoginForm;
import com.bookstore.model.ResponseMessage;
import com.bookstore.entity.User;
import com.bookstore.model.DeleteUserResponseBody;
import com.bookstore.model.SignUpForm;
import com.bookstore.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	DeleteUserResponseBody deleteUserResponseBody;

	User user;

	public SignUpForm storeUserData(SignUpForm signUpData) {
		
		SignUpForm signedUpUserData = new SignUpForm();
		User adedUser;
		user = new User();
		
		// Try to add Add new Record - User
		try {
			user.setUserName(signUpData.getUserName());
			user.setEmail(signUpData.getEmail());
			user.setPassword(signUpData.getPassword());
			user.setPhone(signUpData.getPhone());
			adedUser = userRepo.save(user);
		}
		catch (DataIntegrityViolationException exception) {
			
			// Check Duplicate entry
			signedUpUserData.setMessage(exception.getMostSpecificCause().toString());
			return signedUpUserData;
		}
		
		
//		 If Duplicate entry not found - Get Added Record
		signedUpUserData = new SignUpForm(adedUser.getUserName(), adedUser.getPhone(),
				                          adedUser.getPassword(), adedUser.getEmail());
		signedUpUserData.setMessage("Signed Up Successfully!");
		return signedUpUserData;

	}

	public DeleteUserResponseBody removeUserData(String email) {
		User requiredUserData = userRepo.findByEmail(email);
		userRepo.delete(requiredUserData);

		// Generate Delete Message
		deleteUserResponseBody.setMessage("Deleted User Details");
		deleteUserResponseBody.setEmail(requiredUserData.getEmail());
		deleteUserResponseBody.setId(requiredUserData.getId());
		deleteUserResponseBody.setPassword(requiredUserData.getPassword());
		deleteUserResponseBody.setPhone(requiredUserData.getPhone());
		deleteUserResponseBody.setUserName(requiredUserData.getUserName());

		return deleteUserResponseBody;
	}

	public ResponseMessage loginUser(LoginForm loginData) {
		
		String recordPassword;
		user = new User();
		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			user = userRepo.findByEmail(loginData.getEmail());
			recordPassword = user.getPassword();
		}
		catch(NullPointerException exception) {
			
			responseMessage.setMessage("Invalid Email - If not signed Up Please sign up");
			return responseMessage;
		}
		
		if (recordPassword.equalsIgnoreCase(loginData.getPassword())) {
			responseMessage.setMessage("Login Successful!");
			return responseMessage;
		}
		else {
			responseMessage.setMessage(" Invalid Password :( ");
			return responseMessage;
		}
		
	}

}
