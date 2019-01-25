package com.cykj.survey.interactor.impl;

import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.bean.DisasterBean;
import com.cykj.survey.bean.IDBean;
import com.cykj.survey.bean.ResultBean;
import com.cykj.survey.bean.StringBean;
import com.cykj.survey.interactor.ResultInteractor;
import com.cykj.survey.model.ProjectModel;
import com.cykj.survey.service.ResultService;

import java.util.HashMap;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ResultInteractorImpl implements ResultInteractor {

    private final ResultService api;

    @Inject
    public ResultInteractorImpl(ResultService myApi){
        this.api = myApi;
    }

    @Override
    public Subscription getProjectList(String uniqueId, BaseSubsribe<ResultBean> subsribe) {
        Observable<ResultBean> observable = api.getPorjectList(uniqueId);
        Subscription subscribe = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscribe;
    }

    @Override
    public Subscription createProject(String name,String uniqueId, BaseSubsribe<IDBean> subsribe) {
        Observable<IDBean> observable = api.createProject(name,uniqueId);
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscription;
    }

    @Override
    public Subscription saveProject(Long id, String json, BaseSubsribe<StringBean> subsribe) {
        Observable<StringBean> observable = api.saveProject(id,json);
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscription;
    }

    @Override
    public Subscription updateProject(Long id, String target, String json, BaseSubsribe<StringBean> subsribe) {
        Observable<StringBean> observable = api.updateProject(id,target,json);
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscription;
    }

    @Override
    public Subscription searchYHD(String json, BaseSubsribe<DisasterBean> subsribe) {
        Observable<DisasterBean> observable = api.searchYHD(json);
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscription;
    }

    @Override
    public Subscription postAccident(String json, BaseSubsribe<StringBean> subsribe) {
        Observable<StringBean> observable = api.postAccident(json);
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscription;
    }

    @Override
    public Subscription postProperty(String josn, BaseSubsribe<IDBean> subsribe) {
        Observable<IDBean> observable = api.postProperty(josn);
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscription;
    }
}
