package com.hako.eCommerce.repository.abstarcts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hako.eCommerce.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
  // @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
  // public List<Review> getAllProductReviewsByProductId(@Param("productId") Long productId);
  public List<Review> getAllProductReviewsByProductId(Long productId);
}
