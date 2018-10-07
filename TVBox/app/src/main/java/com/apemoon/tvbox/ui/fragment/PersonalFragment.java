package com.apemoon.tvbox.ui.fragment;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseFragment;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.interfaces.personal.IPersonalView;
import com.apemoon.tvbox.presenter.NoticePresenter;
import com.apemoon.tvbox.presenter.PersonalPresenter;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class PersonalFragment extends BaseFragment implements IPersonalView {

    private PersonalPresenter mPersonalPresenter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPersonalPresenter = new PersonalPresenter(activity,this);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void lazyLoadData() {
        if (mPersonalPresenter != null) {
            mPersonalPresenter.receivePersonalInfo();
            mPersonalPresenter.receiveRecords("2");
            mPersonalPresenter.receiveRecords("4");

        }
    }


    @Override
    public void receivePersonalInfoSuccess(ReceiveNoticeListEntity receiveNoticeListEntity) {

    }

    @Override
    public void receivePersonalInfoFail() {

    }

    @Override
    public void receiveRecordsSuccess() {

    }

    @Override
    public void receiveRecordsFail() {

    }
}
