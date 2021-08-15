package com.bw.myvideo.adapter;

import android.view.View;

import com.bw.myvideo.R;
import com.bw.myvideo.entity.VideoBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public
/**
 *MyVideo
 *file name is : MyAdapter
 *created by Ender on 2021/8/13 16:51.
 *author : 王益德
 *Describe:
 */
class MyAdapter extends BaseMultiItemQuickAdapter<VideoBean.DataBean, BaseViewHolder> {

    int i = 0;

    public MyAdapter(@Nullable List<VideoBean.DataBean> data) {
        super(data);
        addItemType(0, R.layout.item_video);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, VideoBean.DataBean dataBean) {
        StandardGSYVideoPlayer player = baseViewHolder.getView(R.id.item_video_view);
        player.setUp(dataBean.getVideopath(),true,dataBean.getAuthname());

        if (i==0){
            player.startPlayLogic();
            i++;
        }
    }
}
