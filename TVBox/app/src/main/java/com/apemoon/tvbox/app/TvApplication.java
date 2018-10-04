package com.apemoon.tvbox.app;

import android.app.Application;

import com.apemoon.tvbox.utils.GlobalUtil;

/**
 * Created by water on 2018/8/27/027.
 * des：
 */

public class TvApplication extends Application{

    private volatile static TvApplication mInstance = null;

    public static TvApplication getGlobalApplication() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        /*全局工具类的初始化*/
        GlobalUtil.init(this);

    }
}
