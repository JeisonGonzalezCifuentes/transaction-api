package com.example.transaction.infrastructure.interceptor;

import com.example.transaction.infrastructure.rate.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class RateLimitInterceptor implements HandlerInterceptor {

  @Autowired
  private RateLimiterService rateLimiterService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String customerName = request.getHeader("customerName");
    String clientIdentifier = customerName != null ? customerName : request.getRemoteAddr();

    if (clientIdentifier == null || rateLimiterService.isRateLimitExceeded(clientIdentifier)) {
      response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
      response.getWriter().write("Rate limit exceeded. Try again later.");
      return false;
    }

    return true; // Allow the request to proceed
  }

}