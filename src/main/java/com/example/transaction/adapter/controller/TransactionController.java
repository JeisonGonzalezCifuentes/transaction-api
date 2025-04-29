package com.example.transaction.adapter.controller;

import com.example.transaction.adapter.controller.dto.CreateTransactionRequest;
import com.example.transaction.adapter.controller.dto.TransactionDto;
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
  public TransactionDto createTransaction(@RequestBody CreateTransactionRequest request) {
    Transaction transaction = createTransactionService.createTransaction(request.toDomain());
    return getTransactionDto(transaction);
  }

  @PutMapping("/{id}")
  public TransactionDto updateTransaction(@PathVariable Integer id, @RequestBody UpdateTransactionRequest request) {
    Transaction transaction = updateTransactionService.updateTransaction(id, request.getAmount(), request.getMerchant());
    return getTransactionDto(transaction);
  }

  @DeleteMapping("/{id}")
  public void deleteTransaction(@PathVariable Integer id) {
    deleteTransactionService.deleteTransaction(id);
  }

  @GetMapping("/by-account/{accountNumber}")
  public List<TransactionDto> getTransactionsByAccountNumber(@PathVariable String accountNumber) {
    return retrieveTransactionService.findTransactionsByAccountNumber(accountNumber)
        .stream().map(TransactionController::getTransactionDto).toList();
  }

  private static TransactionDto getTransactionDto(Transaction domain) {
    return TransactionDto.builder()
        .id(domain.getId())
        .accountNumber(domain.getAccountNumber())
        .amount(domain.getAmount())
        .merchant(domain.getMerchant())
        .transactionDate(domain.getTransactionDate())
        .build();
  }

}
