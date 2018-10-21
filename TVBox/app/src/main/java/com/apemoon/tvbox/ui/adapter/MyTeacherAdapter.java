package com.apemoon.tvbox.ui.adapter;

import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class MyTeacherAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public MyTeacherAdapter() {
        super(R.layout.item_school_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.getView(R.id.ll_new).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setTextAnimation(view,hasFocus,1.1f,1.1f,1.0f,1.0f);
            }
        });
    }

}
