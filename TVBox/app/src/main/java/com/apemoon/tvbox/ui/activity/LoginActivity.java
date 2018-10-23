package com.apemoon.tvbox.ui.activity;

import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseActivity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.interfaces.ILoginView;
import com.apemoon.tvbox.presenter.LoginPresenter;
import com.apemoon.tvbox.utils.AccountInfoUtil;
import com.apemoon.tvbox.utils.ConstantUtil;
import com.apemoon.tvbox.utils.GlobalUtil;
import com.apemoon.tvbox.utils.MD5EncoderUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by water on 2018/8/27/027.
 * des：
 */

public class LoginActivity extends BaseActivity implements ILoginView{
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.tv_login)
    Button mTvLogin;



    private String mAccount;
    private String mPassword;
    private LoginPresenter mLoginPresenter;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mEtAccount.setText(getUserName());
        mEtPassword.setText(getPassword());
        mEtAccount.setSelection(getUserName().length());//光标放在号码的最后

    }

    /**
     * 当再次startActivity的时候，接收新的Intent对象
     * 调用的前提是该启动模式是singleTask，或者singleTop但是他得在最上面才有效
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {//数据回调
        mEtAccount.setText(getUserName());
        mEtPassword.setText(getPassword());
        mEtAccount.setSelection(getUserName().length());//光标放在号码的最后
    }



    @Override
    public void initData() {
        mLoginPresenter = new LoginPresenter(this, this);
    }

    @Override
    public void initListener() {
        mTvLogin.setFocusable(true);
        mTvLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {//当选中这个View时做一些你所需要的操作
                    view.setScaleX(1.1f);
                    view.setScaleY(1.1f);
                } else {
                    view.setScaleX(1.0f);
                    view.setScaleY(1.0f);
                }

            }
        });
    }

    @OnClick({R.id.tv_login,R.id.iv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                login();
                break;
            case R.id.iv_setting:
                Intent intent =  new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                break;
        }
    }

    private void login() {
        mAccount = mEtAccount.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(mAccount)) {
            GlobalUtil.showToast("请先输入账号");
            return;
        }
        if (TextUtils.isEmpty(mPassword)) {
            GlobalUtil.showToast("请先输入密码");
            return;
        }
        mLoginPresenter.login(mAccount, mPassword);
    }



    /*
    *   用户登录成功
    * */
    @Override
    public void loginSuccess(UserEntity userEntity,String code,String mAccount,String mPassword) {
        if (userEntity != null) {
            if (!TextUtils.equals(userEntity.getUserType(),"2")) {//2 学生
                GlobalUtil.showToast("只能登录学生的账号");
                return;
            }
            PreferenceUtil.saveAccountDdata(userEntity,mAccount,mPassword);
            MainActivity.actionStart(this,userEntity);
            finish();
        } else {
            GlobalUtil.showToast("用户为空");
        }
    }



    /*
     *  用户登录失败
     * */
    @Override
    public void loginFail() {

    }





}
