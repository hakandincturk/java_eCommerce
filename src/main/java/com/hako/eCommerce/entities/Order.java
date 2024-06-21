package com.hako.eCommerce.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "order_id")
  private String orderId;

  @ManyToOne
  private User user;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  @Column(name = "order_date")
  private LocalDateTime orderDate;

  @Column(name = "delivery_date")
  private LocalDateTime deliveryDate;

  @OneToOne()
  private Address shippingAdress;

  // @Embedded
  // private PaymentDetail paymentDetails = new PaymentDetail();

  @Column(name = "total_price")
  private double totalPrice;

  @Column(name = "total_discounted_price")
  private double totalDiscountedPrice; 

  @Column(name = "discount")
  private Integer discount;

  @Column(name = "order_status")
  private String orderStatus;

  @Column(name = "total_item")
  private Integer totalItem;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
