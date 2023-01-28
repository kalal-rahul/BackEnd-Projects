package com.bookstore.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Entity 
public class Book {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true, length = 50)
	private String bookName;
	
	private double cost;
	
	@OneToMany(mappedBy = "bookId")
	private List<OrderDetail> mapBookId;
	
}
