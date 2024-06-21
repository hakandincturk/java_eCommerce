package com.hako.eCommerce.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hako.eCommerce.business.abstarcts.CartItemService;
import com.hako.eCommerce.business.abstarcts.CartService;
import com.hako.eCommerce.business.abstarcts.OrderService;
import com.hako.eCommerce.business.abstarcts.ProductService;

import com.hako.eCommerce.core.results.exceptions.OrderException;

import com.hako.eCommerce.entities.Address;
import com.hako.eCommerce.entities.Order;
import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.CartRepository;
import com.hako.eCommerce.repository.abstarcts.OrderRepository;

@Service
public class OrderManager implements OrderService {

  private CartRepository cartRepository;
  private CartItemService cartItemService;
  private ProductService productService;
  private OrderRepository orderRepository;

  public OrderManager(CartRepository cartRepository, CartItemService cartItemService, ProductService productService, OrderRepository orderRepository) {
    this.cartRepository = cartRepository;
    this.cartItemService = cartItemService;
    this.productService = productService;
    this.orderRepository = orderRepository;
  }

  @Override
  public Order creatOrder(User user, Address shippingAddress) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'creatOrder'");
  }

  @Override
  public Order findOrderById(Long id) throws OrderException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findOrderById'");
  }

  @Override
  public List<Order> usersOrderHistory(Long userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'usersOrderHistory'");
  }

  @Override
  public Order placedOrder(Long orderId) throws OrderException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'placedOrder'");
  }

  @Override
  public Order confirmedOrder(Long orderId) throws OrderException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'confirmedOrder'");
  }

  @Override
  public Order shippedOrder(Long orderId) throws OrderException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'shippedOrder'");
  }

  @Override
  public Order deliveredOrder(Long orderId) throws OrderException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deliveredOrder'");
  }

  @Override
  public Order canceledOrder(Long orderId) throws OrderException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'canceledOrder'");
  }

  @Override
  public List<Order> getAllOrders() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
  }

  @Override
  public void deleteOrder(Long orderId) throws OrderException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
  }
  
}
