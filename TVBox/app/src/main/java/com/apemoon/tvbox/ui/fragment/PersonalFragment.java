package com.apemoon.tvbox.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import com.apemoon.tvbox.ui.adapter.NewAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.JudegeAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.SanctionAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.SemesterListViewAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.SemestersAdapter;
import com.apemoon.tvbox.ui.adapter.personalCenter.TeachersAdapter;
import com.apemoon.tvbox.ui.view.ItemLinearLayout;
import com.apemoon.tvbox.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */
public class PersonalFragment extends BaseFragment implements IPersonalView ,View.OnFocusChangeListener {

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
        if(getActivity()!=null) {
            GlideUtil.imageCircleLocal(getActivity(), userInfo.getHeadImage(), mIvHead);
        }
        mTvName.setText(userInfo.getName());
        mTvSign.setText(userInfo.getAutograph());
        mTvPoliticsStatus.setText(userInfo.getPoliticsStatus());
        mTvBirth.setText(userInfo.getBirthday());
        mTvPhone.setText(userInfo.getPhone());
        mTvSchool.setText(userInfo.getSchoolName());
        mTvClass.setText(userInfo.getGradeName());
        if(userInfo.getSex().equals("女")){
            mIvSex.setImageDrawable(getResources().getDrawable(R.drawable.nan));
        }else{
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

    @Override
    public void receiveTeachersInfoSuccess(UserTeachersEntity entity) {
        TeachersAdapter mTeachersAdapter = new TeachersAdapter();
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerViewMd1.setLayoutManager(ms);
        mRecyclerViewMd1.setAdapter(mTeachersAdapter);
        ArrayList<String> newList = new ArrayList<>();
        mTeachersAdapter.setNewData(entity.getTeacherList());
    }

    @Override
    public void receiveTeachersInfoFail() {

    }


    @Override
    public void receiveRecords2Success(UserRecordInfoEntity entity) {

        JudegeAdapter mJudegeAdapter = new JudegeAdapter();
        mRecyclerViewMd3.setAdapter(mJudegeAdapter);
        mJudegeAdapter.setNewData(entity.getInfoRecordsList());

    }

    @Override
    public void receiveRecords2Fail() {

    }


    @Override
    public void receiveRecords4Success(UserRecordInfoEntity entity) {


        SanctionAdapter mSanctionAdapter = new SanctionAdapter();
        mRecyclerViewMd2.setAdapter(mSanctionAdapter);
        mSanctionAdapter.setNewData(entity.getInfoRecordsList());


    }

    @Override
    public void receiveRecords4Fail() {

    }

    @Override
    public void receiveRemstersSuccess(UserSemstersEntity entity) {
        semstersBeanList = entity.getSemesterList();
        mUserSemsterEntity = entity;
        md2Spiner.setAdapter(new SmsAdapter(entity.getSemesterList()) );
        md2Spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPersonalPresenter.receiveRecords("2", "" + semstersBeanList.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        md2Spiner.setVisibility(View.VISIBLE);

        md3Spiner.setAdapter(new SmsAdapter(entity.getSemesterList()) );
        md3Spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPersonalPresenter.receiveRecords("4", "" + semstersBeanList.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        md3Spiner.setVisibility(View.VISIBLE);
        mPersonalPresenter.receiveRecords("2", entity.getCurrentSemesterId());
        mPersonalPresenter.receiveRecords("3", entity.getCurrentSemesterId());
        mPersonalPresenter.receiveRecords("4", entity.getCurrentSemesterId());
    }

    @Override
    public void receiveRemseFail() {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
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

    class SmsAdapter<UserSemstersEntity> extends BaseSpinnerAdapter{
        List<com.apemoon.tvbox.entity.userCenter.UserSemstersEntity.SemstersBean> smstersList;

        public SmsAdapter(List<com.apemoon.tvbox.entity.userCenter.UserSemstersEntity.SemstersBean> list) {
            super(list);
            this.smstersList = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rootView = LayoutInflater.from(activity).inflate(R.layout.spinner_item_layout, parent, false);
            ((TextView)rootView.findViewById(R.id.spinnerTv)).setText(smstersList.get(position).getName());
            return rootView;
        }
    }
}
