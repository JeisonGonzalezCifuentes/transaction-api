package com.example.transaction.domain.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class Transaction {

  Integer id;
  BigDecimal amount;
  String merchant;
  String customerName;
  LocalDateTime transactionDate;

}
