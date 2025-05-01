package com.example.transaction.adapter.controller;

import com.example.transaction.adapter.controller.dto.AccountDto;
import com.example.transaction.domain.model.Account;
import com.example.transaction.domain.service.in.RetrieveAccounts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "Account", description = "Operations related to accounts")
public class AccountController {

  private final RetrieveAccounts retrieveAccountsService;

  @Operation(summary = "Get all accounts", description = "Returns a list of all registered accounts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of accounts returned successfully")
  })
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
