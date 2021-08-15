package com.bw.myvideo.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bw.mvp_lib.view.BaseFragment;
import com.bw.myvideo.R;
import com.bw.myvideo.activity.KuaiDetailsActivity;
import com.bw.myvideo.adapter.KuaiVideoAdapter;
import com.bw.myvideo.entity.VideoBean;
import com.bw.myvideo.mvp.contract.IContract;
import com.bw.myvideo.mvp.model.VideoModel;
import com.bw.myvideo.mvp.presenter.VideoPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * MyVideo
 * file name is : KuaiFragment
 * created by Ender on 2021/8/15 17:27.
 * author : 王益德
 * Describe:
 */
public class KuaiFragment extends BaseFragment<VideoPresenter> implements IContract.IVideoView, OnRefreshLoadMoreListener, OnRefreshListener,OnItemClickListener {
    private RecyclerView kuaiRv;
    private KuaiVideoAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private List<VideoBean.DataBean> nowData;

    private int page = 1;
    private final int item = 10;
    private boolean isRefresh;

    @Override
    public void initView() {

        kuaiRv = findViewById(R.id.kuai_rv);
        kuaiRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        refreshLayout = findViewById(R.id.kuai_refresh);
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        presenter = new VideoPresenter(this, new VideoModel());
        presenter.getVideoData(item, page);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_kuai;
    }

    @Override
    public void showVideo(List<VideoBean.DataBean> list) {

        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();

        if (adapter == null) {
            adapter = new KuaiVideoAdapter(list);
            adapter.setOnItemClickListener(this);
            kuaiRv.setAdapter(adapter);
        } else {
            if (isRefresh) {
                adapter.getData().clear();
            }

            adapter.getData().addAll(list);
            adapter.notifyDataSetChanged();
        }

        nowData = adapter.getData();
    }

    @Override
    public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
        isRefresh = false;
        page++;
        presenter.getVideoData(item,page);
    }

    @Override
    public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
        isRefresh = true;
        page++;
        presenter.getVideoData(item,page);
    }

    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        Intent intent = new Intent(getActivity(), KuaiDetailsActivity.class);
        intent.putExtra("data",nowData.get(position));
        startActivity(intent);
    }
}
