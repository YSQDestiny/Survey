package com.cykj.survey.model;

public class Options {

    int name;
    int imageId;

    public Options(int name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public int getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
