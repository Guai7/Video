package com.bw.myvideo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.mvp_lib.view.BaseActivity;
import com.bw.myvideo.R;
import com.bw.myvideo.adapter.MyAdapter;
import com.bw.myvideo.adapter.MyLinear;
import com.bw.myvideo.entity.VideoBean;
import com.bw.myvideo.mvp.contract.IContract;
import com.bw.myvideo.mvp.model.VideoModel;
import com.bw.myvideo.mvp.presenter.VideoPresenter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends BaseActivity<VideoPresenter> implements IContract.IVideoView, OnLoadMoreListener, OnRefreshListener {

    private RecyclerView mainRv;
    private SmartRefreshLayout mainRefresh;
    private MyAdapter myAdapter;
    private boolean isRefresh;

    private int item = 5,page = 1;

    @Override
    public void initView() {
        mainRv = findViewById(R.id.main_rv);
        mainRv.setLayoutManager(new MyLinear(this));
        mainRefresh = findViewById(R.id.main_refresh);
        mainRefresh.setOnLoadMoreListener(this);
        mainRefresh.setOnRefreshListener(this);
    }

    @Override
    public void initData() {

        mainRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        RecyclerView.LayoutManager layoutManager = mainRv.getLayoutManager();
                        StandardGSYVideoPlayer player = layoutManager.getChildAt(0).findViewById(R.id.item_video_view);

                        player.startPlayLogic();
                        break;
                }
            }
        });
        presenter = new VideoPresenter(this,new VideoModel());
        presenter.getVideoData(item,page);

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showVideo(List<VideoBean.DataBean> list) {

        mainRefresh.finishLoadMore();
        mainRefresh.finishRefresh();

        if (myAdapter ==null){
            myAdapter = new MyAdapter(list);
            mainRv.setAdapter(myAdapter);
        }else {
            if (isRefresh){
                myAdapter.getData().clear();
            }else {

            }

            myAdapter.getData().addAll(list);
            myAdapter.notifyDataSetChanged();

            if (list.size()==0){
                mainRefresh.setEnableLoadMoreWhenContentNotFull(true);
            }
        }
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        isRefresh = false;
        page++;
        presenter.getVideoData(item,page);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        isRefresh = true;
        page++;
        presenter.getVideoData(item,page);
    }
}