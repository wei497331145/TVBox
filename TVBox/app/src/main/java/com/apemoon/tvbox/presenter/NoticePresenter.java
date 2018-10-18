package com.apemoon.tvbox.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.rx.ProgressObserver;
import com.apemoon.tvbox.base.rx.RxBasePresenter;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.interfaces.fragment.IReceiveNoticeView;
import com.apemoon.tvbox.utils.ConstantUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;
import com.apemoon.tvbox.utils.RequestUtil;

import java.util.Map;

/**
 * @Des 公告通知的控制器
 * @Author water
 * @Date 2018-04-11
 */
public class NoticePresenter extends RxBasePresenter {

    private IReceiveNoticeView mIReceiveNoticeView;

    public NoticePresenter(Context context , IReceiveNoticeView iReceiveNoticeView) {
        super(context);
        mIReceiveNoticeView = iReceiveNoticeView;
    }

    /**
     * 获取公告列表
     */
    public void receiveNoticeList(String pageNo,String size){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("userId", PreferenceUtil.getString(ConstantUtil.USER_ID,""));
        paras.put("userType", PreferenceUtil.getString(ConstantUtil.USER_TYPE,""));
        paras.put("pageNo", pageNo);
        paras.put("size",size);
        String otherSchoolId = PreferenceUtil.getString(ConstantUtil.OTHER_SCHOO_ID,"");
        if(!TextUtils.isEmpty(otherSchoolId)){
            paras.put("schoolId",otherSchoolId);
            paras.put("selectType","2");
        }else {
            paras.put("selectType", "1");
        }
        addDisposable(mDataManager.getNetService().receiveNoticeListCall(paras),
                new ProgressObserver<HttpResultBody<ReceiveNoticeListEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<ReceiveNoticeListEntity> httpResultBody) {
                        if (mIReceiveNoticeView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mIReceiveNoticeView.getReceiveNoticeListSuccess(httpResultBody.result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mIReceiveNoticeView != null) {
                            mIReceiveNoticeView.getReceiveNoticeListFail();
                        }
                    }
                });
    }

    /**
     * 设置公告为已读
     */
    public void setNoteReaded(String noticeId){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("userId", PreferenceUtil.getString(ConstantUtil.USER_ID,""));
        paras.put("userType", PreferenceUtil.getString(ConstantUtil.USER_TYPE,""));
        paras.put("noticeId", noticeId);
        addDisposable(mDataManager.getNetService().setNoticeRead(paras),
                new ProgressObserver<HttpResultBody<ReceiveNoticeListEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<ReceiveNoticeListEntity> httpResultBody) {
                        if (mIReceiveNoticeView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mIReceiveNoticeView.setNoticeReadSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mIReceiveNoticeView != null) {
                            mIReceiveNoticeView.setNoticeReadFail();
                        }
                    }
                });
    }



}
