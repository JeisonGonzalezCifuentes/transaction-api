package com.example.transaction.adapter.controller;

import com.example.transaction.adapter.controller.dto.CreateTransactionRequest;
import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.service.in.CreateTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

  private final CreateTransaction createTransactionService;

  @PostMapping
  public Transaction createTransaction(@RequestBody CreateTransactionRequest request) {
    return createTransactionService.createTransaction(request.toDomain());
  }

}
