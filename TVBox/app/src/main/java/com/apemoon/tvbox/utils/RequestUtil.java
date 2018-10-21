package com.apemoon.tvbox.utils;

import android.os.Build;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Des 请求数据统一请求处理类
 * @Author zhouy
 * @Date 2018-02-23
 */

public class RequestUtil {

    /**
     * 创建Map数据结构
     *
     * @return
     */
    public static Map<String, String> createMap() {
        Map<String, String> map;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            map = new ArrayMap<>();
        } else {
            map = new HashMap<>();
        }
        map.put("token", PreferenceUtil.getString(ConstantUtil.TOKEN, ""));
        return map;
    }



    /**
     * 创建Map数据结构
     *
     * @return
     */
    public static Map<String, String> createMapWithoutToen() {
        Map<String, String> map;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            map = new ArrayMap<>();
        } else {
            map = new HashMap<>();
        }
        return map;
    }
}
