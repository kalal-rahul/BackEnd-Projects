package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.model.BookDetail;
import com.bookstore.model.DeleteBookResponseBody;
import com.bookstore.repo.BookRepo;

@Service
public class BookService {
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	DeleteBookResponseBody deleteBookResponseBody;

	public Book storeNewBookData(Book newBookDetails) {
		return bookRepo.save(newBookDetails);
	}
	
	public DeleteBookResponseBody deleteBookWithName(String bookName) {
		
		 deleteBookResponseBody = new DeleteBookResponseBody();
		 Book requiredBook = bookRepo.findByBookName(bookName);
		 
		 if (requiredBook != null) {
			 
			 //Delete Book and generate response
			 bookRepo.delete(requiredBook);
			 deleteBookResponseBody.setMessage("Deleted Book Details");
			 deleteBookResponseBody.setBookName(requiredBook.getBookName());
			 deleteBookResponseBody.setCost(requiredBook.getCost());
			 deleteBookResponseBody.setId(requiredBook.getId());
			 
			 return deleteBookResponseBody;
		 }
		 else {
			 
			 //When no book found to delete
			 deleteBookResponseBody.setMessage("No book found with Book Name:" + bookName);
			 return deleteBookResponseBody;
		}	 
	}
	
	
	public BookDetail updateCostOfBook(String bookName, double newPrice) {
		Book requiredBook = bookRepo.findByBookName(bookName);
		requiredBook.setCost(newPrice);
		
		Book updatedBook =  bookRepo.save(requiredBook);
		BookDetail updatBookDetail = new BookDetail(updatedBook.getBookName(), updatedBook.getCost());
		return updatBookDetail;
	}

	
	//Add List of Books
	public List<BookDetail> storeBooks(List<Book> books) {
		List<BookDetail> addedBooks = new ArrayList<>();
		
		for(Book book : books) {
			try {
				Book addedBook = storeNewBookData(book);
				addedBooks.add(new BookDetail(addedBook.getBookName(),addedBook.getCost()));
			}
			catch (Exception e) {
				// Skip To Next entry when found duplicate
			}
		}
		
		return addedBooks;
	}
	
}
