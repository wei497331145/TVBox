package com.apemoon.tvbox.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.apemoon.tvbox.app.ActivityManager;
import com.apemoon.tvbox.interfaces.app.IBaseView;
import com.apemoon.tvbox.utils.ConstantUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * @Author water
 * @Date 2017-11-24
 * @des Activity的基类
 */

@SuppressLint({"NewApi", "Registered"})
public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {

    Unbinder mUnbinder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使得音量键控制媒体声音
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
        if (getLayoutRes() != 0) {
            setContentView(getLayoutRes());
            mUnbinder = ButterKnife.bind(this);
        }

        //全局管理栈
        ActivityManager.getIntence().addActivity(this);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        //解决部分手机按home键后，再从桌面点击图标进入时，会重新从启动页进入的问题
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        initView();
        initData();
        initListener();
    }

    /*
    *  请求权限的回调
    * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public String getUserName() {//获取用户名
        return PreferenceUtil.getString(ConstantUtil.USER_ACCOUNT, "");
    }

    public String getPassword() {//获取用户密码
        return PreferenceUtil.getString(ConstantUtil.USER_PASSWORD, "");
    }


    @Override
    protected void onDestroy() {
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
        ActivityManager.getIntence().removeActivity(this);
        super.onDestroy();
    }



}
