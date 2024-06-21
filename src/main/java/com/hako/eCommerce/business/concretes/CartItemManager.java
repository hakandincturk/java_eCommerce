package com.hako.eCommerce.business.concretes;

import org.springframework.stereotype.Service;

import com.hako.eCommerce.business.abstarcts.CartItemService;
import com.hako.eCommerce.business.abstarcts.UserService;
import com.hako.eCommerce.business.rules.CartItemBusinessRules;
import com.hako.eCommerce.core.results.exceptions.CartItemException;
import com.hako.eCommerce.core.results.exceptions.UserException;
import com.hako.eCommerce.entities.Cart;
import com.hako.eCommerce.entities.CartItem;
import com.hako.eCommerce.entities.Product;
import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.CartItemRepository;
import com.hako.eCommerce.repository.abstarcts.CartRepository;

@Service
public class CartItemManager implements CartItemService {

  private CartItemRepository cartItemRepository;
  private UserService userService;
  private CartRepository cartRepository;
  private CartItemBusinessRules cartItemBusinessRules;

  public CartItemManager(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository, CartItemBusinessRules cartItemBusinessRules) {
    this.cartItemRepository = cartItemRepository;
    this.userService = userService;
    this.cartRepository = cartRepository;
    this.cartItemBusinessRules = cartItemBusinessRules;
  }

  @Override
  public CartItem createCartItem(CartItem cartItem) {
    cartItem.setQuantity(1);
    cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
    cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

    CartItem createdCartItem = this.cartItemRepository.save(cartItem);
    return createdCartItem;
  }

  @Override
  public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
    CartItem item = findCartItemById(id);
    User user = this.userService.findUserById(item.getUserId());

    this.cartItemBusinessRules.updateIsCartItemUserIdEqualId(user, userId);

    item.setQuantity(cartItem.getQuantity());
    item.setPrice(item.getProduct().getPrice() * item.getQuantity());
    item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());

    CartItem updatedCartItem = this.cartItemRepository.save(item);
    return updatedCartItem;
  }

  @Override
  public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
    CartItem cartItem = this.cartItemRepository.isCartItemExist(cart, product, size, userId);
    return cartItem;
  }

  @Override
  public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
    CartItem cartItem = findCartItemById(cartItemId);
    User user = this.userService.findUserById(cartItem.getUserId());
    User reqUser = this.userService.findUserById(userId);

    this.cartItemBusinessRules.removeIsCartItemUserIdEqualId(user, reqUser.getId());

    cartItemRepository.deleteById(cartItemId);
  }

  @Override
  public CartItem findCartItemById(Long cartItemId) throws CartItemException {
    this.cartItemBusinessRules.checkCartItemExist(cartItemId);
    CartItem cartItem = this.cartItemRepository.findById(cartItemId).get();
    return cartItem;
  }
  
}
