package com.apemoon.tvbox.base.net;

import android.content.Context;
import com.apemoon.tvbox.interfaces.net.INetService;


/**
 * @Des 数据请求Model管理类，对应Retrofit中INetService
 * @Author zhouy
 * @Date 2018-02-01
 */

public class DataManager {

    private static volatile DataManager sInstance;

    private RetrofitApi mRetrofitApi;

    private DataManager(Context context){
        mRetrofitApi = RetrofitApi.getInstance(context);
    }

    public static DataManager getInstance(Context context){
        if(null == sInstance){
            synchronized (DataManager.class){
                if(null == sInstance){
                    sInstance = new DataManager(context);
                }
            }
        }
        return sInstance;
    }

    public INetService getNetService(){
        return mRetrofitApi.getRetrofitSpecNetService();
    }




}
