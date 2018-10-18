package com.apemoon.tvbox.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.rx.ProgressObserver;
import com.apemoon.tvbox.base.rx.RxBasePresenter;
import com.apemoon.tvbox.entity.SchoolListEntity;
import com.apemoon.tvbox.entity.SchoolTypeListEntity;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.interfaces.SchoolView;
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
public class SchoolPresenter extends RxBasePresenter {

    private SchoolView mSchoolView;

    public SchoolPresenter(Context context , SchoolView iSchoolView) {
        super(context);
        mSchoolView = iSchoolView;
    }

    /**
     * 获取信息列表
     */
    public void receiveSchoolType(){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("token", PreferenceUtil.getString(ConstantUtil.TOKEN,""));
        addDisposable(mDataManager.getNetService().getSchoolType(paras),
                new ProgressObserver<HttpResultBody<SchoolTypeListEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<SchoolTypeListEntity> httpResultBody) {
                        if (mSchoolView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mSchoolView.receiveSchoolTypeSuccess(httpResultBody.result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mSchoolView != null) {
                            mSchoolView.receiveSchoolTypFail();
                        }
                    }
                });
    }

    /**
     * 获取信息列表
     */
    public void receiveSchool(String provience,String city,String area,String schoolTypeId){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("province",provience+"省");
        paras.put("city",city);
        paras.put("area",area);
        paras.put("schoolTypeId",schoolTypeId);


        addDisposable(mDataManager.getNetService().getSchool(paras),
                new ProgressObserver<HttpResultBody<SchoolListEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<SchoolListEntity> httpResultBody) {
                        if (mSchoolView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mSchoolView.receiveSchoolSuccess(httpResultBody.result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mSchoolView != null) {
                            mSchoolView.receiveSchoolFail();
                        }
                    }
                });
    }


}
