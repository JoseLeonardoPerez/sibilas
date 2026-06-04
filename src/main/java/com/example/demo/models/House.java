package com.example.demo.models;

public class House {

    private int number;
    private double degrees;
    private String sign;

    public House() {
    }

    public House(
            int number,
            double degrees,
            String sign
    ) {

        this.number = number;
        this.degrees = degrees;
        this.sign = sign;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(
            int number
    ) {

        this.number = number;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(
            double degrees
    ) {

        this.degrees = degrees;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(
            String sign
    ) {

        this.sign = sign;
    }
}