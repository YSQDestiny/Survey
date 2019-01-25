package com.cykj.survey.service;

import com.cykj.survey.bean.DisasterBean;
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

    /*********************************************************工程******************************************************************************/

    @GET("project/getList")
    Observable<ResultBean> getPorjectList(@Query("uniqueId") String uniqueId);

    @POST("project/createProject")
    Observable<IDBean> createProject(@Query("name") String name,@Query("uniqueId") String uniqueId);

    @POST("project/saveProject")
    Observable<StringBean> saveProject(@Query("id") Long id, @Query("json") String json);

    @POST("project/updateProject")
    Observable<StringBean> updateProject(@Query("id") Long id,@Query("target") String target,@Query("json")String json);

    @POST("project/searchYHD")
    Observable<DisasterBean> searchYHD(@Query("json") String json);

    @POST("project/postAccident")
    Observable<StringBean> postAccident(@Query("json") String json);

    /*********************************************************物业******************************************************************************/
    @POST("property/postProperty")
    Observable<IDBean> postProperty(@Query("json") String json);

}
