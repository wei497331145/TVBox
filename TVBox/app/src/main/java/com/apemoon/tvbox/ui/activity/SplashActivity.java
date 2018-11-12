package com.apemoon.tvbox.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseActivity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.interfaces.ILoginView;
import com.apemoon.tvbox.presenter.SplashPresenter;
import com.apemoon.tvbox.utils.ConstantUtil;
import com.apemoon.tvbox.utils.GlobalUtil;
import com.apemoon.tvbox.utils.MD5EncoderUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;

import butterknife.BindView;

/**
 * Created by water on 2018/8/31/031.
 * des：启动界面
 */

public class SplashActivity extends BaseActivity implements ILoginView {
    @BindView(R.id.iv_splash)
    ImageView mIvSplash;

    private ObjectAnimator mAplha;
    private SplashPresenter mSplashPresenter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mSplashPresenter = new SplashPresenter(this, this);
        startAlphaAnim();
    }

    /**
     * 开始动画
     */
    private void startAlphaAnim() {
        mAplha = ObjectAnimator.ofFloat(mIvSplash, "alpha", 0, 1).setDuration(3000);
        mAplha.start();
        mAplha.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                String userName = getUserName();
                String password = getPassword();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } else {
                    mSplashPresenter.login(userName, password);
                }
            }
        });
    }

    @Override
    public void initListener() {

    }

    /**
     *  用户登录成功
     */
    @Override
    public void loginSuccess(UserEntity userEntity, String code,String mAccount,String mPassword) {
        if (userEntity != null) {
            if (!TextUtils.equals(userEntity.getUserType(), "2")) {//2 学生
                GlobalUtil.showToast("只能登录学生的账号");
                return;
            }
            PreferenceUtil.saveAccountDdata(userEntity,mAccount,mPassword);
        }

        MainActivity.actionStart(this, userEntity);
        finish();
    }

    /*
     *  用户登录失败
     * */
    @Override
    public void loginFail() {
        MainActivity.actionStart(this, null);
        finish();
    }

    @Override
    protected void onDestroy() {
        if (mAplha != null) {
            mAplha.cancel();
        }
        super.onDestroy();
    }
}
