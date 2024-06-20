package com.hako.eCommerce.entities;

import java.time.LocalDate;

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
@Table(name = "payment_informations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "card_holder_name")
  private String cardHolderName;

  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "expiration_date")
  private LocalDate expirationDate;

  @Column(name = "cvv")
  private String cvv;
}
