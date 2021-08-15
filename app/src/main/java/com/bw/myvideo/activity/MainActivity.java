package com.bw.myvideo.activity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.mvp_lib.view.BaseActivity;
import com.bw.myvideo.R;
import com.bw.myvideo.adapter.FragmentAdapter;
import com.bw.myvideo.fragment.KuaiFragment;
import com.bw.myvideo.fragment.TikTokFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *MyVideo
 *file name is : MainActivity
 *created by Ender on 2021/8/15 16:54.
 *author : 王益德
 *Describe:
 */
public class MainActivity extends BaseActivity  {

//    private com.google.android.material.tabs.TabLayout mainTabs;
    private androidx.viewpager.widget.ViewPager mainVp;

    @Override
    public void initView() {

//        mainTabs = findViewById(R.id.main_tabs);
        mainVp = findViewById(R.id.main_vp);
    }

    @Override
    public void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TikTokFragment());
        fragments.add(new KuaiFragment());

        List<String> titles = new ArrayList<>();
        titles.add("抖音视图");
        titles.add("快手视图");

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),titles,fragments);
        mainVp.setAdapter(fragmentAdapter);
//        mainTabs.setupWithViewPager(mainVp);

        mainVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (TikTokFragment.getGsyVideoManager() != null) {
                    TikTokFragment.getGsyVideoManager().pause();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

}
