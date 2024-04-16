package com.example.airport.usecases;

import com.example.airport.entities.Airline;
import com.example.airport.entities.Plane;
import com.example.airport.persistence.dao.PlaneDAO;
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
public class Planes implements Serializable {

    @Inject
    private PlaneDAO planeDAO;

    @Inject
    private Airlines airlines;

    @Getter
    @Setter
    private Plane planeToCreate = new Plane();

    @Getter
    private List<Plane> allSelectedPlanes;

    public void loadSelectedPlanes(){
        if (airlines.getSelectedAirlineId() != null) {
            this.allSelectedPlanes = planeDAO.findByAirline(airlines.getSelectedAirlineId());
        } else {
            this.allSelectedPlanes = new ArrayList<>();
        }
    }

    @PostConstruct
    public void initialize() {
        loadSelectedPlanes();
    }

    @Transactional
    public void createPlane() {
        if (airlines.getSelectedAirlineId() != null) {
            Airline selectedAirline = airlines.getById(airlines.getSelectedAirlineId());
            if (selectedAirline != null) {
                planeToCreate.setAirline(selectedAirline);
                planeDAO.create(planeToCreate);
                loadSelectedPlanes();

                airlines.refreshAirline();
                planeToCreate = new Plane();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error: Airline is not selected.",
                            null));
        }
    }

    @Transactional
    public void deletePlane(Plane plane) {
        planeDAO.delete(plane);
        loadSelectedPlanes();
    }
}
