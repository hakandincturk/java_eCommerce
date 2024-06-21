package com.hako.eCommerce.repository.abstarcts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hako.eCommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
  
}
