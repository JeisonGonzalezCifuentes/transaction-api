package com.example.transaction.config;

import com.example.transaction.infrastructure.interceptor.RateLimitInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public RateLimitInterceptor rateLimitInterceptor() {
    return new RateLimitInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(rateLimitInterceptor())
        .addPathPatterns("/**") // Apply the interceptor to all endpoints
        .excludePathPatterns("/login", "/signup"); // Exclude certain paths
  }
}