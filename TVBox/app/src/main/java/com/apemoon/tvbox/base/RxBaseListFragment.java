package com.apemoon.tvbox.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.apemoon.tvbox.interfaces.IBaseListView;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * @Des 列表基类
 * @Author water
 * @Date 2018-07-05
 */

public abstract class RxBaseListFragment extends BaseFragment implements IBaseListView,
        BaseQuickAdapter.RequestLoadMoreListener {


    protected RecyclerView mRecyclerView;

    protected BaseQuickAdapter mBaseQuickAdapter;

    protected View mEmptyView;

    private int mCurrentPage = 1;

    private int mPageSize = 20;

    private int mRequestType = REQUESTTYPE_NEW_DATE;

    public static final int REQUESTTYPE_NEW_DATE = 1;
    public static final int REQUESTTYPE_ADD_DATE = 2;

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        mCurrentPage = currentPage;
    }


    public int getPageSize() {
        return mPageSize;
    }

    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }

    public int getRequestType() {
        return mRequestType;
    }

    public void setRequestType(int requestType) {
        mRequestType = requestType;
    }


    @Override
    public void initView() {
        mBaseQuickAdapter = getAdapter();
        mRecyclerView = getRecyclerView();
        if (mRecyclerView != null) {
//            mRecyclerView.setAdapter(mBaseQuickAdapter);
            mBaseQuickAdapter.bindToRecyclerView(mRecyclerView);
            if (isLoadMore()) {
                mBaseQuickAdapter.setEnableLoadMore(true);
                mBaseQuickAdapter.setOnLoadMoreListener(this, mRecyclerView);
            }
        }
        init();
    }


    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (mRecyclerView != null){
            mRecyclerView.setLayoutManager(layoutManager);
        }
    }

    public void requestNew() {
        setCurrentPage(1);
        setRequestType(REQUESTTYPE_NEW_DATE);

    }

    @Override
    public void onLoadMoreRequested() {
        setRequestType(REQUESTTYPE_ADD_DATE);
    }

    public void setPageInfo(int size) {
        setCurrentPage(getCurrentPage() + 1);
        switch (getRequestType()) {
            case REQUESTTYPE_NEW_DATE:
                if (size == 0) {

                } else if (size <getPageSize()) {
                    mBaseQuickAdapter.loadMoreEnd();
                }
                break;
            case REQUESTTYPE_ADD_DATE:
                if (mBaseQuickAdapter != null) {
                    if (size == 0) {
                        mBaseQuickAdapter.loadMoreEnd();
                    } else {
                        mBaseQuickAdapter.loadMoreComplete();
                    }
                }
                break;
        }
    }




















}
