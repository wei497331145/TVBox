package com.apemoon.tvbox.ui.adapter.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Fragment界面适配器
 * @Author zhouy
 * @Date 2017-11-24
 */

public class CFragmentPagerAdapter extends FragmentPagerAdapter {

    private int mChildCount = 0;

    private List<? extends Fragment> mData;

    private List<String> mPageTitle;

    public CFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CFragmentPagerAdapter(FragmentManager fm, List<String> pageTitle) {
        super(fm);
        mPageTitle = pageTitle;
    }


    public void setData(List<? extends Fragment> list){
        mData = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return null == mData? null: mData.get(position);
    }

    @Override
    public int getCount() {
        return null == mData? 0: mData.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(mChildCount > 0){
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(null != mPageTitle && mPageTitle.size() > 0){
            return mPageTitle.get(position);
        }
        return super.getPageTitle(position);
    }
}
