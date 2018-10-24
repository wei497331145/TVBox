package com.apemoon.tvbox.ui.adapter;

import android.text.Html;
import android.view.KeyEvent;
import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.utils.DateTimeUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class NoticeAdapter extends BaseQuickAdapter<ReceiveNoticeListEntity.NoticeListBean, BaseViewHolder> {
    private NoticeRecyclerViewItemSelectListener listener;

    public NoticeAdapter(NoticeRecyclerViewItemSelectListener listener) {
        super(R.layout.item_notice_news);
        this.listener = listener;
    }


    @Override
    protected void convert(BaseViewHolder helper, ReceiveNoticeListEntity.NoticeListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_promulgator, item.getAutograph());
            helper.setText(R.id.tv_content, (Html.fromHtml(item.getContent())).toString());
            helper.setText(R.id.tv_time, DateTimeUtil.getStrTime(item.getCreateTime()));
            helper.setVisible(R.id.v_dot_read, "0".endsWith(item.getIsRead()));
        }

        helper.getView(R.id.ll_notice).setId(View.generateViewId());

        helper.getView(R.id.ll_notice).setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                int count = getRecyclerView().getLayoutManager().getChildCount();
                for (int i = 0; i < count; i++) {
                    getRecyclerView().getLayoutManager().getChildAt(i).setBackgroundResource(R.drawable.bg_bl_notice_selector);
                }
            }
            if (view != null && listener != null && hasFocus) {
                listener.onItemSelectListner(helper.getLayoutPosition(), view);
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

    public interface NoticeRecyclerViewItemSelectListener {
        void onItemSelectListner(int position, View itemView);
    }

}
