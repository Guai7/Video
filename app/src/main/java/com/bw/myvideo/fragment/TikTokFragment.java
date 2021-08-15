package com.bw.myvideo.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.mvp_lib.view.BaseFragment;
import com.bw.myvideo.R;
import com.bw.myvideo.adapter.TikTokVideoAdapter;
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
import com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TikTokFragment extends BaseFragment<VideoPresenter> implements IContract.IVideoView, OnLoadMoreListener, OnRefreshListener {

    private RecyclerView tiktokRv;
    private SmartRefreshLayout tiktokRefresh;
    private TikTokVideoAdapter myAdapter;
    private boolean isRefresh;
    private static GSYVideoViewBridge manager;

    private final int item = 5;
    private int page = 1;

    public static GSYVideoViewBridge getGsyVideoManager() {
        if (manager!=null){
            return manager;
        }
        return null;
    }

    @Override
    public void initView() {
        tiktokRv = findViewById(R.id.tiktok_rv);
        tiktokRv.setLayoutManager(new MyLinear(getActivity()));
        tiktokRefresh = findViewById(R.id.tiktok_refresh);
        tiktokRefresh.setOnLoadMoreListener(this);
        tiktokRefresh.setOnRefreshListener(this);
    }

    @Override
    public void initData() {

        tiktokRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                switch (newState){
                    //滑动暂停时
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //获取布局管理器
                        RecyclerView.LayoutManager layoutManager = tiktokRv.getLayoutManager();
                        //获取当前视图内 第一个条目 并查找视图
                        StandardGSYVideoPlayer player = layoutManager.getChildAt(0).findViewById(R.id.item_video_view);

                        //获取视频管理器
                        manager = player.getGSYVideoManager();

                        //播放视频
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
        return R.layout.fragment_tiktok;
    }

    @Override
    public void showVideo(List<VideoBean.DataBean> list) {

        tiktokRefresh.finishLoadMore();
        tiktokRefresh.finishRefresh();

        if (myAdapter ==null){
            myAdapter = new TikTokVideoAdapter(list);
            tiktokRv.setAdapter(myAdapter);

        }else {
            if (isRefresh){
                myAdapter.getData().clear();
            }else {

            }

            myAdapter.getData().addAll(list);
            myAdapter.notifyDataSetChanged();

            if (list.size()==0){
                tiktokRefresh.setEnableLoadMoreWhenContentNotFull(true);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.stop();
    }
}