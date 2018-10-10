package com.apemoon.tvbox.ui.adapter.personalCenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.entity.userCenter.UserSemstersEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukry on 2015/8/13.
 */
public class SemesterListViewAdapter extends BaseAdapter {

    private List<UserSemstersEntity.SemstersBean> semesterList;//= new ArrayList<RatesBean>();
    private Context context;

    public SemesterListViewAdapter(Context context, List<UserSemstersEntity.SemstersBean> semesterList) {
        this.context = context;
        this.semesterList = semesterList;
    }

    @Override
    public int getCount() {
        return semesterList.size();
    }

    @Override
    public Object getItem(int position) {
        return semesterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // ViewHolder holder;
        final UserSemstersEntity.SemstersBean bean = semesterList.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(
                R.layout.item_personl_semester, null);
        TextView msgTv = (TextView) convertView
                .findViewById(R.id.item_personl_semester);
        msgTv.setText(bean.getName());
        return convertView;
    }

}
