package com.example.transaction.infrastructure.repository.mapper;

import com.example.transaction.domain.model.Transaction;
import com.example.transaction.infrastructure.repository.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionEntityMapper {

  TransactionEntityMapper INSTANCE = Mappers.getMapper(TransactionEntityMapper.class);

  TransactionEntity toEntity(Transaction domainModel);
  Transaction toDomain(TransactionEntity entity);

}
