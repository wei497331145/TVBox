package com.apemoon.tvbox.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseFragment;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.interfaces.fragment.IInformationView;
import com.apemoon.tvbox.presenter.InformationPresenter;
import com.apemoon.tvbox.ui.activity.MainActivity;
import com.apemoon.tvbox.ui.adapter.NewAdapter;
import com.apemoon.tvbox.ui.view.MainTabView;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.LogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

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

        RoundedBitmapDrawable drawableC = RoundedBitmapDrawableFactory.create(getResources(), id2Bitmap(getActivity(), R.drawable.school_album));
        drawableC.setCornerRadius(5L);
        mIvAlbum.setImageDrawable(drawableC);

        RoundedBitmapDrawable drawableD = RoundedBitmapDrawableFactory.create(getResources(), id2Bitmap(getActivity(), R.drawable.school_curriculum));
        drawableD.setCornerRadius(5L);
        mIvCurriculum.setImageDrawable(drawableD);


    }

    public static Bitmap id2Bitmap(Context context, int id) {
        return BitmapFactory.decodeResource(context.getResources(), id);
    }

    @Override
    public void onStart() {
        super.onStart();
        mInformationPresenter.receiveNewestInformations();
    }

    @Override
    public void initData() {
        mInformationPresenter = new InformationPresenter(getActivity(), this);
        mNewAdapter = new NewAdapter();
        mRecyclerView.setAdapter(mNewAdapter);
        mNewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InfoListEntity.InformationBean bean = mNewAdapter.getItem(position);
                Fragment fragment = InfoListFragment.getInstance(bean.getTwoClassifyId(), bean.getId(), 0);
                fragment.setUserVisibleHint(true);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                if (!fragment.isAdded()) {
                    transaction.add(R.id.fl_main, fragment);
                }
                transaction.hide(HomeFragment.this);
                transaction.show(fragment).commit();
            }
        });

        FragmentActivity ac = getActivity();
        if (null != ac) {
            fManager = ac.getSupportFragmentManager();
        }
    }

    private FragmentManager fManager;

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
                AnimationUtil.setTextAnimation(view, hasFocus, 1.1f, 1.1f, 1.0f, 1.0f);
            }
        });
    }

    private void setMainChangeListener(View view) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setAdapter(view, hasFocus);
            }
        });
    }


    @Override
    protected void lazyLoadData() {

    }


    private void changeTab(int position) {
        if (activity instanceof MainActivity) {
            MainTabView tabView = ((MainActivity) activity).getMainTab();
            if (null != tabView) {
                tabView.setPosition(position);
                tabView.setTabUnable();
            }
        }
    }

    private void selectedClassRoomFragment(int position) {
        if (null != fManager) {
            List<Fragment> fragments = fManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (fragment instanceof ClassRoomFragment) {
                    ((ClassRoomFragment) fragment).initSelectedPosition(position);
                    LogUtil.d("requestFocus1    " + position);
                    //((ClassRoomFragment) fragment).setSelectedPosition(position);
                }
            }
        }
    }


    private void selectedClassFragment(int position) {
        if (null != fManager) {
            List<Fragment> fragments = fManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (fragment instanceof ClassFragment) {
                    ((ClassFragment) fragment).initSelectedPosition(position);
                    LogUtil.d("requestFocus1    " + position);
                    //((ClassRoomFragment) fragment).setSelectedPosition(position);
                }
            }
        }
    }


    @OnClick({R.id.tv_class_schedule, R.id.tv_class_performance, R.id.tv_class_task, R.id.tv_my_achievement, R.id.cv_album, R.id.cv_curriculum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_class_schedule://班级课表
                changeTab(3);
                new Handler(Looper.getMainLooper()).postDelayed(() -> selectedClassRoomFragment(0), 200);

                break;
            case R.id.tv_class_performance://课堂表现
                changeTab(3);
                new Handler(Looper.getMainLooper()).postDelayed(() -> selectedClassRoomFragment(1), 200);
                break;
            case R.id.tv_class_task://课堂作业
                changeTab(3);
                new Handler(Looper.getMainLooper()).postDelayed(() -> selectedClassRoomFragment(2), 200);

                break;
            case R.id.tv_my_achievement://我的成绩
                changeTab(3);


                new Handler(Looper.getMainLooper()).postDelayed(() -> selectedClassRoomFragment(3), 200);
                break;
            case R.id.cv_album://班级相册
                changeTab(4);
                new Handler(Looper.getMainLooper()).postDelayed(() -> selectedClassFragment(3), 200);

                break;
            case R.id.cv_curriculum://精品课程
                break;
        }
    }

    @Override
    public void receiveNewestInformationsSuccess(InfoListEntity entity) {
        if(mRecyclerView == null){
            return;
        }
        if (entity.getInformationList().size() > 0) {
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
