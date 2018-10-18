package com.cykj.survey.model;

import java.io.Serializable;

public class PropertyOption implements Serializable {

    private String name;

    private String area;

    private String point;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
