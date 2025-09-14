package com.sporty.take_home.airport.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CircuitBreakerConfig {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    public void registerEventListeners() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("airport-api");
        
        circuitBreaker.getEventPublisher()
            .onStateTransition(event -> 
                log.info("Circuit breaker state transition: {} -> {} for airport-api", 
                    event.getStateTransition().getFromState(), 
                    event.getStateTransition().getToState()))
            .onFailureRateExceeded(event -> 
                log.warn("Circuit breaker failure rate exceeded: {}% for airport-api", 
                    event.getFailureRate()))
            .onCallNotPermitted(event -> 
                log.warn("Circuit breaker call not permitted for airport-api"))
            .onIgnoredError(event -> 
                log.debug("Circuit breaker ignored error for airport-api: {}", 
                    event.getThrowable().getMessage()));
    }
}