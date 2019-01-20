package com.cykj.survey.base.service;

import com.cykj.survey.bean.WeatherResultBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 天气接口Api
 */
public interface WeatherApiService {

    /**
     * 查询天气
     */
    @GET("index")
    Observable<WeatherResultBean> queryWeather(@Query("key") String key, @Query("cityname") String cityname);

}
