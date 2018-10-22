package com.apemoon.tvbox.ui.fragment

import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.Spinner
import android.widget.TextView
import com.apemoon.tvbox.R
import com.apemoon.tvbox.base.BaseFragment
import com.apemoon.tvbox.base.net.HttpResultBody
import com.apemoon.tvbox.base.rx.ProgressObserver
import com.apemoon.tvbox.base.rx.RxBasePresenter
import com.apemoon.tvbox.entity.*
import com.apemoon.tvbox.entity.userCenter.UserSemstersEntity
import com.apemoon.tvbox.ui.activity.MainActivity
import com.apemoon.tvbox.ui.adapter.BaseSpinnerAdapter
import com.apemoon.tvbox.utils.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.JsonParser

/**
 *TVBox
 * Created by mukry on 2018/10/19.
 * 我的课堂
 */
class ClassRoomFragment : BaseFragment() {

    val headerList = listOf<String>("班级课表", "课堂表现", "我的作业", "我的成绩", "名师导学")
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

    private var currentPosition: Int = 0

    fun getCurrentPositionItemView(): View? {
        LogUtil.d("getCurrentPositionItemView    $currentPosition")
        return headerRecyclerView?.layoutManager?.findViewByPosition(currentPosition)
    }


    fun setCurrentPositionNextFocusRightId(id: Int) {
        if (headerRecyclerView != null) {
            headerRecyclerView?.post {
                val itemView = headerRecyclerView?.layoutManager?.findViewByPosition(currentPosition)
                itemView?.nextFocusRightId = id
            }
        }
    }

    fun setCurrentPositionNextFocusLeftId(id: Int) {
        if (headerRecyclerView != null) {
            headerRecyclerView?.post {
                val itemView = headerRecyclerView?.layoutManager?.findViewByPosition(currentPosition)
                itemView?.nextFocusLeftId = id
            }
        }
    }


    fun initSelectedPosition(position: Int) {
        if (headerRecyclerView != null && position >= 0 && position < headerList.size) {
            headerRecyclerView?.post {
                val itemView = headerRecyclerView?.layoutManager?.findViewByPosition(position)
                itemView?.requestFocus()
                if (null != itemView && itemView.isFocused) {
                    currentPosition = position
                    LogUtil.d("getCurrentPositionItemView  SelectedPosition   $currentPosition")
                }
            }
        }
    }


    var fragmentsContainer: FrameLayout? = null
    var headerRecyclerView: RecyclerView? = null
    override fun initView() {
        fragmentsContainer = mView?.findViewById<FrameLayout>(R.id.fragmentsContainer)
        headerRecyclerView = mView?.findViewById<RecyclerView>(R.id.headerRecyclerView)

        // headerRecyclerView?.nextFocusUpId = R.id.main_tab
    }

    private fun getLastNodeFragment(): Fragment? {
        activity?.supportFragmentManager?.fragments?.forEach { fragment ->
            if (fragment is ClassRoomFragment) {
                return fragment
            }
        }
        return null
    }


    override fun initData() {
        /*   headerRecyclerView?.adapter =*/object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.curriculum_header, headerList) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper?.getView<TextView>(R.id.headTv)?.text = item
            }

            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                // ("班级课表", "课堂表现", "我的作业", "我的成绩", "名师导学")
                if (position == 0) {
                    holder.getView<View>(R.id.rootHeadLayout)?.nextFocusUpId = (activity as MainActivity).mainTab.id
                    val fragment = getLastNodeFragment()
                    if (fragment is ClassRoomFragment) {
                        val view = fragment.getCurrentPositionItemView()
                        if (null != view) {
                            holder.getView<View>(R.id.rootHeadLayout)?.nextFocusLeftId = view.id
                        }
                    }
                }
                holder.getView<ViewGroup>(R.id.rootHeadLayout)?.setOnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        currentPosition = position
                        LogUtil.d("getCurrentPositionItemView  FocusChangeListener   $currentPosition")
                        if (position == 1 || position == 4) return@setOnFocusChangeListener
                        val fr = FragmentFactory.createFragment(position)
                        replaceFragment(fr!!)
                    }
                }
            }
        }.bindToRecyclerView(headerRecyclerView)

    }

    override fun initListener() {
        initSelectedPosition(0)
    }


    private object FragmentFactory {
        fun createFragment(position: Int): Fragment? {
            when (position) {
                0 -> {
                    return SampleFragmentA()
                }
                1 -> {
                    return EmptyFragment()
                }
                2 -> {
                    return SchoolAssignmentFragment()
                }
                3 -> {
                    return ScoreFragment()
                }
                4 -> {
                    return EmptyFragment()
                }
                else -> return null
            }

        }
    }

}

