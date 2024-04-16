package com.example.airport.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Plane.findByAirline", query = "select p from Plane p where p.airline.id = :id"),
})
@Table(name = "PLANE")
@Getter @Setter
public class Plane {
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(name = "MODEL", nullable = false)
    private String model;

    @Basic(optional = false)
    @Column(name = "CAPACITY", nullable = false)
    private Integer capacity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "AIRLINE_ID", nullable = false)
    private Airline airline;

    @OneToMany(mappedBy = "plane")
    private List<Flight> flights;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Objects.equals(id, plane.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
