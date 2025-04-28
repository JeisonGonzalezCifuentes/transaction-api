package com.example.transaction.infrastructure.persistence;

import com.example.transaction.infrastructure.repository.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Integer> {

  List<TransactionEntity> findByCustomerName(String customerName);

}
