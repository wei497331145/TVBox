package com.apemoon.tvbox.ui.fragment

import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.apemoon.tvbox.R
import com.apemoon.tvbox.base.BaseFragment
import com.apemoon.tvbox.base.net.HttpResultBody
import com.apemoon.tvbox.base.rx.ProgressObserver
import com.apemoon.tvbox.base.rx.RxBasePresenter
import com.apemoon.tvbox.entity.*
import com.apemoon.tvbox.ui.activity.MainActivity
import com.apemoon.tvbox.utils.*
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 *TVBox
 * Created by mukry on 2018/10/19.
 * 深圳市伯尚科技有限公司
 */
class ClassFragment : BaseFragment() {
    val headerList = listOf<String>("班级活动", "校本课", "学校相册", "班级相册", "个人相册")
    override fun lazyLoadData() {
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val manager = childFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.fragmentsContainer, fragment, tag)
        transaction.commit()
    }


    private fun replaceFragment(fragment: Fragment) {
        val manager = childFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentsContainer, fragment)
        transaction.commit()
    }


    override fun getLayoutRes(): Int {
        return R.layout.class_room_fragment
    }

    var fragmentsContainer: FrameLayout? = null
    var headerRecyclerView: RecyclerView? = null
    override fun initView() {
        fragmentsContainer = mView?.findViewById<FrameLayout>(R.id.fragmentsContainer)
        headerRecyclerView = mView?.findViewById<RecyclerView>(R.id.headerRecyclerView)
    }

    override fun initData() {
        headerRecyclerView?.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.curriculum_header, headerList) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper?.getView<TextView>(R.id.headTv)?.text = item
            }

            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder?.getView<ViewGroup>(R.id.rootHeadLayout)?.setOnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        val fr = FragmentFactory.createFragment(position)
                        replaceFragment(fr!!)
                    }
                }
            }
        }
    }

    override fun initListener() {
        (headerRecyclerView?.adapter!! as BaseQuickAdapter<String, BaseViewHolder>).onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val fr = FragmentFactory.createFragment(position = position)
            replaceFragment(fr!!)
        }
    }


    private object FragmentFactory {
        fun createFragment(position: Int): Fragment? {
            when (position) {
                0 -> {
                    return ClassActivityFragment()
                }
                1 -> {
                    return EmptyFragment()
                }
                2 -> {
                    return EmptyFragment()
                }
                3 -> {
                    val fragment = PhotoAlbumFragment()
                    fragment.photoAlbumType = "2"
                    return fragment
                }
                4 -> {
                    val fragment = PhotoAlbumFragment()
                    fragment.photoAlbumType = "1"
                    return fragment
                }
                else -> return null
            }

        }
    }

}

/**
 * 班级活动
 */
class ClassActivityFragment : BaseFragment() {
    override fun lazyLoadData() {
        loadData()
    }

    override fun getLayoutRes(): Int {
        return R.layout.curriculum_list_layout
    }

    var contentRecyclerView: RecyclerView? = null
    override fun initView() {
        contentRecyclerView = mView?.findViewById<RecyclerView>(R.id.contentRecyclerView)
    }

