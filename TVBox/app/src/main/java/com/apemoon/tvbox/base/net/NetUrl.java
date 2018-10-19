package com.apemoon.tvbox.base.net;


import com.apemoon.tvbox.BuildConfig;

/**
 * 网络请求常量类
 *
 * @Author zhouy
 * @Date 2017-11-29
 */

public class NetUrl {
    //网络请求设置相关常量
    public static final int CONNECT_TIME_OUT = 25000;
    public static final int READ_TIME_OUT = 25000;
    public static final String POST_REQUEST = "POST";
    public static final String GET_REQUEST = "GET";

    //微信第三方登录
    public static final String WEIXIN_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";
    public static final String WEIXIN_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?";

    //API基础地址(根据打包环境，动态配置)
    public static final String BASE_URL = BuildConfig.SERVERHEAD;          //API基础地址

    //==============================系统全局的API==================================================


    //==============================账号系统接口API==================================================
    public static final String USER_LOGIN = BASE_URL + "user/login";//用户登录接口


    //==============================通知的接口========================================================
    public static final String NOTICE_RECEIVENOTICELIST = BASE_URL + "notice/receiveNoticeList";//查询我接受的公告通知
    public static final String NOTICE_SETNOTEREADED = BASE_URL + "notice/readNotice";//设置同志公告为已读

    //==============================个人中心接口========================================================
    public static final String USER_INFO = BASE_URL + "user/getUserInfo";//获取用户个人信息

    public static final String USER_SEMSTERS_INFO = BASE_URL + "user/getSemesterList";//获取学期信息

    public static final String USER_STUDY_INFO = BASE_URL + "user/getInfoRecords";//获取奖惩，评价，教师信息

    public static final String USER_TEACHER_INFO = BASE_URL + "user/subjectTeacher";//获取所在班级教师信息

    public static final String USER_SCHOOL_TYPE = BASE_URL + "user/getSchoolType";//获取学校类型

    public static final String USER_SCHOOL = BASE_URL + "user/getSchoolList";//根据省市区获取学校列表

    public static final String USER_CLASS_SCHEDULE_INFO = BASE_URL + "user/getClassSchedule";//

    public static final String USER_EXAMCLASSIFY_INFO = BASE_URL + "user/getExamClassifyList";//查询考试类别列表接口

    public static final String USER_MARK_INFO = BASE_URL + "user/getMark";//查询考试类别列表接口

    public static final String USER_SUBJECT_LIST_INFO = BASE_URL + "user/getSubjectList";//科目

    public static final String USER_WORK_LIST_INFO = BASE_URL + "user/getSeatworkList";//作业列表

    public static final String USER_CLASS_ACTIVITY_LIST_INFO = BASE_URL + "activity/receiveClassActivityList";//活动列表

    public static final String USER_PHOTO_ALBUM_LIST_INFO = BASE_URL + "user/photoAlbumList";//活动列表

    //==============================个人中心接口========================================================
    public static final String INFORMATION_CLASSIFICAl = BASE_URL + "information/informationClassify";//获取咨询列表

    public static final String INFORMATION_LIST = BASE_URL + "information/informationList";//获取咨询列表


}



























