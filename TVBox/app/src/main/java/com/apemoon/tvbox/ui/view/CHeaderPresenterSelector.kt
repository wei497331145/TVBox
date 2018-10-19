package com.apemoon.tvbox.ui.view

import android.support.v17.leanback.widget.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.apemoon.tvbox.R



/**
 *TVBox
 * Created by mukry on 2018/10/16.
 * 深圳市伯尚科技有限公司
 */
class CHeaderPresenterSelector(presenter: Presenter) : PresenterSelector() {

    private var mPresenter: Presenter = presenter


    override fun getPresenter(item: Any): Presenter {
        return mPresenter
    }

    override fun getPresenters(): Array<Presenter> {
        return arrayOf(mPresenter)
    }
}


class CHeaderPresenter : RowHeaderPresenter() {
    //    private var mNullItemVisibilityGone: Boolean = false
//    private val mAnimateSelect: Boolean = false
//    private var mUnselectedAlpha: Float = 0.toFloat()
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
//        mUnselectedAlpha = parent?.resources!!.getFraction(
//                R.fraction.lb_browse_header_unselect_alpha, 1, 1)
        val rootView = LayoutInflater.from(parent?.context).inflate(R.layout.curriculum_header, parent, false)
        // val rootView = mFragment?.layoutInflater?.inflate(R.layout.curriculum_layout, null)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {
        val headerItem = (item as PageRow).headerItem
        if (headerItem is HeaderItem) {
            viewHolder?.view?.findViewById<TextView>(R.id.headTv)?.text = headerItem?.name
        }

    }

//    override fun onSelectLevelChanged(holder: RowHeaderPresenter.ViewHolder?) {
//        holder?.view?.alpha = mUnselectedAlpha + holder?.selectLevel!!.times(1.0f - mUnselectedAlpha)
//    }


}