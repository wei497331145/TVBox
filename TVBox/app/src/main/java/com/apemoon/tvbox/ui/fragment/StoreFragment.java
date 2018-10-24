package com.apemoon.tvbox.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseFragment;

import butterknife.BindView;

public class StoreFragment  extends BaseFragment{
    @BindView(R.id.emptyRootLayout)
    LinearLayout emptyRootLayout;
    @Override
    protected void lazyLoadData() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_store;
    }

    @Override
    public void initView() {
        emptyRootLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
