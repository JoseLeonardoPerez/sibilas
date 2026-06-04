package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "planet_sign_meanings")
public class PlanetSignMeaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet planet;

    @ManyToOne
    @JoinColumn(name = "sign_id")
    private Sign sign;

    @Column(columnDefinition = "TEXT")
    private String meaning;

    public PlanetSignMeaning() {
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

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}