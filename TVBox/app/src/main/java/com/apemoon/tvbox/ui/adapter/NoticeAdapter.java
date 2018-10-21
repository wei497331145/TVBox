package com.apemoon.tvbox.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.DateTimeUtil;
import com.apemoon.tvbox.utils.LogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class NoticeAdapter extends BaseQuickAdapter<ReceiveNoticeListEntity.NoticeListBean,BaseViewHolder> {
    private NoticeRecyclerViewItemSelectListener listener;

    public NoticeAdapter(NoticeRecyclerViewItemSelectListener listener ) {
        super(R.layout.item_notice_news);
        this.listener = listener;
    }



    @Override
    protected void convert(BaseViewHolder helper, ReceiveNoticeListEntity.NoticeListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_content, (Html.fromHtml(item.getContent())).toString());
            helper.setText(R.id.tv_time, DateTimeUtil.getStrTime(item.getCreateTime()));
            helper.setVisible(R.id.v_dot_read,"0".endsWith(item.getIsRead()));
        }

        helper.getView(R.id.ll_notice).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(view !=null && listener!=null && hasFocus) {
                    listener.onItemSelectListner(helper.getLayoutPosition());

                }
            }
        });
    }

    public interface NoticeRecyclerViewItemSelectListener{
        void onItemSelectListner(int position);
    }

}