/**
 *空白内容
 *
 */
class EmptyFragment : BaseFragment() {
    override fun lazyLoadData() {
    }

    override fun getLayoutRes(): Int {
        return R.layout.curriculum_list_layout
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {
    }


}


/**
 * 课表
 */
class SampleFragmentA : BaseFragment() {
    var contentRecyclerView: RecyclerView? = null

    override fun lazyLoadData() {
        loadData()
    }

    override fun getLayoutRes(): Int {
        return R.layout.curriculum_list_layout
    }

    override fun initView() {
        contentRecyclerView = mView?.findViewById<RecyclerView>(R.id.contentRecyclerView)
    }

    private fun getLastNodeFragment(): Fragment? {
        //childFragmentManager
        activity?.supportFragmentManager?.fragments?.forEach { fragment ->
            if (fragment is ClassRoomFragment) {
                return fragment
            }
        }
        return null
    }

    override fun initData() {
        contentRecyclerView?.layoutManager = android.support.v7.widget.GridLayoutManager(activity, 6)
        /*   contentRecyclerView?.adapter = */object : BaseQuickAdapter<ArrayList<String>, BaseViewHolder>(R.layout.curriculum_layout) {
            override fun convert(helper: BaseViewHolder?, item: ArrayList<String>?) {
                val viewGroup = helper?.getView<ViewGroup>(R.id.row_root)
                item?.forEachIndexed { index, s ->
                    if (index <= viewGroup?.childCount!!) {
                        (viewGroup.getChildAt(index) as TextView).text = s
                    }
                }
            }

            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.getView<View>(R.id.row_root)?.nextFocusUpId = (activity as MainActivity).mainTab.id
                if (position == 0) {
                    val fragment = getLastNodeFragment()
                    if (fragment is ClassRoomFragment) {
                        val view = fragment.getCurrentPositionItemView()
                        if (null != view) {
                            holder.getView<View>(R.id.row_root)?.nextFocusLeftId = view.id
                        }
                    }
                }


            }
        }.bindToRecyclerView(contentRecyclerView)
    }

    override fun initListener() {

    }


