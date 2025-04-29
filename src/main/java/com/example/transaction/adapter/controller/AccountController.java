package com.example.transaction.adapter.controller;

import com.example.transaction.adapter.controller.dto.AccountDto;
import com.example.transaction.adapter.controller.dto.TransactionDto;
import com.example.transaction.domain.model.Account;
import com.example.transaction.domain.service.in.RetrieveAccounts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

  private final RetrieveAccounts retrieveAccountsService;

  @GetMapping()
  public List<AccountDto> getAll() {
    return retrieveAccountsService.findAll().stream().map(AccountController::getAccountDto).toList();
  }

  private static AccountDto getAccountDto(Account domain) {
    return AccountDto.builder()
        .id(domain.getId())
        .number(domain.getNumber())
        .customerName(domain.getCustomerName())
        .build();
  }

}
