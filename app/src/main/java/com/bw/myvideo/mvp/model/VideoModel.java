package com.bw.myvideo.mvp.model;

import com.bw.mvp_lib.model.BaseModel;
import com.bw.myvideo.entity.VideoBean;
import com.bw.myvideo.mvp.Api;
import com.bw.myvideo.mvp.contract.IContract;
import com.bw.myvideo.mvp.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public
/**
 *MyVideo
 *file name is : VideoModel
 *created by Ender on 202Enumerating objects: 97, done.
Counting objects: 100% (97/97), done.
Delta compression using up to 12 threads
Compressing objects: 100% (78/78), done.
error: RPC failed; curl 56 Send failure: Connection was reset
fatal: the remote end hung up unexpectedly
Writing objects: 100% (96/96), 40.97 MiB | 1.24 MiB/s, done.
Total 96 (delta 5), reused 0 (delta 0), pack-reused 0
fatal: the remote end hung up unexpectedly
Everything up-to-date1/8/14 8:43.
 *author : 王益德
 *Describe:
 */
class VideoModel extends BaseModel implements IContract.IVideoModel {
    @Override
    public void getVideo(int item,int page,Observer<VideoBean> observer) {
        HttpUtils.getInstance()
                .getRetrofit()
                .create(Api.class)
                .getVideo(item,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
