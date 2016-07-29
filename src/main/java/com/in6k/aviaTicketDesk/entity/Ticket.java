package com.in6k.aviaTicketDesk.entity;

import javax.persistence.*;

/**
 * Created by employee on 7/29/16.
 */
@Entity
@Table(name = "tickets")
public class Ticket {
    private Integer id;
    private Flight flight;
    private Passenger passenger;

    public Ticket() {
    }

    public Ticket(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
    }

    public Ticket(Integer id, Flight flight, Passenger passenger) {
        this(flight, passenger);
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
    @JoinColumn(name = "flight_id")
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (flight != null ? !flight.equals(ticket.flight) : ticket.flight != null) return false;
        return passenger != null ? passenger.equals(ticket.passenger) : ticket.passenger == null;

    }

    @Override
    public int hashCode() {
        int result = flight != null ? flight.hashCode() : 0;
        result = 31 * result + (passenger != null ? passenger.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flight.id=" + flight.getId() +
                ", passenger=" + passenger +
                '}';
    }
}
