package com.example.transaction.domain.service.in;

import com.example.transaction.domain.model.Transaction;

import java.math.BigDecimal;

public interface UpdateTransaction {

  Transaction updateTransaction(Integer id, BigDecimal amount, String merchant);

}
