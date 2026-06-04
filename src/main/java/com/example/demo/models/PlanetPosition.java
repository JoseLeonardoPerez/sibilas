package com.example.demo.models;

public class PlanetPosition {

    private String planet;
    private double degrees;
    private String sign;
    private int house;
    private boolean retrograde;

    public PlanetPosition() {
    }

    public PlanetPosition(String planet, double degrees, String sign) {
        this.planet = planet;
        this.degrees = degrees;
        this.sign = sign;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getHouse() {
        return house;
    }


    public void setHouse(
            int house
    ) {

        this.house = house;
    }

    public boolean isRetrograde() {
        return retrograde;
    }

    public void setRetrograde(
            boolean retrograde
    ) {
        this.retrograde = retrograde;
    }

}
