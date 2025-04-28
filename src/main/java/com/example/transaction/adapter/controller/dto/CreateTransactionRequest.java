package com.example.transaction.adapter.controller.dto;

import java.time.LocalDateTime;

public record CreateTransactionRequest(
    Integer amount,
    String merchant,
    String customerName,
    LocalDateTime transactionDate) {
}
