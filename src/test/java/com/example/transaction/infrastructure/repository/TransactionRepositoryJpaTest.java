package com.example.transaction.infrastructure.repository;

import com.example.transaction.domain.exception.ResourceNotFoundException;
import com.example.transaction.domain.model.Transaction;
import com.example.transaction.infrastructure.persistence.JpaAccountRepository;
import com.example.transaction.infrastructure.persistence.JpaTransactionRepository;
import com.example.transaction.infrastructure.repository.entity.AccountEntity;
import com.example.transaction.infrastructure.repository.entity.TransactionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryJpaTest {

  @Mock
  private JpaTransactionRepository jpaTransactionRepository;

  @Mock
  private JpaAccountRepository jpaAccountRepository;

  @InjectMocks
  private TransactionRepositoryJpa transactionRepositoryJpa;

  private Transaction transaction;
  private TransactionEntity transactionEntity;
  private AccountEntity accountEntity;

  @BeforeEach
  void setUp() {
    accountEntity = AccountEntity.builder()
        .id(1)
        .number("ACC123")
        .customerName("John Doe")
        .build();

    transaction = Transaction.builder()
        .id(10)
        .accountNumber("ACC123")
        .amount(new BigDecimal("100.00"))
        .merchant("Amazon")
        .transactionDate(LocalDateTime.now())
        .build();

    transactionEntity = TransactionEntity.builder()
        .id(10)
        .account(accountEntity)
        .amount(new BigDecimal("100.00"))
        .merchant("Amazon")
        .date(transaction.getTransactionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        .build();
  }

  @Test
  void shouldSaveTransaction_whenAccountExists() {
    // Given
    when(jpaAccountRepository.findByNumber("ACC123")).thenReturn(Optional.of(accountEntity));
    when(jpaTransactionRepository.save(any(TransactionEntity.class))).thenReturn(transactionEntity);

    // When
    Transaction saved = transactionRepositoryJpa.save(transaction);

    // Then
    assertThat(saved).isNotNull();
    assertThat(saved.getId()).isEqualTo(10);
    assertThat(saved.getAmount()).isEqualTo(new BigDecimal("100.00"));
    assertThat(saved.getMerchant()).isEqualTo("Amazon");
    assertThat(saved.getAccountNumber()).isEqualTo("ACC123");
  }

  @Test
  void shouldThrowException_whenAccountDoesNotExist() {
    // Given
    when(jpaAccountRepository.findByNumber("ACC123")).thenReturn(Optional.empty());

    // Then
    assertThatThrownBy(() -> transactionRepositoryJpa.save(transaction))
        .isInstanceOf(ResourceNotFoundException.class)
        .hasMessageContaining("Account not found with number: ACC123");
  }

  @Test
  void shouldFindTransactionById() {
    // Given
    when(jpaTransactionRepository.findById(10)).thenReturn(Optional.of(transactionEntity));

    // When
    Optional<Transaction> result = transactionRepositoryJpa.findById(10);

    // Then
    assertThat(result).isPresent();
    assertThat(result.get().getId()).isEqualTo(10);
  }

  @Test
  void shouldDeleteTransactionById() {
    // When
    transactionRepositoryJpa.delete(10);

    // Then
    verify(jpaTransactionRepository).deleteById(10);
  }

  @Test
  void shouldCountTransactionsByCustomerName() {
    // Given
    when(jpaTransactionRepository.countByAccountCustomerName("John Doe")).thenReturn(3);

    // When
    int count = transactionRepositoryJpa.findByCustomerName("John Doe");

    // Then
    assertThat(count).isEqualTo(3);
  }

  @Test
  void shouldFindAllTransactionsByAccountNumber() {
    // Given
    when(jpaTransactionRepository.findAllByAccountNumber("ACC123")).thenReturn(List.of(transactionEntity));

    // When
    List<Transaction> transactions = transactionRepositoryJpa.findAllByAccountNumber("ACC123");

    // Then
    assertThat(transactions).hasSize(1);
    assertThat(transactions.getFirst().getMerchant()).isEqualTo("Amazon");
  }
}
