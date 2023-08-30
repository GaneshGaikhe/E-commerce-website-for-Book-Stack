package com.bookstack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstack.dao.BookDao;
import com.bookstack.dao.ReviewDao;
import com.bookstack.dao.UserDao;
import com.bookstack.dto.AddReviewRequest;
import com.bookstack.model.*;
import com.bookstack.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/user/")            
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private BookDao bookDao;
	ObjectMapper objectMapper = new ObjectMapper();// used for jason coversion

	@PostMapping("addReview")
	public ResponseEntity<?> addReview(@RequestBody AddReviewRequest ReviewRequest) {
		System.out.println("recieved request for Adding review");
		System.out.println(ReviewRequest);

		Review review = AddReviewRequest.toEntity(ReviewRequest);

		Optional<User> optionalUser = userDao.findById(ReviewRequest.getUser_id());
		User user = null;
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		Optional<Book> optional = bookDao.findById(ReviewRequest.getBook_id());
		Book book = null;

		if (optional.isPresent()) {
			book = optional.get();
		}

		
		review.setBook(book);
		
		review.setUser(user);

		review.setBook(book);
		
		reviewService.addReview(review);

		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(book);

	}
	
	
	@DeleteMapping("deleteReview/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
	    System.out.println("Received request to delete review with ID: " + reviewId);
	    
	    Optional<Review> optionalReview = reviewDao.findById(reviewId);
	    System.out.println(optionalReview );
	    if (!optionalReview.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    reviewService.deleteReview(optionalReview.get());
	    
	    System.out.println("Response sent!");
	    return ResponseEntity.ok().build();
	}

	
	@GetMapping("/reviews/{book_id}")
	public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable int book_id) {
	List<Review> reviews = reviewService.getReviewsByBookId(book_id);
	System.out.println(reviews);
	return ResponseEntity.ok(reviews);
	}
}
