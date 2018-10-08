package com.apemoon.tvbox.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.rx.ProgressObserver;
import com.apemoon.tvbox.base.rx.RxBasePresenter;
import com.apemoon.tvbox.entity.userCenter.UserInfoEntity;
import com.apemoon.tvbox.entity.userCenter.UserRecordInfoEntity;
import com.apemoon.tvbox.interfaces.fragment.IPersonalView;
import com.apemoon.tvbox.utils.ConstantUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;
import com.apemoon.tvbox.utils.RequestUtil;

import java.util.Map;

/**
 * @Des 公告通知的控制器
 * @Author water
 * @Date 2018-04-11
 */
public class PersonalPresenter extends RxBasePresenter {

    private IPersonalView mIPersaonlView;

    public PersonalPresenter(Context context , IPersonalView iPersonalView) {
        super(context);
        mIPersaonlView = iPersonalView;
    }

    /**
     * 获取个人信息
     */
    public void receivePersonalInfo(){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("userId", PreferenceUtil.getString(ConstantUtil.USER_ID,""));
        paras.put("userType", PreferenceUtil.getString(ConstantUtil.USER_TYPE,""));
        addDisposable(mDataManager.getNetService().getUserInfo(paras),
                new ProgressObserver<HttpResultBody<UserInfoEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<UserInfoEntity> httpResultBody) {
                        if (mIPersaonlView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mIPersaonlView.receivePersonalInfoSuccess(httpResultBody.result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mIPersaonlView != null) {
                            mIPersaonlView.receivePersonalInfoFail();
                        }
                    }
                });
    }

    /**
     * 获取奖惩信息
     * 查询类型：学生就读信息(“1”),学生评价信息(“2”),学生担任职务(“3”),学生奖惩信息(“4”),教师任教信息(“5”),教师担任职务(“6”),教师奖惩信息(“7”)
     *
     */
    public void receiveRecords(String type){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("userId", PreferenceUtil.getString(ConstantUtil.USER_ID,""));
        paras.put("userType", PreferenceUtil.getString(ConstantUtil.USER_TYPE,""));
        paras.put("semesterId", PreferenceUtil.getString(ConstantUtil.USER_ACCOUNT,""));
        paras.put("type", type);
        addDisposable(mDataManager.getNetService().getUserRecords(paras),
                new ProgressObserver<HttpResultBody<UserRecordInfoEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<UserRecordInfoEntity> httpResultBody) {
                        if (mIPersaonlView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            if("1".equals(type)) {
                                mIPersaonlView.receiveRecords1Success(httpResultBody.result);
                            }else if("2".equals(type)){
                                mIPersaonlView.receiveRecords2Success(httpResultBody.result);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mIPersaonlView != null) {
                            if("1".equals(type)) {
                                mIPersaonlView.receiveRecords1Fail();
                            }else if("2".equals(type)){
                                mIPersaonlView.receiveRecords2Fail();
                            }
                        }
                    }
                });
    }



}
