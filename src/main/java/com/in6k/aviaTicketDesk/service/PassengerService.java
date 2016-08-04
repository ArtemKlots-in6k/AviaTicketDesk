package com.in6k.aviaTicketDesk.service;

import com.in6k.aviaTicketDesk.dao.PassengerDAO;
import com.in6k.aviaTicketDesk.entity.Passenger;

import java.util.List;

/**
 * Created by employee on 8/3/16.
 */
public class PassengerService {

    private PassengerDAO passengerDAO;

    public void setPassengerDAO(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    public PassengerDAO getPassengerDAO() {
        return passengerDAO;
    }

    public List getAll() {
        return passengerDAO.getAll();
    }

    public Passenger getByName(String name) {
        return passengerDAO.getByName(name);
    }

    public Passenger getById(int id) {
        return passengerDAO.getById(id);
    }

    public boolean isThisPassengerExist(String name) {
        try {
            return passengerDAO.getByName(name) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public Passenger create(String name) {
        return passengerDAO.create(name);
    }
}
