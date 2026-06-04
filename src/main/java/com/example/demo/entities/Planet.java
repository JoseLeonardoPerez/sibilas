package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "planets")
public class Planet {

    @Id
    private Long id;

    private String name;

    private String keywords;

    @Column(name = "light_aspect")
    private String lightAspect;

    @Column(name = "shadow_aspect")
    private String shadowAspect;

    @Column(name = "expression_high")
    private String expressionHigh;

    @Column(name = "expression_low")
    private String expressionLow;

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getKeywords() { return keywords; }

    public String getLightAspect() { return lightAspect; }

    public String getShadowAspect() { return shadowAspect; }

    public String getExpressionHigh() { return expressionHigh; }

    public String getExpressionLow() { return expressionLow; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setKeywords(String keywords) { this.keywords = keywords; }

    public void setLightAspect(String lightAspect) { this.lightAspect = lightAspect; }

    public void setShadowAspect(String shadowAspect) { this.shadowAspect = shadowAspect; }

    public void setExpressionHigh(String expressionHigh) { this.expressionHigh = expressionHigh; }

    public void setExpressionLow(String expressionLow) { this.expressionLow = expressionLow; }
}