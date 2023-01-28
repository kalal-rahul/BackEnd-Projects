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
public class LoginForm extends ResponseMessage {
	
	private String email;
	private String password;

}
