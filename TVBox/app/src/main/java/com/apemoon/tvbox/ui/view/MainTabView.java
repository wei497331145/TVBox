package com.apemoon.tvbox.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.apemoon.tvbox.R;
import com.apemoon.tvbox.interfaces.IMainTabView;
import com.apemoon.tvbox.interfaces.IMainView;

/**
 * Created by water on 2018/8/29/029.
 * des：主界面的TabLayout
 */

public class MainTabView extends LinearLayout {
    View view;
    TextView mTvMain;
    TextView mTvNotice;
    TextView mTvClass;
    TextView mTvClassClass;
    TextView mTvElegantDemeanor;
    TextView mTvTraditionalCulture;
    TextView mTvPersonal;

    private int mPosition = 1;

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    private IMainTabView mIMainTabView;

    public void setIMainTavView(IMainTabView iMainTabView) {
        mIMainTabView = iMainTabView;
    }


    public MainTabView(Context context) {
        super(context);
        init(context);
    }

    public MainTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void rqFocus(){
        if(view!=null){
            view.requestFocus();
        }
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_main_tab, this);
        mTvMain = view.findViewById(R.id.tv_main);
        mTvNotice = view.findViewById(R.id.tv_notice);
        mTvClass = view.findViewById(R.id.tv_class);
        mTvClassClass = view.findViewById(R.id.tv_class_class);
        mTvElegantDemeanor = view.findViewById(R.id.tv_elegant_demeanor);
        mTvTraditionalCulture = view.findViewById(R.id.tv_traditional_culture);
        mTvPersonal = view.findViewById(R.id.tv_personal);
    }

    public void setTabChange(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT://你按左方向键
                if (mPosition >1 && mIMainTabView != null) {
                    mIMainTabView.tabUnselected(mPosition);
                    mIMainTabView.tabSelected( mPosition- 1);
                    mPosition--;
                    setTab();
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT://你按右方向键
                if (mPosition < 7 && mIMainTabView != null) {
                    mIMainTabView.tabUnselected( mPosition);
                    mIMainTabView.tabSelected( mPosition+ 1);
                    mPosition++;
                    setTab();
                }
                break;
        }
    }

    public void setTab() {
        switch (mPosition) {
            case 1 :
                setTabChange(mTvMain);
                break;
            case 2 :
                setTabChange(mTvNotice);
                break;
            case 3:
                setTabChange(mTvClass);
                break;
            case 4 :
                setTabChange(mTvClassClass);
                break;
            case 5 :
                setTabChange(mTvElegantDemeanor);
                break;
            case 6 :
                setTabChange(mTvTraditionalCulture);
                break;
            case 7 :
                setTabChange(mTvPersonal);
                break;
        }
    }


    private void setTabChange(View view) {
        mTvMain.setEnabled(false);
        mTvNotice.setEnabled(false);
        mTvClass.setEnabled(false);
        mTvClassClass.setEnabled(false);
        mTvElegantDemeanor.setEnabled(false);
        mTvTraditionalCulture.setEnabled(false);
        mTvPersonal.setEnabled(false);
        view.setEnabled(true);
    }















}
