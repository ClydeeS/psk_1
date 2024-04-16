package com.example.airport.usecases;

import com.example.airport.entities.Airline;
import com.example.airport.entities.Pilot;
import com.example.airport.persistence.dao.PilotDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
@SessionScoped
public class Pilots implements Serializable {

    @Inject
    private PilotDAO pilotDAO;

    @Inject
    private Airlines airlines;

    @Getter
    @Setter
    private Pilot pilotToCreate = new Pilot();

    @Getter
    private List<Pilot> allSelectedPilots;

    public void loadSelectedPilots(){
        if (airlines.getSelectedAirlineId() != null) {
            this.allSelectedPilots = pilotDAO.findByAirline(airlines.getSelectedAirlineId());
        } else {
            this.allSelectedPilots = new ArrayList<>();
        }
    }

    @PostConstruct
    public void initialize() {
        loadSelectedPilots();
    }

    @Transactional
    public void createPilot() {
        if (airlines.getSelectedAirlineId() != null) {
            Airline selectedAirline = airlines.getById(airlines.getSelectedAirlineId());
            if (selectedAirline != null) {
                try {
                    pilotToCreate.setAirline(selectedAirline);
                    pilotDAO.create(pilotToCreate);
                    loadSelectedPilots();
                    pilotToCreate = new Pilot();
                }
                catch (IllegalArgumentException e)
                {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Error: Pilot identification number must be made from 5 digits.",
                                    null));
                }
            }
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error: Airline is not selected.",
                            null));
        }
    }

    @Transactional
    public void deletePilot(Pilot pilot) {
        pilotDAO.delete(pilot);
        loadSelectedPilots();
    }
}
