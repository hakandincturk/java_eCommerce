package com.hako.eCommerce.business.abstarcts;

import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.dto.CartItem.requests.AddItemRequest;
import com.hako.eCommerce.entities.Cart;
import com.hako.eCommerce.entities.User;

public interface CartService {
  public Cart createCart(User user);
  public String addCartItem(Long userId, AddItemRequest addItemRequest) throws ProductException;
  public Cart findUserCart(Long userId);
} 
