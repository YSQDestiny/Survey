package com.cykj.survey.model;

import java.io.Serializable;

public class Disaster implements Serializable {

    private String city;
    private String county;
    private String hiddenDanger;
    private String type;
    private String town;
    private String village;
    private String group;
    private int threatProperty;
    private int threatHouseholds;
    private int threatPeople;
    private String isPlan;
    private String isIssue;
    private String isBuild;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getHiddenDanger() {
        return hiddenDanger;
    }

    public void setHiddenDanger(String hiddenDanger) {
        this.hiddenDanger = hiddenDanger;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getThreatProperty() {
        return threatProperty;
    }

    public void setThreatProperty(int threatProperty) {
        this.threatProperty = threatProperty;
    }

    public int getThreatHouseholds() {
        return threatHouseholds;
    }

    public void setThreatHouseholds(int threatHouseholds) {
        this.threatHouseholds = threatHouseholds;
    }

    public int getThreatPeople() {
        return threatPeople;
    }

    public void setThreatPeople(int threatPeople) {
        this.threatPeople = threatPeople;
    }

    public String getIsPlan() {
        return isPlan;
    }

    public void setIsPlan(String isPlan) {
        this.isPlan = isPlan;
    }

    public String getIsIssue() {
        return isIssue;
    }

    public void setIsIssue(String isIssue) {
        this.isIssue = isIssue;
    }

    public String getIsBuild() {
        return isBuild;
    }

    public void setIsBuild(String isBuild) {
        this.isBuild = isBuild;
    }
}
