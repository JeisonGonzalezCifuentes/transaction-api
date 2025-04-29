package com.example.transaction.infrastructure.persistence;

import com.example.transaction.infrastructure.repository.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTransactionRepository extends JpaRepository<Transactions, Integer> {

  List<Transactions> findByCustomerName(String customerName);

}
