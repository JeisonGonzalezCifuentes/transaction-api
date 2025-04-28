package com.example.transaction.domain.service;

import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.repository.TransactionRepository;
import com.example.transaction.domain.service.in.CreateTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TransactionService implements CreateTransaction {

  @Value("${transaction.limit}")
  private int transactionLimit;

  private final TransactionRepository transactionRepository;

  @Override
  public Transaction createTransaction(Transaction transaction) {
    verifyTransactionsLimit(transaction.getCustomerName());
    verifyAmount(transaction.getAmount());
    verifyDate(transaction.getTransactionDate());

    return transactionRepository.save(transaction);
  }

  private void verifyDate(LocalDateTime transactionDate) {
    LocalDateTime now = LocalDateTime.now();
    if (transactionDate.isAfter(now)) {
      throw new IllegalArgumentException("Transaction date cannot be after now");
    }
  }

  private void verifyAmount(Double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
  }

  private void verifyTransactionsLimit(String customerName) {
    int byCustomerName = transactionRepository.findByCustomerName(customerName);
    if (byCustomerName >= transactionLimit) {
      throw new IllegalArgumentException("You have reached the maximum number of transactions");
    }
  }
}