package com.hako.eCommerce.business.abstarcts;

import java.util.List;

import com.hako.eCommerce.core.results.exceptions.OrderException;
import com.hako.eCommerce.entities.Address;
import com.hako.eCommerce.entities.Order;
import com.hako.eCommerce.entities.User;

public interface OrderService {
  public Order creatOrder(User user, Address shippingAddress);
  public Order findOrderById(Long id) throws OrderException;
  public List<Order> usersOrderHistory(Long userId);
  public Order placedOrder(Long orderId) throws OrderException;
  public Order confirmedOrder(Long orderId) throws OrderException;
  public Order shippedOrder(Long orderId) throws OrderException;
  public Order deliveredOrder(Long orderId) throws OrderException;
  public Order canceledOrder(Long orderId) throws OrderException;
  public List<Order> getAllOrders();
  public void deleteOrder(Long orderId) throws OrderException;
}
