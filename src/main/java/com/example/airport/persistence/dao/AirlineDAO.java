package com.example.airport.persistence.dao;

import com.example.airport.entities.Airline;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AirlineDAO extends BaseDAO<Airline> {
    public AirlineDAO() {
        super(Airline.class);
    }

    public int getPlaneCount(Long airlineId) {
        // Assuming you have an EntityManager injected in your DAO
        return ((Number) em.createNamedQuery("Airline.getPlaneCount")
                .setParameter("airlineId", airlineId)
                .getSingleResult()).intValue();
    }
}
