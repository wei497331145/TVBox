package com.apemoon.tvbox.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseActivity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.factory.main.FragmentFactory;
import com.apemoon.tvbox.interfaces.IMainTabView;
import com.apemoon.tvbox.interfaces.IMainView;
import com.apemoon.tvbox.presenter.MainPresenter;
import com.apemoon.tvbox.ui.view.MainTabView;
import com.apemoon.tvbox.ui.view.address.AddressSelectorNew;
import com.apemoon.tvbox.ui.view.address.BottomDialog;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.apemoon.tvbox.utils.GlobalUtil;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.widget.OnAddressSelectedListener;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView, OnAddressSelectedListener, AddressSelectorNew.OnDialogCloseListener, AddressSelectorNew.onSelectorAreaPositionListener {
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

    public MainTabView getMainTab() {
        return mMainTab;
    }

    @BindView(R.id.main_tab)
    MainTabView mMainTab;
    @BindView(R.id.fl_main)
    FrameLayout mFlMain;
    private BottomDialog dialog;

    public static final String USER_ENTITY = "user_entity";
    private UserEntity mUserEntity;

    private MainPresenter mMainPresenter;

    public static void actionStart(Context context, UserEntity userEntity) {//带参启动界面
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ENTITY, userEntity);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        onTabSelected(0);
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

        mMainPresenter = new MainPresenter(this, this);
    }

    private void setUserData(UserEntity.UserInfoBean userInfo) {
        GlideUtil.imageCircleLocal(this, userInfo.getHeadImage(), mIvHead);
        mTvSchoolName.setText(userInfo.getSchoolName());
        mTvName.setText(userInfo.getName());
        mTvGradeName.setText(userInfo.getGradeName());
    }

    @Override
    public void initListener() {
        setMainChangeListener(mMainTab);

        mMainTab.setIMainTavView(new IMainTabView() {
            @Override
            public void tabSelected(int position) {
                onTabSelected(position - 1);
                onRequestMainTabFocus();
            }

            @Override
            public void tabUnselected(int position) {
                onTabUnselected(position - 1);
                onRequestMainTabFocus();
            }
        });


    }


    @OnClick({R.id.iv_switch_school, R.id.iv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_switch_school://切换学校
                showSchoolPop();
                break;
            case R.id.iv_setting://设置
                SettingActivity.actionStart(this, mUserEntity);
                break;
        }
    }

    private void setMainChangeListener(View view) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setAdapter(view, hasFocus);
            }
        });
    }

    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = FragmentFactory.getIntance().getFragment(position);
        if(fragment == null){
            return;
        }
        fragment.setUserVisibleHint(true);
        if (!fragment.isAdded()) {
            transaction.replace(R.id.fl_main, fragment, String.valueOf(position));
        }
        transaction.show(fragment).commit();
    }

    public void onTabUnselected(int position) {
        Fragment fragment = FragmentFactory.getIntance().getFragment(position);
        if(fragment==null){
            return;
        }
        fragment.setUserVisibleHint(false);
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }


    @Override
    public void success() {

    }

    @Override
    public void fail() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mMainTab.hasFocus()) {
            mMainTab.setTabChange(keyCode);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setMainTabVisiable(boolean isVisiable) {
        if (isVisiable) {
            mMainTab.setVisibility(View.VISIBLE);
            mMainTab.requestFocus();
        } else {
            mMainTab.setVisibility(View.GONE);
        }
    }


    public void onRequestMainTabFocus() {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mMainTab == null) {
                        return;
                    }
                    mMainTab.rqFocus();
                }
            }, 1000);
    }

    @Override
    public void onDestroy() {
        FragmentFactory.getIntance().clearFragment();
        super.onDestroy();
    }

    private void showSchoolPop() {
        if (dialog != null) {
            dialog.show();
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
            dialog.show();
        }
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
    public void onAddressSelected(Province province, City city, County county, Street street) {

    }
}
