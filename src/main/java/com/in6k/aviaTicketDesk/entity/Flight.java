package com.in6k.aviaTicketDesk.entity;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @Column(name = "aircraft_capacity")
    public int getAircraftCapacity() {
        return aircraftCapacity;
    }

    public void setAircraftCapacity(int aircraftCapacity) {
        this.aircraftCapacity = aircraftCapacity;
    }

    @Column(name = "free_seats")
    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    @Column(name = "departure_date_time")
    public Timestamp getDepartureDateTime() {
        return Timestamp.valueOf(departureDateTime);
    }

    public void setDepartureDateTime(Timestamp departureDateTime) {
        this.departureDateTime = departureDateTime.toLocalDateTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (aircraftCapacity != flight.aircraftCapacity) return false;
        if (freeSeats != flight.freeSeats) return false;
        if (departureAirport != null ? !departureAirport.equals(flight.departureAirport) : flight.departureAirport != null)
            return false;
        if (destinationAirport != null ? !destinationAirport.equals(flight.destinationAirport) : flight.destinationAirport != null)
            return false;
        return departureDateTime != null ? departureDateTime.equals(flight.departureDateTime) : flight.departureDateTime == null;

    }

    @Override
    public int hashCode() {
        int result = departureAirport != null ? departureAirport.hashCode() : 0;
        result = 31 * result + (destinationAirport != null ? destinationAirport.hashCode() : 0);
        result = 31 * result + aircraftCapacity;
        result = 31 * result + freeSeats;
        result = 31 * result + (departureDateTime != null ? departureDateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", departureAirport=" + departureAirport +
                ", destinationAirport=" + destinationAirport +
                ", aircraftCapacity=" + aircraftCapacity +
                ", freeSeats=" + freeSeats +
                ", departureDateTime=" + departureDateTime +
                '}';
    }
}
