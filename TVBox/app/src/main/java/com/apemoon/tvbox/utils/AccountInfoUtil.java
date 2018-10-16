package com.apemoon.tvbox.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.entity.AccountListEntity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.ui.adapter.AccountAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.SemestersAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.TeachersAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 wxj
 * 创建日期 2018/10/16.
 */

public class AccountInfoUtil {

    /**
     * 新增账户
     *
     * @param userEntity
     * @param mAccount
     * @param mPassword
     */
    public static void saveAccount(UserEntity userEntity, String mAccount, String mPassword) {
        if (!AccountInfoUtil.isExistsAccount(mAccount)) {
            AccountListEntity accountListEntity = new AccountListEntity();
            UserEntity.UserInfoBean userInfoBean = userEntity.getUserInfo();
            AccountListEntity.AccountInfoBean bean = accountListEntity.new AccountInfoBean(mAccount, mPassword, userInfoBean.getName(), userInfoBean.getHeadImage());
            List<AccountListEntity.AccountInfoBean> accountInfoBeanList = AccountInfoUtil.getAccountList();
            accountInfoBeanList.add(bean);
            //保存本地
            String json = new Gson().toJson(accountInfoBeanList);
            PreferenceUtil.getString(ConstantUtil.ACCOUNT_LIST_STR, json);
        }

    }

    /**
     * 获取账户列表
     *
     * @return
     */
    public static List<AccountListEntity.AccountInfoBean> getAccountList() {
        String accountStr = PreferenceUtil.getString(ConstantUtil.ACCOUNT_LIST_STR, "");
        AccountListEntity entity = new Gson().fromJson(accountStr, AccountListEntity.class);
        if (entity != null) {
            return entity.getAccountInfoBeanList();
        }
        return null;
    }

    /**
     * 是否已存在账户
     *
     * @return
     */
    public static boolean isExistsAccount(String mAccount) {
        List<AccountListEntity.AccountInfoBean> accountInfoBeanList = AccountInfoUtil.getAccountList();

        for (AccountListEntity.AccountInfoBean bean : accountInfoBeanList) {
            if (mAccount.equals(bean.getAccountNo())) {
                return true;
            }
        }

        return false;
    }

    public static void showSelectAccountWindow(View view, Activity context) {
        List<AccountListEntity.AccountInfoBean> accountInfoBeanList = AccountInfoUtil.getAccountList();
        if(accountInfoBeanList.size()<1){
            return;
        }
        View popupView = context.getLayoutInflater().inflate(R.layout.layout_pop_select_account, null);
        PopupWindow popView = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
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

        popView.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        RecyclerView mRecyclerView = popupView.findViewById(R.id.recyclerView);


        AccountAdapter mTeachersAdapter = new AccountAdapter();
        LinearLayoutManager ms = new LinearLayoutManager(context);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(ms);
        mRecyclerView.setAdapter(mTeachersAdapter);
        mTeachersAdapter.setNewData(accountInfoBeanList);


    }
}
