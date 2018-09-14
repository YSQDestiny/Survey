package com.cykj.survey.model;

import java.util.List;

public class WeatherModel {

    public List<WeatherInfo> weatherInfoList;

    public List<WeatherInfo> getWeatherInfoList() {
        return weatherInfoList;
    }

    public void setWeatherInfoList(List<WeatherInfo> weatherInfoList) {
        this.weatherInfoList = weatherInfoList;
    }
}
