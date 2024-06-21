package com.hako.eCommerce.business.abstarcts;

import com.hako.eCommerce.core.results.exceptions.CartItemException;
import com.hako.eCommerce.core.results.exceptions.UserException;
import com.hako.eCommerce.entities.Cart;
import com.hako.eCommerce.entities.CartItem;
import com.hako.eCommerce.entities.Product;

public interface CartItemService {
  public CartItem createCartItem(CartItem cartItem);
  public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
  public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
  public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
  public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
