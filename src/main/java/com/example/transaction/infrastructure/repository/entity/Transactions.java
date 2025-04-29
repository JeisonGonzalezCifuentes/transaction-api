package com.example.transaction.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "transactions", indexes = {
    @Index(name = "idx_customer_name", columnList = "customerName")
})
public class Transactions {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(precision = 15, scale = 2)
  private BigDecimal amount;
  private String merchant;
  private String customerName;
  private String date;

}
