package com.apemoon.tvbox.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseActivity;
import com.apemoon.tvbox.entity.AppUpdateEntity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.factory.main.FragmentFactory;
import com.apemoon.tvbox.interfaces.IMainTabView;
import com.apemoon.tvbox.interfaces.IMainView;
import com.apemoon.tvbox.presenter.MainPresenter;
import com.apemoon.tvbox.ui.view.MainTabView;
import com.apemoon.tvbox.ui.view.address.AddressSelectorNewB;
import com.apemoon.tvbox.ui.view.address.BottomDialog;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.apemoon.tvbox.utils.GlobalUtil;
import com.king.app.updater.AppUpdater;
import com.king.app.updater.UpdateConfig;
import com.king.app.updater.callback.UpdateCallback;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.widget.OnAddressSelectedListener;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView, OnAddressSelectedListener, AddressSelectorNewB.OnDialogCloseListener, AddressSelectorNewB.onSelectorAreaPositionListener {
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

    public MainTabView getMainTab() {
        return mMainTab;
    }
    public View getSwitchSchool() {
        return mIvSwitchSchool;
    }

    @BindView(R.id.main_tab)
    MainTabView mMainTab;
    @BindView(R.id.fl_main)
    FrameLayout mFlMain;
    private BottomDialog dialog;

    public static final String USER_ENTITY = "user_entity";
    private UserEntity mUserEntity;

    private MainPresenter mMainPresenter;

    private Context mContext;

    private final Object mLock = new Object();

    private Toast toast;


    public static void actionStart(Context context, UserEntity userEntity) {//带参启动界面
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
        mMainPresenter.getSysAppVersion();
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
        setSettingChangeListener(mIvSetting);

        mMainTab.setIMainTavView(new IMainTabView() {
            @Override
            public void tabSelected(int position) {
                onTabSelected(position - 1);

            }

            @Override
            public void tabUnselected(int position) {
                onTabUnselected(position - 1);
            }
        });
        mMainTab.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(view!=null){
                    if(b) {
                        mMainTab.setTab();
                    }else{
                        mMainTab.setTabUnable();
                    }
                }
            }
        });


    }


    @OnClick({R.id.rl_switch_school, R.id.iv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_switch_school://切换学校
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

    private void setSettingChangeListener(View view) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setSettingAdapter(view, hasFocus);
            }
        });
    }

    public void onTabSelected(int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!MainActivity.this.isFinishing()) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    Fragment fragment = FragmentFactory.getIntance().getFragment(position);
                    if (fragment == null) {
                        return;
                    }
                    fragment.setUserVisibleHint(true);
                    if (!fragment.isAdded()) {
                        transaction.replace(R.id.fl_main, fragment, String.valueOf(position));
                    }
                    transaction.show(fragment).commitAllowingStateLoss();
                }
            }
        },100);

    }

    public void onTabUnselected(int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!MainActivity.this.isFinishing()) {
                    Fragment fragment = FragmentFactory.getIntance().getFragment(position);
                    if (fragment == null) {
                        return;
                    }
                    fragment.setUserVisibleHint(false);
                    getSupportFragmentManager().beginTransaction().hide(fragment).commit();
                }
            }
        },100);
    }


    @Override
    public void success() {

    }

    @Override
    public void fail() {

    }

    @Override
    public void getSystemAppVersion(AppUpdateEntity entity) {
        if(updateApp(TvApplication.getVersionName(),entity.getConfigdesc())) {
            showAppUpgradeDialog(mRootView, MainActivity.this, entity);
        }
    }


    @Override
    public void getSystemAppVersionFail() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mMainTab.hasFocus()) {
            boolean flag = mMainTab.setTabChange(keyCode);
            if(flag){
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setMainTabVisiable(boolean isVisiable) {
        if(mMainTab!=null) {
            if (isVisiable) {
                mMainTab.setVisibility(View.VISIBLE);
                mIvSwitchSchool.setNextFocusDownId(mMainTab.getId());
                mMainTab.requestFocus();
            } else {
                mMainTab.setVisibility(View.GONE);

            }
        }
    }


//    public void onRequestMainTabFocus() {
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (mMainTab == null) {
//                        return;
//                    }
//                    mMainTab.rqFocus();
//                }
//            }, 1000);
//    }

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


    }

    @Override
    public void selectorAreaPosition(int provincePosition, int cityPosition, int countyPosition, int streetPosition) {
        GlobalUtil.showToast("选择成功");
        dialog.dismiss();
        recreate();
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {

    }


    /**
     * App 升级提示
     * @param view
     * @param context
     * @param entity
     */
    public void showAppUpgradeDialog(View view, Activity context, AppUpdateEntity entity) {

        View popupView = context.getLayoutInflater().inflate(R.layout.layout_pop_show_app_upgrade, null);

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();


        PopupWindow popView = new PopupWindow(popupView, width*2/3, height*2/3, true);
        popView.setTouchable(true);
        popView.setOutsideTouchable(false);
        // 设置背景为半透明灰色
        popView.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        // 设置动画
        popView.setAnimationStyle(R.style.invitation_anim);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popView.dismiss();
                return true;
            }
        });

        popView.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView mTvVersion = (TextView)popupView.findViewById(R.id.tv_version);

        TextView mTvEnsure = (TextView)popupView.findViewById(R.id.tv_ensure);

        ProgressBar progressBar = (ProgressBar)popupView.findViewById(R.id.progressBar);

        mTvVersion.setText("最新版本："+entity.getConfigdesc());

        mTvEnsure.requestFocus();


        mTvEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateConfig config = new UpdateConfig();
                config.setUrl(entity.getConfigvalue());
                new AppUpdater(getContext(),config)
                        .setUpdateCallback(new UpdateCallback() {

                            @Override
                            public void onDownloading(boolean isDownloading) {
                                if(isDownloading){
                                    showToast("已经在下载中,请勿重复下载。");
                                }
                            }

                            @Override
                            public void onStart(String url) {
                                progressBar.setProgress(0);
                                progressBar.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onProgress(int progress, int total, boolean isChange) {
                                if(isChange){
                                    progressBar.setMax(total);
                                    progressBar.setProgress(progress);
                                }
                            }

                            @Override
                            public void onFinish(File file) {
                                progressBar.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onError(Exception e) {
                                progressBar.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onCancel() {
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        })
                        .start();            }
        });


    }
    public void showToast(String text){
        if(toast == null){
            synchronized (mLock){
                if(toast == null){
                    toast = Toast.makeText(getContext(),text,Toast.LENGTH_SHORT);
                }
            }
        }
        toast.setText(text);
        toast.show();
    }


    public Context getContext(){
        return this;
    }


    /**
     * 判断版本更新
     * @param localVersion 本地app 版本号
     * @param newVersion 最新版本号
     * @return true 需要更新 false 不用
     */
    public boolean updateApp(String localVersion, String newVersion) {
        try {
            Double dlocal = Double.parseDouble(localVersion);
            Double dnew = Double.parseDouble(newVersion);
            if(dnew > dlocal){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
