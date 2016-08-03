package com.in6k.aviaTicketDesk.form;

/**
 * Created by employee on 8/3/16.
 */
public class BuyTicketForm {
    private String passengerName;
    private int numberOfTickets;
    private int flight;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int number) {
        this.numberOfTickets = number;
    }

    public int getFlight() {
        return flight;
    }

    public void setFlight(int flight) {
        this.flight = flight;
    }


}
