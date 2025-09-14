package com.sporty.take_home.airport.cache;

import com.sporty.take_home.airport.view.AirportDetailView;

import java.util.List;
import java.util.Optional;

/**
 * Cache adapter interface for airport data.
 * Allows switching between different cache implementations (Local/Redis).
 */
public interface AirportCacheAdapter {

    /**
     * Get cached airport data by code
     */
    Optional<List<AirportDetailView>> get(String code);

    /**
     * Put airport data into cache
     */
    void put(String code, List<AirportDetailView> airports);

    /**
     * Remove specific entry from cache
     */
    void evict(String code);

    /**
     * Clear all cache entries
     */
    void clear();

    /**
     * Get cache type identifier
     */
    String getCacheType();
}