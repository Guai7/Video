package com.bw.myvideo.activity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
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
    private BottomNavigationBar mainBv;

    @Override
    public void initView() {
//        mainTabs = findViewById(R.id.main_tabs);
        mainVp = findViewById(R.id.main_vp);
        mainBv = findViewById(R.id.main_bv);
    }

    @Override
    public void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TikTokFragment());
        fragments.add(new KuaiFragment());

//        List<String> titles = new ArrayList<>();
//        titles.add("抖音视图");
//        titles.add("快手视图");

        mainBv.addItem(new BottomNavigationItem(R.mipmap.tiktok,"抖音视图"))
                .addItem(new BottomNavigationItem(R.mipmap.kuai,"快手视图"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .initialise();

        mainBv.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //未选中->选中
                mainVp.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {
                //选中->未选中
            }

            @Override
            public void onTabReselected(int position) {
                //选中->选中
            }
        });


        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
        mainVp.setAdapter(fragmentAdapter);
//        mainTabs.setupWithViewPager(mainVp);

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onPause() {
        super.onPause();
        TikTokFragment.getGsyVideoManager().pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        TikTokFragment.getGsyVideoManager().start();
    }
}
