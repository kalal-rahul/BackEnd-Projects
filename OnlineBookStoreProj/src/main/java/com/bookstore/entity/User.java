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
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email"}) }) -- Table level condition
public class User{
	

	@Id
	@GeneratedValue
	private int id;
	
	private String userName;
	
	@Column(length = 10)
	private String phone;
	
	@Column(length = 10)
	private String password;
	
	@Column(length = 25, unique = true)
	private String email;
	
	@OneToMany(mappedBy = "user") // The instance that is defined in Mapped Entity class
	List<Purchase> purchase;
}


