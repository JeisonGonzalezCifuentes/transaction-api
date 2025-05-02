package com.example.transaction.domain.service;

import com.example.transaction.domain.exception.*;
import com.example.transaction.domain.model.Account;
import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.repository.AccountRepository;
import com.example.transaction.domain.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

  @Mock
  private TransactionRepository transactionRepository;
  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(transactionService, "TRANSACTION_LIMIT", 5);
  }

  @Test
  void createTransaction_shouldSucceed() {
    Transaction tx = getValidTransaction();
    Account account = getValidAccount();

    when(accountRepository.findByNumber("ACC123")).thenReturn(Optional.of(account));
    when(transactionRepository.findByCustomerName("Juan")).thenReturn(2);
    when(transactionRepository.save(any())).thenReturn(tx);

    Transaction result = transactionService.createTransaction(tx);

    assertNotNull(result);
    verify(transactionRepository).save(tx);
  }

  @Test
  void createTransaction_shouldFail_whenAccountNotFound() {
    Transaction tx = getValidTransaction();

    when(accountRepository.findByNumber("ACC123")).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> transactionService.createTransaction(tx));
  }

  @Test
  void createTransaction_shouldFail_whenAmountIsNegative() {
    Transaction tx = getValidTransaction().toBuilder().amount(new BigDecimal("-10")).build();
    Account account = getValidAccount();

    when(accountRepository.findByNumber("ACC123")).thenReturn(Optional.of(account));

    assertThrows(InvalidAmountException.class, () -> transactionService.createTransaction(tx));
  }

  @Test
  void createTransaction_shouldFail_whenDateIsInFuture() {
    Transaction tx = getValidTransaction().toBuilder().transactionDate(LocalDateTime.now().plusDays(1)).build();
    Account account = getValidAccount();

    when(accountRepository.findByNumber("ACC123")).thenReturn(Optional.of(account));

    assertThrows(InvalidTransactionDateException.class, () -> transactionService.createTransaction(tx));
  }

  @Test
  void createTransaction_shouldFail_whenTransactionLimitExceeded() {
    Transaction tx = getValidTransaction();
    Account account = getValidAccount();

    when(accountRepository.findByNumber("ACC123")).thenReturn(Optional.of(account));
    when(transactionRepository.findByCustomerName("Juan")).thenReturn(5);

    assertThrows(TransactionLimitExceededException.class, () -> transactionService.createTransaction(tx));
  }

  @Test
  void updateTransaction_shouldSucceed() {
    Transaction oldTx = getValidTransaction();
    Transaction updatedTx = oldTx.toBuilder().amount(new BigDecimal("200")).merchant("Updated").build();

    when(transactionRepository.findById(1)).thenReturn(Optional.of(oldTx));
    when(transactionRepository.save(any())).thenReturn(updatedTx);

    Transaction result = transactionService.updateTransaction(1, new BigDecimal("200"), "Updated");

    assertEquals("Updated", result.getMerchant());
    verify(transactionRepository).save(any());
  }

  @Test
  void updateTransaction_shouldFail_whenNotFound() {
    when(transactionRepository.findById(999)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
        () -> transactionService.updateTransaction(999, new BigDecimal("100"), "X"));
  }

  @Test
  void deleteTransaction_shouldSucceed() {
    Transaction tx = getValidTransaction();

    when(transactionRepository.findById(1)).thenReturn(Optional.of(tx));

    transactionService.deleteTransaction(1);

    verify(transactionRepository).delete(1);
  }

  @Test
  void deleteTransaction_shouldFail_whenNotFound() {
    when(transactionRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> transactionService.deleteTransaction(1));
  }

  @Test
  void findTransactionsByAccountNumber_shouldReturnList() {
    List<Transaction> transactions = List.of(getValidTransaction());

    when(transactionRepository.findAllByAccountNumber("ACC123")).thenReturn(transactions);

    List<Transaction> result = transactionService.findTransactionsByAccountNumber("ACC123");

    assertEquals(1, result.size());
    verify(transactionRepository).findAllByAccountNumber("ACC123");
  }

  // Helpers
  private Transaction getValidTransaction() {
    return Transaction.builder()
        .id(1)
        .accountNumber("ACC123")
        .amount(new BigDecimal("100"))
        .merchant("Amazon")
        .transactionDate(LocalDateTime.now().minusDays(1))
        .build();
  }

  private Account getValidAccount() {
    return Account.builder()
        .id(1)
        .number("ACC123")
        .customerName("Juan")
        .build();
  }
}
