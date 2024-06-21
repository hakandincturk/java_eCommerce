package com.hako.eCommerce.repository.abstarcts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hako.eCommerce.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
  
  // @Query("SELECT r FROM Rating r WHERE r.product.id = :productId")
  // public List<Rating> getAllProductRatings(@Param("productId") Long productId);
  public List<Rating> getAllProductRatingsByProductId(Long productId);
}
