package com.sporty.take_home.airport.query;

import com.sporty.take_home.airport.cache.AirportCacheAdapter;
import com.sporty.take_home.airport.view.AirportDetailView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class CachedAirportQuery implements AirportQuery {

    private final AirportRemoteQuery remoteQuery;
    private final AirportCacheAdapter cacheAdapter;

    @Override
    public List<AirportDetailView> findAllByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return Collections.emptyList();
        }

        log.debug("Querying airports for code: {} using {} cache", code, cacheAdapter.getCacheType());
        
        // Try cache first
        Optional<List<AirportDetailView>> cachedResult = cacheAdapter.get(code);
        if (cachedResult.isPresent()) {
            log.debug("Cache hit for airport code: {} from {} cache", code, cacheAdapter.getCacheType());
            return cachedResult.get();
        }

        log.debug("Cache miss for airport code: {} in {} cache, fetching from remote", 
                code, cacheAdapter.getCacheType());
        
        // Fetch from remote (which uses SingleFlight internally)
        List<AirportDetailView> result = remoteQuery.findAllByCode(code);
        
        // Cache the result
        if (result != null && !result.isEmpty()) {
            cacheAdapter.put(code, result);
            log.debug("Cached {} airports for code: {} in {} cache", 
                    result.size(), code, cacheAdapter.getCacheType());
        }
        
        return result != null ? result : Collections.emptyList();
    }


}