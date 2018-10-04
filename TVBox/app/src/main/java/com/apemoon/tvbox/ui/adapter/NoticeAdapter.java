package com.apemoon.tvbox.ui.adapter;

import android.text.Html;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.utils.DateTimeUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class NoticeAdapter extends BaseQuickAdapter<ReceiveNoticeListEntity.NoticeListBean,BaseViewHolder> {

    public NoticeAdapter() {
        super(R.layout.item_notice_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReceiveNoticeListEntity.NoticeListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_content, (Html.fromHtml(item.getContent())).toString());
            helper.setText(R.id.tv_time, DateTimeUtil.getStrTime(item.getCreateTime()));
        }
    }


}
