package com.example.transaction.infrastructure.repository;

import com.example.transaction.domain.model.Account;
import com.example.transaction.infrastructure.persistence.JpaAccountRepository;
import com.example.transaction.infrastructure.repository.entity.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryJpaTest {

  @Mock
  private JpaAccountRepository jpaAccountRepository;

  @InjectMocks
  private AccountRepositoryJpa accountRepositoryJpa;

  private AccountEntity sampleEntity;

  @BeforeEach
  void setUp() {
    sampleEntity = AccountEntity.builder()
        .id(1)
        .number("123456")
        .customerName("John Doe")
        .transactions(List.of())
        .build();
  }

  @Test
  void shouldReturnAllAccounts() {
    // Given
    when(jpaAccountRepository.findAll()).thenReturn(List.of(sampleEntity));

    // When
    List<Account> result = accountRepositoryJpa.findAll();

    // Then
    assertThat(result).hasSize(1);
    assertThat(result.getFirst())
        .extracting(Account::getId, Account::getNumber, Account::getCustomerName)
        .containsExactly(1, "123456", "John Doe");
  }

  @Test
  void shouldReturnAccountByNumber_whenExists() {
    // Given
    when(jpaAccountRepository.findByNumber("123456")).thenReturn(Optional.of(sampleEntity));

    // When
    Optional<Account> result = accountRepositoryJpa.findByNumber("123456");

    // Then
    assertThat(result).isPresent();
    assertThat(result.get())
        .extracting(Account::getId, Account::getNumber, Account::getCustomerName)
        .containsExactly(1, "123456", "John Doe");
  }

  @Test
  void shouldReturnEmpty_whenAccountNotFound() {
    // Given
    when(jpaAccountRepository.findByNumber("999999")).thenReturn(Optional.empty());

    // When
    Optional<Account> result = accountRepositoryJpa.findByNumber("999999");

    // Then
    assertThat(result).isNotPresent();
  }
}
