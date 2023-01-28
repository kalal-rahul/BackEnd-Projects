package com.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.entity.Purchase;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {

}
