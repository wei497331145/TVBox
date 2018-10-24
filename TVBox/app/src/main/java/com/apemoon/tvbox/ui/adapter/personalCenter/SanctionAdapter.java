package com.apemoon.tvbox.ui.adapter.personalCenter;

import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.entity.userCenter.UserRecordInfoEntity;
import com.apemoon.tvbox.ui.view.ItemLinearLayout;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class SanctionAdapter extends BaseQuickAdapter<UserRecordInfoEntity.UserRecordsInfoBean, BaseViewHolder> {

    public SanctionAdapter() {
        super(R.layout.item_personl_santion);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserRecordInfoEntity.UserRecordsInfoBean item) {
        if (item != null) {
            ((ItemLinearLayout) helper.getView(R.id.item_school)).setRightText(item.getSchoolName());
            ((ItemLinearLayout) helper.getView(R.id.item_time)).setRightText(item.getJcTime());
            ((ItemLinearLayout) helper.getView(R.id.item_santion)).setRightText(item.getContent());
            ((ItemLinearLayout) helper.getView(R.id.item_info)).setRightText(item.getIntro());
        }

        helper.getView(R.id.ll_notice).setNextFocusLeftId(R.id.tv_sanction_info);

        helper.getView(R.id.ll_notice).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setTextAnimation(view, hasFocus, 1.01f, 1.01f, 1.0f, 1.0f);

            }
        });
    }

}
