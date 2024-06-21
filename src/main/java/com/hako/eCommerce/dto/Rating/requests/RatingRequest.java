package com.hako.eCommerce.dto.Rating.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {
  private Long productId;
  private double rating;
  private String comment;
}
