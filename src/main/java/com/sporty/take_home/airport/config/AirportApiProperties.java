package com.sporty.take_home.airport.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "airport.api")
public class AirportApiProperties {
    
    /**
     * Base URL for the external aviation API
     */
    private String baseUrl;
    
    /**
     * Airports endpoint URL
     */
    private String airportsEndpoint;
}