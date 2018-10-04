package com.apemoon.tvbox.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.apemoon.tvbox.interfaces.app.IBaseView;
import com.trello.rxlifecycle2.components.support.RxFragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author water
 * @Date 2017-11-24
 * @des Fragment的基类
 */
public abstract class BaseFragment extends RxFragment implements IBaseView {
    public BaseActivity activity;
    protected ViewGroup mView;
    private boolean isFristCreate = true;
    protected Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            activity = (BaseActivity) getActivity();
            mView = (ViewGroup) activity.getLayoutInflater().inflate(getLayoutRes(), container, false);
            mUnBinder = ButterKnife.bind(this, mView);
            if (isFristCreate && getUserVisibleHint()) {
                isFristCreate = false;
            }
        }
        initView();
        initData();
        initListener();
        if (getUserVisibleHint()) {
            lazyLoadData();
        }
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mView == null) {
            return;
        }
        if (getUserVisibleHint()) {
            lazyLoadData();
        }
    }

    protected abstract void lazyLoadData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mUnBinder) {
            mUnBinder.unbind();
        }
        mView = null;
    }



}
