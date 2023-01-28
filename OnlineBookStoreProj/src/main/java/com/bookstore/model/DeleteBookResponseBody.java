package com.bookstore.model;

import org.springframework.stereotype.Component;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Component
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class DeleteBookResponseBody extends ResponseMessage {
	
	private int id;
	
	private String bookName;
	
	private double cost;

}
