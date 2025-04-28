package com.example.transaction.domain.repository;

import com.example.transaction.domain.model.Transaction;

public interface TransactionRepository {

  Transaction save(Transaction transaction);

  int findByCustomerName(String customerName);

}
