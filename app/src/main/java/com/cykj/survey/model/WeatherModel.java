package com.cykj.survey.model;

import java.util.List;

public class WeatherModel {

    public List<WeatherData> weatherDataList;

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }
}
