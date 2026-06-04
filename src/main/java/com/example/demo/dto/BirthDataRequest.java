package com.example.demo.dto;


public class BirthDataRequest {

    private String birthDate;
    private String birthTime;
    private String city;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private Double currentLatitude;

    private Double currentLongitude;

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(
            Double currentLatitude
    ) {
        this.currentLatitude = currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(
            Double currentLongitude
    ) {
        this.currentLongitude = currentLongitude;
    }


}