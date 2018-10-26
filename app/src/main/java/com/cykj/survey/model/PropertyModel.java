package com.cykj.survey.model;

import java.util.List;

public class PropertyModel {

    private Property property;

    private List<PropertyAccident> accidentList;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<PropertyAccident> getAccidentList() {
        return accidentList;
    }

    public void setAccidentList(List<PropertyAccident> accidentList) {
        this.accidentList = accidentList;
    }
}
