package com.cykj.survey.base.config;

import com.cykj.survey.base.RestApiAdapter;
import com.cykj.survey.base.service.WeatherApiService;
import com.cykj.survey.interactor.WeatherInteractor;
import com.cykj.survey.interactor.impl.WeatherInteractorImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Module类
 * 提供需要注入的类
 */
@Module
public class InteractorModule {

    @Provides
    public Retrofit provideRestAdapter() {
        return RestApiAdapter.getInstance();
    }

    @Provides
    public WeatherApiService provideHomeApi(Retrofit restAdapter){
        return restAdapter.create(WeatherApiService.class);
    }

    @Provides
    public WeatherInteractor provideHomeInteractor(WeatherApiService myApi){
        return new WeatherInteractorImpl(myApi);
    }
}
