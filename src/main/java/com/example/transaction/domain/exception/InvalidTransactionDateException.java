package com.example.transaction.domain.exception;

public class InvalidTransactionDateException extends RuntimeException {
  public InvalidTransactionDateException(String message) {
    super(message);
  }
}