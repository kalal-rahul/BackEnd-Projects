package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.model.BookTitle;
import com.bookstore.model.PurchaseBookResponseBody;
import com.bookstore.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	
	@PostMapping("/buyBooks")
	public PurchaseBookResponseBody purchaseBook(@RequestParam(name = "email") String email, @RequestBody List<BookTitle> selectedBooks) {
		return purchaseService.buyBooks(email, selectedBooks );
	}
	
	@GetMapping("/purchaseDetail/{purchaseId}")
	public PurchaseBookResponseBody providePurchaseDetails( @PathVariable Integer purchaseId) {
		return purchaseService.obtainPurchaseDetails(purchaseId);
	}

}
