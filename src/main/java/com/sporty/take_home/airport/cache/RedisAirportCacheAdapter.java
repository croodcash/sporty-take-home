package com.sporty.take_home.airport.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sporty.take_home.airport.config.AirportCacheProperties;
import com.sporty.take_home.airport.view.AirportDetailView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "airport.cache.type", havingValue = "redis")
public class RedisAirportCacheAdapter implements AirportCacheAdapter {

    private static final String CACHE_PREFIX = "airport:";
    private static final TypeReference<List<AirportDetailView>> TYPE_REF = new TypeReference<>() {};

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final AirportCacheProperties cacheProperties;

    @Override
    public Optional<List<AirportDetailView>> get(String code) {
        try {
            String key = buildKey(code);
            String cachedValue = redisTemplate.opsForValue().get(key);
            
            if (cachedValue != null) {
                List<AirportDetailView> airports = objectMapper.readValue(cachedValue, TYPE_REF);
                log.debug("Redis cache hit for airport code: {}", code);
                return Optional.of(airports);
            }
            
            log.debug("Redis cache miss for airport code: {}", code);
            return Optional.empty();
            
        } catch (Exception ex) {
            log.error("Error reading from Redis cache for code: {}", code, ex);
            return Optional.empty();
        }
    }

    @Override
    public void put(String code, List<AirportDetailView> airports) {
        if (airports == null || airports.isEmpty()) {
            return;
        }

        try {
            String key = buildKey(code);
            String value = objectMapper.writeValueAsString(airports);
            
            redisTemplate.opsForValue().set(key, value, cacheProperties.getRedis().getExpireAfterWrite());
            log.debug("Cached {} airports in Redis for code: {}", airports.size(), code);
            
        } catch (Exception ex) {
            log.error("Error writing to Redis cache for code: {}", code, ex);
        }
    }

    @Override
    public void evict(String code) {
        try {
            String key = buildKey(code);
            Boolean deleted = redisTemplate.delete(key);
            log.debug("Evicted Redis cache for airport code: {}, deleted: {}", code, deleted);
            
        } catch (Exception ex) {
            log.error("Error evicting from Redis cache for code: {}", code, ex);
        }
    }

    @Override
    public void clear() {
        try {
            Set<String> keys = redisTemplate.keys(CACHE_PREFIX + "*");
            if (keys != null && !keys.isEmpty()) {
                redisTemplate.delete(keys);
                log.info("Cleared {} airport entries from Redis cache", keys.size());
            }
            
        } catch (Exception ex) {
            log.error("Error clearing Redis cache", ex);
        }
    }

    @Override
    public String getCacheType() {
        return "REDIS";
    }

    private String buildKey(String code) {
        return CACHE_PREFIX + (code != null ? code.toUpperCase().trim() : "");
    }
}