    //gradeId 	是 	int 	班级id
    private fun loadData() {
        val id = PreferenceUtil.getString(ConstantUtil.GRADED_ID, "")
        val paras = RequestUtil.createMap()
        paras["gradeId"] = id
        val rxP = object : RxBasePresenter(activity) {}
        rxP.addDisposable<HttpResultBody<ClassSchedule>>(rxP.getmDataManager().netService.getClassSchedule(paras),
                object : ProgressObserver<HttpResultBody<ClassSchedule>>(activity, false) {
                    override fun doNext(httpResultBody: HttpResultBody<ClassSchedule>) {
                        try {
                            if (TextUtils.equals(httpResultBody.code, "0000")) {
                                //  CurriculumRowsBean
                                val data = ArrayList<ArrayList<String>>()
                                val weekArr = ArrayList<String>()
                                weekArr.add("")
                                weekArr.add("第一节")
                                weekArr.add("第二节")
                                weekArr.add("第三节")
                                weekArr.add("第四节")
                                weekArr.add("第五节")
                                weekArr.add("第六节")
                                weekArr.add("第七节")
                                data.add(weekArr)

                                data.add(initArr("星期一"))
                                data.add(initArr("星期二"))
                                data.add(initArr("星期三"))
                                data.add(initArr("星期四"))
                                data.add(initArr("星期五"))
                                if ("2" == httpResultBody.result?.classSchedule?.contentType) {
                                    val scheduleJsonStr = httpResultBody.result.classSchedule?.scheduleJson
                                    val jar = JsonParser().parse(scheduleJsonStr).asJsonArray
                                    jar.forEachIndexed { index, jsonElement ->
                                        //7
                                        val jb = jsonElement.asJsonArray
                                        jb.forEachIndexed { _index, jsonElement ->
                                            //6
                                            if (_index != 0) {
                                                data[_index][index + 1] = jsonElement.asString
                                            }
                                        }
                                    }
                                }
                                (contentRecyclerView?.adapter as BaseQuickAdapter<ArrayList<String>, BaseViewHolder>).replaceData(data)
                            }
                        } catch (e: Exception) {
                            Log.e("ee", e.toString())
                            // LogUtil.d(e.toString())
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })

    }

    fun initArr(week: String): ArrayList<String> {
        val arr = ArrayList<String>()
        arr.add(week)
        arr.add("")
        arr.add("")
        arr.add("")
        arr.add("")
        arr.add("")
        arr.add("")
        arr.add("")
        return arr

    }

}

/**我的成绩
 *
 */
class ScoreFragment : BaseFragment() {
    override fun lazyLoadData() {
        receiveSemseterList()
    }

    override fun getLayoutRes(): Int {
        return R.layout.curriculum_list_layout
    }

    var contentRecyclerView: RecyclerView? = null
    var spinner1: Spinner? = null
    var spinner1Layout: View? = null

    var spinner2: Spinner? = null
    var spinner2Layout: View? = null


    override fun initView() {
        spinner1 = mView?.findViewById<Spinner>(R.id.spinner1)
        spinner1Layout = mView?.findViewById(R.id.spinner1Layout)
        spinner2 = mView?.findViewById<Spinner>(R.id.spinner2)
        spinner2Layout = mView?.findViewById(R.id.spinner2Layout)

        spinner1Layout?.visibility = View.VISIBLE
        spinner2Layout?.visibility = View.VISIBLE

        spinner1?.isFocusable = true
        spinner2?.isFocusable = true
        contentRecyclerView = mView?.findViewById<RecyclerView>(R.id.contentRecyclerView)

        val fragment = getLastNodeFragment()
        if (fragment is ClassRoomFragment) {
            val view = fragment.getCurrentPositionItemView()
            view?.nextFocusRightId = spinner1?.id!!
            if (null != view) {
                spinner1?.nextFocusLeftId = view.id
            }
        }
    }

    private fun getLastNodeFragment(): Fragment? {
        activity?.supportFragmentManager?.fragments?.forEach { fragment ->
            if (fragment is ClassRoomFragment) {
                return fragment
            }
        }
        return null
    }


    override fun initData() {
        contentRecyclerView?.layoutManager = GridLayoutManager(activity, 6)
        /*contentRecyclerView?.adapter =*/ object : BaseQuickAdapter<MarkBean, BaseViewHolder>(R.layout.user_mark_layout) {
            override fun convert(helper: BaseViewHolder?, item: MarkBean?) {
                val markNameTv = helper?.getView<TextView>(R.id.markNameTv)
                val markValueTv = helper?.getView<TextView>(R.id.markValueTv)
                val mark = (item as MarkBean)
                markNameTv?.text = mark.key
                markValueTv?.text = mark.vaule
            }

            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                if (position == 0) {
                    holder.getView<View>(R.id.row_root)?.nextFocusUpId = spinner1?.id!!
                }
                if (position % 6 == 0) {
                    val fragment = getLastNodeFragment()
                    if (fragment is ClassRoomFragment) {
                        val view = fragment.getCurrentPositionItemView()
                        view?.nextFocusRightId = spinner1?.id!!
                        if (null != view) {
                            holder.getView<View>(R.id.row_root)?.nextFocusLeftId = view.id
                        }
                    }
                }
            }
        }.bindToRecyclerView(contentRecyclerView)
    }

    override fun initListener() {

    }

    private lateinit var examClassifyList: ExamClassifyList
    /**
     * 获取考试类型
     */
    fun examClassifyListList() {
        val paras = RequestUtil.createMap()
        paras["schoolId"] = PreferenceUtil.getString(ConstantUtil.SCHOOL_ID, "")
        val rxP = object : RxBasePresenter(activity) {}
        rxP.addDisposable<HttpResultBody<ExamClassifyList>>(rxP.getmDataManager().netService.getExamClassifyList(paras),
                object : ProgressObserver<HttpResultBody<ExamClassifyList>>(activity, true) {
                    override fun doNext(httpResultBody: HttpResultBody<ExamClassifyList>) {
                        if (TextUtils.equals(httpResultBody.code, "0000")) {
                            examClassifyList = httpResultBody.result
                            spinner2?.adapter = object : BaseSpinnerAdapter<ExamClassifyBean>(examClassifyList.examClassifyList) {
                                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                                    val rootView = LayoutInflater.from(activity).inflate(R.layout.spinner_item_layout, parent, false)
                                    rootView.findViewById<TextView>(R.id.spinnerTv).text = getItem(position).name
                                    return rootView
                                }
                            }

                            spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    examinationClassifyId = (spinner2?.adapter as BaseSpinnerAdapter<ExamClassifyBean>).getItem(position).id
                                    if (!TextUtils.isEmpty(semesterId) && !TextUtils.isEmpty(examinationClassifyId)) {
                                        loadData()
                                    }
                                }

                            }

                            if (!examClassifyList.examClassifyList.isEmpty()) {
                                spinner2?.setSelection(0)
                            }

                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)

                    }
                })
    }


    private lateinit var semstersEntity: UserSemstersEntity
    /**
     * 获取学期信息
     */
    private fun receiveSemseterList() {
        val paras = RequestUtil.createMap()
        paras["userId"] = PreferenceUtil.getString(ConstantUtil.USER_ID, "")
        paras["userType"] = PreferenceUtil.getString(ConstantUtil.USER_TYPE, "")
        val rxP = object : RxBasePresenter(activity) {}
        rxP.addDisposable<HttpResultBody<UserSemstersEntity>>(rxP.getmDataManager().netService.getSemstersInfo(paras),
                object : ProgressObserver<HttpResultBody<UserSemstersEntity>>(activity, true) {

                    override fun doNext(httpResultBody: HttpResultBody<UserSemstersEntity>) {
                        if (TextUtils.equals(httpResultBody.code, "0000")) {
                            semstersEntity = httpResultBody.result
                            spinner1?.adapter = object : BaseSpinnerAdapter<UserSemstersEntity.SemstersBean>(semstersEntity.semesterList) {
                                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                                    val rootView = LayoutInflater.from(activity).inflate(R.layout.spinner_item_layout, parent, false)
                                    rootView.findViewById<TextView>(R.id.spinnerTv).text = getItem(position).name
                                    return rootView
                                }
                            }
                            spinner1?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    semesterId = (spinner1?.adapter as BaseSpinnerAdapter<UserSemstersEntity.SemstersBean>).getItem(position).id.toString()
                                    if (!TextUtils.isEmpty(semesterId) && !TextUtils.isEmpty(examinationClassifyId)) {
                                        loadData()
                                    }
                                }

                            }
                            if (!semstersEntity.semesterList.isEmpty()) {
                                spinner1?.setSelection(0)
                            }
                            examClassifyListList()
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)

                    }
                })
    }

