package com.example.airport.usecases;

import com.example.airport.entities.Airline;
import com.example.airport.entities.Flight;
import com.example.airport.entities.Pilot;
import com.example.airport.entities.Plane;
import com.example.airport.persistence.dao.AirlineDAO;
import com.example.airport.persistence.dao.FlightDAO;
import com.example.airport.persistence.dao.PilotDAO;
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
public class Flights implements Serializable{
    @Inject
    private FlightDAO flightDAO;

    @Inject
    private AirlineDAO airlineDAO;

    @Inject
    private PlaneDAO planeDAO;

    @Inject
    private PilotDAO pilotDAO;

    @Getter @Setter
    private Flight flightToCreate = new Flight();

    @Getter @Setter
    private Long selectedAirlineId;

    @Getter
    private List<Plane> availablePlanes;

    @Getter
    private List<Pilot> availablePilots;

    @Getter
    private List<Flight> allFlights;

    private List<Airline> allAirlines;

    @Getter @Setter
    private Long selectedPlaneId;

    public void loadAllFlights(){
        this.allFlights = flightDAO.findAll();
    }

    @PostConstruct
    public void initialize() {
        loadAllFlights();
    }

    public List<Airline> getAllAirlines() {
        return airlineDAO.findAll();
    }

    public void onAirlineChange() {
        if (selectedAirlineId != null) {
            availablePlanes = planeDAO.findByAirline(selectedAirlineId);
            availablePilots = pilotDAO.findByAirline(selectedAirlineId);
        } else {
            availablePlanes = new ArrayList<>();
            availablePilots = new ArrayList<>();
        }
    }

    public void onPlaneChange() {
        if (selectedPlaneId != null && !selectedPlaneId.equals(0L)) {
            Plane selectedPlane = planeDAO.findById(selectedPlaneId);
            flightToCreate.setPlane(selectedPlane);
        } else {
            flightToCreate.setPlane(null);
        }
    }

    @Transactional
    public void createFlight() {
        try {
            flightDAO.create(flightToCreate);
            flightToCreate = new Flight();
            loadAllFlights();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error: " + e,
                            null));
        }
    }

    @Transactional
    public void deleteFlight(Flight flight) {
        flightDAO.delete(flight);
        loadAllFlights();
    }
}
