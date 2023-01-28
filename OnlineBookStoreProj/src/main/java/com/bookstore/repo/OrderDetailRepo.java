package com.bookstore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.Purchase;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
	
	List<OrderDetail> findByPurchaseId(Purchase purchase);

}
