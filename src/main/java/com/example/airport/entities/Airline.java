package com.example.airport.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Airline.findAll", query = "select a from Airline as a"),
})
@Table(name = "AIRLINE")
@Getter @Setter
public class Airline {
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(unique = true, name = "NAME", nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @OneToMany(mappedBy = "airline", fetch = FetchType.EAGER)
    private List<Plane> planes;

    @OneToMany(mappedBy = "airline")
    private List<Pilot> pilots;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return Objects.equals(name, airline.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
