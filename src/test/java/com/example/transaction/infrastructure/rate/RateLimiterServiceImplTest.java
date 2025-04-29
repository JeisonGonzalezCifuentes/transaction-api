package com.example.transaction.infrastructure.rate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RateLimiterServiceImplTest {

  private RateLimiterServiceImpl rateLimiterService;

  @BeforeEach
  void setUp() {
    rateLimiterService = new RateLimiterServiceImpl();

    // Set @Value properties using ReflectionTestUtils
    ReflectionTestUtils.setField(rateLimiterService, "MAX_REQUESTS", 5);  // Max number of requests allowed
    ReflectionTestUtils.setField(rateLimiterService, "TIME_WINDOW_SECONDS", 1);  // Time window of 1 second
  }

  @Test
  void isRateLimitExceeded_shouldReturnFalse_whenRequestIsWithinLimit() {
    // Given
    String clientIdentifier = "client1";

    // Simulate that the client has made 3 requests within the time window
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 1
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 2
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 3

    // When
    boolean result = rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 4

    // Then
    assertFalse(result); // Rate limit should not be exceeded
  }

  @Test
  void isRateLimitExceeded_shouldReturnTrue_whenRequestExceedsLimit() {
    // Given
    String clientIdentifier = "client1";

    // Simulate that the client has made 5 requests within the time window
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 1
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 2
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 3
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 4
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 5

    // When
    boolean result = rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 6

    // Then
    assertTrue(result); // Rate limit should be exceeded
  }

  @Test
  void isRateLimitExceeded_shouldReturnFalse_afterRemovingOldRequests() {
    // Given
    String clientIdentifier = "client1";

    // Simulate that the client has made 5 requests, but some are outside the time window
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 1
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 2
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 3
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 4
    rateLimiterService.isRateLimitExceeded(clientIdentifier); // Request 5

    // Simulate that enough time has passed (e.g., 1000 milliseconds) to remove requests outside the limit
    try {
      Thread.sleep(1000);  // Sleep for 1000 milliseconds
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // When
    boolean result = rateLimiterService.isRateLimitExceeded(clientIdentifier); // New request after time has passed

    // Then
    assertFalse(result); // After removing requests outside the time window, rate limit is not exceeded
  }

}
