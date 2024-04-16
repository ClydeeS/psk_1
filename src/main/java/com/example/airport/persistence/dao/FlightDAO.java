package com.example.airport.persistence.dao;

import com.example.airport.entities.Flight;
import com.example.airport.entities.Pilot;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FlightDAO extends BaseDAO<Flight> {
    public FlightDAO() {
        super(Flight.class);
    }
}
