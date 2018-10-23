package com.apemoon.tvbox.factory.main;


import android.support.v4.app.Fragment;

import com.apemoon.tvbox.ui.fragment.ClassFragment;
import com.apemoon.tvbox.ui.fragment.ClassRoomFragment;
import com.apemoon.tvbox.ui.fragment.ElegantDemeanorFragment;
import com.apemoon.tvbox.ui.fragment.HomeFragment;
import com.apemoon.tvbox.ui.fragment.NoticeFragment;
import com.apemoon.tvbox.ui.fragment.PersonalFragment;
import com.apemoon.tvbox.ui.fragment.StoreFragment;
import com.apemoon.tvbox.ui.fragment.TraditionalFragment;


public class FragmentFactory {

    private static FragmentFactory factory = null;

    private HomeFragment mHomeFragment;
    private NoticeFragment mNoticeFragment;
    private ClassRoomFragment mClassRoomFragment;
    private ClassFragment mClassFragment;
    private ElegantDemeanorFragment mElegantDemeanorFragment;
    private TraditionalFragment mTraditionalFragment;
    private PersonalFragment mPersonalFragment;
    private StoreFragment mStoreFragment;

    private FragmentFactory() {

    }

    public static FragmentFactory getIntance() {
        if (factory == null) {
            synchronized (FragmentFactory.class) {
                if (factory == null) {
                    factory = new FragmentFactory();
                }
            }
        }
        return factory;
    }


    public Fragment getFragment(int position) {
        Fragment baseFragment = null;
        switch (position) {
            case 0://首页
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                }
                baseFragment = mHomeFragment;
                break;
            case 1://公告通知
                if (mNoticeFragment == null) {
                    mNoticeFragment = new NoticeFragment();
                }
                baseFragment = mNoticeFragment;
                break;
            case 2://我的课堂
                if (mClassRoomFragment == null) {
                    mClassRoomFragment = new ClassRoomFragment();
                }
                baseFragment = mClassRoomFragment;
                break;
            case 3://我的班级
                if (mClassFragment == null) {
                    mClassFragment = new ClassFragment();
                }
                baseFragment = mClassFragment;
                break;
            case 4://校园风采
                if (mElegantDemeanorFragment == null) {
                    mElegantDemeanorFragment = new ElegantDemeanorFragment();
                }
                baseFragment = mElegantDemeanorFragment;
                break;
            case 5://传统文化
                if (mTraditionalFragment == null) {
                    mTraditionalFragment = new TraditionalFragment();
                }
                baseFragment = mTraditionalFragment;
                break;
            case 6://个人中心
                if (mPersonalFragment == null) {
                    mPersonalFragment = new PersonalFragment();
                }
                baseFragment = mPersonalFragment;
                break;
            case 7://商城
                if (mStoreFragment == null) {
                    mStoreFragment = new StoreFragment();
                }
                baseFragment = mStoreFragment;
                break;
        }
        return baseFragment;
    }

    public void clearFragment() {
        mHomeFragment = null;
        mNoticeFragment = null;
        mClassRoomFragment = null;
        mClassFragment = null;
        mElegantDemeanorFragment = null;
        mTraditionalFragment = null;
        mPersonalFragment = null;
        mStoreFragment = null;
    }


}
