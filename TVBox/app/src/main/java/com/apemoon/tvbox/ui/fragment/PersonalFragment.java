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
import com.apemoon.tvbox.entity.userCenter.UserSemstersEntity;
import com.apemoon.tvbox.entity.userCenter.UserTeachersEntity;
import com.apemoon.tvbox.interfaces.fragment.IPersonalView;
import com.apemoon.tvbox.presenter.PersonalPresenter;
import com.apemoon.tvbox.ui.adapter.MyTeacherAdapter;
import com.apemoon.tvbox.ui.adapter.NewAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.TeachersAdapter;
import com.apemoon.tvbox.ui.view.ItemLinearLayout;
import com.apemoon.tvbox.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class PersonalFragment extends BaseFragment implements IPersonalView {

    private PersonalPresenter mPersonalPresenter;

    //年级信息
    List<UserSemstersEntity.SemstersBean> semstersBeanList;

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

    /**
     *  奖罚模块
     */
    @BindView(R.id.tv2_semester_select)
    TextView tv2SemesterSelect;
    @BindView(R.id.item_school)
    ItemLinearLayout itemSchool;
    @BindView(R.id.item_time)
    ItemLinearLayout itemTime;
    @BindView(R.id.item_santion)
    ItemLinearLayout itemSantion;
    @BindView(R.id.item_info)
    ItemLinearLayout itemInfo;


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
            case R.id.tv2_semester_select:



        }
    }


    private void setUserData(UserInfoEntity userEntity) {
        UserEntity.UserInfoBean userInfo = userEntity.getUserInfo();
        GlideUtil.imageCircleLocal(getActivity(), userInfo.getHeadImage(), mIvHead);
        mTvName.setText(userInfo.getName());
        mTvSign.setText(userInfo.getAutograph());
        mTvPoliticsStatus.setText(userInfo.getPoliticsStatus());
        mTvBirth.setText(userInfo.getBirthday());
        mTvPhone.setText(userInfo.getPhone());
        mTvSchool.setText(userInfo.getSchoolName());
        mTvClass.setText(userInfo.getGradeName());
    }


    private void initTeacherData() {
        mMyTeacheradapter = new MyTeacherAdapter();
        mRecyclerViewMd1.setAdapter(mMyTeacheradapter);
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newList.add("1");
        }
        mMyTeacheradapter.setNewData(newList);
        mPersonalPresenter = new PersonalPresenter(activity, this);
    }


    @Override
    protected void lazyLoadData() {
        if (mPersonalPresenter != null) {
            mPersonalPresenter.receivePersonalInfo();
            mPersonalPresenter.receiveTeacherslInfo("11");
            mPersonalPresenter.receiveSemseterList();

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
    public void receiveTeachersInfoSuccess(UserTeachersEntity entity) {
        TeachersAdapter mTeachersAdapter = new TeachersAdapter();
        mRecyclerViewMd1.setAdapter(mTeachersAdapter);
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newList.add("1");
        }
        mTeachersAdapter.setNewData(entity.getTeacherList());
    }

    @Override
    public void receiveTeachersInfoFail() {

    }


    @Override
    public void receiveRecords2Success(UserRecordInfoEntity entity) {
//        mNewAdapter = new NewAdapter();
//        ree.setAdapter(mNewAdapter);
//        ArrayList<String> newList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            newList.add("1");
//        }
//        mNewAdapter.setNewData(newList);

    }

    @Override
    public void receiveRecords2Fail() {

    }


    @Override
    public void receiveRecords4Success(UserRecordInfoEntity entity) {


    }

    @Override
    public void receiveRecords4Fail() {

    }

    @Override
    public void receiveRemstersSuccess(UserSemstersEntity entity) {
        semstersBeanList = entity.getSemesterList();
        tv2SemesterSelect.setText(entity.getCurrentSemesterName());
        mPersonalPresenter.receiveRecords("2", entity.getCurrentSemesterId());
        mPersonalPresenter.receiveRecords("3", entity.getCurrentSemesterId());
        mPersonalPresenter.receiveRecords("4", entity.getCurrentSemesterId());
    }

    @Override
    public void receiveRemseFail() {

    }
}
