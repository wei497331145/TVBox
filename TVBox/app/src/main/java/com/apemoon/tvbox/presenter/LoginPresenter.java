package com.apemoon.tvbox.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.rx.ProgressObserver;
import com.apemoon.tvbox.base.rx.RxBasePresenter;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.interfaces.ILoginView;
import com.apemoon.tvbox.utils.ConstantUtil;
import com.apemoon.tvbox.utils.MD5EncoderUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;
import com.apemoon.tvbox.utils.RequestUtil;

import java.util.Map;

/**
 * @Des 登录的控制器
 * @Author water
 * @Date 2018-04-11
 */
public class LoginPresenter extends RxBasePresenter {

    private ILoginView mILoginView;

    public LoginPresenter(Context context , ILoginView iLoginView) {
        super(context);
        mILoginView = iLoginView;
    }

    /**
     * 用户登录接口
     */
    public void login(String account,String password){
        Map<String, String> paras = RequestUtil.createMapWithoutToen();
        paras.put("account", account);//15245645236
        paras.put("password", MD5EncoderUtil.encodeByMd5(password));//jiaoyu888
        addDisposable(mDataManager.getNetService().loginCall(paras),
                new ProgressObserver<HttpResultBody<UserEntity>>(mContext, true) {

                    @Override
                    public void doNext(HttpResultBody<UserEntity> httpResultBody) {
                        if (mILoginView != null && TextUtils.equals(httpResultBody.code,"0000")) {
                            mILoginView.loginSuccess(httpResultBody.result,httpResultBody.code,account,password);
                            //wxj 清除其他学校信息
                            PreferenceUtil.commitString(ConstantUtil.OTHER_SCHOO_ID, "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mILoginView != null ) {
                            mILoginView.loginFail();
                        }
                    }
                });
    }




}
