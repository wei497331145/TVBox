package com.apemoon.tvbox.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v17.leanback.app.BackgroundManager
import android.support.v17.leanback.app.BrowseSupportFragment
import android.support.v17.leanback.widget.*
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.apemoon.tvbox.R
import com.apemoon.tvbox.base.GridFragment
import com.apemoon.tvbox.base.net.HttpResultBody
import com.apemoon.tvbox.base.rx.ProgressObserver
import com.apemoon.tvbox.base.rx.RxBasePresenter
import com.apemoon.tvbox.entity.ClassActivityBean
import com.apemoon.tvbox.entity.ClassActivityList
import com.apemoon.tvbox.entity.PhotoAlbumBean
import com.apemoon.tvbox.entity.PhotoAlbumList
import com.apemoon.tvbox.ui.view.CHeaderPresenter
import com.apemoon.tvbox.ui.view.CHeaderPresenterSelector
import com.apemoon.tvbox.utils.ConstantUtil
import com.apemoon.tvbox.utils.GlobalUtil.mContext
import com.apemoon.tvbox.utils.PreferenceUtil
import com.apemoon.tvbox.utils.RequestUtil
import com.bumptech.glide.Glide


/**
 * Created by water on 2018/8/31/031.
 * des： 我的班级
 */
class ClassFragment : BrowseSupportFragment() {
    private var mBackgroundManager: BackgroundManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRows()
        setHeaderPresenterSelector(CHeaderPresenterSelector(CHeaderPresenter()))
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager?.drawable = ColorDrawable(Color.parseColor("#00000000"))
        if (!mBackgroundManager!!.isAttached) {
            mBackgroundManager?.attach(activity?.window)
        }
        mainFragmentRegistry.registerFragment(PageRow::class.java,
                PageRowFragmentFactory(mBackgroundManager!!))
    }


    private var mRowsAdapter: ArrayObjectAdapter? = null
    private fun loadRows() {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val presenterHeader0 = HeaderItem(0, "班级活动")
        val presenterHeader1 = HeaderItem(1, "校本课")
        val presenterHeader2 = HeaderItem(2, "学校相册")
        val presenterHeader3 = HeaderItem(3, "班级相册")
        val presenterHeader4 = HeaderItem(4, "个人相册")
        //课表
//        val mGridPresenter0 = CurriculumItemPresenter()
//        val gridRowAdapter0 = ArrayObjectAdapter(mGridPresenter0)
        mRowsAdapter?.add(PageRow(presenterHeader0))
        mRowsAdapter?.add(PageRow(presenterHeader1))
        mRowsAdapter?.add(PageRow(presenterHeader2))
        mRowsAdapter?.add(PageRow(presenterHeader3))
        mRowsAdapter?.add(PageRow(presenterHeader4))

        /* set */
        adapter = mRowsAdapter
    }

    private class PageRowFragmentFactory internal constructor(private val mBackgroundManager: BackgroundManager) : BrowseSupportFragment.FragmentFactory<Fragment>() {

        override fun createFragment(rowObj: Any): Fragment? {
            val row = rowObj as Row
            mBackgroundManager.drawable = ColorDrawable(Color.parseColor("#00000000"))
            when (row.headerItem.id) {
                0L -> {
                    return ClassActivityFragment()
                }
                1L -> {
                    return ClassRoomFragment.EmptyFragment()
                }
                2L -> {
                    return ClassRoomFragment.EmptyFragment()
                }
                3L -> {
                    val photoSFragment = PhotoSFragment()
                    photoSFragment.photoAlbumType = "2"
                    return photoSFragment
                }
                4L -> {
                    val photoSFragment = PhotoSFragment()
                    photoSFragment.photoAlbumType = "1"
                    return photoSFragment
                }
                else -> throw IllegalArgumentException(String.format("Invalid row %s", rowObj))
            }

        }
    }


    /**
     * 班级活动
     */
    class ClassActivityFragment : GridFragment() {
        private val ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_LARGE
        private var mAdapter: ArrayObjectAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setupAdapter()
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            loadData()
        }

        private fun setupAdapter() {
            val presenter = VerticalGridPresenter(ZOOM_FACTOR)
            presenter.numberOfColumns = 3
            gridPresenter = presenter
            mAdapter = ArrayObjectAdapter(CurriculumPresenter(this))
            adapter = mAdapter
            onItemViewClickedListener = OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            }
        }

        // userId 	是 	int 	用户id
