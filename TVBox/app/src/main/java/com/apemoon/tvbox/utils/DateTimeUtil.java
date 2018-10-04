package com.apemoon.tvbox.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by water on 2018/8/20/020.
 * des：时间戳与时间字符串相互转换的工具类
 */

public class DateTimeUtil {
    /** 日期格式：yyyy-MM-dd HH:mm:ss **/
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /** 日期格式：yyyy-MM-dd HH:mm **/
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /** 日期格式：yyyy-MM-dd **/
    public static final String DF_YYYY_MM_DD_ONE = "yyyy.MM.dd";

    public static final String DF_YYYY_MM_DD_TWO = "yyyy-MM-dd";

    /** 日期格式：HH:mm:ss **/
    public static final String DF_HH_MM_SS = "HH:mm:ss";

    /** 日期格式：HH:mm **/
    public static final String DF_HH_MM = "HH:mm";

    /**
     * 时间戳转为时间(年月日，时分秒)
     *
     * @param cc_time 时间戳
     * @return
     */
    public static String getStrTime(long cc_time) {
        return getStrTime(cc_time,DF_YYYY_MM_DD_ONE);
    }

    /**
     * 时间戳转为时间(年月日，时分秒)
     *
     * @param cc_time 时间戳
     * @param format  时间对应格式  例如: yyyy-MM-dd
     * @return
     */
    public static String getStrTime(long cc_time,String format) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 例如：cc_time=1291778220
        re_StrTime = sdf.format(new Date(cc_time));
        return re_StrTime;
    }

    /**
     * 时间转换为时间戳
     *
     * @param timeStr 时间 例如: 2016-03-09
     * @param format  时间对应格式  例如: yyyy-MM-dd
     * @return
     */
    public static long getTimeStamp(String timeStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(timeStr);
            long timeStamp = date.getTime();
            return timeStamp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }



}
