package com.sporty.take_home.airport.config;

import com.sporty.take_home.common.tools.SingleFlight;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AirportSingleFlight {

    public enum Key{
        AIRPORT_DETAIL
    }

    @Bean("airportSingleFlight")
    public SingleFlight airportSingleFlight(){
        return new SingleFlight();
    }

}
