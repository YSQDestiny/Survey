package com.cykj.survey.base.config;

import com.cykj.survey.base.RestApiAdapter;
import com.cykj.survey.interactor.ResultInteractor;
import com.cykj.survey.interactor.impl.ResultInteractorImpl;
import com.cykj.survey.service.ResultService;
import com.cykj.survey.service.WeatherApiService;
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

    @Provides
    public ResultService provideResultApi(Retrofit restAdapter){
        return restAdapter.create(ResultService.class);
    }

    @Provides
    public ResultInteractor provideResultInteractor(ResultService myApi){
        return new ResultInteractorImpl(myApi);
    }


}
