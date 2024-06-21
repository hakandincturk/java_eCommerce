package com.hako.eCommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column (name = "payment_method")
  private String paymentMethod;

  @Column(name = "status")
  private String status;
  
  @Column(name = "payment_id")
  private String paymentId;

  @Column(name = "razorpay_payment_link_id")
  private String razorpayPaymentLinkId;

  @Column(name = "razorpay_payment_link_reference_id")
  private String razorpayPaymentLinkReferenceId;

  @Column(name = "razorpay_payment_link_status")
  private String razorpayPaymentLinkStatus;

  @Column(name = "razorpay_payment_id")
  private String razorpayPaymentId;


}
