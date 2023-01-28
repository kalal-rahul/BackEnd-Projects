package com.bookstore.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
public class SignUpForm extends ResponseMessage {

	private String userName;
	
	private String phone;
	
	private String password;
	
	private String email;

}
