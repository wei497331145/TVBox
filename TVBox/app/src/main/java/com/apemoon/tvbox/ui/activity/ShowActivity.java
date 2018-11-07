package com.apemoon.tvbox.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseActivity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.interfaces.ILoginView;
import com.apemoon.tvbox.presenter.SplashPresenter;
import com.apemoon.tvbox.utils.GlobalUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;

import butterknife.BindView;

/**
 * Created by water on 2018/8/31/031.
 * des：启动界面
 */

public class ShowActivity extends BaseActivity implements ILoginView {

    public static void actionStart(Context context) {//带参启动界面
        Intent intent = new Intent(context, ShowActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_show;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void initListener() {

    }

    /*
     *   用户登录成功
     * */
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
        super.onDestroy();
    }
}
