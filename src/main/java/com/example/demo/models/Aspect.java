package com.example.demo.models;

public class Aspect {

    private String planet1;
    private String planet2;
    private String type;
    private double angle;

    public Aspect() {
    }

    public Aspect(
            String planet1,
            String planet2,
            String type,
            double angle
    ) {

        this.planet1 = planet1;
        this.planet2 = planet2;
        this.type = type;
        this.angle = angle;
    }

    public String getPlanet1() {
        return planet1;
    }

    public void setPlanet1(
            String planet1
    ) {

        this.planet1 = planet1;
    }

    public String getPlanet2() {
        return planet2;
    }

    public void setPlanet2(
            String planet2
    ) {

        this.planet2 = planet2;
    }

    public String getType() {
        return type;
    }

    public void setType(
            String type
    ) {

        this.type = type;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(
            double angle
    ) {

        this.angle = angle;
    }
}