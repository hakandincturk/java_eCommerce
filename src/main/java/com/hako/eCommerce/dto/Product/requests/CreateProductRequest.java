package com.hako.eCommerce.dto.Product.requests;

import java.util.HashSet;
import java.util.Set;

import com.hako.eCommerce.entities.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
  private String title;
  private String description;
  private double price;

  private int discountedPrice;
  private int discountPercent;
  private int quantity;
  private String brand;
  private String color;
  private Set<Size> sizes = new HashSet<>();
  private String imageUrl;

  private String topLevelCategory;
  private String secondLevelCategory;
  private String thirdLevelCategory;
}
