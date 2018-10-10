package com.apemoon.tvbox.interfaces.net;

import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.net.NetUrl;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.entity.userCenter.UserInfoEntity;
import com.apemoon.tvbox.entity.userCenter.UserRecordInfoEntity;
import com.apemoon.tvbox.entity.userCenter.UserSemstersEntity;
import com.apemoon.tvbox.entity.userCenter.UserTeachersEntity;

import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author water
 * @Date 2017-11-24
 * @des 封装的网络请求接口
 */

public interface INetService {
    //===========================  登录的接口 ==================================
    /**
     * 用户登录接口
     */
    @FormUrlEncoded
    @POST(NetUrl.USER_LOGIN)
    Observable<HttpResultBody<UserEntity>> loginCall(@FieldMap Map<String, String> paras);


    //===========================  通知的接口 ==================================
    /**
     * 查询我接受的公告通知
     */
    @FormUrlEncoded
    @POST(NetUrl.NOTICE_RECEIVENOTICELIST)
    Observable<HttpResultBody<ReceiveNoticeListEntity>> receiveNoticeListCall(@FieldMap Map<String, String> paras);

    /**
     * 设置公告通知为已读
     */
    @FormUrlEncoded
    @POST(NetUrl.NOTICE_SETNOTEREADED)
    Observable<HttpResultBody<ReceiveNoticeListEntity>> setNoticeRead(@FieldMap Map<String, String> paras);


    //===========================  个人中心的接口 ==================================
    /**
     * 获取个人用户信息
     */
    @FormUrlEncoded
    @POST(NetUrl.USER_INFO)
    Observable<HttpResultBody<UserInfoEntity>> getUserInfo(@FieldMap Map<String, String> paras);

    /**
     * 获取学期信息
     */
    @FormUrlEncoded
    @POST(NetUrl.USER_SEMSTERS_INFO)
    Observable<HttpResultBody<UserSemstersEntity>> getSemstersInfo(@FieldMap Map<String, String> paras);

    /**
     * 获取奖惩，评价，教师信息
     */
    @FormUrlEncoded
    @POST(NetUrl.USER_STUDY_INFO)
    Observable<HttpResultBody<UserRecordInfoEntity>> getUserRecords(@FieldMap Map<String, String> paras);


    /**
     * 获取所在班级教师信息
     */
    @FormUrlEncoded
    @POST(NetUrl.USER_TEACHER_INFO)
    Observable<HttpResultBody<UserTeachersEntity>> getUserTeachers(@FieldMap Map<String, String> paras);


    //=========================== 咨询列表的接口 ==================================
    /**
     * 获取资讯分类信息
     */
    @FormUrlEncoded
    @POST(NetUrl.INFORMATION_CLASSIFICAl)
    Observable<HttpResultBody<InfoClassicalEntity>> getInfoClassical(@FieldMap Map<String, String> paras);

    /**
     * 获取资讯列表
     */
    @FormUrlEncoded
    @POST(NetUrl.INFORMATION_LIST)
    Observable<HttpResultBody<InfoListEntity>> getInfoList(@FieldMap Map<String, String> paras);



}
















































