package com.example.transaction.infrastructure.rate;

public interface RateLimiterService {

  boolean isRateLimitExceeded(String clientIdentifier);

}