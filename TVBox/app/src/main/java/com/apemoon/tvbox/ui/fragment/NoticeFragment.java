package com.apemoon.tvbox.ui.fragment;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.RxBaseListFragment;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.interfaces.notice.IReceiveNoticeView;
import com.apemoon.tvbox.presenter.NoticePresenter;
import com.apemoon.tvbox.ui.adapter.NoticeAdapter;
import com.apemoon.tvbox.utils.LogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static java.lang.System.in;

/**
 * Created by water on 2018/8/28/028.
 * des：公告通知的碎片
 */

public class NoticeFragment extends RxBaseListFragment implements IReceiveNoticeView {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.ll_web)
    LinearLayout mLlWeb;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private NoticeAdapter mNoticeAdapter;
    private NoticePresenter mNoticePresenter;

    @Override
    public Boolean isLoadMore() {
        return true;
    }

    @Override
    public BaseQuickAdapter<?, ?> getAdapter() {
        mNoticeAdapter = new NoticeAdapter();
        return mNoticeAdapter;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_notice;
    }

    @Override
    public void init() {
        mWebView.setBackgroundColor(0);
        mWebView.getBackground().setAlpha(0);
        mLlWeb.setBackgroundResource(R.drawable.bg_web_selected);

        configWebView(mWebView);
    }

    @Override
    public void initData() {
        mNoticePresenter = new NoticePresenter(activity, this);

    }


    @Override
    public void requestNew() {
        super.requestNew();
        if (mNoticePresenter != null) {
            mNoticePresenter.receiveNoticeList(String.valueOf(getCurrentPage()), String.valueOf(getPageSize()));
        }
    }

    @Override
    public void onLoadMoreRequested() {
        super.onLoadMoreRequested();
        mNoticePresenter.receiveNoticeList(String.valueOf(getCurrentPage()), String.valueOf(getPageSize()));
    }

    @Override
    public void initListener() {
        mNoticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ReceiveNoticeListEntity.NoticeListBean noticeListBean = mNoticeAdapter.getData().get(position);
                if (noticeListBean != null) {
                    mTvTitle.setText(noticeListBean.getTitle());
                    mWebView.loadDataWithBaseURL(null, noticeListBean.getContent(), "text/html", "utf-8", null);
                }
            }
        });
        mWebView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mLlWeb.setBackgroundResource(R.drawable.bg_web_selected);
                } else {
                    mLlWeb.setBackgroundResource(R.drawable.bg_web_normal);
                }
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        requestNew();
    }

    private void configWebView(WebView webView) {
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
    public void onDestroy() {
        if (null != mWebView) {
            //解决webview destroy崩溃的问题
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (null != parent) {
                parent.removeView(mWebView);
            }
            mWebView.clearCache(true);
            mWebView.clearHistory();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }


    @Override
    public void getReceiveNoticeListSuccess(ReceiveNoticeListEntity receiveNoticeListEntity) {
        if (receiveNoticeListEntity != null) {
            List<ReceiveNoticeListEntity.NoticeListBean> noticeList = receiveNoticeListEntity.getNoticeList();
            if (noticeList != null) {
                setPageInfo(noticeList.size());
                switch (getRequestType()) {
                    case REQUESTTYPE_NEW_DATE:
                        if (noticeList.size() != 0) {
                            ReceiveNoticeListEntity.NoticeListBean noticeListBean = noticeList.get(0);
                            if (noticeList != null) {
                                mTvTitle.setText(noticeListBean.getTitle());
                                mWebView.loadDataWithBaseURL(null, noticeListBean.getContent(), "text/html", "utf-8", null);
                            }
                        }
                        mNoticeAdapter.setNewData(noticeList);
                        break;
                    case REQUESTTYPE_ADD_DATE:
                        mNoticeAdapter.addData(noticeList);
                        break;
                }
            }
        }
    }

    @Override
    public void getReceiveNoticeListFail() {

    }




}
