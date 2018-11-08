package com.apemoon.tvbox.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.RxBaseListFragment;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.interfaces.fragment.IInformationView;
import com.apemoon.tvbox.interfaces.recyclerview.RecyclerViewItemSelectListener;
import com.apemoon.tvbox.presenter.InformationPresenter;
import com.apemoon.tvbox.ui.activity.MainActivity;
import com.apemoon.tvbox.ui.adapter.information.InfoTwoClassicalAdapter;
import com.apemoon.tvbox.ui.adapter.information.InformationAdapter;
import com.apemoon.tvbox.ui.view.RecycleViewDivider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class ElegantDemeanorFragment extends RxBaseListFragment implements IInformationView {

    private InformationPresenter mInformaitonPresenter;
    private int currentTwoClassId = 0;
    private InformationAdapter mInformationAdater;
    private List<InfoClassicalEntity.TwoClassicalBean> twoClasscialList;
    private List<InfoListEntity.InformationBean> informationBeanList;

    @BindView(R.id.ll_recy)
    LinearLayout llRecy;
    @BindView(R.id.emptyRootLayout)
    LinearLayout emptyRootLayout;
    @BindView(R.id.lv_two_classical)
    RecyclerView lvTwoClassical;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public Boolean isLoadMore() {
        return true;
    }

    @Override
    public BaseQuickAdapter<?, ?> getAdapter() {
        mInformationAdater = new InformationAdapter();
        mInformationAdater.setLoadMoreView(new LoadMoreView() {

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
        return mInformationAdater;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_traditional_culture;
    }


    @Override
    public RecyclerView getRecyclerView() {
        GridLayoutManager mManagerLayout = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mManagerLayout);
        return mRecyclerView;
    }

    @Override
    public void initData() {
        mInformaitonPresenter = new InformationPresenter(getActivity(), this);

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && null != clickView) {
            clickView.requestFocus();
        }
    }

    private View clickView = null;

    @Override
    public void initListener() {
        if (mInformationAdater == null) {
            mInformationAdater = new InformationAdapter(this);
        }
        mInformationAdater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                clickView = view;
                InfoListEntity.InformationBean bean = mInformationAdater.getItem(position);
                Fragment fragment = InfoListFragment.getInstance(currentTwoClassId, bean.getId(), 4);
                fragment.setUserVisibleHint(true);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                if (!fragment.isAdded()) {
                    transaction.add(R.id.fl_main, fragment);
                }
                transaction.hide(ElegantDemeanorFragment.this);
                transaction.show(fragment).commit();
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        mInformaitonPresenter.receiveInfoClassfication();
    }

    @Override
    public void receiveNewestInformationsSuccess(InfoListEntity entity) {

    }

    @Override
    public void receiveNewestInformationsFail() {

    }

    @Override
    public void receiveInformationsSuccess(InfoListEntity entity) {
        if(mRecyclerView == null){
            return;
        }
        if (entity != null) {
            informationBeanList = entity.getInformationList();
            setPageInfo(informationBeanList.size());
            if (informationBeanList != null && informationBeanList.size() > 0) {
                switch (getRequestType()) {
                    case REQUESTTYPE_NEW_DATE:
                        mInformationAdater.setNewData(informationBeanList);
                        break;
                    case REQUESTTYPE_ADD_DATE:
                        mInformationAdater.addData(informationBeanList);
                        break;
                }
                if (emptyRootLayout != null) {
                    emptyRootLayout.setVisibility(View.GONE);
                }
            } else {
                if (getRequestType() == REQUESTTYPE_NEW_DATE) {
                    if (emptyRootLayout != null) {
                        emptyRootLayout.setBackground(null);
                        emptyRootLayout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    @Override
    public void receiveInformationsFail() {

    }

    private View selectLeftView = null;

    @Override
    public void receiveInformationClassicalSuccess(InfoClassicalEntity entity) {
        if(lvTwoClassical == null || entity == null){
            return;
        }
        twoClasscialList = entity.getSchoollTwoClassical();
        if (twoClasscialList != null && twoClasscialList.size() > 0) {
            currentTwoClassId = twoClasscialList.get(0).getId();
            InfoTwoClassicalAdapter twoClassicaladapter = new InfoTwoClassicalAdapter(new RecyclerViewItemSelectListener() {
                @Override
                public void onItemSelectListner(int position, View view) {
                    selectLeftView = view;
                    InfoClassicalEntity.TwoClassicalBean bean = twoClasscialList.get(position);
                    if (bean != null) {
                        currentTwoClassId = bean.getId();
                        requestNew();
                    }
                }
            });
            //  lvTwoClassical.setAdapter(twoClassicaladapter);

            twoClassicaladapter.setNewData(twoClasscialList);
            twoClassicaladapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    InfoClassicalEntity.TwoClassicalBean bean = twoClassicaladapter.getData().get(position);
                    if (bean != null) {
                        currentTwoClassId = bean.getId();
                        requestNew();
                    }
                }
            });

            twoClassicaladapter.bindToRecyclerView(lvTwoClassical);

            lvTwoClassical.postDelayed(() -> {
                if (null != lvTwoClassical && lvTwoClassical.getLayoutManager().getChildCount() > 0) {
                    View itemView = lvTwoClassical.getLayoutManager().getChildAt(0);
                    if (null != activity && itemView != null) {
                        ((MainActivity) activity).getMainTab().setNextFocusDownId(itemView.getId());
                    }
                }
            }, 20);


            requestNew();
        } else {
            if (emptyRootLayout != null) {
                llRecy.setVisibility(View.VISIBLE);
                emptyRootLayout.setVisibility(View.VISIBLE);
            }

        }
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


    public View getSelectLeftView() {
        return selectLeftView;
    }
}
