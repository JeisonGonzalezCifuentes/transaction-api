package com.example.transaction.adapter.controller.mapper;

import com.example.transaction.adapter.controller.dto.CreateTransactionRequest;
import com.example.transaction.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

  Transaction toDomain(CreateTransactionRequest createTransactionRequest);

}
