package com.example.transaction.infrastructure.interceptor;

import com.example.transaction.infrastructure.rate.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
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
  @Mock
  private PrintWriter writer;

  @InjectMocks
  private RateLimitInterceptor interceptor;

  @BeforeEach
  void setUp() throws Exception {
    var field = RateLimitInterceptor.class.getDeclaredField("rateLimiterService");
    field.setAccessible(true);
    field.set(interceptor, rateLimiterService);
  }

  @Test
  void testPreHandle_AllowsRequest_WhenRateLimitNotExceeded() throws Exception {
    when(request.getHeader("origin")).thenReturn("example.com");
    when(request.getRemoteAddr()).thenReturn("192.168.0.1");
    when(rateLimiterService.isRateLimitExceeded("192.168.0.1-example.com")).thenReturn(false);

    boolean result = interceptor.preHandle(request, response, new Object());

    assertTrue(result);
    verify(response, never()).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
  }

  @Test
  void testPreHandle_BlocksRequest_WhenRateLimitExceeded() throws Exception {
    when(request.getHeader("origin")).thenReturn("example.com");
    when(request.getRemoteAddr()).thenReturn("192.168.0.1");
    when(rateLimiterService.isRateLimitExceeded("192.168.0.1-example.com")).thenReturn(true);
    when(response.getWriter()).thenReturn(writer);

    boolean result = interceptor.preHandle(request, response, new Object());

    assertFalse(result);
    verify(response).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
    verify(writer).write("Rate limit exceeded. Try again later.");
  }
}