    override fun initData() {
        contentRecyclerView?.layoutManager = GridLayoutManager(activity, 3)
        contentRecyclerView?.adapter = object : BaseQuickAdapter<ClassActivityBean, BaseViewHolder>(R.layout.class_activity_layout) {
            override fun convert(helper: BaseViewHolder?, item: ClassActivityBean?) {
                helper?.getView<TextView>(R.id.activityTitleTv)?.text = item?.title
                if (!TextUtils.isEmpty(item?.createTime)) {
                    helper?.getView<TextView>(R.id.activityTimeTv)?.text = DateTimeUtil.getStrTime(item?.createTime?.toLong()!!)
                }
                when (item?.type) {
                    "1" -> {
                        helper?.getView<TextView>(R.id.activityTypeTv)?.text = "普通活动"
                        helper?.getView<ImageView>(R.id.activityTypeIv)?.setImageResource(R.drawable.put)
                    }
                    "2" -> {
                        helper?.getView<TextView>(R.id.activityTypeTv)?.text = "投票活动"
                        helper?.getView<ImageView>(R.id.activityTypeIv)?.setImageResource(R.drawable.toup)
                    }
                }
            }
        }
        (contentRecyclerView?.adapter as BaseQuickAdapter<ClassActivityBean, BaseViewHolder>).setOnItemClickListener { adapter, view, position ->
            val manager = activity.supportFragmentManager
            val fragments = manager.fragments
            fragments.forEachIndexed { index, fragment ->
                if (fragment is ClassFragment) {
                    val transaction = manager.beginTransaction()
                    transaction.hide(fragment)
                    transaction.commit()
                    (activity as MainActivity).setMainTabVisiable(false)
                }
            }
            var tagFragment = manager.findFragmentByTag("ClassActivityDetailFragment")
            val transaction = manager.beginTransaction()
            if (null == tagFragment) {
                tagFragment = ClassActivityDetailFragment()
                transaction.add(R.id.fl_main, tagFragment, "ClassActivityDetailFragment")
            }
            (tagFragment as ClassActivityDetailFragment).selcetId = position
            tagFragment.userVisibleHint = true
            transaction.commit()
        }
    }

    override fun initListener() {

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
                            (contentRecyclerView?.adapter as BaseQuickAdapter<ClassActivityBean, BaseViewHolder>).replaceData(httpResultBody.result.classActivityList)
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)

                    }
                })
    }

}

/**
 * 班级活动详情
 */

class ClassActivityDetailFragment : BaseFragment() {
    var selcetId = 0
    override fun lazyLoadData() {
        loadData()
    }

    override fun getLayoutRes(): Int {
        return R.layout.class_activity_detail_layout
    }

    var headerRecyclerView: RecyclerView? = null
    var titleTv: TextView? = null
    var timeTv: TextView? = null
    var contentTv: TextView? = null
    var tv_back: TextView? = null
    override fun initView() {
        headerRecyclerView = mView?.findViewById<RecyclerView>(R.id.headerRecyclerView)
        titleTv = mView?.findViewById<TextView>(R.id.titleTv)
        timeTv = mView?.findViewById<TextView>(R.id.timeTv)
        contentTv = mView?.findViewById<TextView>(R.id.contentTv)

        tv_back = mView?.findViewById<TextView>(R.id.tv_back)
    }

    private fun resetViews() {
        titleTv?.text = ""
        timeTv?.text = ""
        contentTv?.text = ""
    }


    override fun initData() {
        headerRecyclerView?.layoutManager = LinearLayoutManager(activity)
        headerRecyclerView?.adapter = object : BaseQuickAdapter<ClassActivityBean, BaseViewHolder>(R.layout.class_activity_layout) {
            override fun convert(helper: BaseViewHolder?, item: ClassActivityBean?) {
                helper?.getView<TextView>(R.id.activityTitleTv)?.text = item?.title
                if (!TextUtils.isEmpty(item?.createTime)) {
                    helper?.getView<TextView>(R.id.activityTimeTv)?.text = DateTimeUtil.getStrTime(item?.createTime?.toLong()!!)
                }
                when (item?.type) {
                    "1" -> {
                        helper?.getView<TextView>(R.id.activityTypeTv)?.text = "普通活动"
                        helper?.getView<ImageView>(R.id.activityTypeIv)?.setImageResource(R.drawable.put)
                    }
                    "2" -> {
                        helper?.getView<TextView>(R.id.activityTypeTv)?.text = "投票活动"
                        helper?.getView<ImageView>(R.id.activityTypeIv)?.setImageResource(R.drawable.toup)
                    }
                }
            }

            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.getView<ViewGroup>(R.id.rootItemLayout)?.setOnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        val id = getItem(position)?.id
                        if (!TextUtils.isEmpty(id)) {
                            loadDetailData(id!!)
                        }
                    }
                }
            }
        }
    }

    override fun initListener() {
        tv_back?.setOnClickListener {
            val manager = activity.supportFragmentManager
            val fragments = manager.fragments
            fragments.forEachIndexed { index, fragment ->
                if (fragment is ClassFragment) {
                    val transaction = manager.beginTransaction()
                    transaction.show(fragment)
                    transaction.commit()
                }
            }
            val tagFragment = manager.findFragmentByTag("ClassActivityDetailFragment")
            if (null != tagFragment) {
                tagFragment.userVisibleHint = true
                val transaction = manager.beginTransaction()
                transaction.remove(tagFragment)
                transaction.commit()
            }
            (activity as MainActivity).setMainTabVisiable(true)
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
                            (headerRecyclerView?.adapter as BaseQuickAdapter<ClassActivityBean, BaseViewHolder>).replaceData(httpResultBody.result.classActivityList)
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)

                    }
                })
    }


    //    activityId 	是 	int 	活动id
