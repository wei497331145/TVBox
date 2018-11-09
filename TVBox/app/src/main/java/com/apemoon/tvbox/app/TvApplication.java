package com.apemoon.tvbox.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.apemoon.tvbox.utils.GlobalUtil;

/**
 * Created by water on 2018/8/27/027.
 * des：
 */

public class TvApplication extends Application{

    private volatile static TvApplication mInstance = null;

    private Context mContext;

    public static TvApplication getGlobalApplication() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = this;
        /*全局工具类的初始化*/
        GlobalUtil.init(this);

    }


    public static String getVersionName() {
        String version = "";
        try {
            PackageManager manager = mInstance.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mInstance.getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // e.printStackTrace();
        }
        return version;
    }


    public static int getVersionCode() {
        String pName = mInstance.getPackageName();
        int versionCode = 0;
        try {
            PackageInfo pinfo = mInstance.getPackageManager().getPackageInfo(pName, PackageManager.GET_CONFIGURATIONS);
            versionCode = pinfo.versionCode;
        } catch (Exception e) {

        }
        return versionCode;
    }

}
