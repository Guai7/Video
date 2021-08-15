package com.bw.myvideo;

import android.app.Application;
import android.content.Context;

public
/**
 *MyVideo
 *file name is : App
 *created by Ender on 2021/8/15 20:16.
 *author : 王益德
 *Describe:
 */
class App extends Application {

    private static Context context;

    public static Context getContext() {
        if (context!=null){
            return context;
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
