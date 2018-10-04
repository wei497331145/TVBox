package com.apemoon.tvbox.interfaces.app;

import android.app.Dialog;

import io.reactivex.disposables.Disposable;

/**
 * Created by water on 2018/8/27/027.
 * des：
 */

public interface IBaseRxPresenter {

    void subscribe(Disposable disposable);

    void unSubscribe();
}
