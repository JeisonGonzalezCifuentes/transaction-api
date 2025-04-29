package com.example.transaction.infrastructure.repository;

import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.repository.TransactionRepository;
import com.example.transaction.infrastructure.persistence.JpaTransactionRepository;
import com.example.transaction.infrastructure.repository.entity.Transactions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryJpa implements TransactionRepository {

  private final JpaTransactionRepository jpaTransactionRepository;

  @Override
  public Transaction save(Transaction transaction) {
    Transactions entity = toEntity(transaction);

    Transactions saved = jpaTransactionRepository.save(entity);
    return toDomain(saved);
  }

  @Override
  public int findByCustomerName(String customerName) {
    return jpaTransactionRepository.findByCustomerName(customerName).size();
  }


  private static Transactions toEntity(Transaction domain) {
    return Transactions.builder()
        .id(domain.getId())
        .amount(domain.getAmount())
        .merchant(domain.getMerchant())
        .customerName(domain.getCustomerName())
        .date(domain.getTransactionDate().toString())
        .build();
  }

  private static Transaction toDomain(Transactions entity) {
    return Transaction.builder()
        .id(entity.getId())
        .amount(entity.getAmount())
        .merchant(entity.getMerchant())
        .customerName(entity.getCustomerName())
        .transactionDate(LocalDateTime.parse(entity.getDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        .build();
  }

}