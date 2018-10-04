package com.apemoon.tvbox.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apemoon.tvbox.R;


public class LoadingDialog extends AppCompatDialog {

    private Context mContext;

    private TextView mHintTextView;

    private ImageView mLoadingImageView;

    private LinearLayout mLinearLayout;

    private Animation mRotateAnimation;

    private String mHintText;


    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogStyleDimFalse);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        initDialog();
    }

    private void initDialog() {
        mHintTextView = (TextView) findViewById(R.id.text_hint_tv);
        mLinearLayout = (LinearLayout) findViewById(R.id.loading_root_ll);
        mLoadingImageView = (ImageView)findViewById(R.id.loading_progress_iv);
        mRotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.loading);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setDuration(3000);
        mRotateAnimation.setRepeatMode(Animation.INFINITE);
        mLoadingImageView.setAnimation(mRotateAnimation);
        if (!TextUtils.isEmpty(mHintText)) {
            mHintTextView.setText(mHintText);
        }
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        attributes.gravity = Gravity.CENTER;
        window.setDimAmount(0);
        window.setAttributes(attributes);
    }


    public void setHint(String waitHint) {
        mHintText = waitHint;
    }

    public void showLoading(){
        if(!isShowing()){
            if(null != mRotateAnimation){
                mRotateAnimation.start();
            }
            show();
        }
    }

    public void dimissLoading(){
        if(isShowing()){
            if(null != mRotateAnimation){
                mRotateAnimation.reset();
                mRotateAnimation.cancel();
            }
            dismiss();
        }
    }

}