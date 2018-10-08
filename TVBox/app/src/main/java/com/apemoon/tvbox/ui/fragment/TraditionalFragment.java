package com.apemoon.tvbox.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseFragment;
import com.apemoon.tvbox.base.RxBaseListFragment;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.interfaces.fragment.IInformationView;
import com.apemoon.tvbox.presenter.InformationPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class TraditionalFragment extends RxBaseListFragment implements IInformationView {

    private InformationPresenter mInformaitonPresenter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_traditional_culture;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mInformaitonPresenter = new InformationPresenter(getActivity(),this);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void lazyLoadData() {
        requestNew();
    }

    @Override
    public void receiveInformationsSuccess(InfoListEntity entity) {

    }

    @Override
    public void receiveInformationsFail() {

    }

    @Override
    public void init() {

    }


    @Override
    public void requestNew() {
        super.requestNew();
        if (mInformaitonPresenter != null) {
            mInformaitonPresenter.receiveInformations(String.valueOf(getCurrentPage()), String.valueOf(getPageSize()));
        }
    }

    @Override
    public void onLoadMoreRequested() {
        super.onLoadMoreRequested();
        mInformaitonPresenter.receiveInformations(String.valueOf(getCurrentPage()), String.valueOf(getPageSize()));
    }



    @Override
    public Boolean isLoadMore() {
        return null;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return null;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return null;
    }
}
