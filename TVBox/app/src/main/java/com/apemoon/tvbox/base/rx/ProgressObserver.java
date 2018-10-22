package com.apemoon.tvbox.base.rx;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.apemoon.tvbox.app.ActivityManager;
import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.net.HttpResultListBody;
import com.apemoon.tvbox.ui.activity.SettingActivity;
import com.apemoon.tvbox.ui.view.LoadingDialog;
import com.apemoon.tvbox.utils.GlobalUtil;
import com.apemoon.tvbox.utils.NetworkUtils;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import io.reactivex.observers.DisposableObserver;

/**
 * @Des 统一errorCode弹窗处理
 * @Author water
 * @Date 2018-08-27
 */

public abstract class ProgressObserver<T> extends DisposableObserver<T> {

    public static final int SHOW_LOADING = 10;

    private Context mContext;

    private LoadingDialog mLoadingDialog;
    private static String mHint;
    private boolean mIsShowLoading;
    private MHandler mMHandler;

    private static class MHandler extends Handler {
        private WeakReference<ProgressObserver> mWeakReference;

        public MHandler(ProgressObserver observer) {
            mWeakReference = new WeakReference<ProgressObserver>(observer);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ProgressObserver observer = mWeakReference.get();
            if (null != observer) {
                switch (msg.what) {
                    case SHOW_LOADING:
                        observer.showLoadingDialog(mHint);
                        break;
                }
            }
        }
    }

    public ProgressObserver(Context context, boolean isShowLoading) {
        mContext = context;
        mHint = "正在加载中...";
        mIsShowLoading = isShowLoading;
        mMHandler = new MHandler(this);

    }

    public ProgressObserver(Context context, boolean isShowLoading, String hint) {
        this(context, isShowLoading);
        mHint = hint;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!NetworkUtils.isNetworkConnect(mContext)) {
            GlobalUtil.showToast("网络中断，请检查您的网络状态");
            return;
        }
        if (null != mMHandler) {
            mMHandler.sendEmptyMessage(SHOW_LOADING);
        }
    }

    @Override
    public void onNext(T t) {
        if (t instanceof HttpResultBody) {
            HttpResultBody body = (HttpResultBody) t;
            if (null != body) {
                dealUnifiedCode(t, body.code, body.message);
            }
        } else if (t instanceof HttpResultListBody) {
            HttpResultListBody listBody = (HttpResultListBody) t;
            if (null != listBody) {
                dealUnifiedCode(t, listBody.code, listBody.message);
            }
        }
    }

    private void dealUnifiedCode(T t, String errorCode, String msg) {
        //1：token失效  5： 账号被禁用 14: 群组中被踢
        dismissLoadingDialog();
        switch (errorCode) {
            case "0000":
                doNext(t);
                break;
            case "10000":
                ActivityManager.getIntence().logout(mContext);
                showToast(msg);
                break;
            default:
                doNext(t);
                showToast(msg);
                break;
        }
    }


    public abstract void doNext(T t);

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            GlobalUtil.showToast("网络请求超时，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            GlobalUtil.showToast("网络连接异常，请检查您的网络状态");
        } else if (e instanceof SocketException) {
            GlobalUtil.showToast("数据请求关闭异常，请重试");
        }
        dismissLoadingDialog();
    }

    @Override
    public void onComplete() {
        dismissLoadingDialog();
    }

    private void showLoadingDialog(String hint) {
        if (mIsShowLoading) {
            if (null == mLoadingDialog) {
                mLoadingDialog = new LoadingDialog(mContext);
            }
            mLoadingDialog.setHint(hint);
            mLoadingDialog.showLoading();
        }
    }

    private void dismissLoadingDialog() {
        if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dimissLoading();
        }
    }

    /**
     * 提示信息(activity)
     */
    public void showToast(String error_msg) {
        if (  !TextUtils.isEmpty(error_msg)) {//气泡方式
            GlobalUtil.showToast(error_msg);
        }/* else if (error_tips.equals("B") && !TextUtils.isEmpty(error_msg)) {//模态窗口
            //new AlertDialog.Builder(activity).setTitle("提示信息").setMessage(error_msg).show();
        }*/
    }

    public void destroy() {
        dismissLoadingDialog();
        mLoadingDialog = null;
    }
}
