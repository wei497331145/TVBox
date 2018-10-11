package com.apemoon.tvbox.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.RxBaseListFragment;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.interfaces.fragment.IInformationView;
import com.apemoon.tvbox.presenter.InformationPresenter;
import com.apemoon.tvbox.ui.adapter.information.InformationAdapter;
import com.apemoon.tvbox.ui.adapter.information.InfoTwoClassicalListViewAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class TraditionalFragment extends RxBaseListFragment implements IInformationView {

    private InformationPresenter mInformaitonPresenter;
    private int currentTwoClassId;
    private InformationAdapter mInformationAdater;
    private List<InfoListEntity.InformationBean> informationBeanList;

    @BindView(R.id.lv_two_classical)
    ListView lvTwoClassical;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

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
        return R.layout.fragment_traditional_culture;
    }



    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
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
        List<InfoClassicalEntity.TwoClassicalBean> twoClasscialList = entity.getTraditonalTwoClassical();
        currentTwoClassId = twoClasscialList.get(0).getId();

        InfoTwoClassicalListViewAdapter mAdapter = new InfoTwoClassicalListViewAdapter(getActivity(), twoClasscialList);
        lvTwoClassical.setAdapter(mAdapter);

        lvTwoClassical.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InfoListEntity.InformationBean bean = mInformationAdater.getData().get(i);
                currentTwoClassId = bean.getTwoClassifyId();
                requestNew();
            }
        });

        mInformaitonPresenter.receiveInformations(String.valueOf(getCurrentPage()), String.valueOf(getPageSize()), String.valueOf(currentTwoClassId));
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





}
