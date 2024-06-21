package com.hako.eCommerce.repository.abstarcts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hako.eCommerce.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
  // @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
  // public Cart findByUserId(@Param("userId") Long userId);
  public Cart findByUserId(Long userId);

}
