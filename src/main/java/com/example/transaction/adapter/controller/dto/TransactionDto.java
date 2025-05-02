package com.example.transaction.adapter.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class TransactionDto {

  Integer id;
  String accountNumber;
  BigDecimal amount;
  String merchant;
  LocalDateTime transactionDate;

}
