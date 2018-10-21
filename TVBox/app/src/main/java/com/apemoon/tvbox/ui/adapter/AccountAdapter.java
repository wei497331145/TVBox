package com.apemoon.tvbox.ui.adapter;

import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.TvApplication;
import com.apemoon.tvbox.entity.AccountListEntity;
import com.apemoon.tvbox.entity.userCenter.UserTeachersEntity;
import com.apemoon.tvbox.interfaces.recyclerview.RecyclerViewItemSelectListener;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class AccountAdapter extends BaseQuickAdapter<AccountListEntity.AccountInfoBean,BaseViewHolder> {

    private RecyclerViewItemSelectListener recyclerViewItemSelectListener;

    public AccountAdapter(RecyclerViewItemSelectListener listener) {
        super(R.layout.item_personl_accounts);
        recyclerViewItemSelectListener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper,AccountListEntity.AccountInfoBean item) {
        if (item != null) {
            helper.setText(R.id.tv_name, item.getUserName());
            ((helper.getView(R.id.tv_duty))).setVisibility(View.GONE);
            GlideUtil.imageCircleLocal(TvApplication.getGlobalApplication(),item.getUserHeadImg(),helper.getView(R.id.iv_head));

        }

        helper.getView(R.id.ll_notice).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                AnimationUtil.setTextAnimation(view,hasFocus,1.1f,1.1f,1.0f,1.0f);
                recyclerViewItemSelectListener.onItemSelectListner(helper.getLayoutPosition());
            }
        });
    }

}
