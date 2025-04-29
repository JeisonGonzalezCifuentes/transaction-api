package com.example.transaction.domain.service;

import com.example.transaction.domain.model.Account;
import com.example.transaction.domain.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private AccountService accountService;

  @Test
  void findAll_shouldReturnListOfAccounts() {
    // Given
    List<Account> accounts = List.of(
        Account.builder().id(1).number("ACC001").customerName("Juan Pérez").build(),
        Account.builder().id(2).number("ACC002").customerName("Ana Gómez").build()
    );
    when(accountRepository.findAll()).thenReturn(accounts);

    // When
    List<Account> result = accountService.findAll();

    // Then
    assertEquals(2, result.size());
    assertEquals("ACC001", result.get(0).getNumber());
    assertEquals("ACC002", result.get(1).getNumber());
    verify(accountRepository).findAll();
  }
}