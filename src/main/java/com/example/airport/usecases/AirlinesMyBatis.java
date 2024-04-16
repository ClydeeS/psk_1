package com.example.airport.usecases;

import com.example.airport.myBatis.dao.AirlineMapper;
import com.example.airport.myBatis.model.Airline;
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
import java.util.List;

@Model
@SessionScoped
public class AirlinesMyBatis implements Serializable {

    @Inject
    private AirlineMapper airlineMapper;

    @Getter @Setter
    private Long selectedAirlineId;

    @Getter @Setter
    private Airline airlineToCreate = new Airline();

    @Getter
    private List<Airline> allAirlines;

    public void loadAllAirlines(){
        this.allAirlines = airlineMapper.selectAll();
    }

    @PostConstruct
    public void initialize() {
        loadAllAirlines();
    }

    @Transactional
    public void createAirline() {
        try {
            this.airlineMapper.insert(airlineToCreate);
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
        airlineMapper.deleteByPrimaryKey(airline.getId());
        loadAllAirlines();
    }

    public Airline getById(Long id) {
        return airlineMapper.selectByPrimaryKey(id);
    }

    @Inject
    private PlanesMyBatis planes;

    @Inject
    private PilotsMyBatis pilots;

    public void onAirlineSelected() {
        if (selectedAirlineId != null) {
            planes.loadSelectedPlanes();
            pilots.loadSelectedPilots();
        }
    }
}
