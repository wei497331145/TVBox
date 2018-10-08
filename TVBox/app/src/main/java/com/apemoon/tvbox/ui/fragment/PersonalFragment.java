package com.apemoon.tvbox.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseFragment;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.entity.userCenter.UserInfoEntity;
import com.apemoon.tvbox.entity.userCenter.UserRecordInfoEntity;
import com.apemoon.tvbox.interfaces.fragment.IPersonalView;
import com.apemoon.tvbox.presenter.PersonalPresenter;
import com.apemoon.tvbox.ui.adapter.MyTeacherAdapter;
import com.apemoon.tvbox.utils.GlideUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class PersonalFragment extends BaseFragment implements IPersonalView {

    private PersonalPresenter mPersonalPresenter;

    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_sign)
    TextView mTvSign;
    @BindView(R.id.tv_nation)
    TextView mTvNation;
    @BindView(R.id.tv_politicsStatus)
    TextView mTvPoliticsStatus;
    @BindView(R.id.tv_birth)
    TextView mTvBirth;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_school)
    TextView mTvSchool;
    @BindView(R.id.tv_class)
    TextView mTvClass;

    @BindView(R.id.recyclerView_md1)
    RecyclerView mRecyclerViewMd1;
    @BindView(R.id.recyclerView_md2)
    RecyclerView mRecyclerViewMd2;

    @BindView(R.id.person_md1)
    LinearLayout llPersonalMd1;
    @BindView(R.id.person_md2)
    LinearLayout llPersonalMd2;
    @BindView(R.id.person_md3)
    LinearLayout llPersonalMd3;

    @BindView(R.id.tv_base_info)
    TextView mTvBaseInfo;
    @BindView(R.id.tv_sanction_info)
    TextView mTvSanctionInfo;
    @BindView(R.id.tv_judge_info)
    TextView mTvJudgeInfo;


    private MyTeacherAdapter mMyTeacheradapter;





    @Override
    public int getLayoutRes() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPersonalPresenter = new PersonalPresenter(getActivity(), this);
    }

    @Override
    public void initListener() {

    }


    @OnClick({R.id.tv_base_info, R.id.tv_judge_info, R.id.tv_sanction_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_base_info://个人信息
                llPersonalMd1.setVisibility(View.VISIBLE);
                llPersonalMd2.setVisibility(View.GONE);
                llPersonalMd3.setVisibility(View.GONE);
                break;
            case R.id.tv_sanction_info://奖罚信息
                llPersonalMd1.setVisibility(View.GONE);
                llPersonalMd2.setVisibility(View.VISIBLE);
                llPersonalMd3.setVisibility(View.GONE);
                break;
            case R.id.tv_judge_info://评价信息
                llPersonalMd1.setVisibility(View.GONE);
                llPersonalMd2.setVisibility(View.GONE);
                llPersonalMd3.setVisibility(View.VISIBLE);

                break;


        }
    }




        private void setUserData(UserInfoEntity userEntity) {
        UserEntity.UserInfoBean userInfo = userEntity.getUserInfo();
        GlideUtil.imageCircleLocal(getActivity(),userInfo.getHeadImage(),mIvHead);
        mTvName.setText(userInfo.getName());
        mTvSign.setText(userInfo.getAutograph());
        mTvPoliticsStatus.setText(userInfo.getPoliticsStatus());
        mTvBirth.setText(userInfo.getBirthday());
        mTvPhone.setText(userInfo.getPhone());
        mTvSchool.setText(userInfo.getSchoolName());
        mTvClass.setText(userInfo.getGradeName());
    }


    private void initTeacherData(){
        mMyTeacheradapter = new MyTeacherAdapter();
        mRecyclerViewMd1.setAdapter(mMyTeacheradapter);
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newList.add("1");
        }
        mMyTeacheradapter.setNewData(newList);
        mPersonalPresenter = new PersonalPresenter(activity,this);
    }


    @Override
    protected void lazyLoadData() {
        if (mPersonalPresenter != null) {
            mPersonalPresenter.receivePersonalInfo();
            mPersonalPresenter.receiveRecords("2");
            mPersonalPresenter.receiveRecords("4");
            mPersonalPresenter.receiveRecords("5");
            mPersonalPresenter.receiveRecords("6");
            mPersonalPresenter.receiveRecords("7");

        }
    }


    @Override
    public void receivePersonalInfoSuccess(UserInfoEntity entity) {
        setUserData(entity);
    }

    @Override
    public void receivePersonalInfoFail() {

    }

    @Override
    public void receiveRecords1Success(UserRecordInfoEntity entity) {
//        initTeacherData()

    }

    @Override
    public void receiveRecords1Fail() {

    }


    @Override
    public void receiveRecords2Success(UserRecordInfoEntity entity) {


    }

    @Override
    public void receiveRecords2Fail() {

    }
}
