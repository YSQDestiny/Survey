package com.cykj.survey.model;

import java.io.Serializable;

public class HydroImageModel implements Serializable {

    private String name;

    private String img;

    private long hydroID;

    public HydroImageModel(String name , String img, long hydroID){
        this.name = name;
        this.img = img;
        this.hydroID = hydroID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getHydroID() {
        return hydroID;
    }

    public void setHydroID(long hydroID) {
        this.hydroID = hydroID;
    }
}
