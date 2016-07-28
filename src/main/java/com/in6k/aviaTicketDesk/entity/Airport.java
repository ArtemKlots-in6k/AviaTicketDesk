package com.in6k.aviaTicketDesk.entity;

import javax.persistence.*;

/**
 * Created by employee on 7/28/16.
 */
@Entity
@Table(name = "airports")
public class Airport {
    private Integer id;
    private String title;
    private City city;

    public Airport() {
    }

    public Airport(String title, City city) {
        this.title = title;
        this.city = city;
    }

    public Airport(Integer id, String title, City city) {
        this(title, city);
        this.city = city;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    @JoinColumn(name = "city_id")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", city=" + city +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (title != null ? !title.equals(airport.title) : airport.title != null) return false;
        return city != null ? city.equals(airport.city) : airport.city == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
