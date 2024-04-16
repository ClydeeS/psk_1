package com.example.airport.usecases;

import com.example.airport.myBatis.dao.PlaneMapper;
import com.example.airport.myBatis.model.Airline;
import com.example.airport.myBatis.model.Plane;
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
public class PlanesMyBatis implements Serializable {

    @Inject
    private PlaneMapper planeMapper;

    @Inject
    private AirlinesMyBatis airlines;

    @Getter
    @Setter
    private Plane planeToCreate = new Plane();

    @Getter
    private List<Plane> allSelectedPlanes;

    public void loadSelectedPlanes(){
        if (airlines.getSelectedAirlineId() != null) {
            this.allSelectedPlanes = planeMapper.findByAirline(airlines.getSelectedAirlineId());
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
            planeToCreate.setAirlineId(airlines.getSelectedAirlineId());
            planeMapper.insert(planeToCreate);
                loadSelectedPlanes();
                planeToCreate = new Plane();
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error: Airline is not selected.",
                            null));
        }
    }

    @Transactional
    public void deletePlane(Plane plane) {
        planeMapper.deleteByPrimaryKey(plane.getId());
        loadSelectedPlanes();
    }
}

