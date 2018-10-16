package com.apemoon.tvbox.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
public class PersonalFragment extends BaseFragment implements IPersonalView {

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

    /**
     *  奖罚模块
     */
    @BindView(R.id.md2_tv_semester_select)
    Button md2TvSemesterSelect;
    private PopupWindow md2_popView;

    /**
     *  评价模块
     */
    @BindView(R.id.md3_tv_semester_select)
    Button md3TvSemesterSelect;
    private PopupWindow md3_popView;



    private MyTeacherAdapter mMyTeacheradapter;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {
        ((MainActivity)getActivity()).onRequestMainTabFocus();
    }

    @Override
    public void initData() {
        mPersonalPresenter = new PersonalPresenter(getActivity(), this);
    }

    @Override
    public void initListener() {

    }


    @OnClick({R.id.tv_base_info, R.id.tv_judge_info, R.id.tv_sanction_info,R.id.md2_tv_semester_select,R.id.md3_tv_semester_select})
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
            case R.id.md2_tv_semester_select: {
                initPopWindow(md2TvSemesterSelect,"2");
                break;
            }
            case R.id.md3_tv_semester_select: {
                initPopWindow(md3TvSemesterSelect,"4");


                break;
            }

        }
    }

    private void initPopWindow(Button selectView, String type){
        View popupView = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_fee, null);
        md2_popView = new PopupWindow(popupView,selectView.getWidth(), WindowManager.LayoutParams.WRAP_CONTENT, true);
        md2_popView.setTouchable(true);
        md2_popView.setOutsideTouchable(false);
        md2_popView.setFocusable(true);
        // 设置背景为半透明灰色
        md2_popView.setBackgroundDrawable(new BitmapDrawable(null,""));
        // 设置动画
//        md2_popView.setAnimationStyle(R.style.invitation_anim);

        RecyclerView rates_lst = (RecyclerView) popupView.findViewById(R.id.rates_lst);
        SemestersAdapter mAdapter = new SemestersAdapter();
        rates_lst.setAdapter(mAdapter);
        mAdapter.setNewData(semstersBeanList);
        rates_lst.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                selectView.setText(semstersBeanList.get(position).getName());
                mPersonalPresenter.receiveRecords(type, "" + semstersBeanList.get(position).getId());
                md2_popView.dismiss();
            }
        });


        md2_popView.showAsDropDown(md2TvSemesterSelect);
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
        if(userInfo.getSex().equals("女")){

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

        SanctionAdapter mSanctionAdapter = new SanctionAdapter();
        mRecyclerViewMd2.setAdapter(mSanctionAdapter);
        mSanctionAdapter.setNewData(entity.getInfoRecordsList());


    }

    @Override
    public void receiveRecords2Fail() {

    }


    @Override
    public void receiveRecords4Success(UserRecordInfoEntity entity) {

        JudegeAdapter mJudegeAdapter = new JudegeAdapter();
        mRecyclerViewMd3.setAdapter(mJudegeAdapter);
        mJudegeAdapter.setNewData(entity.getInfoRecordsList());

    }

    @Override
    public void receiveRecords4Fail() {

    }

    @Override
    public void receiveRemstersSuccess(UserSemstersEntity entity) {
        semstersBeanList = entity.getSemesterList();
        mUserSemsterEntity = entity;
        md2TvSemesterSelect.setText(entity.getCurrentSemesterName());
        md3TvSemesterSelect.setText(entity.getCurrentSemesterName());
        mPersonalPresenter.receiveRecords("2", entity.getCurrentSemesterId());
        mPersonalPresenter.receiveRecords("3", entity.getCurrentSemesterId());
        mPersonalPresenter.receiveRecords("4", entity.getCurrentSemesterId());
    }

    @Override
    public void receiveRemseFail() {

    }
}
