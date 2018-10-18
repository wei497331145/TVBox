package com.apemoon.tvbox.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.apemoon.tvbox.entity.AccountListEntity;
import com.apemoon.tvbox.presenter.LoginPresenter;
import com.apemoon.tvbox.presenter.SettingPresenter;
import com.apemoon.tvbox.ui.adapter.AccountAdapter;
import com.apemoon.tvbox.ui.view.address.AddressSelectorNew;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smarttop.library.R;
import com.smarttop.library.widget.OnAddressSelectedListener;

import java.util.List;


/**
 * Created by smartTop on 2016/10/19.
 */

public class AccountDialog extends Dialog {

    private Activity context;
    List<AccountListEntity.AccountInfoBean> accountInfoBeanList;
    private SettingPresenter mSettingPresenter;

    public AccountDialog(Activity context, List<AccountListEntity.AccountInfoBean> mAccountInfoBeanList, SettingPresenter presenter) {
        super(context, R.style.bottom_dialog);
        mSettingPresenter = presenter;
        accountInfoBeanList = mAccountInfoBeanList;
        this.context = context;

    }

    public AccountDialog(Activity context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    public AccountDialog(Activity context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Activity context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.layout_dialog_fee, null);

        setContentView(layout);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        params.width = width/2;
        params.height = height/2;
        window.setAttributes(params);

        window.setGravity(Gravity.CENTER);

        RecyclerView mRecyclerView = findViewById(com.apemoon.tvbox.R.id.recyclerView);


        AccountAdapter mAccountAdapter = new AccountAdapter();
        LinearLayoutManager ms = new LinearLayoutManager(context);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(ms);
        mRecyclerView.setAdapter(mAccountAdapter);
        mAccountAdapter.setNewData(accountInfoBeanList);

        mAccountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AccountListEntity.AccountInfoBean bean = accountInfoBeanList.get(position);
                mSettingPresenter.login(bean.getAccountNo(),bean.getAccountPwd());
            }
        });

    }



    public static AccountDialog show(Activity context) {
        AccountDialog dialog = new AccountDialog(context, R.style.bottom_dialog);
        dialog.show();

        return dialog;
    }


}
