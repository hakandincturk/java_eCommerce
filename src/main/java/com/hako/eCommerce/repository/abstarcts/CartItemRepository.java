package com.hako.eCommerce.repository.abstarcts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hako.eCommerce.entities.Cart;
import com.hako.eCommerce.entities.CartItem;
import com.hako.eCommerce.entities.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
  @Query("SELECT c FROM CartItem c WHERE c.cart = :cart AND c.product = :product AND c.size = :size AND c.userId = :userId")
  public CartItem isCartItemExist(
    @Param("cart") Cart cart,
    @Param("product") Product product,
    @Param("size") String size,
    @Param("userId") Long userId
  );
}
