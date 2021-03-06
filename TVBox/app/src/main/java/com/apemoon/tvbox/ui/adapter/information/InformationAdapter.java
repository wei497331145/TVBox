package com.apemoon.tvbox.ui.adapter.information;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.TvApplication;
import com.apemoon.tvbox.base.BaseFragment;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.ui.fragment.ElegantDemeanorFragment;
import com.apemoon.tvbox.ui.fragment.TraditionalFragment;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.DateTimeUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class InformationAdapter extends BaseQuickAdapter<InfoListEntity.InformationBean, BaseViewHolder> {

    private BaseFragment fragment;

    public InformationAdapter(BaseFragment fragment) {
        super(R.layout.item_info_infolist);
        this.fragment = fragment;
    }


    public InformationAdapter() {
        super(R.layout.item_info_infolist);
    }


    @Override
    protected void convert(BaseViewHolder helper, InfoListEntity.InformationBean item) {
        if (item != null) {
            helper.setText(R.id.tv_name, item.getTitle());
            helper.setText(R.id.tv_time, DateTimeUtil.getStrTime(item.getCreateTime()));
            GlideUtil.image(TvApplication.getGlobalApplication(), item.getCover(), helper.getView(R.id.iv_head));
            helper.setVisible(R.id.iv_video_noti, item.getType().equals("3"));
        }
        helper.getView(R.id.ll_notice).setId(View.generateViewId());
        helper.getView(R.id.ll_notice).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
//                if(hasFocus){
//                    view.setBackgroundResource(R.drawable.bg_notice_normal);
//                }else{
//                    view.setBackgroundResource(R.drawable.bg_notice_selected);
//                }
                AnimationUtil.setTextAnimation(view, hasFocus, 1.01f, 1.01f, 1.0f, 1.0f);
            }
        });

        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int spanCount = 0;
            if (null != layoutManager && layoutManager instanceof GridLayoutManager) {
                spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            }
            if (spanCount == 0 || helper.getLayoutPosition() % spanCount == 0) {
                if (null != fragment && (fragment instanceof TraditionalFragment)) {
                    if (null != ((TraditionalFragment) fragment).getSelectLeftView()) {
                        helper.getView(R.id.ll_notice).setNextFocusLeftId(((TraditionalFragment) fragment).getSelectLeftView().getId());
                    }
                } else if (null != fragment && (fragment instanceof ElegantDemeanorFragment)) {
                    if (null != ((ElegantDemeanorFragment) fragment).getSelectLeftView()) {
                        helper.getView(R.id.ll_notice).setNextFocusLeftId(((ElegantDemeanorFragment) fragment).getSelectLeftView().getId());
                    }
                }
            }
        }
    }

}
