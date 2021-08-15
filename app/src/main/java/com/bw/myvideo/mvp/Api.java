package com.bw.myvideo.mvp;

import com.bw.myvideo.entity.VideoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public
/**
 *MyVideo
 *file name is : Api
 *created by Ender on 2021/8/14 8:29.
 *author : 王益德
 *Describe:
 */
interface Api {

    public final String VideoUrl = "http://39.98.153.96:8088/zytestapi/video/";

    @GET("findVideos")
    Observable<VideoBean> getVideo(@Query("pageSize")int item,@Query("currentPage")int page);

}
