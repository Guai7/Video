package com.bw.myvideo.mvp.utils;

import com.bw.myvideo.mvp.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public
/**
 *MyVideo
 *file name is : HttpUtils
 *created by Ender on 2021/8/14 8:36.
 *author : 王益德
 *Describe:
 */
class HttpUtils {
    private Retrofit retrofit;
    private static HttpUtils utils;

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        if (utils ==null){
            synchronized (HttpUtils.class){
                if (utils == null){
                    utils = new HttpUtils();
                }
            }
        }
        return utils;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.VideoUrl)
                    .client(new OkHttpClient.Builder()
                            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
