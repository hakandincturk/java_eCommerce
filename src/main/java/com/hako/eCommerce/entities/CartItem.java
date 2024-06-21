package com.hako.eCommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @JsonIgnore
  @ManyToOne
  private Cart cart; 

  @ManyToOne
  private Product product;

  @Column(name = "size")
  private String size;
  
  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "price")
  private double price;

  @Column(name = "discounted_price")
  private double discountedPrice;

  @Column(name = "user_id")
  private Long userId;

}
