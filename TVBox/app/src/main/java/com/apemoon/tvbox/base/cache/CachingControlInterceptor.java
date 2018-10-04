package com.apemoon.tvbox.base.cache;

import com.apemoon.tvbox.utils.GlobalUtil;
import com.apemoon.tvbox.utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author water
 * @Date 2017-11-24
 * @des  请求网络的拦截器
 */

public class CachingControlInterceptor {

    //有网络是的拦截器
    public static final Interceptor NETISWORK_INTERCEPTOR =  new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response originalResponse = chain.proceed(request);
            int maxAge = 1 * 60; // 在线缓存在1分钟内可读取 单位:秒
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        }
    };

    //没有网络时的拦截器
    public static final Interceptor NONET_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isNetworkConnect(GlobalUtil.mContext)) {
                /**
                 * 离线缓存控制  总的缓存时间=在线缓存时间+设置离线缓存时间
                 */
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周,单位:秒
                CacheControl tempCacheControl = new CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale(maxStale, TimeUnit.SECONDS)
                        .build();
                request = request.newBuilder()
                        .cacheControl(tempCacheControl)
                        .build();
            }
            return chain.proceed(request);
        }


    };

}