//        userType 	是 	int 	用户类型
//        type 	是 	int 	类型：1收到的班级班级活动列表、2发送的班级活动列表
        private fun loadData() {
            val userId = PreferenceUtil.getString(ConstantUtil.USER_ID, "")
            val userType = PreferenceUtil.getString(ConstantUtil.USER_TYPE, "")
            val paras = RequestUtil.createMap()
            paras["userId"] = userId
            paras["userType"] = userType
            paras["type"] = "1"
            paras["pageNo"] = "1"
            paras["pageSize"] = "100"
            val rxP = object : RxBasePresenter(activity) {}
            rxP.addDisposable<HttpResultBody<ClassActivityList>>(rxP.getmDataManager().netService.getClassActivityList(paras),
                    object : ProgressObserver<HttpResultBody<ClassActivityList>>(activity, false) {
                        override fun doNext(httpResultBody: HttpResultBody<ClassActivityList>) {
                            if (TextUtils.equals(httpResultBody.code, "0000")) {
                                mAdapter?.clear()
                                val list = httpResultBody.result.classActivityList
                                list.forEach {
                                    mAdapter?.add(it)
                                }
                                mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
                            }
                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)

                        }
                    })
        }


        class CurriculumPresenter(private var mFragment: Fragment?) : Presenter() {
            override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
                val rootView = LayoutInflater.from(parent?.context).inflate(R.layout.class_activity_layout, parent, false)
                // val rootView = mFragment?.layoutInflater?.inflate(R.layout.curriculum_layout, null)
                return ViewHolder(rootView)
            }

            override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
                if (item is ClassActivityBean) {
                    viewHolder?.view?.findViewById<TextView>(R.id.activityTitleTv)?.text = item.title
                    viewHolder?.view?.findViewById<TextView>(R.id.activityTimeTv)?.text = item.createTime
                    when (item.type) {
                        "1" -> {
                            viewHolder?.view?.findViewById<TextView>(R.id.activityTypeTv)?.text = "普通活动"
                            // viewHolder?.view?.findViewById<ImageView>(R.id.activityTypeIv)?.setImageResource()
                        }
                        "2" -> {
                            viewHolder?.view?.findViewById<TextView>(R.id.activityTypeTv)?.text = "投票活动"
                            // viewHolder?.view?.findViewById<ImageView>(R.id.activityTypeIv)?.setImageResource()
                        }
                    }
                }
            }

            override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
            }
        }

    }

    /**
     * 学校相册 School photo album
     */
    class PhotoSFragment : GridFragment() {
        var photoAlbumType: String = "1"
        private val ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_LARGE
        private var mAdapter: ArrayObjectAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setupAdapter()
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            loadData()
        }

        private fun setupAdapter() {
            val presenter = VerticalGridPresenter(ZOOM_FACTOR)
            presenter.numberOfColumns = 3
            gridPresenter = presenter
            mAdapter = ArrayObjectAdapter(PhotoAlbumPresenter(this))
            adapter = mAdapter
            onItemViewClickedListener = OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            }
        }

        //        userId 	是 	int 	登录用户的id
//        userType 	是 	int 	登录用户的类型：1教师、2学生、3家长、4企业用户
//        photoAlbumUserId 	是 	int 	被查询用户的id
//        photoAlbumUserType 	是 	int 	查被询用户的类型 ：1教师、2学生、3家长、4企业用户
//        photoAlbumType 	是 	int 	相册类型:1个人相册、2班级相册
//        pageNo 	是 	int 	页码
//        pageSize 	是 	int 	每页显示记录数
        private fun loadData() {
            val userId = PreferenceUtil.getString(ConstantUtil.USER_ID, "")
            val userType = PreferenceUtil.getString(ConstantUtil.USER_TYPE, "")
            val paras = RequestUtil.createMap()
            paras["userId"] = userId
            paras["userType"] = userType
            paras["photoAlbumUserType"] = "2"
            paras["photoAlbumUserId"] = userId
            paras["photoAlbumType"] = photoAlbumType
            paras["pageNo"] = "1"
            paras["pageSize"] = "100"
            val rxP = object : RxBasePresenter(activity) {}
            rxP.addDisposable<HttpResultBody<PhotoAlbumList>>(rxP.getmDataManager().netService.getPhotoAlbumList(paras),
                    object : ProgressObserver<HttpResultBody<PhotoAlbumList>>(activity, false) {
                        override fun doNext(httpResultBody: HttpResultBody<PhotoAlbumList>) {
                            if (TextUtils.equals(httpResultBody.code, "0000")) {
                                mAdapter?.clear()
                                val list = httpResultBody.result.photoAlbumList
                                list.forEach {
                                    mAdapter?.add(it)
                                }
                                mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
                            }
                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)

                        }
                    })

        }


        class PhotoAlbumPresenter(private var mFragment: Fragment?) : Presenter() {

            internal class ViewHolder(view: View) : Presenter.ViewHolder(view) {
                val defaultCardImage: Drawable
                var photoAlbumBean: PhotoAlbumBean? = null
                var itemView: View? = null
                var photoIv: ImageView? = null

                init {
                    itemView = view
                    photoIv = view.findViewById<ImageView>(R.id.photoIv)
                    defaultCardImage = mContext.resources.getDrawable(R.drawable.set)
                }

                fun updateCardViewImage(uri: String) {
                    Glide.with(mContext).load(uri).into(photoIv)
//                    Picasso.with(mContext)
//                            .load(uri.toString())
//                            .resize(Utils.convertDpToPixel(mContext, CARD_WIDTH),
//                                    Utils.convertDpToPixel(mContext, CARD_HEIGHT))
//                            .error(mDefaultCardImage)
//                            .into(mImageCardViewTarget)
                }


            }


            override fun onCreateViewHolder(parent: ViewGroup?): Presenter.ViewHolder {
                val rootView = LayoutInflater.from(parent?.context).inflate(R.layout.photo_item_layout, parent, false)
                // val rootView = mFragment?.layoutInflater?.inflate(R.layout.curriculum_layout, null)
                return ViewHolder(rootView)
            }

            override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {
                //  PhotoAlbumBean
                if (item is PhotoAlbumBean) {
                    (viewHolder as ViewHolder).photoAlbumBean = item
                    viewHolder.updateCardViewImage(item.cover)
                    //  val imageView = viewHolder.view?.findViewById<ImageView>(R.id.photoIv)

                    viewHolder.view?.findViewById<TextView>(R.id.photoNameTv)?.text = item.name
                    viewHolder.view?.findViewById<TextView>(R.id.timeTv)?.text = item.createTime
                }

            }

            override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder?) {

            }
        }

    }

}
