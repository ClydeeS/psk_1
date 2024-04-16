package com.example.airport.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Entity
@NamedQueries({
        @NamedQuery(name = "Pilot.findByAirline", query = "select p from Pilot p where p.airline.id = :id"),
})
@Table(name = "PILOT")
@Getter @Setter
public class Pilot {
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(name = "IDENTIFICATION_NUMBER", nullable = false)
    private String identificationNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "AIRLINE_ID", nullable = false)
    private Airline airline;

    @Basic(optional = false)
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Basic(optional = false)
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @PrePersist
    @PreUpdate
    private void validateIdentificationNumber() {
        if (!Pattern.matches("^\\d{5}$", identificationNumber)) {
            throw new IllegalArgumentException("Invalid identification number format");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilot pilot = (Pilot) o;
        return Objects.equals(id, pilot.id) &&
                Objects.equals(identificationNumber, pilot.identificationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificationNumber);
    }

    @ManyToMany(mappedBy = "pilots")
    private List<Flight> flights;
}
