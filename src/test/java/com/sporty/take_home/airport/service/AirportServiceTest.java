package com.sporty.take_home.airport.service;

import com.sporty.take_home.airport.query.AirportQuery;
import com.sporty.take_home.airport.view.AirportDetailView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {

    @Mock
    private AirportQuery airportQuery;

    private AirportService airportService;

    @BeforeEach
    void setUp() {
        airportService = new AirportService(airportQuery);
    }

    @Test
    void getAirportsByCode_WithValidCode_ShouldReturnAirports() {
        // Given
        String code = "JFK";
        List<AirportDetailView> expectedAirports = createMockAirports();
        when(airportQuery.findAllByCode(code)).thenReturn(expectedAirports);

        // When
        List<AirportDetailView> result = airportService.getAirportsByCode(code);

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getFaaIdent()).isEqualTo("JFK");
    }

    @Test
    void getAirportsByCode_WithEmptyResult_ShouldReturnEmptyList() {
        // Given
        String code = "UNKNOWN";
        when(airportQuery.findAllByCode(code)).thenReturn(Collections.emptyList());

        // When
        List<AirportDetailView> result = airportService.getAirportsByCode(code);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void getAirportsByCode_WithNullCode_ShouldThrowException() {
        // When & Then
        assertThatThrownBy(() -> airportService.getAirportsByCode(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Airport code cannot be null or empty");
    }

    private List<AirportDetailView> createMockAirports() {
        AirportDetailView airport1 = new AirportDetailView();
        airport1.setFaaIdent("JFK");
        airport1.setFacilityName("John F Kennedy International Airport");

        AirportDetailView airport2 = new AirportDetailView();
        airport2.setFaaIdent("JFK2");
        airport2.setFacilityName("JFK Secondary");

        return Arrays.asList(airport1, airport2);
    }
}