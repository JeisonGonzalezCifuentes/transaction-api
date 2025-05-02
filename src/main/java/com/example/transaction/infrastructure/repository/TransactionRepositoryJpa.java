package com.example.transaction.infrastructure.repository;

import com.example.transaction.domain.exception.ResourceNotFoundException;
import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.repository.TransactionRepository;
import com.example.transaction.infrastructure.persistence.JpaAccountRepository;
import com.example.transaction.infrastructure.persistence.JpaTransactionRepository;
import com.example.transaction.infrastructure.repository.entity.AccountEntity;
import com.example.transaction.infrastructure.repository.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryJpa implements TransactionRepository {

  private final JpaTransactionRepository jpaTransactionRepository;
  private final JpaAccountRepository jpaAccountRepository;

  @Override
  public Transaction save(Transaction transaction) {
    AccountEntity account = jpaAccountRepository
        .findByNumber(transaction.getAccountNumber())
        .orElseThrow(() -> new ResourceNotFoundException("Account not found with number: " + transaction.getAccountNumber()));

    TransactionEntity entity = toEntity(transaction, account);
    TransactionEntity saved = jpaTransactionRepository.save(entity);
    return toDomain(saved);
  }

  @Override
  public Optional<Transaction> findById(Integer id) {
    return jpaTransactionRepository.findById(id).map(TransactionRepositoryJpa::toDomain);
  }

  @Override
  public void delete(Integer id) {
    jpaTransactionRepository.deleteById(id);
  }

  @Override
  public int findByCustomerName(String customerName) {
    return jpaTransactionRepository.countByAccountCustomerName(customerName);
  }

  @Override
  public List<Transaction> findAllByAccountNumber(String accountNumber) {
    return jpaTransactionRepository.findAllByAccountNumber(accountNumber).stream()
        .map(TransactionRepositoryJpa::toDomain)
        .toList();
  }

  private static TransactionEntity toEntity(Transaction domain, AccountEntity account) {
    return TransactionEntity.builder()
        .id(domain.getId())
        .amount(domain.getAmount())
        .merchant(domain.getMerchant())
        .account(account)
        .date(domain.getTransactionDate().toString())
        .build();
  }

  private static Transaction toDomain(TransactionEntity entity) {
    return Transaction.builder()
        .id(entity.getId())
        .amount(entity.getAmount())
        .merchant(entity.getMerchant())
        .transactionDate(LocalDateTime.parse(entity.getDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        .accountNumber(entity.getAccount().getNumber())
        .build();
  }

}
