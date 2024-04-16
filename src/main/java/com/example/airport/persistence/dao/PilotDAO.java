package com.example.airport.persistence.dao;

import com.example.airport.entities.Pilot;
import com.example.airport.entities.Plane;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PilotDAO extends BaseDAO<Pilot> {
    public PilotDAO() {
        super(Pilot.class);
    }

    public List<Pilot> findByAirline(Long id) {
        return em.createNamedQuery("Pilot.findByAirline", Pilot.class)
                .setParameter("id", id)
                .getResultList();
    }
}
