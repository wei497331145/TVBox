package com.apemoon.tvbox.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.ActivityManager;
import com.apemoon.tvbox.base.BaseActivity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by water on 2018/8/30/030.
 * des：
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.tv_school_name)
    TextView mTvSchoolName;
    @BindView(R.id.iv_switch_school)
    ImageView mIvSwitchSchool;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_grade_name)
    TextView mTvGradeName;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.iv_head_use)
    ImageView mIvHeadUse;
    @BindView(R.id.tv_name_user)
    TextView mTvNameUser;
    @BindView(R.id.tv_logout)
    TextView mTvLogout;
    @BindView(R.id.tv_switch_account)
    TextView mTvSwitchAccount;

    public static final String USER_ENTITY = "user_entity";
    private UserEntity mUserEntity;

    public static void actionStart(Context context, UserEntity userEntity) {//带参启动界面
        Intent intent = new Intent(context, SettingActivity.class);
        intent.putExtra(USER_ENTITY, userEntity);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        mTvLogout.requestFocus();
    }

    @Override
    public void initData() {
        mUserEntity = (UserEntity) getIntent().getSerializableExtra(USER_ENTITY);
        if (mUserEntity != null) {
            UserEntity.UserInfoBean userInfo = mUserEntity.getUserInfo();
            if (userInfo != null) {
                setUserData(userInfo);
            }
        }
    }

    private void setUserData( UserEntity.UserInfoBean userInfo) {
        GlideUtil.imageCircleLocal(this,userInfo.getHeadImage(),mIvHead);
        GlideUtil.imageCircleLocal(this,userInfo.getHeadImage(),mIvHeadUse);
        mTvSchoolName.setText(userInfo.getSchoolName());
        mTvName.setText(userInfo.getName());
        mTvNameUser.setText(userInfo.getName());
        mTvGradeName.setText(userInfo.getGradeName());
    }

    @Override
    public void initListener() {
        setTextChangeListener(mTvBack);
    }

    private void setTextChangeListener(View view) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setTextAnimation(view,hasFocus,1.1f,1.1f,1.0f,1.0f);
            }
        });
    }


    @OnClick({R.id.iv_switch_school, R.id.iv_setting, R.id.tv_back, R.id.tv_logout, R.id.tv_switch_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_switch_school://切换学校

                break;
            case R.id.iv_setting://设置

                break;
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.tv_logout://退出登录
                ActivityManager.getIntence().logout(SettingActivity.this);
                break;
            case R.id.tv_switch_account://切换账号

                break;
        }
    }





















}
