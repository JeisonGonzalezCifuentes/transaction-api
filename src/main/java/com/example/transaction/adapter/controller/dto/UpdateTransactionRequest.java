package com.example.transaction.adapter.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class UpdateTransactionRequest {

  BigDecimal amount;
  String merchant;

}
