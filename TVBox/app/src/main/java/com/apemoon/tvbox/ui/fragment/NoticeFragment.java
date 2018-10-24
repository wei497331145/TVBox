package com.apemoon.tvbox.ui.fragment;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.RxBaseListFragment;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.interfaces.fragment.IReceiveNoticeView;
import com.apemoon.tvbox.presenter.NoticePresenter;
import com.apemoon.tvbox.ui.activity.MainActivity;
import com.apemoon.tvbox.ui.adapter.NoticeAdapter;
import com.apemoon.tvbox.ui.view.RecycleViewDivider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by water on 2018/8/28/028.
 * des：公告通知的碎片
 */

public class NoticeFragment extends RxBaseListFragment implements IReceiveNoticeView, NoticeAdapter.NoticeRecyclerViewItemSelectListener {

    @BindView(R.id.emptyRootLayout)
    LinearLayout emptyRootLayout;
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

    List<ReceiveNoticeListEntity.NoticeListBean> noticeList = null;


    @Override
    public Boolean isLoadMore() {
        return true;
    }

    @Override
    public BaseQuickAdapter<?, ?> getAdapter() {
        mNoticeAdapter = new NoticeAdapter(this);
        mNoticeAdapter.setLoadMoreView(new LoadMoreView() {

            @Override
            public int getLayoutId() {
                return R.layout.view_baseadapter_view;
            }

            @Override
            protected int getLoadingViewId() {
                return R.id.view_loading;
            }

            @Override
            protected int getLoadFailViewId() {
                return R.id.view_loading;
            }

            @Override
            protected int getLoadEndViewId() {
                return R.id.view_loading;
            }
        });
        return mNoticeAdapter;
    }

    @Override
    public RecyclerView getRecyclerView() {
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 15, getResources().getColor(R.color.transparent)));

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
        configWebView(mWebView);
        mWebView.setFocusable(true);
    }

    @Override
    public void onStart() {
        super.onStart();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initListener() {
        mNoticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ReceiveNoticeListEntity.NoticeListBean noticeListBean = mNoticeAdapter.getData().get(position);
                if ("0".endsWith(noticeListBean.getIsRead())) {
                    mNoticePresenter.setNoteReaded("" + noticeListBean.getId());
                    noticeListBean.setIsRead("1");
                    noticeList.remove(position);
                    noticeList.add(position, noticeListBean);
//                    mNoticeAdapter.addData(noticeList);

                }
            }
        });

    }

    private void setContent(ReceiveNoticeListEntity.NoticeListBean noticeListBean) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (noticeListBean != null && mTvTitle != null && mWebView != null) {
                    mTvTitle.setText(noticeListBean.getTitle());
                    mWebView.loadDataWithBaseURL(null, noticeListBean.getContent(), "text/html", "utf-8", null);

                }
            }
        }, 10);

    }

    @Override
    protected void lazyLoadData() {
        requestNew();
    }

    private void configWebView(WebView webView) {
        if (null != webView) {
//            webView.requestFocus();
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
            noticeList = receiveNoticeListEntity.getNoticeList();
            setPageInfo(noticeList.size());
            if (noticeList != null && noticeList.size() > 0 && emptyRootLayout != null) {
                switch (getRequestType()) {
                    case REQUESTTYPE_NEW_DATE:
                        if (noticeList.size() != 0) {
                            ReceiveNoticeListEntity.NoticeListBean noticeListBean = noticeList.get(0);
                            if (noticeList != null) {
                                setContent(noticeListBean);
                            }
                        }
                        mNoticeAdapter.setNewData(noticeList);
                        break;
                    case REQUESTTYPE_ADD_DATE:
                        if (noticeList != null) {
//                            noticeList.addAll(receiveNoticeListEntity.getNoticeList());

                            mNoticeAdapter.addData(receiveNoticeListEntity.getNoticeList());
                        }
                        break;
                }
                if (emptyRootLayout != null) {
                    emptyRootLayout.setVisibility(View.GONE);
                }
            } else {
                if (getRequestType() == REQUESTTYPE_NEW_DATE) {
                    if (emptyRootLayout != null) {
                        emptyRootLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
            //不要注释上，，如果webview中有控件可以获取焦点，，焦点不一定会走左侧
            if (mRecyclerView != null) {
                mRecyclerView.postDelayed(() -> {
                    if (mRecyclerView != null && mRecyclerView.getLayoutManager().getChildCount() > 0) {
                        View itemView = mRecyclerView.getLayoutManager().getChildAt(0);
                        if (null != activity && itemView != null) {
                            ((MainActivity) activity).getMainTab().setNextFocusDownId(itemView.getId());
                        }
                    }
                }, 20);
            }
        }
    }

    @Override
    public void getReceiveNoticeListFail() {

    }

    @Override
    public void setNoticeReadSuccess() {

    }

    @Override
    public void setNoticeReadFail() {
//        requestNew();
    }


    private View selectView = null;


    @Override
    public void onItemSelectListner(int position, View itemView) {
        if (null != mWebView && null != itemView) {
            mWebView.setNextFocusLeftId(itemView.getId());
        }
        if (noticeList != null && mNoticeAdapter.getData().size() > 0 && position < mNoticeAdapter.getData().size()) {
            selectView = itemView;
            setContent(mNoticeAdapter.getData().get(position));
        }
    }
}
