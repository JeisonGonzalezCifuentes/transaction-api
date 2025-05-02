package com.example.transaction.adapter.controller;

import com.example.transaction.adapter.controller.dto.CreateTransactionRequest;
import com.example.transaction.adapter.controller.dto.TransactionDto;
import com.example.transaction.adapter.controller.dto.UpdateTransactionRequest;
import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.service.in.CreateTransaction;
import com.example.transaction.domain.service.in.DeleteTransaction;
import com.example.transaction.domain.service.in.RetrieveTransaction;
import com.example.transaction.domain.service.in.UpdateTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

  @Mock
  private CreateTransaction createTransactionService;

  @Mock
  private UpdateTransaction updateTransactionService;

  @Mock
  private DeleteTransaction deleteTransactionService;

  @Mock
  private RetrieveTransaction retrieveTransactionService;

  @InjectMocks
  private TransactionController transactionController;

  @Test
  void createTransaction_shouldReturnTransactionDto() {
    // Given
    LocalDateTime now = LocalDateTime.now();

    CreateTransactionRequest request = CreateTransactionRequest.builder()
        .accountNumber("ACC123")
        .amount(new BigDecimal("100.00"))
        .merchant("Amazon")
        .transactionDate(now)
        .build();

    Transaction transaction = Transaction.builder()
        .id(1)
        .accountNumber("ACC123")
        .amount(new BigDecimal("100.00"))
        .merchant("Amazon")
        .transactionDate(now)
        .build();

    when(createTransactionService.createTransaction(any())).thenReturn(transaction);

    // When
    TransactionDto result = transactionController.createTransaction(request);

    // Then
    assertNotNull(result);
    assertEquals("ACC123", result.getAccountNumber());
    assertEquals("Amazon", result.getMerchant());
    assertEquals(new BigDecimal("100.00"), result.getAmount());
    verify(createTransactionService).createTransaction(any());
  }

  @Test
  void updateTransaction_shouldReturnUpdatedTransactionDto() {
    // Given
    UpdateTransactionRequest request = UpdateTransactionRequest.builder()
        .amount(new BigDecimal("200.00"))
        .merchant("Walmart")
        .build();

    Transaction updatedTransaction = Transaction.builder()
        .id(2)
        .accountNumber("ACC999")
        .amount(new BigDecimal("200.00"))
        .merchant("Walmart")
        .transactionDate(LocalDateTime.now())
        .build();

    when(updateTransactionService.updateTransaction(eq(2), any(), any())).thenReturn(updatedTransaction);

    // When
    TransactionDto result = transactionController.updateTransaction(2, request);

    // Then
    assertEquals(2, result.getId());
    assertEquals("Walmart", result.getMerchant());
    assertEquals(new BigDecimal("200.00"), result.getAmount());
    verify(updateTransactionService).updateTransaction(eq(2), any(), any());
  }

  @Test
  void deleteTransaction_shouldCallService() {
    // When
    transactionController.deleteTransaction(3);

    // Then
    verify(deleteTransactionService, times(1)).deleteTransaction(3);
  }

  @Test
  void getTransactionsByAccountNumber_shouldReturnListOfTransactionDto() {
    // Given
    Transaction tx1 = Transaction.builder()
        .id(1).accountNumber("ACC789").amount(new BigDecimal("99.99"))
        .merchant("Netflix").transactionDate(LocalDateTime.now()).build();
    Transaction tx2 = Transaction.builder()
        .id(2).accountNumber("ACC789").amount(new BigDecimal("29.99"))
        .merchant("Spotify").transactionDate(LocalDateTime.now()).build();

    when(retrieveTransactionService.findTransactionsByAccountNumber("ACC789"))
        .thenReturn(List.of(tx1, tx2));

    // When
    List<TransactionDto> result = transactionController.getTransactionsByAccountNumber("ACC789");

    // Then
    assertEquals(2, result.size());
    assertEquals("Netflix", result.get(0).getMerchant());
    assertEquals("Spotify", result.get(1).getMerchant());
    verify(retrieveTransactionService).findTransactionsByAccountNumber("ACC789");
  }
}
