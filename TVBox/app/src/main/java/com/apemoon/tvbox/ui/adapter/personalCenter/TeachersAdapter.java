package com.apemoon.tvbox.ui.adapter.personalCenter;

import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.TvApplication;
import com.apemoon.tvbox.entity.userCenter.UserTeachersEntity;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class TeachersAdapter extends BaseQuickAdapter<UserTeachersEntity.TeachersInfoBean,BaseViewHolder> {

    public TeachersAdapter() {
        super(R.layout.item_personl_teachers);
    }

    @Override
    protected void convert(BaseViewHolder helper,UserTeachersEntity.TeachersInfoBean item) {
        if (item != null) {
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_duty,item.getDutyName());
            GlideUtil.imageCircleLocal(TvApplication.getGlobalApplication(),item.getHeadImage(),helper.getView(R.id.iv_head));

        }

        helper.getView(R.id.ll_notice).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setTextAnimation(view,hasFocus,1.01f,1.01f,1.0f,1.0f);

            }
        });
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if(position==0){
            holder.getView(R.id.ll_notice).setNextFocusUpId(R.id.scroll);
        }
    }
}
