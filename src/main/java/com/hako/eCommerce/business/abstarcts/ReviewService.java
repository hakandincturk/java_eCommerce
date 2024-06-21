package com.hako.eCommerce.business.abstarcts;

import java.util.List;

import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.dto.Review.requests.CreateReviewRequest;
import com.hako.eCommerce.entities.Review;
import com.hako.eCommerce.entities.User;

public interface ReviewService {
  public Review createReview(CreateReviewRequest reviewRequest, User user) throws ProductException;
  public List<Review> getProductReviews(Long productId) throws ProductException;
}
