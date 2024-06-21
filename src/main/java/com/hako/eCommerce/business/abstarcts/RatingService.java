package com.hako.eCommerce.business.abstarcts;

import java.util.List;

import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.dto.Rating.requests.RatingRequest;
import com.hako.eCommerce.entities.Rating;
import com.hako.eCommerce.entities.User;

public interface RatingService {
  public Rating createRating(RatingRequest ratingRequest, User user) throws ProductException; 
  public List<Rating> getProductsRatings(Long productId);
}
