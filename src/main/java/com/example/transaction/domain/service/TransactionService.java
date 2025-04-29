package com.example.transaction.domain.service;

import com.example.transaction.domain.exception.InvalidAmountException;
import com.example.transaction.domain.exception.InvalidTransactionDateException;
import com.example.transaction.domain.exception.TransactionLimitExceededException;
import com.example.transaction.domain.exception.ResourceNotFoundException;
import com.example.transaction.domain.model.Account;
import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.repository.AccountRepository;
import com.example.transaction.domain.repository.TransactionRepository;
import com.example.transaction.domain.service.in.CreateTransaction;
import com.example.transaction.domain.service.in.DeleteTransaction;
import com.example.transaction.domain.service.in.RetrieveTransaction;
import com.example.transaction.domain.service.in.UpdateTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService implements CreateTransaction, UpdateTransaction, DeleteTransaction, RetrieveTransaction {

  @Value("${transaction.limit}")
  private int TRANSACTION_LIMIT;

  private final TransactionRepository transactionRepository;
  private final AccountRepository accountRepository;

  @Override
  public Transaction createTransaction(Transaction transaction) {
    Account account = accountRepository.findByNumber(transaction.getAccountNumber())
        .orElseThrow(() -> new ResourceNotFoundException("Account not found with number: " + transaction.getAccountNumber()));

    verifyTransactionsLimit(account.getCustomerName());
    verifyAmount(transaction.getAmount());
    verifyDate(transaction.getTransactionDate());

    return transactionRepository.save(transaction);
  }

  @Override
  public Transaction updateTransaction(Integer id, BigDecimal amount, String merchant) {
    verifyAmount(amount);

    Optional<Transaction> existingTransaction = transactionRepository.findById(id);
    if (existingTransaction.isPresent()) {
      Transaction transactionToUpdate = Transaction.builder()
          .id(id)
          .amount(amount)
          .merchant(merchant)
          .accountNumber(existingTransaction.get().getAccountNumber())
          .transactionDate(existingTransaction.get().getTransactionDate())
          .build();

      return transactionRepository.save(transactionToUpdate);
    } else {
      throw new ResourceNotFoundException("Transaction not found with id " + id);
    }
  }

  @Override
  public void deleteTransaction(Integer id) {
    Optional<Transaction> existingTransaction = transactionRepository.findById(id);
    if (existingTransaction.isPresent()) {
      transactionRepository.delete(id);
    } else {
      throw new ResourceNotFoundException("Transaction not found with id " + id);
    }
  }

  @Override
  public List<Transaction> findTransactionsByAccountNumber(String accountNumber) {
    return transactionRepository.findAllByAccountNumber(accountNumber);
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
    if (byCustomerName >= TRANSACTION_LIMIT) {
      throw new TransactionLimitExceededException("You have reached the maximum number of transactions");
    }
  }

}