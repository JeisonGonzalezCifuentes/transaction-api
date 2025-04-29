package com.example.transaction.domain.service.in;

import com.example.transaction.domain.model.Transaction;

import java.util.List;

public interface RetrieveTransaction {

  List<Transaction> findTransactionsByAccountNumber(String accountNumber);

}
