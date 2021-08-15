package com.bw.myvideo.mvp.presenter;

import com.bw.mvp_lib.presenter.BasePresenter;
import com.bw.myvideo.entity.VideoBean;
import com.bw.myvideo.mvp.contract.IContract;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public
/**
 *MyVideo
 *file name is : VideoPresenter
 *created by Ender on 2021/8/14 8:45.
 *author : 王益德
 *Describe:
 */
class VideoPresenter extends BasePresenter<IContract.IVideoView, IContract.IVideoModel> {

    private Disposable disposable;

    public VideoPresenter(IContract.IVideoView view, IContract.IVideoModel model) {
        super(view, model);
    }

    public void getVideoData(int item,int page){
        model.getVideo(item,page, new Observer<VideoBean>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NotNull VideoBean videoBean) {
                view.showVideo(videoBean.getData());
            }

            @Override
            public void onError(@NotNull Throwable e) {
                view.showToast(e.getMessage().toString());
                disposable.dispose();
            }

            @Override
            public void onComplete() {
                disposable.dispose();
            }
        });
    }
}
