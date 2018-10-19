package com.apemoon.tvbox.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseFragment;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.interfaces.fragment.IInformationView;
import com.apemoon.tvbox.presenter.InformationPresenter;
import com.apemoon.tvbox.ui.adapter.NewAdapter;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.GlobalUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by water on 2018/8/28/028.
 * des：首页的碎片
 */

public class HomeFragment extends BaseFragment implements IInformationView {
    @BindView(R.id.tv_class_schedule)
    TextView mTvClassSchedule;
    @BindView(R.id.tv_class_performance)
    TextView mTvClassPerformance;
    @BindView(R.id.tv_class_task)
    TextView mTvClassTask;
    @BindView(R.id.tv_my_achievement)
    TextView mTvMyAchievement;
    @BindView(R.id.iv_album)
    ImageView mIvAlbum;
    @BindView(R.id.cv_album)
    RelativeLayout mCvAlbum;
    @BindView(R.id.iv_curriculum)
    ImageView mIvCurriculum;
    @BindView(R.id.cv_curriculum)
    RelativeLayout mCvCurriculum;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private NewAdapter mNewAdapter;
    private InformationPresenter mInformationPresenter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mTvClassSchedule.setFocusable(true);
        mTvClassPerformance.setFocusable(true);
        mTvClassTask.setFocusable(true);
        mTvMyAchievement.setFocusable(true);
        mCvAlbum.setFocusable(true);
        mCvCurriculum.setFocusable(true);


    }

    @Override
    public void onStart() {
        super.onStart();
        mInformationPresenter.receiveNewestInformations();
    }

    @Override
    public void initData() {
        mInformationPresenter = new InformationPresenter(getActivity(),this);
        mNewAdapter = new NewAdapter();
        mRecyclerView.setAdapter(mNewAdapter);
        mNewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InfoListEntity.InformationBean bean= mNewAdapter.getItem(position);
                Fragment fragment = InfoListFragment.getInstance(bean.getTwoClassifyId(),bean.getId(),0);
                fragment.setUserVisibleHint(true);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                if (!fragment.isAdded()) {
                    transaction.add(R.id.fl_main, fragment);
                }
                transaction.hide(HomeFragment.this);
                transaction.show(fragment).commit();
            }
        });
      }


    @Override
    public void initListener() {
        setTextChangeListener(mTvClassSchedule);
        setTextChangeListener(mTvClassPerformance);
        setTextChangeListener(mTvClassTask);
        setTextChangeListener(mTvMyAchievement);
        setMainChangeListener(mCvAlbum);
        setMainChangeListener(mCvCurriculum);
    }

    private void setTextChangeListener(View view) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setTextAnimation(view,hasFocus,1.1f,1.1f,1.0f,1.0f);
            }
        });
    }

    private void setMainChangeListener(View view) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setAdapter(view,hasFocus);
            }
        });
    }


    @Override
    protected void lazyLoadData() {

    }


    @OnClick({R.id.tv_class_schedule, R.id.tv_class_performance, R.id.tv_class_task, R.id.tv_my_achievement, R.id.cv_album, R.id.cv_curriculum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_class_schedule://班级课表
                break;
            case R.id.tv_class_performance://课堂表现
                break;
            case R.id.tv_class_task://课堂作业
                break;
            case R.id.tv_my_achievement://我的成绩
                break;
            case R.id.cv_album://班级相册
                break;
            case R.id.cv_curriculum://精品课程
                break;
        }
    }

    @Override
    public void receiveNewestInformationsSuccess(InfoListEntity entity) {
        if(entity.getInformationList().size()>0) {
            mNewAdapter.setNewData(entity.getInformationList());
        }

    }

    @Override
    public void receiveNewestInformationsFail() {

    }

    @Override
    public void receiveInformationsSuccess(InfoListEntity entity) {

    }

    @Override
    public void receiveInformationsFail() {

    }

    @Override
    public void receiveInformationClassicalSuccess(InfoClassicalEntity entity) {

    }

    @Override
    public void receiveInformationClassicalFail() {

    }
}
