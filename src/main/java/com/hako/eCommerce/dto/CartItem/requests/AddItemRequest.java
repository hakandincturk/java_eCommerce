package com.hako.eCommerce.dto.CartItem.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {
  private long productId;
  private String size;
  private int quantity;
  private double price;
}