//    userId 	是 	int 	当前登录用户的id
//    userType 	是 	int 	当前登录用户类型
//    childrenId 	是 	int 	家长查询普通活动的时候需要传孩子id
    private fun loadDetailData(activityId: String) {
        val userId = PreferenceUtil.getString(ConstantUtil.USER_ID, "")
        val userType = PreferenceUtil.getString(ConstantUtil.USER_TYPE, "")
        val paras = RequestUtil.createMap()
        paras["userId"] = userId
        paras["userType"] = userType
        paras["activityId"] = activityId
        paras["childrenId"] = userId
        val rxP = object : RxBasePresenter(activity) {}
        rxP.addDisposable<HttpResultBody<ClassActivityDetail>>(rxP.getmDataManager().netService.getClassActivityDetail(paras),
                object : ProgressObserver<HttpResultBody<ClassActivityDetail>>(activity, false) {
                    override fun doNext(httpResultBody: HttpResultBody<ClassActivityDetail>) {
                        resetViews()
                        if (TextUtils.equals(httpResultBody.code, "0000")) {
                            titleTv?.text = httpResultBody.result?.classActivity?.title
                            val timeStr = httpResultBody.result?.classActivity?.createTime
                            if (!TextUtils.isEmpty(timeStr)) {
                                timeTv?.text = DateTimeUtil.getStrTime(timeStr?.toLong()!!)
                            }
                            contentTv?.text = httpResultBody.result?.classActivity?.content
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        resetViews()
                    }
                })
    }

}


/**
 *  相册列表 School photo album
 */
class PhotoAlbumFragment : BaseFragment() {
    var photoAlbumType: String = "1"
    override fun lazyLoadData() {
        loadData()
    }

    override fun getLayoutRes(): Int {
        return R.layout.curriculum_list_layout
    }

    var contentRecyclerView: RecyclerView? = null
    override fun initView() {
        contentRecyclerView = mView?.findViewById<RecyclerView>(R.id.contentRecyclerView)
    }

    override fun initData() {
        contentRecyclerView?.layoutManager = GridLayoutManager(activity, 3)
        contentRecyclerView?.adapter = object : BaseQuickAdapter<PhotoAlbumBean, BaseViewHolder>(R.layout.photo_album_item_layout) {
            override fun convert(helper: BaseViewHolder?, item: PhotoAlbumBean?) {
                val photoIv = helper?.getView<ImageView>(R.id.photoIv)
                Glide.with(GlobalUtil.mContext).load(item?.cover).into(photoIv)
                if (!TextUtils.isEmpty(item?.createTime)) {
                    helper?.getView<TextView>(R.id.timeTv)?.text = DateTimeUtil.getStrTime(item?.createTime?.toLong()!!)
                }
                helper?.getView<TextView>(R.id.photoNameTv)?.text = item?.name
            }
        }

        (contentRecyclerView?.adapter as BaseQuickAdapter<PhotoAlbumBean, BaseViewHolder>).setOnItemClickListener { adapter, view, position ->
            val manager = activity.supportFragmentManager
            val fragments = manager.fragments
            fragments.forEachIndexed { index, fragment ->
                if (fragment is ClassFragment) {
                    val transaction = manager.beginTransaction()
                    transaction.hide(fragment)
                    transaction.commit()
                    (activity as MainActivity).setMainTabVisiable(false)
                }
            }
            var tagFragment = manager.findFragmentByTag("PhotoListFragment")
            val transaction = manager.beginTransaction()
            if (null == tagFragment) {
                tagFragment = PhotoListFragment()
                transaction.add(R.id.fl_main, tagFragment, "PhotoListFragment")
            }
            (tagFragment as PhotoListFragment).photoAlbumId = (adapter.getItem(position) as PhotoAlbumBean).id

            tagFragment.userVisibleHint = true
            transaction.commit()
        }
    }

