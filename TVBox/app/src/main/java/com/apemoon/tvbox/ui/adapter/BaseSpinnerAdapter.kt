package com.apemoon.tvbox.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.util.*

/**
 *TVBox
 * Created by mukry on 2018/10/17.
 * 深圳市伯尚科技有限公司
 */
public abstract class BaseSpinnerAdapter<T>(list: List<T>) : BaseAdapter() {

    private var mData: MutableList<T>? = null

    init {
        if (null == mData) {
            mData = ArrayList()
        } else {
            mData!!.clear()
        }
        mData!!.addAll(list)
    }

    fun repData(list: List<T>?) {
        if (null != list && !list.isEmpty()) {
            mData!!.addAll(list)
        }
    }


    override fun getCount(): Int {
        return mData!!.size
    }

    override fun getItem(position: Int): T {
        return mData!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    abstract override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
}