package com.bookstore.model;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Component
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class DeleteUserResponseBody extends ResponseMessage {
	
	private int id;
	private String userName;
	private String phone;
	private String password;
	private String email;

}
