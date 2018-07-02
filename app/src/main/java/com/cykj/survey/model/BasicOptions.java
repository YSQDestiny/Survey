package com.cykj.survey.model;

public class BasicOptions {

    private String name;
    private int imageId;
    private String insurance;

    public BasicOptions(String name,int imageId,String insurance){
        this.name = name;
        this.imageId = imageId;
        this.insurance = insurance;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getInsurance() {
        return insurance;
    }

}
