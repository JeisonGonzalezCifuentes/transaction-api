package com.example.transaction.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {

  private final Integer id;
  private final Double amount;
  private final String merchant;
  private final String customerName;
  private final LocalDateTime transactionDate;

}
