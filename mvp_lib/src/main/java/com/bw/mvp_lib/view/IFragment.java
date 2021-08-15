package com.bw.mvp_lib.view;

import android.view.View;

import androidx.annotation.IdRes;

public
/**
 *MyVideo
 *file name is : IFragment
 *created by Ender on 2021/8/13 17:00.
 *author : 王益德
 *Describe:
 */
interface IFragment extends IActivity {
    <T extends View> T findViewById(@IdRes int id);
}
