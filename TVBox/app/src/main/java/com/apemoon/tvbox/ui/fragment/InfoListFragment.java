package com.apemoon.tvbox.ui.fragment;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.RxBaseListFragment;
import com.apemoon.tvbox.entity.VideoDetailInfo;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.factory.main.FragmentFactory;
import com.apemoon.tvbox.interfaces.fragment.IInformationView;
import com.apemoon.tvbox.presenter.InformationPresenter;
import com.apemoon.tvbox.ui.activity.MainActivity;
import com.apemoon.tvbox.ui.adapter.information.InfoImagesAdapter;
import com.apemoon.tvbox.ui.adapter.information.InfoTwoClassicalAdapter;
import com.apemoon.tvbox.ui.adapter.information.InformationAdapter;
import com.apemoon.tvbox.ui.adapter.information.InformationTvAdapter;
import com.apemoon.tvbox.ui.view.ItemLinearLayout;
import com.apemoon.tvbox.ui.view.RecycleViewDivider;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.DateTimeUtil;
import com.boredream.bdvideoplayer.BDVideoPlayer;
import com.boredream.bdvideoplayer.BDVideoView;
import com.boredream.bdvideoplayer.listener.SimpleOnVideoControlListener;
import com.boredream.bdvideoplayer.utils.DisplayUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class InfoListFragment extends RxBaseListFragment implements IInformationView {

    private InformationPresenter mInformaitonPresenter;
    private int currentTwoClassId;
    private int currentSelectItemPosition;
    private InformationTvAdapter mInformationAdater;
    private List<InfoListEntity.InformationBean> informationBeanList;

    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.ll_tv)
    LinearLayout llTv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.item_autor)
    ItemLinearLayout itemAuthor;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.video_view)
    BDVideoView videoView;
    @BindView(R.id.rv_info_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.rv_img_list)
    RecyclerView mRVImgList;

    private static final String CURRENT_TWO_CLASSICAL_ID = "currnet_two_classical_id";
    private static final String CURRENT_INFO_ITEM_ID = "currnet_info_item_id";

    private static final String TYPE_TEXT_ID = "1";
    private static final String TYPE_TURL_ID = "2";
    private static final String TYPE_VIDEO_ID = "3";
    private static final String TYPE_PAGES_ID = "4";

    public static InfoListFragment getInstance(int currentTwoClassId,int selectInfoPosition){
        InfoListFragment fragment = new InfoListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_TWO_CLASSICAL_ID,currentTwoClassId);//这里的values就是我们要传的值
        bundle.putInt(CURRENT_INFO_ITEM_ID,selectInfoPosition);//
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public Boolean isLoadMore() {
        return true;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        mInformationAdater = new InformationTvAdapter();
        return mInformationAdater;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_info_list;
    }



    @Override
    public RecyclerView getRecyclerView() {
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.transparent)));

        return mRecyclerView;
    }


    private void configWebView(WebView webView) {
        setTextChangeListener(mTvBack);
        if (null != webView) {
            webView.requestFocus();
            webView.setHorizontalScrollBarEnabled(true);
            webView.setVerticalScrollBarEnabled(true);
            WebSettings settings = webView.getSettings();
            if (null != settings) {
                settings.setJavaScriptEnabled(true);
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);

                settings.setAllowFileAccess(true); // 允许访问文件
                settings.setSupportZoom(true); // 支持缩放
                settings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
        }
    }


    @Override
    public void initData() {
        webView.setBackgroundColor(0);
//        webView.getBackground().setAlpha(0);
//        mLlWeb.setBackgroundResource(R.drawable.bg_bl_tv_selector);
        configWebView(webView);

        mInformaitonPresenter = new InformationPresenter(getActivity(), this);
    }


    @Override
    public void initListener() {
        mInformationAdater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InfoListEntity.InformationBean bean = mInformationAdater.getData().get(position);
                setTvContent(bean);
            }
        });

