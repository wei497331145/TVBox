package com.apemoon.tvbox.ui.adapter.information;

import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.interfaces.recyclerview.RecyclerViewItemSelectListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class InfoTwoClassicalAdapter extends BaseQuickAdapter<InfoClassicalEntity.TwoClassicalBean, BaseViewHolder> {
    private RecyclerViewItemSelectListener recyclerViewItemSelectListener;

    public InfoTwoClassicalAdapter(RecyclerViewItemSelectListener listener) {
        super(R.layout.item_info_two_classical);
        recyclerViewItemSelectListener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoClassicalEntity.TwoClassicalBean item) {
        if (item != null) {
            helper.setText(R.id.item_classical, item.getName());
        }
        helper.getView(R.id.ll_notice).setId(View.generateViewId());

        helper.getView(R.id.ll_notice).setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                recyclerViewItemSelectListener.onItemSelectListner(helper.getLayoutPosition(),view);
                RecyclerView recyclerView = getRecyclerView();
                if (null != recyclerView) {
                    int count = recyclerView.getLayoutManager().getChildCount();
                    for (int i = 0; i < count; i++) {
                        recyclerView.getLayoutManager().getChildAt(i).setBackgroundResource(R.drawable.bg_bl_tv_info_selector);
                    }
                }
            }
        });

        helper.getView(R.id.ll_notice).setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (v.getNextFocusRightId() != v.getId()) {
                    v.setBackgroundResource(R.drawable.bg_bl_tv_info_drawable);
                }
            }
            return false;
        });
    }

}
