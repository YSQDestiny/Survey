package com.cykj.survey.base.config;

import com.cykj.survey.SurveyApplication;
import com.cykj.survey.interactor.ResultInteractor;
import com.cykj.survey.interactor.WeatherInteractor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                InteractorModule.class,
        }
)
public interface AppComponent {

    void inject(SurveyApplication app);

    WeatherInteractor getWeatherInteractor();

    ResultInteractor getResultInteractor();

}
