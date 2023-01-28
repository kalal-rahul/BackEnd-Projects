package com.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);

}