//        webView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean hasFocus) {
//                if (hasFocus) {
//                    mLlWeb.setBackgroundResource(R.drawable.bg_web_selected);
//                } else {
//                    mLlWeb.setBackgroundResource(R.drawable.bg_web_normal);
//                }
//            }
//        });
    }

    private void setTextChangeListener(View view) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setTextAnimation(view,hasFocus,1.1f,1.1f,1.0f,1.0f);
            }
        });
    }

    private void setTvContent(InfoListEntity.InformationBean bean){
        setContentsGone();
        if(bean!=null){
            switch (bean.getType()){
                case TYPE_TEXT_ID://富文本
                    initText(bean);
                    break;
                case TYPE_TURL_ID://链接
                    initWebView(bean);
                    break;
                case TYPE_PAGES_ID://图片
                    initImages(bean);
                    break;
                case TYPE_VIDEO_ID://视屏
                    initVideo(bean);
                    break;

            }
        }
    }

    private void setContentsGone(){
        llContent.setVisibility(View.GONE);
        llTv.setVisibility(View.GONE);
        mRVImgList.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void lazyLoadData() {
        requestNew();
    }


    @Override
    public void receiveInformationsSuccess(InfoListEntity entity) {
        if (entity != null) {
            informationBeanList = entity.getInformationList();
            if (informationBeanList != null && informationBeanList.size()>0) {
                setPageInfo(informationBeanList.size());
                setTvContent(informationBeanList.get(0));
                if(currentSelectItemPosition<informationBeanList.size()){
                    setTvContent(informationBeanList.get(currentSelectItemPosition));
                }
                switch (getRequestType()) {
                    case REQUESTTYPE_NEW_DATE:
                        mInformationAdater.setNewData(informationBeanList);
                        mRecyclerView.scrollToPosition(currentSelectItemPosition);
                        mRecyclerView.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                if(mRecyclerView.findViewHolderForAdapterPosition(currentSelectItemPosition)!=null )
                                {

                                    mRecyclerView.findViewHolderForAdapterPosition(currentSelectItemPosition).itemView.requestFocus();
                                }
                            }
                        },50);

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
        ((MainActivity)getActivity()).setMainTabVisiable(false);
        if(getArguments()!=null) {
            currentTwoClassId = getArguments().getInt(CURRENT_TWO_CLASSICAL_ID);
            currentSelectItemPosition = getArguments().getInt(CURRENT_INFO_ITEM_ID);
        }

    }

    @OnClick( R.id.tv_back)
    public void onViewClicked(View view) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        InfoListFragment.this.setUserVisibleHint(false);
        transaction.hide(InfoListFragment.this);
        Fragment fragment = FragmentFactory.getIntance().getFragment(5);
        fragment.setUserVisibleHint(true);
        transaction.show(fragment).commit();
        ((MainActivity)getActivity()).setMainTabVisiable(true);
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

    private void initText(InfoListEntity.InformationBean bean){
        llContent.setVisibility(View.VISIBLE);
        llTv.setVisibility(View.VISIBLE);
        tvTitle.setText(bean.getTitle());
        tvTime.setText(DateTimeUtil.getStrTime(bean.getCreateTime()));
        webView.loadDataWithBaseURL(null, bean.getContent(), "text/html", "utf-8", null);

    }


    /**
     * 加载图片
     * @param bean
     */
    private void initImages(InfoListEntity.InformationBean bean){
        InfoImagesAdapter imagesAdapter = new InfoImagesAdapter();
        mRVImgList.setAdapter(imagesAdapter);
        List<String>list = Arrays.asList(bean.getImages().split(";"));
        if(list.size()>0) {
            mRVImgList.setVisibility(View.VISIBLE);
            imagesAdapter.setNewData(list);
        }

    }

    /**
     * 加载webview
     */
    private void initWebView(InfoListEntity.InformationBean bean){
        llContent.setVisibility(View.VISIBLE);
        llTv.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
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
        webView.loadUrl(bean.getUrl());
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
     * @param bean
     */
    private void initVideo(InfoListEntity.InformationBean bean){
        if(TextUtils.isEmpty(bean.getVideos())){
            return;
        }
        videoView.setVisibility(View.VISIBLE);
        VideoDetailInfo info = new VideoDetailInfo(bean.getTitle(),bean.getVideos());

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
        videoView.requestFocus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity)getActivity()).setMainTabVisiable(true);
    }
}
