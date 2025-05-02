package com.example.transaction.adapter.controller.dto;

import com.example.transaction.domain.model.Transaction;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class CreateTransactionRequest {

  BigDecimal amount;
  String merchant;
  String accountNumber;
  LocalDateTime transactionDate;

  public Transaction toDomain() {
    return Transaction.builder()
        .amount(this.getAmount())
        .merchant(this.getMerchant())
        .accountNumber(this.getAccountNumber())
        .transactionDate(this.getTransactionDate())
        .build();
  }

}
