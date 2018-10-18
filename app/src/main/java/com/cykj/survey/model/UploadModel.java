package com.cykj.survey.model;

import java.io.Serializable;

public class UploadModel implements Serializable {

    private String target;

    private String json;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