    override fun initListener() {
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
                            val list = httpResultBody.result.photoAlbumList
                            (contentRecyclerView?.adapter as BaseQuickAdapter<PhotoAlbumBean, BaseViewHolder>).replaceData(list)
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)

                    }
                })

    }
}


/**
 * 相片列表
 */
class PhotoListFragment : BaseFragment() {

    var photoAlbumId: String = ""
    override fun lazyLoadData() {
        loadData()
    }

    override fun getLayoutRes(): Int {
        return R.layout.photo_list_layout
    }

    var photosRecyclerView: RecyclerView? = null
    var tv_back: TextView? = null
    override fun initView() {
        tv_back = mView?.findViewById<TextView>(R.id.tv_back)
        photosRecyclerView = mView.findViewById<RecyclerView>(R.id.photosRecyclerView)
    }

    override fun initData() {
        photosRecyclerView?.layoutManager = GridLayoutManager(activity, 4)
        photosRecyclerView?.adapter = object : BaseQuickAdapter<PhotoBean, BaseViewHolder>(R.layout.photo_item_layout) {
            override fun convert(helper: BaseViewHolder?, item: PhotoBean?) {
                val photoIv = helper?.getView<ImageView>(R.id.photoIv)
                Glide.with(GlobalUtil.mContext).load(item?.image).into(photoIv)
                if (!TextUtils.isEmpty(item?.createTime)) {
                    helper?.getView<TextView>(R.id.timeTv)?.text = DateTimeUtil.getStrTime(item?.createTime?.toLong()!!)
                }
            }
        }
    }

    override fun initListener() {

        tv_back?.setOnClickListener {
            val manager = activity.supportFragmentManager
            val fragments = manager.fragments
            fragments.forEachIndexed { index, fragment ->
                if (fragment is ClassFragment) {
                    val transaction = manager.beginTransaction()
                    transaction.show(fragment)
                    transaction.commit()
                }
            }
            val tagFragment = manager.findFragmentByTag("PhotoListFragment")
            if (null != tagFragment) {
                tagFragment.userVisibleHint = true
                val transaction = manager.beginTransaction()
                transaction.remove(tagFragment)
                transaction.commit()
            }
            (activity as MainActivity).setMainTabVisiable(true)
        }
    }

    //photoAlbumId 	是 	int 	相册id
//    pageNo 	是 	int 	页码
//    pageSize 	是 	int 	每页显示记录数
    private fun loadData() {

        val userId = PreferenceUtil.getString(ConstantUtil.USER_ID, "")
        val userType = PreferenceUtil.getString(ConstantUtil.USER_TYPE, "")
        val paras = RequestUtil.createMap()
        paras["userId"] = userId
        paras["userType"] = userType
        paras["photoAlbumId"] = photoAlbumId
        paras["pageNo"] = "1"
        paras["pageSize"] = "1000"
        val rxP = object : RxBasePresenter(activity) {}
        rxP.addDisposable<HttpResultBody<PhotoList>>(rxP.getmDataManager().netService.getPhotoList(paras),
                object : ProgressObserver<HttpResultBody<PhotoList>>(activity, false) {
                    override fun doNext(httpResultBody: HttpResultBody<PhotoList>) {
                        if (TextUtils.equals(httpResultBody.code, "0000")) {
                            val list = httpResultBody.result.photoList
                            (photosRecyclerView?.adapter as BaseQuickAdapter<PhotoBean, BaseViewHolder>).replaceData(list)
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)

                    }
                })

    }
}
