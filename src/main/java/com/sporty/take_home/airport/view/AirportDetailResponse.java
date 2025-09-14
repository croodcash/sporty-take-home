package com.sporty.take_home.airport.view;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportDetailResponse {

    private final Map<String, List<AirportDetailView>> airports = new HashMap<>();

    @JsonAnySetter
    public void addAirport(String code, List<AirportDetailView> info) {
        airports.put(code, info);
    }

    public Map<String, List<AirportDetailView>> getAirports() {
        return airports;
    }
}