package com.example.airport.usecases;

import com.example.airport.entities.Airline;
import com.example.airport.persistence.dao.AirlineDAO;
import com.example.airport.persistence.dao.PlaneDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model
@SessionScoped
public class Airlines implements Serializable {

    @Inject
    private AirlineDAO airlineDAO;

    @Getter @Setter
    private Long selectedAirlineId;

    @Getter @Setter
    private Airline airlineToCreate = new Airline();

    @Getter
    private List<Airline> allAirlines;

//    @Getter
//    private Map<Long, Integer> planeCount = new HashMap<>();

    public void loadAllAirlines(){
        this.allAirlines = airlineDAO.findAll();
    }

    @PostConstruct
    public void initialize() {
        loadAllAirlines();
    }

    @Transactional
    public void createAirline() {
        try {
            this.airlineDAO.create(airlineToCreate);
            loadAllAirlines();
            airlineToCreate = new Airline();
        } catch (PersistenceException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error: Airline name must be unique.",
                            null));
        }
    }

    @Transactional
    public void deleteAirline(Airline airline) {
            airlineDAO.delete(airline);
            loadAllAirlines();
    }

    public Airline getById(Long id) {
        return airlineDAO.findById(id);
    }

    @Inject
    private Planes planes;

    @Inject
    private Pilots pilots;

    public void onAirlineSelected() {
        if (selectedAirlineId != null) {
            planes.loadSelectedPlanes();
            pilots.loadSelectedPilots();
        }
    }

    public void refreshAirline()
    {
        loadAllAirlines();
    }
}
