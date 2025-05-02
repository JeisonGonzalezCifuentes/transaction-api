package com.example.transaction.infrastructure.exception;

import com.example.transaction.domain.exception.InvalidAmountException;
import com.example.transaction.domain.exception.InvalidTransactionDateException;
import com.example.transaction.domain.exception.TransactionLimitExceededException;
import com.example.transaction.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(TransactionLimitExceededException.class)
  public ResponseEntity<ErrorResponse> handleTransactionLimitExceededException(TransactionLimitExceededException exception) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .error(ErrorCode.TRANSACTION_LIMIT_EXCEEDED.toString())
        .message(exception.getMessage())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidAmountException.class)
  public ResponseEntity<ErrorResponse> handleInvalidAmountException(InvalidAmountException exception) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .error(ErrorCode.INVALID_AMOUNT.toString())
        .message(exception.getMessage())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .error(ErrorCode.RESOURCE_NOT_FOUND.toString())
        .message(exception.getMessage())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidTransactionDateException.class)
  public ResponseEntity<ErrorResponse> handleInvalidTransactionDateException(InvalidTransactionDateException exception) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .error(ErrorCode.INVALID_TRANSACTION_DATE.toString())
        .message(exception.getMessage())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .error(ErrorCode.BAD_REQUEST.toString())
        .message(exception.getMessage())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .error(ErrorCode.INTERNAL_SERVER_ERROR.toString())
        .message("An unexpected error occurred")
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
