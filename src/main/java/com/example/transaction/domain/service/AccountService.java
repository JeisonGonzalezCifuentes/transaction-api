package com.example.transaction.domain.service;

import com.example.transaction.domain.model.Account;
import com.example.transaction.domain.repository.AccountRepository;
import com.example.transaction.domain.service.in.RetrieveAccounts;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements RetrieveAccounts {

  private final AccountRepository accountRepository;

  @Override
  @Cacheable("all-accounts")
  public List<Account> findAll() {
    return accountRepository.findAll();
  }

}
