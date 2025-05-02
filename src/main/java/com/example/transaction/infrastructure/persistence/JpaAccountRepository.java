package com.example.transaction.infrastructure.persistence;

import com.example.transaction.infrastructure.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Integer> {

  Optional<AccountEntity> findByNumber(String accountNumber);

}
