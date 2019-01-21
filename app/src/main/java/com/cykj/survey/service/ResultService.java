package com.cykj.survey.service;

import com.cykj.survey.bean.IDBean;
import com.cykj.survey.bean.ResultBean;
import com.cykj.survey.bean.StringBean;
import com.cykj.survey.model.ProjectModel;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ResultService {

    @GET("project/getList")
    Observable<ResultBean> getPorjectList(@Query("uniqueId") String uniqueId);

    @POST("project/createProject")
    Observable<IDBean> createProject(@Query("name") String name,@Query("uniqueId") String uniqueId);

    @POST("project/saveProject")
    Observable<StringBean> saveProject(@Query("id") Long id, @Body ProjectModel project);
}