//        userId 	是 	int 	用户id ，教师查看成绩填写教师id，学生和家长查看成绩都是填写学生id
//        userType 	是 	int 	用户类型 ，教师查看成绩填写教师类型，学生和家长查看成绩都是填写学生的类型
//        semesterId 	是 	int 	学期id
//        examinationClassifyId 	是 	int 	考试类型id
//        gradeId 	是 	int 	班级id

    private var semesterId = ""
    private var examinationClassifyId = ""

    private fun loadData() {
        val id = PreferenceUtil.getString(ConstantUtil.GRADED_ID, "")
        val userId = PreferenceUtil.getString(ConstantUtil.USER_ID, "")
        val userType = PreferenceUtil.getString(ConstantUtil.USER_TYPE, "")
        val paras = RequestUtil.createMap()
        paras["userId"] = userId
        paras["userType"] = userType
        paras["semesterId"] = semesterId
        paras["examinationClassifyId"] = examinationClassifyId
        paras["gradeId"] = id
        val rxP = object : RxBasePresenter(activity) {}
        rxP.addDisposable<HttpResultBody<UserMark>>(rxP.getmDataManager().netService.getMark(paras),
                object : ProgressObserver<HttpResultBody<UserMark>>(activity, false) {
                    override fun doNext(httpResultBody: HttpResultBody<UserMark>) {
                        if (TextUtils.equals(httpResultBody.code, "0000")) {
                            val markList = ArrayList<MarkBean>()
                            val list = httpResultBody.result.mark
                            if (!list.isEmpty()) {
                                val jo = list[0]
                                val keys = jo.keySet()
                                keys?.forEachIndexed { index, s ->
                                    if (s == "姓名" || s == "学号") {
                                        //todo
                                    } else {
                                        markList.add(MarkBean(s, jo.get(s).asString))
                                    }
                                }
                            }
                            (contentRecyclerView?.adapter as BaseQuickAdapter<MarkBean, BaseViewHolder>).replaceData(markList)
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })
    }
}


/**
 * 作业
 */
class SchoolAssignmentFragment : BaseFragment() {
    override fun lazyLoadData() {
        getSubjectList()
    }

    override fun getLayoutRes(): Int {
        return R.layout.curriculum_list_layout
    }

    var contentRecyclerView: RecyclerView? = null
    var spinner1: Spinner? = null
    var spinner1Layout: View? = null
    override fun initView() {
        spinner1 = mView?.findViewById<Spinner>(R.id.spinner1)
        spinner1Layout = mView?.findViewById(R.id.spinner1Layout)
        spinner1Layout?.visibility = View.VISIBLE

        spinner1?.isFocusable = true
        contentRecyclerView = mView?.findViewById<RecyclerView>(R.id.contentRecyclerView)

        val fragment = getLastNodeFragment()
        if (fragment is ClassRoomFragment) {
            val view = fragment.getCurrentPositionItemView()
            view?.nextFocusRightId = spinner1?.id!!
            if (null != view) {
                spinner1?.nextFocusLeftId = view.id
            }
        }

    }

    private fun getLastNodeFragment(): Fragment? {
        activity?.supportFragmentManager?.fragments?.forEach { fragment ->
            if (fragment is ClassRoomFragment) {
                return fragment
            }
        }
        return null
    }

    override fun initData() {
        contentRecyclerView?.layoutManager = LinearLayoutManager(activity)
        /*  contentRecyclerView?.adapter =*/ object : BaseQuickAdapter<WorkBean, BaseViewHolder>(R.layout.user_work_layout) {
            override fun convert(helper: BaseViewHolder?, item: WorkBean?) {
                val titleTv = helper?.getView<TextView>(R.id.titleTv)
                val contentTv = helper?.getView<TextView>(R.id.contentTv)
                val timeTv = helper?.getView<TextView>(R.id.timeTv)
                titleTv?.text = item?.title
                contentTv?.text = Html.fromHtml(item?.content)
                if (!TextUtils.isEmpty(item?.createTime)) {
                    timeTv?.text = DateTimeUtil.getStrTime(item?.createTime?.toLong()!!)
                }
            }

            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                if (position == 0) {
                    holder.getView<View>(R.id.itemRootLayout)?.nextFocusUpId = spinner1?.id!!
                }
                val fragment = getLastNodeFragment()
                if (fragment is ClassRoomFragment) {
                    val view = fragment.getCurrentPositionItemView()
                    view?.nextFocusRightId = spinner1?.id!!
                    if (null != view) {
                        holder.getView<View>(R.id.itemRootLayout)?.nextFocusLeftId = view.id
                    }
                }
            }
        }.bindToRecyclerView(contentRecyclerView)

        (contentRecyclerView?.adapter as BaseQuickAdapter<WorkBean, BaseViewHolder>).setOnItemClickListener { adapter, view, position ->
            val manager = activity.supportFragmentManager
            val fragments = manager.fragments
            fragments.forEachIndexed { index, fragment ->
                if (fragment is ClassRoomFragment) {
                    val transaction = manager.beginTransaction()
                    transaction.hide(fragment)
                    transaction.commit()
                    (activity as MainActivity).setMainTabVisiable(false)
                }
            }
            var tagFragment = manager.findFragmentByTag("SchoolAssignmentDetailFragment")
            val transaction = manager.beginTransaction()
            if (null == tagFragment) {
                tagFragment = SchoolAssignmentDetailFragment()
                transaction.add(R.id.fl_main, tagFragment, "SchoolAssignmentDetailFragment")
            }
            (tagFragment as SchoolAssignmentDetailFragment).seatworkId = (adapter.getItem(position) as WorkBean).id
            tagFragment.userVisibleHint = true
            transaction.commit()
        }

    }

    override fun initListener() {
    }

    /**
     * 获取科目信息 getSubjectList
     */
//        userId 	是 	int 	用户id
//        userType 	是 	int 	用户类型：1教师、2学生（家长查看的时候也是填写对应学生的id和类型）
//        schoolId 	是 	int 	学校id
//        classId 	是 	int 	年级id
    var subs = ArrayList<SubjectBean>()
    var subjectId = ""
    private fun getSubjectList() {
        val paras = RequestUtil.createMap()
        paras["userId"] = PreferenceUtil.getString(ConstantUtil.USER_ID, "")
        paras["userType"] = PreferenceUtil.getString(ConstantUtil.USER_TYPE, "")
        paras["schoolId"] = PreferenceUtil.getString(ConstantUtil.SCHOOL_ID, "")
        paras["classId"] = PreferenceUtil.getString(ConstantUtil.CLASS_ID, "")
        val rxP = object : RxBasePresenter(activity) {}
        rxP.addDisposable<HttpResultBody<SubjectList>>(rxP.getmDataManager().netService.getSubjectList(paras),
                object : ProgressObserver<HttpResultBody<SubjectList>>(activity, true) {
                    override fun doNext(httpResultBody: HttpResultBody<SubjectList>) {
                        if (TextUtils.equals(httpResultBody.code, "0000")) {
                            subs.clear()
                            val list = httpResultBody.result.subjectList
                            if (!list.isEmpty()) {
                                subs.addAll(list)
                            }
                            spinner1?.adapter = object : BaseSpinnerAdapter<SubjectBean>(subs) {
                                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                                    val rootView = LayoutInflater.from(activity).inflate(R.layout.spinner_item_layout, parent, false)
                                    rootView.findViewById<TextView>(R.id.spinnerTv).text = getItem(position).name
                                    return rootView
                                }
                            }
                            spinner1?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    subjectId = (spinner1?.adapter as BaseSpinnerAdapter<SubjectBean>).getItem(position).id
                                    if (!TextUtils.isEmpty(subjectId)) {
                                        loadData(subjectId)
                                    }
                                }
                            }
                            if (!subs.isEmpty()) {
                                spinner1?.setSelection(0)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)

                    }
                })
    }


    //        userId 	是 	int 	用户id
