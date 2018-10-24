package com.apemoon.tvbox.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.ActivityManager;
import com.apemoon.tvbox.base.BaseActivity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.interfaces.ILoginView;
import com.apemoon.tvbox.presenter.SettingPresenter;
import com.apemoon.tvbox.ui.view.address.AddressSelectorNew;
import com.apemoon.tvbox.ui.view.address.AddressSelectorNewB;
import com.apemoon.tvbox.ui.view.address.BottomDialog;
import com.apemoon.tvbox.utils.AccountInfoUtil;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.ConstantUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.apemoon.tvbox.utils.GlobalUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.widget.OnAddressSelectedListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by water on 2018/8/30/030.
 * des：
 */

public class SettingActivity extends BaseActivity implements ILoginView,OnAddressSelectedListener, AddressSelectorNewB.OnDialogCloseListener, AddressSelectorNewB.onSelectorAreaPositionListener {
    @BindView(R.id.root_view)
    LinearLayout mRootView;
    @BindView(R.id.tv_school_name)
    TextView mTvSchoolName;
    @BindView(R.id.rl_switch_school)
    RelativeLayout mIvSwitchSchool;
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

    private SettingPresenter mSettingPresenter;


    private BottomDialog dialog;



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
        mSettingPresenter = new SettingPresenter(this,this);
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


    @OnClick({R.id.rl_switch_school, R.id.iv_setting, R.id.tv_back, R.id.tv_logout, R.id.tv_switch_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_switch_school://切换学校
                showSchoolPop();
                break;
            case R.id.iv_setting://设置
                Intent intent =  new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                break;
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.tv_logout://退出登录
                ActivityManager.getIntence().logout(SettingActivity.this);
                break;
            case R.id.tv_switch_account://切换账号
                showAccountPop();
                break;
        }
    }


    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {

    }

    private void showSchoolPop(){
        if (dialog != null) {
            dialog.show(this);
        } else {
            dialog = new BottomDialog(this);
            dialog.setOnAddressSelectedListener(this);
            dialog.setDialogDismisListener(this);
            dialog.setTextSize(14);//设置字体的大小
            dialog.setIndicatorBackgroundColor(android.R.color.holo_orange_light);//设置指示器的颜色
            dialog.setTextSelectedColor(android.R.color.holo_orange_light);//设置字体获得焦点的颜色
            dialog.setTextUnSelectedColor(android.R.color.holo_blue_light);//设置字体没有获得焦点的颜色
//            dialog.setDisplaySelectorArea("31",1,"2704",1,"2711",0,"15582",1);//设置已选中的地区
            dialog.setSelectorAreaPositionListener(this);
            dialog.show(this);
        }
    }

    private void showAccountPop(){
        AccountInfoUtil.showSelectAccountWindow(mRootView,SettingActivity.this,mSettingPresenter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void dialogclose() {
        GlobalUtil.showToast("选择成功");
        dialog.dismiss();
        recreate();
    }

    @Override
    public void selectorAreaPosition(int provincePosition, int cityPosition, int countyPosition, int streetPosition) {

    }

    @Override
    public void loginSuccess(UserEntity userEntity, String code,String mAccount,String mPassword) {
        ActivityManager.getIntence().finishAllActivity();

        PreferenceUtil.saveAccountDdata(userEntity,mAccount,mPassword);
        MainActivity.actionStart(this, userEntity);
    }

    @Override
    public void loginFail() {

    }
}
