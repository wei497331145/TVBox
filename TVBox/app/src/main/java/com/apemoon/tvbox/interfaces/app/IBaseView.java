
package com.apemoon.tvbox.interfaces.app;


/**
 * Created by water on 2018/8/27/027.
 * des：
 */

public interface IBaseView {
    //填充布局
    int getLayoutRes();
    //初始化控件
    void initView();
    //初始画数据
    void initData();
    //初始化监听事件
    void initListener();



}
