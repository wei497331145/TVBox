package com.apemoon.tvbox.ui.adapter.information;

import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.TvApplication;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.DateTimeUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class InfoTwoClassicalAdapter extends BaseQuickAdapter<InfoClassicalEntity.TwoClassicalBean,BaseViewHolder> {

    public InfoTwoClassicalAdapter() {
        super(R.layout.item_info_two_classical);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoClassicalEntity.TwoClassicalBean item) {
        if (item != null) {
            helper.setText(R.id.item_classical, item.getName());
        }

        helper.getView(R.id.ll_notice).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
//                if(hasFocus){
//                    view.setBackgroundResource(R.drawable.bg_notice_normal);
//                }else{
//                    view.setBackgroundResource(R.drawable.bg_notice_selected);
//                }
//                AnimationUtil.setTextAnimation(view,hasFocus,1.01f,1.01f,1.0f,1.0f);

            }
        });
    }

}
