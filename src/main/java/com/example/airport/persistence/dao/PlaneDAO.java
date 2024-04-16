package com.example.airport.persistence.dao;

import com.example.airport.entities.Airline;
import com.example.airport.entities.Plane;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlaneDAO extends BaseDAO<Plane> {
    public PlaneDAO() {
        super(Plane.class);
    }

    public List<Plane> findByAirline(Long id) {
        return em.createNamedQuery("Plane.findByAirline", Plane.class)
                .setParameter("id", id)
                .getResultList();
    }
}
