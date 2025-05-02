package com.example.transaction.adapter.controller;

import com.example.transaction.adapter.controller.dto.AccountDto;
import com.example.transaction.domain.model.Account;
import com.example.transaction.domain.service.in.RetrieveAccounts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

  @Mock
  private RetrieveAccounts retrieveAccountsService;

  @InjectMocks
  private AccountController accountController;

  @Test
  void getAll_shouldReturnListOfAccountDto() {
    // Given
    Account account1 = Account.builder().id(1).number("123456").customerName("Juan Pérez").build();
    Account account2 = Account.builder().id(2).number("654321").customerName("Ana Gómez").build();

    when(retrieveAccountsService.findAll()).thenReturn(List.of(account1, account2));

    // When
    List<AccountDto> result = accountController.getAll();

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());

    assertEquals("123456", result.get(0).getNumber());
    assertEquals("Juan Pérez", result.get(0).getCustomerName());

    assertEquals("654321", result.get(1).getNumber());
    assertEquals("Ana Gómez", result.get(1).getCustomerName());

    verify(retrieveAccountsService, times(1)).findAll();
  }
}
