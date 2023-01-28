package com.bookstore.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Entity 
public class OrderDetail {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "fk_purchaseId", referencedColumnName = "id")
	private Purchase purchaseId ;
	
	@ManyToOne
	@JoinColumn(name = "fk_bookId", referencedColumnName = "id")
    private Book bookId;
	
	
}
