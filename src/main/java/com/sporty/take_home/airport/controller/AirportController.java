package com.sporty.take_home.airport.controller;

import com.sporty.take_home.airport.service.AirportService;
import com.sporty.take_home.airport.view.AirportDetailView;
import com.sporty.take_home.common.constant.ApiConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.AIRPORTS)
@RequiredArgsConstructor
@Tag(name = "Airport API", description = "Airport information endpoints")
public class AirportController {

    private final AirportService airportService;

    @GetMapping(ApiConstant.AIRPORT_BY_CODE)
    @Operation(summary = "Get airports by code", description = "Retrieve airport information by airport code")
    public ResponseEntity<List<AirportDetailView>> getAirportsByCode(
            @Parameter(description = "Airport code", example = "JFK")
            @PathVariable String code) {
        
        List<AirportDetailView> airports = airportService.getAirportsByCode(code);
        return ResponseEntity.ok(airports);
    }
}