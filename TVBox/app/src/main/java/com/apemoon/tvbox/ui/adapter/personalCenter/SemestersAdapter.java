package com.apemoon.tvbox.ui.adapter.personalCenter;

import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.entity.userCenter.UserRecordInfoEntity;
import com.apemoon.tvbox.entity.userCenter.UserSemstersEntity;
import com.apemoon.tvbox.ui.view.ItemLinearLayout;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class SemestersAdapter extends BaseQuickAdapter<UserSemstersEntity.SemstersBean,BaseViewHolder> {

    public SemestersAdapter() {
        super(R.layout.item_personl_semester);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserSemstersEntity.SemstersBean item) {
        if (item != null) {
            helper.setText(R.id.tv_schemer,item.getName());
//            ((ItemLinearLayout)helper.getView(R.id.item_school)).setRightText(item.getSchoolName());
//            ((ItemLinearLayout)helper.getView(R.id.item_time)).setRightText(item.getStartTime());
//            ((ItemLinearLayout)helper.getView(R.id.item_santion)).setRightText(item.get());
//            ((ItemLinearLayout)helper.getView(R.id.item_info)).setRightText(item.get());



        }

        helper.getView(R.id.ll_notice).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setTextAnimation(view,hasFocus,1.01f,1.01f,1.0f,1.0f);

            }
        });
    }

}
