package com.cykj.survey.interactor.impl;

import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.base.service.WeatherApiService;
import com.cykj.survey.bean.WeatherResultBean;
import com.cykj.survey.interactor.WeatherInteractor;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherInteractorImpl implements WeatherInteractor {

    private final WeatherApiService api;

    @Inject
    public WeatherInteractorImpl(WeatherApiService api){
        this.api = api;
    }

    @Override
    public Subscription queryWeather(String apikey, String cityname, BaseSubsribe<WeatherResultBean> subsribe) {
        Observable<WeatherResultBean> observable = api.queryWeather(apikey,cityname);
        Subscription subscribe = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscribe;
    }

}
