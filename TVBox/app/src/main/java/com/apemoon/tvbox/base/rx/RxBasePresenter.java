package com.apemoon.tvbox.base.rx;

import android.content.Context;
import com.apemoon.tvbox.base.net.DataManager;
import com.apemoon.tvbox.interfaces.app.IBaseRxPresenter;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @Des 解决rxjava内存泄露问题
 * @Author zhouy
 * @Date 2018-02-01
 */

public abstract class RxBasePresenter implements IBaseRxPresenter {

    protected Context mContext;

    protected DataManager mDataManager;

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public RxBasePresenter(Context context){
        mContext = context;
        mDataManager = DataManager.getInstance(context);
    }

    @Override
    public void subscribe(Disposable disposable) {
        if(null != mCompositeDisposable){
            mCompositeDisposable.add(disposable);
        }
    }

    /**
     * 取消祖册
     */
    @Override
    public void unSubscribe() {
        if(null != mCompositeDisposable){
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
    }


    public <T> void addDisposable(Observable<T> observable, DisposableObserver<T> observer){
        observable
                .onTerminateDetach()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(((RxAppCompatActivity)mContext).<T>bindToLifecycle())
                .compose(((RxAppCompatActivity)mContext).<T>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(observer);
        subscribe(observer);
    }




}
