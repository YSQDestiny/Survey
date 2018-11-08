package com.cykj.survey.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class HydroImage implements Serializable {

    private String name;

    private Bitmap img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
