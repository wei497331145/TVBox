package com.apemoon.tvbox.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.rx.ProgressObserver;
import com.apemoon.tvbox.base.rx.RxBasePresenter;
import com.apemoon.tvbox.entity.AccountListEntity;
import com.apemoon.tvbox.entity.AppUpdateEntity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.interfaces.IMainView;
import com.apemoon.tvbox.interfaces.recyclerview.RecyclerViewItemSelectListener;
import com.apemoon.tvbox.ui.adapter.AccountAdapter;
import com.apemoon.tvbox.utils.AccountInfoUtil;
import com.apemoon.tvbox.utils.ConstantUtil;
import com.apemoon.tvbox.utils.MD5EncoderUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;
import com.apemoon.tvbox.utils.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;
import java.util.Map;

/**
 * @Des 主界面的控制器
 * @Author water
 * @Date 2018-04-11
 */
public class MainPresenter extends RxBasePresenter {

    private IMainView mIMainView;


    public MainPresenter(Context context, IMainView iMainView) {
        super(context);
        mIMainView = iMainView;
    }

    /**
     * 用户登录接口
     */
    public void getSysAppVersion(){
        Map<String, String> paras = RequestUtil.createMap();
        paras.put("key", "tv_apk");
        addDisposable(mDataManager.getNetService().systemAppVersion(paras),
                new ProgressObserver<HttpResultBody<AppUpdateEntity>>(mContext, false) {

                    @Override
                    public void doNext(HttpResultBody<AppUpdateEntity> httpResultBody) {
                        if (mIMainView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mIMainView.getSystemAppVersion(httpResultBody.result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mIMainView != null ) {
                            mIMainView.getSystemAppVersionFail();
                        }
                    }
                });
    }



}
