package com.hako.eCommerce.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hako.eCommerce.business.abstarcts.ProductService;
import com.hako.eCommerce.business.abstarcts.RatingService;
import com.hako.eCommerce.business.rules.ProductBusinessRules;
import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.dto.Rating.requests.RatingRequest;
import com.hako.eCommerce.entities.Product;
import com.hako.eCommerce.entities.Rating;
import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.RatingRepository;

@Service
public class RatingManager implements RatingService {

  private RatingRepository ratingRepository;
  private ProductService productService;
  private ProductBusinessRules productBusinessRules;

  public RatingManager(RatingRepository ratingRepository, ProductService productService, ProductBusinessRules productBusinessRules) {
    this.ratingRepository = ratingRepository;
    this.productService = productService;
    this.productBusinessRules = productBusinessRules;
  }

  @Override
  public Rating createRating(RatingRequest ratingRequest, User user) throws ProductException {
    this.productBusinessRules.checkProductExistById(ratingRequest.getProductId());

    Product product = this.productService.findProductById(ratingRequest.getProductId());
    Rating rating = new Rating();
    rating.setProduct(product);
    rating.setUser(user);
    rating.setRating(ratingRequest.getRating());
    rating.setComment(ratingRequest.getComment());
    rating.setCreatedAt(LocalDateTime.now());
    return this.ratingRepository.save(rating);
  }

  @Override
  public List<Rating> getProductsRatings(Long productId) {
    return this.ratingRepository.getAllProductRatingsByProductId(productId);
  }
  
}
