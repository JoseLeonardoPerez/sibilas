package com.example.demo.models;

public class OracleResponse {

    private String interpretation;

    public OracleResponse() {
    }

    public OracleResponse(String interpretation) {
        this.interpretation = interpretation;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
}