package com.in6k.aviaTicketDesk.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by employee on 7/28/16.
 */
@Entity
@Table(name = "flights")
public class Flight {
    private Integer id;
    private Airport departureAirport;
    private Airport destinationAirport;
    private int aircraftCapacity;
    private int freeSeats;
    private LocalDateTime departureDateTime;

    public Flight() {
    }

    public Flight(Airport departureAirport,
                  Airport destinationAirport,
                  int aircraftCapacity,
                  LocalDateTime departureDateTime) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.aircraftCapacity = aircraftCapacity;
        this.departureDateTime = departureDateTime;
        freeSeats = aircraftCapacity;
    }

    public Flight(int id,
                  Airport departureAirport,
                  Airport destinationAirport,
                  int aircraftCapacity,
                  LocalDateTime departureDateTime) {
        this(departureAirport, destinationAirport, aircraftCapacity, departureDateTime);
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    @Column
    public int getAircraftCapacity() {
        return aircraftCapacity;
    }

    public void setAircraftCapacity(int aircraftCapacity) {
        this.aircraftCapacity = aircraftCapacity;
    }

    @Column
    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    @Column
    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
}
