package com.example.transaction.domain.exception;

public class TransactionLimitExceededException extends RuntimeException {
  public TransactionLimitExceededException(String message) {
    super(message);
  }
}