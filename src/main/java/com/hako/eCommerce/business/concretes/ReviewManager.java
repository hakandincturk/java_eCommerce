package com.hako.eCommerce.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import com.hako.eCommerce.business.abstarcts.ProductService;
import com.hako.eCommerce.business.abstarcts.ReviewService;
import com.hako.eCommerce.business.rules.ProductBusinessRules;
import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.dto.Review.requests.CreateReviewRequest;
import com.hako.eCommerce.entities.Product;
import com.hako.eCommerce.entities.Review;
import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.ReviewRepository;

public class ReviewManager implements ReviewService {

  private ReviewRepository reviewRepository;
  private ProductService productService;
  private ProductBusinessRules productBusinessRules;

  public ReviewManager(ReviewRepository reviewRepository, ProductService productService, ProductBusinessRules productBusinessRules) {
    this.reviewRepository = reviewRepository;
    this.productService = productService;
    this.productBusinessRules = productBusinessRules;
  }

  @Override
  public Review createReview(CreateReviewRequest reviewRequest, User user) throws ProductException {
    this.productBusinessRules.checkProductExistById(reviewRequest.getProductId());

    Product product = this.productService.findProductById(reviewRequest.getProductId()); 
    Review review = new Review();
    review.setProduct(product);
    review.setUser(user);
    review.setReview(reviewRequest.getReview());
    review.setCreatedAt(LocalDateTime.now());
    return this.reviewRepository.save(review);
  }

  @Override
  public List<Review> getProductReviews(Long productId) throws ProductException {
    this.productBusinessRules.checkProductExistById(productId);

    return this.reviewRepository.getAllProductReviewsByProductId(productId);
    
  }
  
}
