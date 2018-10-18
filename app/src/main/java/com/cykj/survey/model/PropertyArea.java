package com.cykj.survey.model;

import java.io.Serializable;

public class PropertyArea implements Serializable {

    private String name;

    private int singlePoint;

    private int important;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSinglePoint() {
        return singlePoint;
    }

    public void setSinglePoint(int singlePoint) {
        this.singlePoint = singlePoint;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
