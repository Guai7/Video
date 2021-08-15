package com.bw.myvideo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bw.mvp_lib.view.BaseActivity;
import com.bw.myvideo.App;
import com.bw.myvideo.R;
import com.bw.myvideo.entity.VideoBean;
import com.bw.myvideo.mvp.presenter.VideoPresenter;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge;

import java.io.Serializable;

/**
 *MyVideo
 *file name is : KuaiDetailsActivity
 *created by Ender on 2021/8/15 19:21.
 *author : 王益德
 *Describe:
 */
public class KuaiDetailsActivity extends BaseActivity<VideoPresenter> {
    private StandardGSYVideoPlayer itemKuaiVideo;
    private         GSYVideoViewBridge manager;

    @Override
    public void initView() {

        itemKuaiVideo = findViewById(R.id.item_kuai_video);
        manager = itemKuaiVideo.getGSYVideoManager();

        /*
        设置返回按钮
         */
        itemKuaiVideo.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置放大动画
        itemKuaiVideo.setShowFullAnimation(true);

        /*
        设置放大按钮
         */
        itemKuaiVideo.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemKuaiVideo.startWindowFullscreen(KuaiDetailsActivity.this,false,true);

            }
        });

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        VideoBean.DataBean data = (VideoBean.DataBean) intent.getSerializableExtra("data");

        itemKuaiVideo.setUp(data.getVideopath(),true,data.getAuthname());
        itemKuaiVideo.startPlayLogic();

    }

    @Override
    public int bindLayout() {
        return R.layout.item_kuai_details_video;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.stop();
    }
}
