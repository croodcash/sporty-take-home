package com.sporty.take_home.airport.query;

import com.sporty.take_home.airport.view.AirportDetailView;

import java.util.List;

public interface AirportQuery {

    public List<AirportDetailView> findAllByCode(String code);
}
