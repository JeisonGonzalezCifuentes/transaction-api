package com.example.transaction.domain.repository;

import com.example.transaction.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

  Transaction save(Transaction transaction);

  Optional<Transaction> findById(Integer id);

  void delete(Integer id);

  int findByCustomerName(String customerName);

  List<Transaction> findAllByCustomerName(String customerName);

}
