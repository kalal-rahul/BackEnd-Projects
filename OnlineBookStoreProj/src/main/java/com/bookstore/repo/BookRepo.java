package com.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {

	Book findByBookName(String bookName);

}
