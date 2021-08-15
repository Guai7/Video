package com.bw.myvideo.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public
/**
 *MyVideo
 *file name is : MyLinear
 *created by Ender on 2021/8/14 8:11.
 *author : 王益德
 *Describe:
 */
class MyLinear extends LinearLayoutManager {

    private PagerSnapHelper helper;

    public MyLinear(Context context) {
        super(context);
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);

        //页面管理器
        helper = new PagerSnapHelper();
        //设置一条目 为一页 达成抖音效果
        helper.attachToRecyclerView(view);
    }
}
