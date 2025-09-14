package com.sporty.take_home.airport.controller;

import com.sporty.take_home.airport.service.AirportService;
import com.sporty.take_home.airport.view.AirportDetailView;
import com.sporty.take_home.common.constant.ApiConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Test
    void getAirportsByCode_WithValidCode_ShouldReturnAirports() throws Exception {
        // Given
        String code = "JFK";
        List<AirportDetailView> airports = createMockAirports();
        when(airportService.getAirportsByCode(code)).thenReturn(airports);

        // When & Then
        mockMvc.perform(get(ApiConstant.AIRPORTS + ApiConstant.AIRPORT_BY_CODE, code))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].faa_ident", is("JFK")))
                .andExpect(jsonPath("$[0].facility_name", is("John F Kennedy International Airport")));
    }

    @Test
    void getAirportsByCode_WithEmptyResult_ShouldReturnEmptyArray() throws Exception {
        // Given
        String code = "UNKNOWN";
        when(airportService.getAirportsByCode(code)).thenReturn(Collections.emptyList());

        // When & Then
        mockMvc.perform(get(ApiConstant.AIRPORTS + ApiConstant.AIRPORT_BY_CODE, code))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    private List<AirportDetailView> createMockAirports() {
        AirportDetailView airport1 = new AirportDetailView();
        airport1.setFaaIdent("JFK");
        airport1.setFacilityName("John F Kennedy International Airport");
        airport1.setCity("New York");
        airport1.setState("NY");

        AirportDetailView airport2 = new AirportDetailView();
        airport2.setFaaIdent("JFK2");
        airport2.setFacilityName("JFK Secondary");
        airport2.setCity("New York");
        airport2.setState("NY");

        return Arrays.asList(airport1, airport2);
    }
}