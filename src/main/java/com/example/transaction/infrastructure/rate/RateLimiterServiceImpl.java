package com.example.transaction.infrastructure.rate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Service
public class RateLimiterServiceImpl implements RateLimiterService {

  private final Map<String, LinkedList<LocalDateTime>> requestLogs = new HashMap<>();

  @Value("${rate.max-requests}")
  private int MAX_REQUESTS;

  @Value("${rate.time-window-seconds}")
  private int TIME_WINDOW_SECONDS;

  public boolean isRateLimitExceeded(String clientIdentifier) {
    LocalDateTime now = LocalDateTime.now();

    // Get or initialize the list of request timestamps for the client
    LinkedList<LocalDateTime> logs = requestLogs.computeIfAbsent(clientIdentifier, k -> new LinkedList<>());

    // Remove old requests that are outside the time window
    while (!logs.isEmpty() && logs.getFirst().isBefore(now.minusSeconds(TIME_WINDOW_SECONDS))) {
      logs.poll();
    }

    // If the client has the maximum requests in the last time window seconds, the rate limit is exceeded
    if (logs.size() >= MAX_REQUESTS) {
      return true;
    }

    // Add the current request timestamp and return false (allow request)
    logs.add(now);
    return false;
  }
}