package com.hako.eCommerce.business.rules;

import org.springframework.stereotype.Service;

import com.hako.eCommerce.core.results.exceptions.CartItemException;
import com.hako.eCommerce.core.results.exceptions.UserException;
import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.CartItemRepository;

@Service
public class CartItemBusinessRules {

  private CartItemRepository cartItemRepository;
  public CartItemBusinessRules(CartItemRepository cartItemRepository) {
    this.cartItemRepository = cartItemRepository;
  }

  public void checkCartItemExist(Long cartItemId) throws CartItemException {
    if (this.cartItemRepository.findById(cartItemId).orElse(null) == null) {
      throw new CartItemException("Cart item not found with id: " + cartItemId);
    }
  }

  public void updateIsCartItemUserIdEqualId(User user, Long userId) throws UserException {
    if (!user.getId().equals(userId)) {
      throw new UserException("User is not authorized to update this cart item");
    }
  }

  public void removeIsCartItemUserIdEqualId(User user, Long userId) throws UserException {
    if (!user.getId().equals(userId)) {
      throw new UserException("User is not authorized to remove this cart item");
    }
  }

}
