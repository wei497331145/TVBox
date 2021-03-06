package com.apemoon.tvbox.ui.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.apemoon.tvbox.ui.activity.MainActivity;
import com.apemoon.tvbox.ui.adapter.BaseSpinnerAdapter;
import com.apemoon.tvbox.ui.adapter.MyTeacherAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.JudegeAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.SanctionAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.TeachersAdapter;
import com.apemoon.tvbox.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class PersonalFragment extends BaseFragment implements IPersonalView, View.OnFocusChangeListener {

    private PersonalPresenter mPersonalPresenter;
    //年级信息
    List<UserSemstersEntity.SemstersBean> semstersBeanList;
    private UserSemstersEntity mUserSemsterEntity;

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
    @BindView(R.id.iv_sex)
    ImageView mIvSex;

    @BindView(R.id.recyclerView_md1)
    RecyclerView mRecyclerViewMd1;
    @BindView(R.id.recyclerView_md2)
    RecyclerView mRecyclerViewMd2;
    @BindView(R.id.recyclerView_md3)
    RecyclerView mRecyclerViewMd3;


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

    @BindView(R.id.spinner_md2)
    Spinner md2Spiner;

    @BindView(R.id.spinner_md3)
    Spinner md3Spiner;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void initData() {
        mPersonalPresenter = new PersonalPresenter(getActivity(), this);
    }

    @Override
    public void initListener() {
        mTvBaseInfo.setOnFocusChangeListener(this);
        mTvSanctionInfo.setOnFocusChangeListener(this);
        mTvJudgeInfo.setOnFocusChangeListener(this);

        //指定向上焦点
        mTvBaseInfo.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (v.getNextFocusRightId() != v.getId()) {
                    v.setBackgroundResource(R.drawable.bg_bl_tv_info_drawable);
                }
            }
            return false;
        });

        mTvSanctionInfo.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (v.getNextFocusRightId() != v.getId()) {
                    v.setBackgroundResource(R.drawable.bg_bl_tv_info_drawable);
                }
            }
            return false;
        });

        mTvJudgeInfo.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (v.getNextFocusRightId() != v.getId()) {
                    v.setBackgroundResource(R.drawable.bg_bl_tv_info_drawable);
                }
            }
            return false;
        });
        if (null != activity) {
            int view = ((MainActivity) activity).getMainTab().getId();
            ((MainActivity) activity).getMainTab().setNextFocusDownId(mTvBaseInfo.getId());
            mTvBaseInfo.setNextFocusUpId(view);
        }
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

    /**
     * 加载个人信息
     * @param userEntity
     */
    private void setUserData(UserInfoEntity userEntity) {
        UserEntity.UserInfoBean userInfo = userEntity.getUserInfo();
        if (getActivity() != null) {
            GlideUtil.imageCircleLocal(getActivity(), userInfo.getHeadImage(), mIvHead);
        }
        mTvName.setText(userInfo.getName());
        mTvSign.setText(userInfo.getAutograph());
        mTvPoliticsStatus.setText(userInfo.getPoliticsStatus());
        mTvBirth.setText(userInfo.getBirthday());
        mTvPhone.setText(userInfo.getPhone());
        mTvSchool.setText(userInfo.getSchoolName());
        mTvClass.setText(userInfo.getGradeName());
        if (userInfo.getSex().equals("女")) {
            mIvSex.setImageDrawable(getResources().getDrawable(R.drawable.nan));
        } else {
            mIvSex.setImageDrawable(getResources().getDrawable(R.drawable.nv));
        }
    }


    @Override
    protected void lazyLoadData() {
        if (mPersonalPresenter != null) {
            mPersonalPresenter.receivePersonalInfo();
            mPersonalPresenter.receiveTeacherslInfo();
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

    /**
     * 获取教师头数据
     * @param entity
     */
    @Override
    public void receiveTeachersInfoSuccess(UserTeachersEntity entity) {
        if(mRecyclerViewMd1 == null){
            return;
        }
        TeachersAdapter mTeachersAdapter = new TeachersAdapter();
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerViewMd1.setLayoutManager(ms);
        mRecyclerViewMd1.setAdapter(mTeachersAdapter);
        mTeachersAdapter.setNewData(entity.getTeacherList());
    }


    /**
     * 获取评价信息
     * @param entity
     */
    @Override
    public void receiveRecords2Success(UserRecordInfoEntity entity) {
        if(mRecyclerViewMd3 == null){
            return;
        }
        JudegeAdapter mJudegeAdapter = new JudegeAdapter();
        mRecyclerViewMd3.setAdapter(mJudegeAdapter);
        mJudegeAdapter.setNewData(entity.getInfoRecordsList());

    }

    @Override
    public void receiveRecords2Fail() {

    }

    /**
     * 获取奖惩信息
     * @param entity
     */
    @Override
    public void receiveRecords4Success(UserRecordInfoEntity entity) {
        if(mRecyclerViewMd2 == null){
            return;
        }
        SanctionAdapter mSanctionAdapter = new SanctionAdapter();
        mRecyclerViewMd2.setAdapter(mSanctionAdapter);
        mSanctionAdapter.setNewData(entity.getInfoRecordsList());


    }

    @Override
    public void receiveRecords4Fail() {

    }

    @Override
    public void receiveRemstersSuccess(UserSemstersEntity entity) {
        if(md2Spiner == null){
            return;
        }
        semstersBeanList = entity.getSemesterList();
        mUserSemsterEntity = entity;
        md2Spiner.setAdapter(new SmsAdapter(entity.getSemesterList()));
        md2Spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPersonalPresenter.receiveRecords("4", "" + semstersBeanList.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        md2Spiner.setVisibility(View.VISIBLE);
        md3Spiner.setAdapter(new SmsAdapter(entity.getSemesterList()));
        md3Spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPersonalPresenter.receiveRecords("2", "" + semstersBeanList.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        md3Spiner.setVisibility(View.VISIBLE);
        mPersonalPresenter.receiveRecords("2", entity.getCurrentSemesterId());
        mPersonalPresenter.receiveRecords("4", entity.getCurrentSemesterId());
    }

    @Override
    public void receiveRemseFail() {

    }

    public void resetInfoItemDrawable() {
        mTvBaseInfo.setBackgroundResource(R.drawable.bg_bl_tv_info_selector);
        mTvSanctionInfo.setBackgroundResource(R.drawable.bg_bl_tv_info_selector);
        mTvJudgeInfo.setBackgroundResource(R.drawable.bg_bl_tv_info_selector);
    }


    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.tv_base_info://个人信息
                if (b) {
                    resetInfoItemDrawable();
                }
                llPersonalMd1.setVisibility(View.VISIBLE);
                llPersonalMd2.setVisibility(View.GONE);
                llPersonalMd3.setVisibility(View.GONE);
                break;

            case R.id.tv_sanction_info://奖罚信息
                if (b) {
                    resetInfoItemDrawable();
                }

                llPersonalMd1.setVisibility(View.GONE);
                llPersonalMd2.setVisibility(View.VISIBLE);
                llPersonalMd3.setVisibility(View.GONE);
                break;
            case R.id.tv_judge_info://评价信息
                if (b) {
                    resetInfoItemDrawable();
                }
                llPersonalMd1.setVisibility(View.GONE);
                llPersonalMd2.setVisibility(View.GONE);
                llPersonalMd3.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 年级信息Adapter
     * @param <UserSemstersEntity>
     */
    class SmsAdapter<UserSemstersEntity> extends BaseSpinnerAdapter {
        List<com.apemoon.tvbox.entity.userCenter.UserSemstersEntity.SemstersBean> smstersList;

        public SmsAdapter(List<com.apemoon.tvbox.entity.userCenter.UserSemstersEntity.SemstersBean> list) {
            super(list);
            this.smstersList = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rootView = LayoutInflater.from(activity).inflate(R.layout.spinner_item_layout, parent, false);
            rootView.setBackgroundColor(Color.TRANSPARENT);
            ((TextView) rootView.findViewById(R.id.spinnerTv)).setText(smstersList.get(position).getName());
            return rootView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View rootView = LayoutInflater.from(activity).inflate(R.layout.spinner_item_layout, parent, false);
            ((TextView) rootView.findViewById(R.id.spinnerTv)).setText(smstersList.get(position).getName());
            return rootView;
        }
    }
}
