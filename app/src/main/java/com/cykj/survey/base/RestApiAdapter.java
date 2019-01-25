package com.cykj.survey.base;

import com.cykj.survey.base.api.ConstantApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null){
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
            OkHttpClient okHttpClient = new OkHttpClient();
            OkHttpClient.Builder builder = okHttpClient.newBuilder();
            builder.retryOnConnectionFailure(true);

            retrofit = new Retrofit.Builder().client(okHttpClient)
                    .baseUrl(ConstantApi.SERVICE)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
