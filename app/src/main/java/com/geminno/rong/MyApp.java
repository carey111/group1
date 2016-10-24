package com.geminno.rong;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2016/10/19.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化融云
         */
        RongIM.init(this);
    }


}
