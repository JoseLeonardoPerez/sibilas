package com.example.demo.models;

public class PlanetDefinition {

    private String name;
    private int swissEphCode;

    public PlanetDefinition(
            String name,
            int swissEphCode
    ) {

        this.name = name;
        this.swissEphCode = swissEphCode;
    }

    public String getName() {
        return name;
    }

    public int getSwissEphCode() {
        return swissEphCode;
    }
}