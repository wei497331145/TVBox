package com.apemoon.tvbox.ui.adapter.information;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.userCenter.UserSemstersEntity;

import java.util.List;

/**
 * Created by mukry on 2015/8/13.
 */
public class InfoTwoClassicalListViewAdapter extends BaseAdapter {

    List<InfoClassicalEntity.TwoClassicalBean> twoClassicalBeans;
    private Context context;

    public InfoTwoClassicalListViewAdapter(Context context, List<InfoClassicalEntity.TwoClassicalBean> twoClassicalBeans) {
        this.context = context;
        this.twoClassicalBeans = twoClassicalBeans;
    }

    @Override
    public int getCount() {
        return twoClassicalBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return twoClassicalBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // ViewHolder holder;
        final InfoClassicalEntity.TwoClassicalBean bean = twoClassicalBeans.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(
                R.layout.item_info_two_classical, null);
        TextView msgTv = (TextView) convertView
                .findViewById(R.id.item_classical);
        msgTv.setText(bean.getName());
        return convertView;
    }

}
