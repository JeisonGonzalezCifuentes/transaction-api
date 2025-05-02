package com.example.transaction.domain.service.in;

import com.example.transaction.domain.model.Account;

import java.util.List;

public interface RetrieveAccounts {

  List<Account> findAll();

}
