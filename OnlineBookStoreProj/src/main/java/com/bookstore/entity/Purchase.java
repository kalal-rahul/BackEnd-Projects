package com.bookstore.entity;


import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Entity 
@ToString
public class Purchase {
	
	@Id
	@GeneratedValue
	private int id;
	
	private double totalCost; 
	
	private int quantity;
	
	//Foreign Key - Store UserId - Mention name of the class/Entity
	@OneToOne
	@JoinColumn(name = "fk_userId", referencedColumnName = "id")
	private User user; //Store Customer Details
	
	/*
	 * Note :
	 * 1. The column name which is to be created is -----> name (Created in current table)
	 * 		- This stores the user Id referenced from Another Table
	 * 
	 * 2. The column that is to be referenced is --------> referencedColumnName (Another Table)
	 * 3. The table that is to be looked for/ referenced is  "private User user"
	 * 		- This variable/instance name is the value for attribute "mappedBy"
	 * 		- "mappedBy = user" this is to be mentioned in other entity class
	 */

	@OneToMany(mappedBy = "purchaseId")
	private List<OrderDetail> mapPurchaseID;
}
