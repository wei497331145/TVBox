package com.apemoon.tvbox.ui.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.interfaces.IMainTabView;

import java.util.logging.Handler;

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
    TextView mTvStore;

    private int mOldPOsition = 1;
    private int mPosition = 1;
    private View indicator;

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        if (position > 7 || position < 1) {
            return;
        }
        if (mPosition > 0 && mIMainTabView != null) {
            mIMainTabView.tabUnselected(mPosition);
            mIMainTabView.tabSelected(position);
            mPosition = position;
            setTab();
        }
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

    public void rqFocus() {

            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (view != null) {
                        view.requestFocus();
                    }
                }
            },500);


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
        mTvStore = view.findViewById(R.id.tv_store);
        this.indicator = view.findViewById(R.id.indicator); //指示器


    }


    /**
     * tab 来回切换的动画
     *
     * @param tab
     * @return
     */
    private AnimatorSet buildIndicatorAnimatorTowards(View tab) {
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(indicator, "X", indicator.getX(), tab.getX()+20);

        final ViewGroup.LayoutParams params = indicator.getLayoutParams();
        ValueAnimator widthAnimator = ValueAnimator.ofInt(params.width, tab.getMeasuredWidth()-40);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.width = (int) animation.getAnimatedValue();
                indicator.setLayoutParams(params);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.playTogether(xAnimator, widthAnimator);

        return set;
    }



    public boolean setTabChange(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT://你按左方向键
                if (mPosition > 1 && mIMainTabView != null) {
                    mIMainTabView.tabUnselected(mPosition);
                    mIMainTabView.tabSelected(mPosition - 1);
                    mPosition--;
                    setTab();
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT://你按右方向键
                if (mPosition < 8 && mIMainTabView != null) {
                    mIMainTabView.tabUnselected(mPosition);
                    mIMainTabView.tabSelected(mPosition + 1);
                    mPosition++;
                    setTab();
                    return true;
                }
//                rqFocus();
                break;
        }
        return false;
    }

    public void setTab() {
        switch (mPosition) {
            case 1:
                setTabChange(mTvMain);
                break;
            case 2:
                setTabChange(mTvNotice);
                break;
            case 3:
                setTabChange(mTvClass);
                break;
            case 4:
                setTabChange(mTvClassClass);
                break;
            case 5:
                setTabChange(mTvElegantDemeanor);
                break;
            case 6:
                setTabChange(mTvTraditionalCulture);
                break;
            case 7:
                setTabChange(mTvPersonal);
                break;
            case 8:
                setTabChange(mTvStore);
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
        mTvStore.setEnabled(false);
        view.setEnabled(true);
        buildIndicatorAnimatorTowards(view).start();

    }

    public void setTabUnable(){
        mTvMain.setEnabled(false);
        mTvNotice.setEnabled(false);
        mTvClass.setEnabled(false);
        mTvClassClass.setEnabled(false);
        mTvElegantDemeanor.setEnabled(false);
        mTvTraditionalCulture.setEnabled(false);
        mTvPersonal.setEnabled(false);
        mTvStore.setEnabled(false);

    }

}
