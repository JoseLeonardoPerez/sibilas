package com.example.demo.dto;

import java.util.List;

public class AiInterpretationRequest {

    private List<String> symbolicData;

    public List<String> getSymbolicData() {
        return symbolicData;
    }

    public void setSymbolicData(List<String> symbolicData) {
        this.symbolicData = symbolicData;
    }
}
