package com.bw.myvideo.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.myvideo.R;
import com.bw.myvideo.entity.VideoBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public
/**
 *MyVideo
 *file name is : KuaiVideoAdapter
 *created by Ender on 2021/8/15 17:40.
 *author : 王益德
 *Describe:
 */
class KuaiVideoAdapter extends BaseMultiItemQuickAdapter<VideoBean.DataBean, BaseViewHolder> {

    public KuaiVideoAdapter(@Nullable List<VideoBean.DataBean> data) {
        super(data);
        addItemType(0, R.layout.item_kuai_video);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, VideoBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.item_name_text,dataBean.getAuthname());
        Glide.with(getContext()).load(dataBean.getHeadpath()).circleCrop().into((ImageView) baseViewHolder.getView(R.id.item_tou_img));
        Glide.with(getContext()).load(dataBean.getVideomainimg()).into((ImageView) baseViewHolder.getView(R.id.item_img));
    }
}
