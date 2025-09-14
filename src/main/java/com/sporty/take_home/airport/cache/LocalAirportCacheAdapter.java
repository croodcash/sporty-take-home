package com.sporty.take_home.airport.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.sporty.take_home.airport.config.AirportCacheProperties;
import com.sporty.take_home.airport.view.AirportDetailView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@ConditionalOnProperty(name = "airport.cache.type", havingValue = "local", matchIfMissing = true)
public class LocalAirportCacheAdapter implements AirportCacheAdapter {

    private final Cache<String, List<AirportDetailView>> cache;

    public LocalAirportCacheAdapter(AirportCacheProperties cacheProperties) {
        AirportCacheProperties.Local localConfig = cacheProperties.getLocal();
        log.info("Initializing Local Airport Cache with maxSize={}, expireAfterWrite={}, expireAfterAccess={}", 
                localConfig.getMaxSize(), localConfig.getExpireAfterWrite(), localConfig.getExpireAfterAccess());
        
        this.cache = Caffeine.newBuilder()
                .maximumSize(localConfig.getMaxSize())
                .expireAfterWrite(localConfig.getExpireAfterWrite())
                .expireAfterAccess(localConfig.getExpireAfterAccess())
                .build();
    }

    @Override
    public Optional<List<AirportDetailView>> get(String code) {
        String key = normalizeKey(code);
        List<AirportDetailView> result = cache.getIfPresent(key);
        
        if (result != null) {
            log.debug("Local cache hit for airport code: {}", code);
            return Optional.of(result);
        }
        
        log.debug("Local cache miss for airport code: {}", code);
        return Optional.empty();
    }

    @Override
    public void put(String code, List<AirportDetailView> airports) {
        if (airports != null && !airports.isEmpty()) {
            String key = normalizeKey(code);
            cache.put(key, airports);
            log.debug("Cached {} airports locally for code: {}", airports.size(), code);
        }
    }

    @Override
    public void evict(String code) {
        String key = normalizeKey(code);
        cache.invalidate(key);
        log.debug("Evicted local cache for airport code: {}", code);
    }

    @Override
    public void clear() {
        cache.invalidateAll();
        log.info("Cleared all local airport cache");
    }

    @Override
    public String getCacheType() {
        return "LOCAL";
    }

    private String normalizeKey(String code) {
        return code != null ? code.toUpperCase().trim() : "";
    }
}