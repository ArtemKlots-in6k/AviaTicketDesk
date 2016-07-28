package com.in6k.aviaTicketDesk.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by employee on 7/28/16.
 */
@Entity
@Table(name = "cities")
public class City {
    private Integer id;
    private String title;

    public City() {
    }

    public City(String title) {
        this.title = title;
    }

    public City(int id, String title) {
        this(title);
        this.id = id;
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

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return title != null ? title.equals(city.title) : city.title == null;
    }
}
