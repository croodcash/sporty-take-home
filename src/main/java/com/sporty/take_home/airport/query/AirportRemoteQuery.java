package com.sporty.take_home.airport.query;

import com.sporty.take_home.airport.config.AirportApiProperties;
import com.sporty.take_home.airport.config.AirportSingleFlight;
import com.sporty.take_home.airport.view.AirportDetailResponse;
import com.sporty.take_home.airport.view.AirportDetailView;
import com.sporty.take_home.common.tools.SingleFlight;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class AirportRemoteQuery implements AirportQuery {

    private final SingleFlight airportSingleFlight;
    private final AirportApiProperties apiProperties;

    public AirportRemoteQuery(@Qualifier("singleFlight") SingleFlight airportSingleFlight, 
                             AirportApiProperties apiProperties) {
        this.airportSingleFlight = airportSingleFlight;
        this.apiProperties = apiProperties;
    }

    @Override
    public List<AirportDetailView> findAllByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            log.warn("Airport code is null or empty");
            return Collections.emptyList();
        }

        String singleFlightKey = AirportSingleFlight.Key.AIRPORT_DETAIL + ":" + code.toUpperCase();
        
        try {
            log.debug("Executing airport query for code: {} with singleflight key: {}", code, singleFlightKey);
            return airportSingleFlight.run(singleFlightKey, () -> fetchAirportDataWithCircuitBreaker(code));
        } catch (Exception ex) {
            log.error("Failed to fetch airport data for code: {}", code, ex);
            return Collections.emptyList();
        }
    }

    @CircuitBreaker(name = "airport-api", fallbackMethod = "fallbackAirportData")
    @TimeLimiter(name = "airport-api")
    private List<AirportDetailView> fetchAirportDataWithCircuitBreaker(String code) {
        log.info("Fetching airport data from external API for code: {}", code);
        
        URI uri = UriComponentsBuilder.fromUriString(apiProperties.getAirportsEndpoint())
            .queryParam("apt", "{code}")
            .build(code);

        AirportDetailResponse response = RestClient.create()
            .get()
            .uri(uri)
            .retrieve()
            .body(AirportDetailResponse.class);

        if (response == null || response.getAirports() == null) {
            log.warn("No airport data received for code: {}", code);
            return Collections.emptyList();
        }

        List<AirportDetailView> airports = response.getAirports().get(code);
        log.info("Successfully fetched {} airports for code: {}", 
            airports != null ? airports.size() : 0, code);
        
        return airports != null ? airports : Collections.emptyList();
    }

    private List<AirportDetailView> fallbackAirportData(String code, Exception ex) {
        log.warn("Circuit breaker activated for airport code: {}. Reason: {}", code, ex.getMessage());
        return Collections.emptyList();
    }
}