//        userType 	是 	int 	用户类型：1教师、2学生（家长查看的时候也是填写对应学生的id和类型）
//        subjectId 	是 	int 	科目id
//        pageNo 	是 	int 	页码
//        pageSize 	是 	int 	每页显示记录数
//        gradeId 	是 	int 	班级id
    private fun loadData(subjectId: String) {
        val id = PreferenceUtil.getString(ConstantUtil.GRADED_ID, "")
        val userId = PreferenceUtil.getString(ConstantUtil.USER_ID, "")
        val userType = PreferenceUtil.getString(ConstantUtil.USER_TYPE, "")
        val paras = RequestUtil.createMap()
        paras["userId"] = userId
        paras["userType"] = userType
        paras["subjectId"] = subjectId
        paras["pageNo"] = "1"
        paras["pageSize"] = "100"
        paras["gradeId"] = id
        val rxP = object : RxBasePresenter(activity) {}
        rxP.addDisposable<HttpResultBody<WorkList>>(rxP.getmDataManager().netService.getWorkList(paras),
                object : ProgressObserver<HttpResultBody<WorkList>>(activity, false) {
                    override fun doNext(httpResultBody: HttpResultBody<WorkList>) {
                        if (TextUtils.equals(httpResultBody.code, "0000")) {
                            val jo = httpResultBody.result.seatworkList
                            if (null != jo) {
                                (contentRecyclerView?.adapter as BaseQuickAdapter<WorkBean, BaseViewHolder>).replaceData(jo)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })
    }


}


/**
 * 作业详情
 */
class SchoolAssignmentDetailFragment : BaseFragment() {

    var seatworkId: String = ""

    override fun lazyLoadData() {
        loadData(seatworkId)
    }

    override fun getLayoutRes(): Int {
        return R.layout.school_assignment_detail_layout
    }

    private var contentTv: TextView? = null
    private var titleTv: TextView? = null
    private var tv_back: TextView? = null
    override fun initView() {
        contentTv = mView.findViewById<TextView>(R.id.contentTv)
        titleTv = mView.findViewById<TextView>(R.id.titleTv)
        tv_back = mView.findViewById<TextView>(R.id.tv_back)
    }

    override fun initData() {
    }

    override fun initListener() {
        tv_back?.setOnClickListener {
            val manager = activity.supportFragmentManager
            val fragments = manager.fragments
            fragments.forEachIndexed { index, fragment ->
                if (fragment is ClassRoomFragment) {
                    val transaction = manager.beginTransaction()
                    transaction.show(fragment)
                    transaction.commit()
                }
            }
            val tagFragment = manager.findFragmentByTag("SchoolAssignmentDetailFragment")
            if (null != tagFragment) {
                tagFragment.userVisibleHint = true
                val transaction = manager.beginTransaction()
                transaction.remove(tagFragment)
                transaction.commit()
            }
            (activity as MainActivity).setMainTabVisiable(true)
        }
        tv_back?.requestFocus()
    }


    private fun loadData(seatworkId: String) {
        val id = PreferenceUtil.getString(ConstantUtil.GRADED_ID, "")
        val paras = RequestUtil.createMap()
        paras["seatworkId"] = seatworkId
        val rxP = object : RxBasePresenter(activity) {}
        rxP.addDisposable<HttpResultBody<WorkDetail>>(rxP.getmDataManager().netService.getWorkDetail(paras),
                object : ProgressObserver<HttpResultBody<WorkDetail>>(activity, false) {
                    override fun doNext(httpResultBody: HttpResultBody<WorkDetail>) {
                        try {
                            if (TextUtils.equals(httpResultBody.code, "0000")) {
                                titleTv?.text = httpResultBody.result.seatwork.title
                                // contentTv?.text = httpResultBody.result.seatwork.content
                                contentTv?.text = Html.fromHtml(httpResultBody.result.seatwork.content)
                            }
                        } catch (e: Exception) {

                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })
    }

}
