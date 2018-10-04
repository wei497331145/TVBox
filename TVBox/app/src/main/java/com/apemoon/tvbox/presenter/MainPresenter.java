package com.apemoon.tvbox.presenter;

import android.content.Context;
import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.base.rx.ProgressObserver;
import com.apemoon.tvbox.base.rx.RxBasePresenter;
import com.apemoon.tvbox.interfaces.IMainView;
import com.apemoon.tvbox.utils.RequestUtil;
import java.util.Map;

/**
 * @Des 主界面的控制器
 * @Author water
 * @Date 2018-04-11
 */
public class MainPresenter extends RxBasePresenter {

    private IMainView mIMainView;


    public MainPresenter(Context context, IMainView iMainView) {
        super(context);
        mIMainView = iMainView;
    }



}
