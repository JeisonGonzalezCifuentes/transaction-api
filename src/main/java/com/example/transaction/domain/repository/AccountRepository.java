package com.example.transaction.domain.repository;

import com.example.transaction.domain.model.Account;
import com.example.transaction.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

  List<Account> findAll();

}
