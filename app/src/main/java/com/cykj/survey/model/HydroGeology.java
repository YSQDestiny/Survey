package com.cykj.survey.model;

import java.io.Serializable;

public class HydroGeology implements Serializable {

    private String rainStorm;

    private String flood;

    private String low;

    private String lightning;

    private String geology;

    public String getRainStorm() {
        return rainStorm;
    }

    public void setRainStorm(String rainStorm) {
        this.rainStorm = rainStorm;
    }

    public String getFlood() {
        return flood;
    }

    public void setFlood(String flood) {
        this.flood = flood;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getLightning() {
        return lightning;
    }

    public void setLightning(String lightning) {
        this.lightning = lightning;
    }

    public String getGeology() {
        return geology;
    }

    public void setGeology(String geology) {
        this.geology = geology;
    }
}
