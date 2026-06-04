package com.example.demo.models;

import java.util.List;
import java.util.List;
public class NatalChart {

    private String aiInterpretation;
    private List<PlanetPosition> planets;

    public String getAiInterpretation() {
        return aiInterpretation;
    }

    public void setAiInterpretation(String aiInterpretation) {
        this.aiInterpretation = aiInterpretation;
    }

    public NatalChart() {
    }

    public List<PlanetPosition> getPlanets() {
        return planets;
    }

    private String ascendant;
    private List<House> houses;
    private List<Aspect> aspects;
    private List<Interpretation> interpretations;

    private List<String> symbolicContext;

    public List<String> getSymbolicContext() {
        return symbolicContext;
    }

    public void setSymbolicContext(
            List<String> symbolicContext
    ) {
        this.symbolicContext = symbolicContext;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(
            List<House> houses
    ) {

        this.houses = houses;
    }

    public List<Aspect> getAspects() {
        return aspects;
    }

    public void setAspects(
            List<Aspect> aspects
    ) {

        this.aspects = aspects;
    }

    public List<Interpretation> getInterpretations() {
        return interpretations;
    }

    public void setInterpretations(
            List<Interpretation> interpretations
    ) {

        this.interpretations = interpretations;
    }

    public String getAscendant() {
        return ascendant;
    }

    public void setAscendant(
            String ascendant
    ) {

        this.ascendant = ascendant;
    }

    public void setPlanets(List<PlanetPosition> planets) {
        this.planets = planets;
    }
}