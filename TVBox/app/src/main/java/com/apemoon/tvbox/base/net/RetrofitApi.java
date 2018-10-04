package com.apemoon.tvbox.base.net;

import android.content.Context;
import com.apemoon.tvbox.BuildConfig;
import com.apemoon.tvbox.base.cache.CachingControlInterceptor;
import com.apemoon.tvbox.factory.NullOnEmptyConverterFactory;
import com.apemoon.tvbox.factory.ResponseConverterFactory;
import com.apemoon.tvbox.interfaces.net.INetService;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author water
 * @Date 2017-11-24
 * @des  请求网络数据Retrofit的工具类
 */

public class RetrofitApi {

    private static volatile RetrofitApi mInstance;
    private INetService mINetService;
    private Context mContext;

    private RetrofitApi(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static RetrofitApi getInstance(Context context) {
        if (mInstance == null) {
            synchronized (RetrofitApi.class) {
                if(null == mInstance){
                    mInstance = new RetrofitApi(context);
                }
            }
        }
        return mInstance;
    }

    public INetService getRetrofitSpecNetService() {
        //缓存容量
        long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
        //缓存路径
        String cacheFile = mContext.getApplicationContext().getCacheDir() + "/httpCache";
        Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);
        //利用okhttp实现缓存
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(BuildConfig.ENABLE_DEBUG){
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                //没网络时的拦截器
                .addInterceptor(CachingControlInterceptor.NONET_INTERCEPTOR)
                //有网络时的拦截器
                .addNetworkInterceptor(CachingControlInterceptor.NETISWORK_INTERCEPTOR)
                .cache(cache)
                .build();
        //返回网络访问接口对象
        return new Retrofit.Builder().baseUrl(NetUrl.BASE_URL)
                .client(client)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(INetService.class);
    }

    public INetService getRetrofitNetService() {
        //缓存容量
        long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
        //缓存路径
        String cacheFile = mContext.getApplicationContext().getCacheDir() + "/httpCache";
        Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);
        //利用okhttp实现缓存
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if(BuildConfig.ENABLE_DEBUG){
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                //没网络时的拦截器
                .addInterceptor(CachingControlInterceptor.NONET_INTERCEPTOR)
                //有网络时的拦截器
                .addNetworkInterceptor(CachingControlInterceptor.NETISWORK_INTERCEPTOR)
                .cache(cache)
                .build();
        //返回网络访问接口对象
        return new Retrofit.Builder().baseUrl(NetUrl.BASE_URL)
                .client(client)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(INetService.class);
    }
}
