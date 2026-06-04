package com.example.demo.models;

public class TransitAspect {

    private String transitPlanet;

    private String natalPlanet;

    private String aspect;

    private double orb;

    public TransitAspect() {
    }

    public String getTransitPlanet() {
        return transitPlanet;
    }

    public void setTransitPlanet(
            String transitPlanet
    ) {
        this.transitPlanet = transitPlanet;
    }

    public String getNatalPlanet() {
        return natalPlanet;
    }

    public void setNatalPlanet(
            String natalPlanet
    ) {
        this.natalPlanet = natalPlanet;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(
            String aspect
    ) {
        this.aspect = aspect;
    }

    public double getOrb() {
        return orb;
    }

    public void setOrb(
            double orb
    ) {
        this.orb = orb;
    }
}
