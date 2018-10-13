package com.apemoon.tvbox.ui.adapter.information;

import android.text.TextUtils;
import android.view.View;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.app.TvApplication;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.utils.AnimationUtil;
import com.apemoon.tvbox.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by water on 2018/8/28/028.
 * des：
 */

public class InfoImagesAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public InfoImagesAdapter() {
        super(R.layout.item_info_images);
    }

    @Override
    protected void convert(BaseViewHolder helper, String imge) {
        if (!TextUtils.isEmpty(imge)) {
            GlideUtil.image(TvApplication.getGlobalApplication(),imge,helper.getView(R.id.img));
        }

    }

}
