package com.example.transaction.adapter.controller;

import com.example.transaction.adapter.controller.dto.CreateTransactionRequest;
import com.example.transaction.adapter.controller.dto.UpdateTransactionRequest;
import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.service.in.CreateTransaction;
import com.example.transaction.domain.service.in.DeleteTransaction;
import com.example.transaction.domain.service.in.RetrieveTransaction;
import com.example.transaction.domain.service.in.UpdateTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

  private final CreateTransaction createTransactionService;
  private final UpdateTransaction updateTransactionService;
  private final DeleteTransaction deleteTransactionService;
  private final RetrieveTransaction retrieveTransactionService;

  @PostMapping
  public Transaction createTransaction(@RequestBody CreateTransactionRequest request) {
    return createTransactionService.createTransaction(request.toDomain());
  }

  @PutMapping("/{id}")
  public Transaction updateTransaction(@PathVariable Integer id, @RequestBody UpdateTransactionRequest request) {
    return updateTransactionService.updateTransaction(id, request.getAmount(), request.getMerchant());
  }

  @DeleteMapping("/{id}")
  public void deleteTransaction(@PathVariable Integer id) {
    deleteTransactionService.deleteTransaction(id);
  }

  @GetMapping("/customer/{customerName}")
  public List<Transaction> getTransactionsByCustomerName(@PathVariable String customerName) {
    return retrieveTransactionService.findTransactionsByCustomerName(customerName);
  }

}
