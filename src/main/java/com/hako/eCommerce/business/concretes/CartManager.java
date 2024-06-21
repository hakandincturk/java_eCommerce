package com.hako.eCommerce.business.concretes;

import org.springframework.stereotype.Service;

import com.hako.eCommerce.business.abstarcts.CartItemService;
import com.hako.eCommerce.business.abstarcts.CartService;
import com.hako.eCommerce.business.abstarcts.ProductService;
import com.hako.eCommerce.business.rules.CartBusinessRules;
import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.dto.CartItem.requests.AddItemRequest;
import com.hako.eCommerce.entities.Cart;
import com.hako.eCommerce.entities.CartItem;
import com.hako.eCommerce.entities.Product;
import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.CartRepository;

@Service
public class CartManager implements CartService {

  private CartRepository cartRepository;
  private CartItemService cartItemService;
  private ProductService productService;
  private CartBusinessRules cartBusinessRules;

  public CartManager(CartRepository cartRepository, CartItemService cartItemService, ProductService productService, CartBusinessRules cartBusinessRules) {
    this.cartRepository = cartRepository;
    this.cartItemService = cartItemService;
    this.productService = productService;
    this.cartBusinessRules = cartBusinessRules;
  }

  @Override
  public Cart createCart(User user) {
    Cart cart = new Cart();
    cart.setUser(user);
    Cart createdCart = this.cartRepository.save(cart);
    return createdCart;
  }

  @Override
  public String addCartItem(Long userId, AddItemRequest addItemRequest) throws ProductException { // add item to cart
    Cart cart = this.cartRepository.findByUserId(userId);
    Product product = this.productService.findProductById(addItemRequest.getProductId());

    CartItem isPresent = this.cartItemService.isCartItemExist(cart, product, addItemRequest.getSize(), userId);
    if(isPresent == null) {
      CartItem cartItem = new CartItem();
      cartItem.setCart(cart);
      cartItem.setProduct(product);
      cartItem.setQuantity(addItemRequest.getQuantity());
      cartItem.setUserId(userId);

      double price = addItemRequest.getQuantity() * product.getDiscountedPrice();
      cartItem.setPrice(price);
      cartItem.setSize(addItemRequest.getSize());

      CartItem createdCartItem = this.cartItemService.createCartItem(cartItem);
      cart.getCartItems().add(createdCartItem);
    }

    return "Item added to cart";
  }

  @Override
  public Cart findUserCart(Long userId) {
    Cart cart = this.cartRepository.findByUserId(userId);
    double totalPrice = 0;
    double totalDiscountedPrice = 0;
    int totalItem = 0;

    for (CartItem cartItem : cart.getCartItems()) {
      totalPrice += cartItem.getPrice();
      totalDiscountedPrice += cartItem.getProduct().getDiscountedPrice();
      totalItem += cartItem.getQuantity();
    }

    cart.setTotalItem(totalItem);
    cart.setTotalDiscountedPrice(totalDiscountedPrice);
    cart.setTotalPrice(totalPrice);
    cart.setDiscount((int)totalPrice - (int)totalDiscountedPrice);
    return cartRepository.save(cart);
  }
  
}
