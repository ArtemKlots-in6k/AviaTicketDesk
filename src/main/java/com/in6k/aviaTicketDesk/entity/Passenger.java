package com.in6k.aviaTicketDesk.entity;

import javax.persistence.*;

/**
 * Created by employee on 7/29/16.
 */
@Entity
@Table(name = "passengers")
public class Passenger {
    private Integer id;
    private String name;

    public Passenger() {
    }

    public Passenger(String name) {
        this.name = name;
    }

    public Passenger(int id, String name) {
        this(name);
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

    @Column
    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        return name != null ? name.equals(passenger.name) : passenger.name == null;
    }
}
