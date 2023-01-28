package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Component
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class PurchaseBookResponseBody extends ResponseMessage {
	
	private int purchaseId;
	private String customerName;
	private double totalCost;
	private int quantity;
	private List<BookDetail> purchasedBooks =  new ArrayList<>();
	private List<BookTitle> unAvailableBooks = new ArrayList<>();

}
