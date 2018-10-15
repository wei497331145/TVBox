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
     * 获取信息列表
     */
    public void receiveInformations(String pageNo,String size,String twoClassId){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("userId", PreferenceUtil.getString(ConstantUtil.USER_ID,""));
        paras.put("userType", PreferenceUtil.getString(ConstantUtil.USER_TYPE,""));
        paras.put("pageNo", pageNo);
        paras.put("pageSize",size);
        paras.put("twoClassifyId",twoClassId);
        paras.put("selectType","1");
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
     * 获取信息列表
     */
    public void receiveInfoClassfication(){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("schoolId", PreferenceUtil.getString(ConstantUtil.SCHOOL_ID,""));
        paras.put("selectType","1");
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
