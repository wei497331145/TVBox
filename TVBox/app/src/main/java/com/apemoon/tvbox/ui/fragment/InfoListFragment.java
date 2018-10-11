package com.apemoon.tvbox.ui.fragment;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.RxBaseListFragment;
import com.apemoon.tvbox.entity.VideoDetailInfo;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.interfaces.fragment.IInformationView;
import com.apemoon.tvbox.presenter.InformationPresenter;
import com.apemoon.tvbox.ui.adapter.information.InformationAdapter;
import com.boredream.bdvideoplayer.BDVideoPlayer;
import com.boredream.bdvideoplayer.BDVideoView;
import com.boredream.bdvideoplayer.listener.SimpleOnVideoControlListener;
import com.boredream.bdvideoplayer.utils.DisplayUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class InfoListFragment extends RxBaseListFragment implements IInformationView {

    private InformationPresenter mInformaitonPresenter;
    private int currentTwoClassId;
    private InformationAdapter mInformationAdater;
    private List<InfoListEntity.InformationBean> informationBeanList;

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.video_view)
    BDVideoView videoView;
    @BindView(R.id.rv_info_list)
    RecyclerView mRVInfoList;
    @BindView(R.id.rv_img_list)
    RecyclerView mRVImgList;



    @Override
    public Boolean isLoadMore() {
        return true;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        mInformationAdater = new InformationAdapter();
        return mInformationAdater;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_info_list;
    }



    @Override
    public RecyclerView getRecyclerView() {
        return mRVInfoList;
    }

    @Override
    public void initData() {
        mInformaitonPresenter = new InformationPresenter(getActivity(), this);
        GridLayoutManager mManagerLayout = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mManagerLayout);
    }

    @Override
    public void initListener() {
        mInformationAdater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected void lazyLoadData() {
        mInformaitonPresenter.receiveInfoClassfication();
    }

    @Override
    public void receiveInformationsSuccess(InfoListEntity entity) {
        if (entity != null) {
            informationBeanList = entity.getInformationList();
            if (informationBeanList != null) {
                setPageInfo(informationBeanList.size());
                switch (getRequestType()) {
                    case REQUESTTYPE_NEW_DATE:
                        mInformationAdater.setNewData(informationBeanList);
                        break;
                    case REQUESTTYPE_ADD_DATE:
                        mInformationAdater.addData(informationBeanList);
                        break;
                }
            }
        }
    }

    @Override
    public void receiveInformationsFail() {

    }

    @Override
    public void receiveInformationClassicalSuccess(InfoClassicalEntity entity) {

    }

    @Override
    public void receiveInformationClassicalFail() {

    }

    @Override
    public void init() {

    }


    @Override
    public void requestNew() {
        super.requestNew();
        if (mInformaitonPresenter != null) {
            mInformaitonPresenter.receiveInformations(String.valueOf(getCurrentPage()), String.valueOf(getPageSize()), String.valueOf(currentTwoClassId));
        }
    }

    @Override
    public void onLoadMoreRequested() {
        super.onLoadMoreRequested();
        mInformaitonPresenter.receiveInformations(String.valueOf(getCurrentPage()), String.valueOf(getPageSize()), String.valueOf(currentTwoClassId));
    }



    /**
     * 加载webview
     */
    private void initWebView(String url) {
        WebSettings webSettings = webView.getSettings();
        // 是否允许javascript
        webSettings.setJavaScriptEnabled(true);
        // NORMAL：正常显示，没有渲染变化。
        // SINGLE_COLUMN：把所有内容放到WebView组件等宽的一列中。
        // NARROW_COLUMNS：可能的话，使所有列的宽度不超过屏幕宽度。
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        // 设置默认的字体大小，默认为16，有效值区间在1-72之间。
        //webSettings.setDefaultFontSize(18);
        // 无痕加载
        //webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDefaultTextEncodingName("UTF-8");
        //提高渲染的优先级
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 将图片下载阻塞
        webSettings.setBlockNetworkImage(true);
        // 保留缩放功能但隐藏缩放控件
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        // 注意：setDisplayZoomControls是在Android 3.0中新增的API.
        webSettings.setDisplayZoomControls(false);
        // 加载URL
        webView.loadUrl(url);
        // 添加js可调用的接口
//        webView.addJavascriptInterface(new JS(), "jl");
        //屏蔽掉长按事件 因为webview长按时将会调用系统的复制控件:
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());

    }


    /**
     * WebViewClient主要帮助WebView处理各种通知、请求事件的，比如：
     * onLoadResource
     * onPageStart
     * onPageFinish
     * onReceiveError
     * onReceivedHttpAuthRequest
     */
    private class MyWebViewClient extends WebViewClient {

        // 在点击请求的链接时才会调用，
        // 重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        // 重写此方法可以让webview处理https请求
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        // 重写此方法才能够处理在浏览器中的按键事件。
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }

        // 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        // 在页面加载开始时调用
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        // 在页面加载结束时调用。
        // WebView 在Android4.4的手机上onPageFinished()回调会多调用一次(具体原因待追查)
        // 需要尽量避免在onPageFinished()中做业务操作，否则会导致重复调用，还有可能会引起逻辑上的错误.
        @Override
        public void onPageFinished(WebView view, String url) {
            // 载入图片
            webView.getSettings().setBlockNetworkImage(false);
        }

        // 页面加载出错时调用
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    /**
     * WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等比如
     * onCloseWindow(关闭WebView)
     * onCreateWindow()
     * onJsAlert (WebView上alert无效，需要定制WebChromeClient处理弹出)
     * onJsPrompt
     * onJsConfirm
     * onProgressChanged
     * onReceivedIcon
     * onReceivedTitle
     */
    private class MyWebChromeClient extends WebChromeClient {

        // 获取网页的title
        @Override
        public void onReceivedTitle(WebView view, String title) {

            super.onReceivedTitle(view, title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    }

    /**
     * 播放视屏
     * @param info
     */
    private void initVideo(VideoDetailInfo info){


        videoView.setOnVideoControlListener(new SimpleOnVideoControlListener() {

            @Override
            public void onRetry(int errorStatus) {
                // TODO: 2017/6/20 调用业务接口重新获取数据
                // get info and call method "videoView.startPlayVideo(info);"
            }

            @Override
            public void onBack() {
                onDestroy();
            }

            @Override
            public void onFullScreen() {
                DisplayUtils.toggleScreenOrientation(getActivity());
            }
        });
        videoView.startPlayVideo(info);
    }


}
