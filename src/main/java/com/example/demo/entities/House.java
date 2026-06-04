package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "houses")
public class House {

    @Id
    private Long id;

    private Integer number;

    private String keywords;

    @Column(name = "light_aspect")
    private String lightAspect;

    @Column(name = "shadow_aspect")
    private String shadowAspect;

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getLightAspect() {
        return lightAspect;
    }

    public String getShadowAspect() {
        return shadowAspect;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setLightAspect(String lightAspect) {
        this.lightAspect = lightAspect;
    }

    public void setShadowAspect(String shadowAspect) {
        this.shadowAspect = shadowAspect;
    }
}