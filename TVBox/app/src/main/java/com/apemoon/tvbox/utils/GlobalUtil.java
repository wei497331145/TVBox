package com.apemoon.tvbox.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.apemoon.tvbox.app.TvApplication;
import com.google.gson.Gson;


/**
 * @Author water
 * @Date 2017-11-24
 * @des 全局公共类, 封装一些公共公能
 */

public class GlobalUtil {

    public static Context mContext;

    public static float mDensity;

    public static float mScreenWidth;

    public static float mScreenHeight;

    public static Gson mGson;

    private static Toast mToast;

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void init(Context context) {
        if (null != context) {
            mContext = context.getApplicationContext();
        } else {
            mContext = TvApplication.getGlobalApplication();
        }
        mGson = new Gson();
        initScreenSize();

        /*SP的初始化*/
        PreferenceUtil.init(context);
        LogUtil.setIsDeBug(true);
        //registerWeChat(mContext);

    }


    private static void initScreenSize() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        mDensity = dm.density;
        mScreenHeight = dm.heightPixels;
        mScreenWidth = dm.widthPixels;
    }


    /**
     * 判断当前线程是否是主线程
     *
     * @return true表示当前是在主线程中运行
     */
    public static boolean isUIThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOnUIThread(Runnable run) {
        if (isUIThread()) {
            run.run();
        } else {
            mHandler.post(run);
        }
    }

    /**
     * 可以在子线程中调用
     *
     * @param msg toast内容
     */
    public static void showToast(final String msg) {
        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }




}
