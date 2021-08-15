package com.bw.myvideo.mvp.contract;

import com.bw.mvp_lib.model.IModel;
import com.bw.mvp_lib.view.IView;
import com.bw.myvideo.entity.VideoBean;

import java.util.List;

import io.reactivex.Observer;

public
/**
 *MyVideo
 *file name is : IContract
 *created by Ender on 2021/8/14 8:41.
 *author : 王益德
 *Describe:
 */
interface IContract {
    interface IVideoModel extends IModel{
        void getVideo(int item,int page,Observer<VideoBean> observer);
    }

    interface IVideoView extends IView{
        void showVideo(List<VideoBean.DataBean> list);
    }
}
