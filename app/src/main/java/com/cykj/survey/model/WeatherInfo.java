package com.cykj.survey.model;

public class WeatherInfo {

    private String name;

    private String highWeather;

    private String lowWeather;

    private String rainfall;

    public String getHighWeather() {
        return highWeather;
    }

    public void setHighWeather(String highWeather) {
        this.highWeather = highWeather;
    }

    public String getLowWeather() {
        return lowWeather;
    }

    public void setLowWeather(String lowWeather) {
        this.lowWeather = lowWeather;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
