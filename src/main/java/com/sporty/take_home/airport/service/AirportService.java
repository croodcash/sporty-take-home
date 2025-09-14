package com.sporty.take_home.airport.service;

import com.sporty.take_home.airport.query.AirportQuery;
import com.sporty.take_home.airport.view.AirportDetailView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportQuery airportQuery;

    public List<AirportDetailView> getAirportsByCode(String code) {
        log.info("Retrieving airports for code: {}", code);
        
        if (code == null || code.trim().isEmpty()) {
            log.warn("Invalid airport code provided: {}", code);
            throw new IllegalArgumentException("Airport code cannot be null or empty");
        }

        try {
            List<AirportDetailView> airports = airportQuery.findAllByCode(code.trim());
            log.info("Found {} airports for code: {}", airports.size(), code);
            return airports;
        } catch (Exception ex) {
            log.error("Failed to retrieve airports for code: {}", code, ex);
            throw new RuntimeException("Unable to retrieve airport information", ex);
        }
    }
}