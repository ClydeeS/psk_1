package com.example.airport.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Flight.findAll", query = "select a from Flight as a")
})
@Table(name = "FLIGHT")
@Getter @Setter
public class Flight {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PLANE_ID", nullable = false)
    private Plane plane;

    @Basic(optional = false)
    @Column(name = "DEPARTURE_DATE", nullable = false)
    private Date departureDate;

    @Basic(optional = false)
    @Column(name = "FLIGHT_DURATION",nullable = false)
    private Integer duration;

    @Basic
    @Column(name = "DESTINATION_COUNTRY", nullable = false)
    private String destination;

    @ManyToMany
    private List<Pilot> pilots;

    @Basic
    @Column(name = "DEPARTURE_COUNTRY", nullable = false)
    private String origin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
