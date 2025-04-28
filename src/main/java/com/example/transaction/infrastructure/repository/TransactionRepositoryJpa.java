package com.example.transaction.infrastructure.repository;

import com.example.transaction.domain.model.Transaction;
import com.example.transaction.domain.repository.TransactionRepository;
import com.example.transaction.infrastructure.persistence.JpaTransactionRepository;
import com.example.transaction.infrastructure.repository.entity.TransactionEntity;
import com.example.transaction.infrastructure.repository.mapper.TransactionEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryJpa implements TransactionRepository {

  private final JpaTransactionRepository jpaTransactionRepository;

  @Override
  public Transaction save(Transaction transaction) {
    TransactionEntity entity = TransactionEntityMapper.INSTANCE.toEntity(transaction);

    TransactionEntity saved = jpaTransactionRepository.save(entity);
    return TransactionEntityMapper.INSTANCE.toDomain(saved);
  }

  @Override
  public int findByCustomerName(String customerName) {
    return jpaTransactionRepository.findByCustomerName(customerName).size();
  }
}