package com.example.transaction.domain.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class Transaction {

  Integer id;
  String accountNumber;
  BigDecimal amount;
  String merchant;
  LocalDateTime transactionDate;

}
