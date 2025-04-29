package com.example.transaction.infrastructure.interceptor;

import com.example.transaction.infrastructure.rate.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RateLimitInterceptorTest {

  @Mock
  private RateLimiterService rateLimiterService;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @InjectMocks
  private RateLimitInterceptor rateLimitInterceptor;

  @Test
  void preHandle_shouldAllowRequest_whenRateLimitNotExceeded() throws Exception {
    // Given
    String customerName = "Juan";
    when(request.getHeader("customerName")).thenReturn(customerName);
    when(rateLimiterService.isRateLimitExceeded(customerName)).thenReturn(false);

    // When
    boolean result = rateLimitInterceptor.preHandle(request, response, new Object());

    // Then
    assertTrue(result); // Request should be allowed
    verify(response, never()).setStatus(anyInt()); // Status should not be set
  }

  @Test
  void preHandle_shouldBlockRequest_whenRateLimitExceeded() throws Exception {
    // Given
    String customerName = "Juan";
    when(request.getHeader("customerName")).thenReturn(customerName);
    when(rateLimiterService.isRateLimitExceeded(customerName)).thenReturn(true);

    PrintWriter printWriter = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(printWriter);

    // When
    boolean result = rateLimitInterceptor.preHandle(request, response, new Object());

    // Then
    assertFalse(result); // Request should be blocked
    verify(response).setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); // Error status should be set
    verify(response.getWriter()).write("Rate limit exceeded. Try again later."); // Check that write was called with the correct message
  }

  @Test
  void preHandle_shouldAllowRequest_whenCustomerNameIsNullAndExistIp() throws Exception {
    // Given
    when(request.getHeader("customerName")).thenReturn(null);
    when(request.getRemoteAddr()).thenReturn("ipAddressValue");

    // When
    boolean result = rateLimitInterceptor.preHandle(request, response, new Object());

    // Then
    assertTrue(result); // Request should be allowed
    verify(response, never()).setStatus(anyInt()); // Status should not be set
  }

  @Test
  void preHandle_shouldBlockRequest_whenCustomerNameIsNullAndExistIpAndRateLimitExceeded() throws Exception {
    // Given
    when(request.getHeader("customerName")).thenReturn(null);
    when(request.getRemoteAddr()).thenReturn("ipAddressValue");
    when(rateLimiterService.isRateLimitExceeded(request.getRemoteAddr())).thenReturn(true);

    PrintWriter printWriter = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(printWriter);

    // When
    boolean result = rateLimitInterceptor.preHandle(request, response, new Object());

    // Then
    assertFalse(result); // Request should be blocked
    verify(response).setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); // Error status should be set
    verify(response.getWriter()).write("Rate limit exceeded. Try again later."); // Check that write was called with the correct message
  }

  @Test
  void preHandle_shouldBlockRequest_whenCustomerNameIsNullAndIpIsNull() throws Exception {
    // Given
    when(request.getHeader("customerName")).thenReturn(null);
    when(request.getRemoteAddr()).thenReturn(null);

    PrintWriter printWriter = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(printWriter);

    // When
    boolean result = rateLimitInterceptor.preHandle(request, response, new Object());

    // Then
    assertFalse(result); // Request should be blocked
    verify(response).setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); // Error status should be set
    verify(response.getWriter()).write("Rate limit exceeded. Try again later."); // Check that write was called with the correct message
  }
}
