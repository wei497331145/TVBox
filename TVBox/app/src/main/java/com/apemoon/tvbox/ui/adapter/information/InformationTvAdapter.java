package com.apemoon.tvbox.ui.adapter.information;

import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.TvApplication;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.interfaces.recyclerview.RecyclerViewItemSelectListener;
import com.apemoon.tvbox.utils.DateTimeUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class InformationTvAdapter extends BaseQuickAdapter<InfoListEntity.InformationBean,BaseViewHolder> {

    private RecyclerViewItemSelectListener recyclerViewItemSelectListener;
    public InformationTvAdapter(RecyclerViewItemSelectListener listener) {
        super(R.layout.item_info_tv_infolist);
        this.recyclerViewItemSelectListener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoListEntity.InformationBean item) {
        if (item != null) {
            helper.setText(R.id.tv_name, item.getTitle());
            helper.setText(R.id.tv_time, DateTimeUtil.getStrTime(item.getCreateTime()));
            GlideUtil.image(TvApplication.getGlobalApplication(),item.getCover(),helper.getView(R.id.iv_head));
        }

        helper.getView(R.id.ll_notice).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    recyclerViewItemSelectListener.onItemSelectListner(helper.getLayoutPosition(),view);
                }

            }
        });
    }

}
