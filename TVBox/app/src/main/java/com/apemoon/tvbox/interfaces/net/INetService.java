package com.apemoon.tvbox.interfaces.net;

import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.net.NetUrl;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;

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




}
















































