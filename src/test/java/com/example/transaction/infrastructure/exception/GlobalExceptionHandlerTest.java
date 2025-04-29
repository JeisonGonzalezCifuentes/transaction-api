package com.example.transaction.infrastructure.exception;

import com.example.transaction.domain.exception.InvalidAmountException;
import com.example.transaction.domain.exception.InvalidTransactionDateException;
import com.example.transaction.domain.exception.ResourceNotFoundException;
import com.example.transaction.domain.exception.TransactionLimitExceededException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

  @InjectMocks
  private GlobalExceptionHandler globalExceptionHandler;

  @Test
  void handleTransactionLimitExceededException_shouldReturnBadRequest() {
    // Given
    TransactionLimitExceededException exception = new TransactionLimitExceededException("Transaction limit exceeded");

    // When
    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleTransactionLimitExceededException(exception);

    // Then
    assertEquals(400, response.getStatusCodeValue());
    assertEquals("TRANSACTION_LIMIT_EXCEEDED", response.getBody().getError());
    assertEquals("Transaction limit exceeded", response.getBody().getMessage());
  }

  @Test
  void handleInvalidAmountException_shouldReturnBadRequest() {
    // Given
    InvalidAmountException exception = new InvalidAmountException("Amount cannot be negative");

    // When
    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleInvalidAmountException(exception);

    // Then
    assertEquals(400, response.getStatusCodeValue());
    assertEquals("INVALID_AMOUNT", response.getBody().getError());
    assertEquals("Amount cannot be negative", response.getBody().getMessage());
  }

  @Test
  void handleResourceNotFoundException_shouldReturnBadRequest() {
    // Given
    ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");

    // When
    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleResourceNotFoundException(exception);

    // Then
    assertEquals(400, response.getStatusCodeValue());
    assertEquals("RESOURCE_NOT_FOUND", response.getBody().getError());
    assertEquals("Resource not found", response.getBody().getMessage());
  }

  @Test
  void handleInvalidTransactionDateException_shouldReturnBadRequest() {
    // Given
    InvalidTransactionDateException exception = new InvalidTransactionDateException("Transaction date cannot be in the future");

    // When
    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleInvalidTransactionDateException(exception);

    // Then
    assertEquals(400, response.getStatusCodeValue());
    assertEquals("INVALID_TRANSACTION_DATE", response.getBody().getError());
    assertEquals("Transaction date cannot be in the future", response.getBody().getMessage());
  }

  @Test
  void handleIllegalArgumentException_shouldReturnBadRequest() {
    // Given
    IllegalArgumentException exception = new IllegalArgumentException("Invalid argument provided");

    // When
    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleIllegalArgumentException(exception);

    // Then
    assertEquals(400, response.getStatusCodeValue());
    assertEquals("BAD_REQUEST", response.getBody().getError());
    assertEquals("Invalid argument provided", response.getBody().getMessage());
  }

  @Test
  void handleGeneralException_shouldReturnInternalServerError() {
    // Given
    Exception exception = new Exception("An unexpected error occurred");

    // When
    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleGeneralException(exception);

    // Then
    assertEquals(500, response.getStatusCodeValue());
    assertEquals("INTERNAL_SERVER_ERROR", response.getBody().getError());
    assertEquals("An unexpected error occurred", response.getBody().getMessage());
  }
}
