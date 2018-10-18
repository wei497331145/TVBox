package com.apemoon.tvbox.ui.adapter;

import android.view.View;
import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.TvApplication;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.DateTimeUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class NewAdapter extends BaseQuickAdapter<InfoListEntity.InformationBean,BaseViewHolder> {

    public NewAdapter() {
        super(R.layout.item_school_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoListEntity.InformationBean item) {

        helper.setText(R.id.tv_detail, item.getTitle());
        helper.setText(R.id.tv_time, DateTimeUtil.getStrTime(item.getCreateTime()));
        GlideUtil.image(TvApplication.getGlobalApplication(),item.getCover(),helper.getView(R.id.iv_head));

        helper.getView(R.id.ll_new).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setTextAnimation(view,hasFocus,1.05f,1.1f,1.0f,1.0f);
            }
        });
    }

}
