package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Book;
import com.bookstore.model.BookDetail;
import com.bookstore.model.DeleteBookResponseBody;
import com.bookstore.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/addbook")
	public Book addBook(@RequestBody Book newBookDetails) {
		return bookService.storeNewBookData(newBookDetails);
	}
	
	
	@DeleteMapping("deletebook/{bookName}")
	public DeleteBookResponseBody removeBook(@PathVariable String bookName) {
		return bookService.deleteBookWithName(bookName);
	}
	
	
	@PutMapping("/updateCost")
	public BookDetail updateCostOfBook(@RequestParam(name = "bookName") String bookName, @RequestParam(name = "newPrice") double newPrice) {
		return bookService.updateCostOfBook(bookName, newPrice);
	}

	
    @PostMapping("/addListOfBooks")
	public List<BookDetail> addListOfBooks(@RequestBody List<Book> books){
		return bookService.storeBooks(books);
	}
	
}
