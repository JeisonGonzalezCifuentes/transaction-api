package com.example.transaction.domain.repository;

import com.example.transaction.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

  List<Account> findAll();

  Optional<Account> findByNumber(String accountNumber);

}
