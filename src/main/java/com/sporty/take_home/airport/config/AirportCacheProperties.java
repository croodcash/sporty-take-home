package com.sporty.take_home.airport.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Data
@Configuration
@ConfigurationProperties(prefix = "airport.cache")
public class AirportCacheProperties {
    
    /**
     * Cache type: "local" for Caffeine, "redis" for Redis
     */
    private String type = "local";
    
    /**
     * Local cache configuration
     */
    private Local local = new Local();
    
    /**
     * Redis cache configuration
     */
    private Redis redis = new Redis();
    
    @Data
    public static class Local {
        /**
         * Maximum cache size (for local cache)
         */
        private long maxSize = 1000;
        
        /**
         * TTL for cache entries
         */
        private Duration expireAfterWrite = Duration.ofMinutes(30);
        
        /**
         * TTL for cache entries after last access
         */
        private Duration expireAfterAccess = Duration.ofMinutes(15);
    }
    
    @Data
    public static class Redis {
        /**
         * TTL for Redis cache entries
         */
        private Duration expireAfterWrite = Duration.ofMinutes(30);
    }
}