package com.example.transaction.adapter.controller;

import com.example.transaction.adapter.controller.dto.CreateTransactionRequest;
import com.example.transaction.adapter.controller.dto.TransactionDto;
import com.example.transaction.adapter.controller.dto.UpdateTransactionRequest;
import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.service.in.CreateTransaction;
import com.example.transaction.domain.service.in.DeleteTransaction;
import com.example.transaction.domain.service.in.RetrieveTransaction;
import com.example.transaction.domain.service.in.UpdateTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "Operations related to transaction management")
public class TransactionController {

  public static final String RESOURCE_NOT_FOUND_RESPONSE = """
          {
            "timestamp": "2025-04-30T19:39:14.891576",
            "status": 404,
            "error": "RESOURCE_NOT_FOUND",
            "message": "Message description"
          }
      """;

  private final CreateTransaction createTransactionService;
  private final UpdateTransaction updateTransactionService;
  private final DeleteTransaction deleteTransactionService;
  private final RetrieveTransaction retrieveTransactionService;

  @PostMapping
  @Operation(summary = "Create a transaction", description = "Creates a new transaction for an account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Transaction created successfully"),
      @ApiResponse(
          responseCode = "404",
          description = "Account not found",
          content = @Content(
              mediaType = "application/json",
              examples = @ExampleObject(value = RESOURCE_NOT_FOUND_RESPONSE)
          )
      )
  })
  public TransactionDto createTransaction(@RequestBody CreateTransactionRequest request) {
    Transaction transaction = createTransactionService.createTransaction(request.toDomain());
    return getTransactionDto(transaction);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a transaction", description = "Updates an existing transaction's amount and merchant")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Transaction updated successfully"),
      @ApiResponse(
          responseCode = "404",
          description = "Transaction not found",
          content = @Content(
              mediaType = "application/json",
              examples = @ExampleObject(value = RESOURCE_NOT_FOUND_RESPONSE)
          )
      )
  })
  public TransactionDto updateTransaction(@PathVariable Integer id, @RequestBody UpdateTransactionRequest request) {
    Transaction transaction = updateTransactionService.updateTransaction(id, request.getAmount(), request.getMerchant());
    return getTransactionDto(transaction);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a transaction", description = "Deletes a transaction by its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Transaction deleted successfully"),
      @ApiResponse(
          responseCode = "404",
          description = "Transaction not found",
          content = @Content(
              mediaType = "application/json",
              examples = @ExampleObject(value = RESOURCE_NOT_FOUND_RESPONSE)
          )
      )
  })
  public void deleteTransaction(@PathVariable Integer id) {
    deleteTransactionService.deleteTransaction(id);
  }

  @GetMapping("/by-account/{accountNumber}")
  @Operation(summary = "Get transactions by account number", description = "Returns all transactions for a given account number")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully")
  })
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
