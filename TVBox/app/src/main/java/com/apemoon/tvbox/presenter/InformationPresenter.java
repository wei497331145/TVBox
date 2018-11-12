package com.apemoon.tvbox.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.rx.ProgressObserver;
import com.apemoon.tvbox.base.rx.RxBasePresenter;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.entity.userCenter.UserInfoEntity;
import com.apemoon.tvbox.interfaces.fragment.IPersonalView;
import com.apemoon.tvbox.interfaces.fragment.IInformationView;
import com.apemoon.tvbox.utils.ConstantUtil;
import com.apemoon.tvbox.utils.LogUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;
import com.apemoon.tvbox.utils.RequestUtil;

import java.util.Map;

/**
 * @Des 公告通知的控制器
 * @Author water
 * @Date 2018-04-11
 */
public class InformationPresenter extends RxBasePresenter {

    private IInformationView mIInformationView;

    public InformationPresenter(Context context , IInformationView iInformationView) {
        super(context);
        mIInformationView = iInformationView;
    }

    /**
     * 获取最新信息列表
     */
    public void receiveNewestInformations(){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("userId", PreferenceUtil.getString(ConstantUtil.USER_ID,""));
        paras.put("userType", PreferenceUtil.getString(ConstantUtil.USER_TYPE,""));
        paras.put("pageNo", "1");
        paras.put("pageSize","6");
        String otherSchoolId = PreferenceUtil.getString(ConstantUtil.OTHER_SCHOO_ID,"");
        if(!TextUtils.isEmpty(otherSchoolId)){
            paras.put("schoolId",otherSchoolId);
            paras.put("selectType","2");
        }else {
            paras.put("selectType", "1");
        }

        addDisposable(mDataManager.getNetService().getInfoList(paras),
                new ProgressObserver<HttpResultBody<InfoListEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<InfoListEntity> httpResultBody) {
                        if (mIInformationView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mIInformationView.receiveNewestInformationsSuccess(httpResultBody.result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mIInformationView != null) {
                            mIInformationView.receiveNewestInformationsFail();
                        }
                    }
                });
    }


    /**
     * 获取信息列表
     */
    public void receiveInformations(String pageNo,String size,String twoClassId){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("userId", PreferenceUtil.getString(ConstantUtil.USER_ID,""));
        paras.put("userType", PreferenceUtil.getString(ConstantUtil.USER_TYPE,""));
        paras.put("pageNo", pageNo);
        paras.put("pageSize",size);
        paras.put("twoClassifyId",twoClassId);
        String otherSchoolId = PreferenceUtil.getString(ConstantUtil.OTHER_SCHOO_ID,"");
        if(!TextUtils.isEmpty(otherSchoolId)){
            paras.put("schoolId",otherSchoolId);
            paras.put("selectType","2");
        }else {
            paras.put("selectType", "1");
        }
        LogUtil.d("发送数据资讯：" + paras.toString());


        addDisposable(mDataManager.getNetService().getInfoList(paras),
                new ProgressObserver<HttpResultBody<InfoListEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<InfoListEntity> httpResultBody) {
                        if (mIInformationView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mIInformationView.receiveInformationsSuccess(httpResultBody.result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mIInformationView != null) {
                            mIInformationView.receiveInformationsFail();
                        }
                    }
                });
    }

    /**
     * 获取左侧信息列表
     */
    public void receiveInfoClassfication(){
        Map<String, String> paras = RequestUtil.createMap();
        String otherSchoolId = PreferenceUtil.getString(ConstantUtil.OTHER_SCHOO_ID,"");
        if(!TextUtils.isEmpty(otherSchoolId)){
            paras.put("schoolId",otherSchoolId);
            paras.put("selectType","2");
        }else {
            paras.put("schoolId", PreferenceUtil.getString(ConstantUtil.SCHOOL_ID,""));
            paras.put("selectType", "1");
        }
        addDisposable(mDataManager.getNetService().getInfoClassical(paras),
                new ProgressObserver<HttpResultBody<InfoClassicalEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<InfoClassicalEntity> httpResultBody) {
                        if (mIInformationView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mIInformationView.receiveInformationClassicalSuccess(httpResultBody.result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mIInformationView != null) {
                            mIInformationView.receiveInformationsFail();
                        }
                    }
                });
    }


}
