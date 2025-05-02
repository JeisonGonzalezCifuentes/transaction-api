package com.example.transaction.domain.exception;

public class InvalidAmountException extends RuntimeException {
  public InvalidAmountException(String message) {
    super(message);
  }
}