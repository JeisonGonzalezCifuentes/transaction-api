package com.example.transaction.domain.service;

import com.example.transaction.domain.exception.InvalidAmountException;
import com.example.transaction.domain.exception.InvalidTransactionDateException;
import com.example.transaction.domain.exception.TransactionLimitExceededException;
import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.repository.TransactionRepository;
import com.example.transaction.domain.service.in.CreateTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
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
      throw new InvalidTransactionDateException("Transaction date cannot be after now");
    }
  }

  private void verifyAmount(BigDecimal amount) {
    if (BigDecimal.ZERO.compareTo(amount) > 0) {
      throw new InvalidAmountException("Amount cannot be negative");
    }
  }

  private void verifyTransactionsLimit(String customerName) {
    int byCustomerName = transactionRepository.findByCustomerName(customerName);
    if (byCustomerName >= transactionLimit) {
      throw new TransactionLimitExceededException("You have reached the maximum number of transactions");
    }
  }
}