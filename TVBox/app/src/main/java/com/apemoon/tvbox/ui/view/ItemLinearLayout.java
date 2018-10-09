package com.apemoon.tvbox.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apemoon.tvbox.R;


/**
 * 自定义组合控件
 * 一行显示，左右对齐
 * @author 谭忠扬-YuriTam // yangjianwen 2018/3/2
 * @time 2017年4月12日
 */
public class ItemLinearLayout extends LinearLayout {
    private static final String TAG = ItemLinearLayout.class.getSimpleName();

    //描述
    private TextView tv_key_desc;
    //对应的值
    private TextView tv_key_value;

    public ItemLinearLayout(Context context) {
        this(context,null);
    }

    public ItemLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ItemLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
        //初始化自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemLinearLayout);
        if (typedArray != null){
            //左边文字
            String leftText = typedArray.getString(R.styleable.ItemLinearLayout_left_text);
            if (!TextUtils.isEmpty(leftText)){
                tv_key_desc.setText(leftText);
            }
            //右边文字
            String rightText = typedArray.getString(R.styleable.ItemLinearLayout_right_text);
            if (!TextUtils.isEmpty(rightText)){
                tv_key_value.setText(rightText);
            }
            typedArray.recycle();
        }
    }

    private void initView(Context mContext){
        LayoutInflater.from(mContext).inflate(R.layout.item_line_layout,this,true);
        tv_key_desc = (TextView) findViewById(R.id.tv_key_desc);
        tv_key_value = (TextView) findViewById(R.id.tv_key_value);
    }

    public void setLeftText(String leftText) {
        if (!TextUtils.isEmpty(leftText)){
            tv_key_desc.setText(leftText);
        }
    }

    public void setLeftText(int leftResId) {
        if (leftResId != 0){
            tv_key_desc.setText(leftResId);
        }
    }

    public void setLeftText(boolean isBold, int sp, String leftText) {
        if (!TextUtils.isEmpty(leftText)) {
            if (sp > 0) {
                tv_key_desc.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
            }
            tv_key_desc.setText(leftText);
            tv_key_desc.getPaint().setFakeBoldText(isBold);
        }
    }

    public void setLeftText(boolean isBold, int sp, int leftResId) {
        if (leftResId != 0) {
            if (sp > 0) {
                tv_key_desc.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
            }
            tv_key_desc.setText(leftResId);
            tv_key_desc.getPaint().setFakeBoldText(isBold);
        }
    }

    public void setRightText(String rightText){
        if (!TextUtils.isEmpty(rightText)){
            tv_key_value.setText(rightText);
        }
    }

    public void setRightText(int rightResId){
        if (rightResId != 0){
            tv_key_value.setText(rightResId);
        }
    }

    public void setRightText(boolean isBold, int sp, String rightText) {
        if (!TextUtils.isEmpty(rightText)) {
            if (sp > 0) {
                tv_key_value.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
            }
            tv_key_value.setText(rightText);
            tv_key_value.getPaint().setFakeBoldText(isBold);
        }
    }

    public void setRightText(boolean isBold, int sp, int rightResId) {
        if (rightResId != 0) {
            if (sp > 0) {
                tv_key_value.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
            }
            tv_key_value.setText(rightResId);
            tv_key_value.getPaint().setFakeBoldText(isBold);
        }
    }

    public void appendRightText(String text){
        if (tv_key_value.getVisibility() == VISIBLE){
            tv_key_value.append(text);
        }
    }
}
