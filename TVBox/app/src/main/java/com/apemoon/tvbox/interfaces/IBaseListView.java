package com.apemoon.tvbox.interfaces;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */

public interface IBaseListView {

    void init();
    Boolean isLoadMore();
    BaseQuickAdapter getAdapter();
    RecyclerView getRecyclerView();

}
