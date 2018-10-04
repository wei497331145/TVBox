package com.apemoon.tvbox.utils;

import android.util.Log;
import com.apemoon.tvbox.BuildConfig;


/**
 * @Author water
 * @Date 2017-11-29
 * @des  Log的工具类
 */

public class LogUtil {
    private   static boolean isDeBug = false;
     static String logName = "water";
    private static int LOG_MAXLENGTH = 2000;

    public static void setIsDeBug(boolean isDeBug) {
        LogUtil.isDeBug = isDeBug && BuildConfig.ENABLE_DEBUG;
    }

    public static  void d(String tag ,String text){
        if(isDeBug){
            Log.d(tag,text);
        }
    }
    public static  void d(String text){
        if(isDeBug){
            Log.d(logName,text);
        }
    }
    public static  void d(Object object,String text){
        if(isDeBug){
            Log.d(object.getClass().getSimpleName(),text);
        }
    }

    public static void dLong(String tagName, String msg) {
        if (isDeBug) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.d(tagName + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.d(tagName + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }


}
