package com.example.transaction.infrastructure.repository;

import com.example.transaction.domain.model.Account;
import com.example.transaction.domain.repository.AccountRepository;
import com.example.transaction.infrastructure.persistence.JpaAccountRepository;
import com.example.transaction.infrastructure.repository.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryJpa implements AccountRepository {

  private final JpaAccountRepository jpaAccountRepository;

  @Override
  public List<Account> findAll() {
    return jpaAccountRepository.findAll().stream()
        .map(AccountRepositoryJpa::toDomain)
        .toList();
  }

  @Override
  public Optional<Account> findByNumber(String accountNumber) {
    return jpaAccountRepository.findByNumber(accountNumber).map(AccountRepositoryJpa::toDomain);
  }

  private static Account toDomain(AccountEntity entity) {
    return Account.builder()
        .id(entity.getId())
        .number(entity.getNumber())
        .customerName(entity.getCustomerName())
        .build();
  }

}
