package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "planet_house_meanings")
public class PlanetHouseMeaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet planet;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @Column(columnDefinition = "TEXT")
    private String meaning;

    public PlanetHouseMeaning() {
    }

    public Long getId() {
        return id;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}