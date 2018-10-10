package com.apemoon.tvbox.ui.adapter.information;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.TvApplication;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.DateTimeUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class InformationAdapter extends BaseQuickAdapter<InfoListEntity.InformationBean,BaseViewHolder> {

    public InformationAdapter() {
        super(R.layout.item_personl_teachers);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoListEntity.InformationBean item) {
        if (item != null) {
            helper.setText(R.id.tv_name, item.getTitle());
            GlideUtil.image(TvApplication.getGlobalApplication(),item.getCover(),helper.getView(R.id.iv_head));
        }

        helper.getView(R.id.ll_notice).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
//                if(hasFocus){
//                    view.setBackgroundResource(R.drawable.bg_notice_normal);
//                }else{
//                    view.setBackgroundResource(R.drawable.bg_notice_selected);
//                }
                AnimationUtil.setTextAnimation(view,hasFocus,1.01f,1.01f,1.0f,1.0f);

            }
        });
    }

}
