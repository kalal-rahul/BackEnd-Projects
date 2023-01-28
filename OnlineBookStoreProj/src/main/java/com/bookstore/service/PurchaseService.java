package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.Purchase;
import com.bookstore.entity.User;
import com.bookstore.model.BookDetail;
import com.bookstore.model.BookTitle;
import com.bookstore.model.PurchaseBookResponseBody;
import com.bookstore.repo.BookRepo;
import com.bookstore.repo.OrderDetailRepo;
import com.bookstore.repo.PurchaseRepo;
import com.bookstore.repo.UserRepo;

@Service
public class PurchaseService {

	/*
	 * Single instance of all of this would be @Autowired We always need a single
	 * instance of Repository to manipulate Data
	 */
	@Autowired
	BookRepo bookRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	PurchaseRepo purchaseRepo;

	@Autowired
	OrderDetailRepo orderDetailRepo;

	/*
	 * It is always a good idea to to not use entity with @Autowired As we need new
	 * instance for new record
	 */
	Purchase currentPurchase;

	OrderDetail orderDetail;

	PurchaseBookResponseBody purchaseDetails;

	public PurchaseBookResponseBody buyBooks(String email, List<BookTitle> selectedBooks) {

		/*
		 * We want a new instance of record every time the end point is Hit
		 * 
		 * @Autowired works on singleton principle where it gives the same instance
		 * every time
		 * 
		 * If new instance is not created the same record will get updated every time
		 */
		currentPurchase = new Purchase();
		purchaseDetails = new PurchaseBookResponseBody();

		User userRecord = userRepo.findByEmail(email);

		double currentTotalCost = getTotalCost(selectedBooks, purchaseDetails);

		int currentQuantity = selectedBooks.size() - purchaseDetails.getUnAvailableBooks().size();

		// Add Record in PURCHASE entity
		currentPurchase.setUser(userRecord);
		currentPurchase.setQuantity(currentQuantity);
		currentPurchase.setTotalCost(currentTotalCost);
		Purchase addedPurchaseRecord = purchaseRepo.save(currentPurchase);

		// Add Record in ORDER_DETAIL Entity - Get List of Records added
		List<OrderDetail> currentOrderDetails = storeOrderDetails(addedPurchaseRecord, selectedBooks);

		// Generate Purchase Details from Added Record - To send as Response
		purchaseDetails = generatePurchaseDetails(addedPurchaseRecord, purchaseDetails, currentOrderDetails);

		return purchaseDetails;
	}

	private List<OrderDetail> storeOrderDetails(Purchase currentPurchaseRecord, List<BookTitle> selectedBooks) {

		List<OrderDetail> currentOrderDetails = new ArrayList<>();
		OrderDetail orderDetail;

		for (BookTitle bookName : selectedBooks) {

			// New Instance for new Record - If Record not found Skip entry
			orderDetail = new OrderDetail();
			Book bookRecord = bookRepo.findByBookName(bookName.getBookName());

			if (bookRecord != null) {

				// Add Record
				orderDetail.setPurchaseId(currentPurchaseRecord);
				orderDetail.setBookId(bookRecord);
				currentOrderDetails.add(orderDetailRepo.save(orderDetail));
			}

			// Else
			// Skip the book when No book found
		}

		return currentOrderDetails;
	}

	private double getTotalCost(List<BookTitle> selectedBooks, PurchaseBookResponseBody purchaseDetails) {

		double totalCost = 0;

		for (BookTitle book : selectedBooks) {

			try {

				double costOfBook = bookRepo.findByBookName(book.getBookName()).getCost();
				totalCost += costOfBook;
			} catch (NullPointerException e) {

				// When No book is found - Add to unavailable Books List
				purchaseDetails.getUnAvailableBooks().add(book);
			}
		}

		return totalCost;
	}

	private PurchaseBookResponseBody generatePurchaseDetails(Purchase currentPurchase,
			                                                 PurchaseBookResponseBody purchaseDetails, 
			                                                 List<OrderDetail> currentOrderDetails) {

		purchaseDetails.setMessage("Purchase Details - Thank You for purchasing!");
		purchaseDetails.setPurchaseId(currentPurchase.getId());
		purchaseDetails.setCustomerName(currentPurchase.getUser().getUserName());
		purchaseDetails.setQuantity(currentPurchase.getQuantity());
		purchaseDetails.setTotalCost(currentPurchase.getTotalCost());

		for (OrderDetail orderDetail : currentOrderDetails) {
			purchaseDetails.getPurchasedBooks()
					       .add(new BookDetail(orderDetail.getBookId().getBookName(), 
					    		               orderDetail.getBookId().getCost()));
		}

		return purchaseDetails;
	}

	public PurchaseBookResponseBody obtainPurchaseDetails(int purchaseId) {

		purchaseDetails = new PurchaseBookResponseBody();

		// Record Instance - Get record with purchase ID
		currentPurchase = new Purchase();
		currentPurchase = purchaseRepo.findById(purchaseId).orElse(null);

		if (currentPurchase != null) {
			List<OrderDetail> currentOrderDetails = orderDetailRepo.findByPurchaseId(currentPurchase);
			purchaseDetails = generatePurchaseDetails(currentPurchase, purchaseDetails, currentOrderDetails);
		} else {

			// When No record found
			purchaseDetails.setMessage("No record found with PURCHASE - ID: " + purchaseId);
			return purchaseDetails;
		}

		return purchaseDetails;
	}

}
