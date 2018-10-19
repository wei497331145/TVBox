package com.apemoon.tvbox.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v17.leanback.app.BackgroundManager
import android.support.v17.leanback.app.BrowseSupportFragment
import android.support.v17.leanback.widget.*
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.apemoon.tvbox.R
import com.apemoon.tvbox.base.GridFragment
import com.apemoon.tvbox.base.SpinnerGridFragment
import com.apemoon.tvbox.base.net.HttpResultBody
import com.apemoon.tvbox.base.rx.ProgressObserver
import com.apemoon.tvbox.base.rx.RxBasePresenter
import com.apemoon.tvbox.entity.*
import com.apemoon.tvbox.entity.userCenter.UserSemstersEntity
import com.apemoon.tvbox.ui.adapter.BaseSpinnerAdapter
import com.apemoon.tvbox.ui.view.CHeaderPresenter
import com.apemoon.tvbox.ui.view.CHeaderPresenterSelector
import com.apemoon.tvbox.utils.ConstantUtil
import com.apemoon.tvbox.utils.PreferenceUtil
import com.apemoon.tvbox.utils.RequestUtil
import com.google.gson.JsonParser


/**
 * Created by water on 2018/8/31/031.
 * des：我的课堂
 */
class ClassRoomFragment : BrowseSupportFragment() {
    private var mBackgroundManager: BackgroundManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRows()
        setHeaderPresenterSelector(CHeaderPresenterSelector(CHeaderPresenter()))
        brandColor = Color.parseColor("#00000000")

//        headersSupportFragment.b

        //lb_focus_zoom_factor_xsmall

        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager?.drawable = ColorDrawable(Color.parseColor("#00000000"))
        mBackgroundManager?.attach(activity?.window)
        mainFragmentRegistry.registerFragment(PageRow::class.java,
                PageRowFragmentFactory(mBackgroundManager!!))
    }

    //
//    override fun onCreateHeadersSupportFragment(): HeadersSupportFragment {
//        return HeadersSupportFragment()
//    }


    private var mRowsAdapter: ArrayObjectAdapter? = null
    private fun loadRows() {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val presenterHeader0 = HeaderItem(0, "班级课表")
        val presenterHeader1 = HeaderItem(1, "课堂表现")
        val presenterHeader2 = HeaderItem(2, "我的作业")
        val presenterHeader3 = HeaderItem(3, "我的成绩")
        val presenterHeader4 = HeaderItem(4, "名师导学")
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
                    return SampleFragmentA()
                }
                1L -> {
                    return EmptyFragment()
                }
                2L -> {
                    return SchoolAssignmentFragment()
                }
                3L -> {
                    return ScoreFragment()
                }
                4L -> {
                    return EmptyFragment()
                }
                else -> throw IllegalArgumentException(String.format("Invalid row %s", rowObj))
            }

        }
    }

    /**
     * 课表
     */
    class SampleFragmentA : GridFragment() {

//        {
//            "code": "0000",
//            "message": "操作成功",
//            "result": {
//            "classSchedule": {
//            "classId": 2,——》班级id
//            "contentType": "2",——》内容类型：1图片、2json字符串
//            "gradeId": 2,——》班级id
//            "id": 15,
//            "scheduleJson": "",——》课表json
//            "schedulePhoto": "",——》课表图片
//            "schoolId": 3,——》学校id
//        }
//        }
//        }

//
//        "classSchedule": {
//            "classId": 1,
//            "contentType": "2",
//            "createTime": null,
//            "createUser": "",
//            "gradeId": 90,
//            "id": 116,
//            "page": 1,
//            "rows": 10,
//            "scheduleJson": "[[\"empty\",\"\",\"\",\"\",\"\",\"马克思主义原理\"],[\"empty\",\"马克思主义原理\",\"\",\"\",\"马克思主义原理\",\"\"],[\"empty\",\"\",\"\",\"\",\"马克思主义原理\",\"\"],[\"empty\",\"马克思主义原理\",\"\",\"马克思主义原理\",\"\",\"\"],[\"empty\",\"\",\"马克思主义原理\",\"\",\"\",\"\"],[\"empty\",\"\",\"\",\"\",\"\",\"\"],[\"empty\",\"\",\"\",\"\",\"\",\"\"]]",
//            "schedulePhoto": "",
//            "schoolId": 37,
//            "updateTime": null,
//            "updateUser": ""
//        }

        private val ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_SMALL
        private var mAdapter: ArrayObjectAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setupAdapter()
            // mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)

        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            loadData()
        }

        private fun setupAdapter() {
            val presenter = VerticalGridPresenter(ZOOM_FACTOR)
            presenter.numberOfColumns = 1

            gridPresenter = presenter
            mAdapter = ArrayObjectAdapter(CurriculumPresenter(this))
            adapter = mAdapter
            onItemViewClickedListener = OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            }
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
                                    mAdapter?.clear()
                                    val weekArr = ArrayList<String>()
                                    weekArr.add("")
                                    weekArr.add("星期一")
                                    weekArr.add("星期二")
                                    weekArr.add("星期三")
                                    weekArr.add("星期四")
                                    weekArr.add("星期五")
                                    mAdapter?.add(weekArr)
                                    if ("2" == httpResultBody.result?.classSchedule?.contentType) {
                                        val scheduleJsonStr = httpResultBody.result.classSchedule?.scheduleJson
                                        val jar = JsonParser().parse(scheduleJsonStr).asJsonArray
                                        jar.forEachIndexed { index, jsonElement ->
                                            val rowJ = ArrayList<String>()
                                            rowJ.addAll(listOf("", "", "", "", "", ""))
                                            val jb = jsonElement.asJsonObject
                                            val keys = jb.keySet()
                                            keys.forEachIndexed { index, s ->
                                                when (s) {
                                                    "" -> {
                                                        rowJ[0] = jb.get("").asString
                                                    }
                                                    "星期一" -> {
                                                        rowJ[1] = jb.get("星期一").asString
                                                    }
                                                    "星期二" -> {
                                                        rowJ[2] = jb.get("星期二").asString
                                                    }
                                                    "星期三" -> {
                                                        rowJ[3] = jb.get("星期三").asString
                                                    }
                                                    "星期四" -> {
                                                        rowJ[4] = jb.get("星期四").asString
                                                    }
                                                    "星期五" -> {
                                                        rowJ[5] = jb.get("星期五").asString
                                                    }
                                                }
                                            }
                                            mAdapter?.add(rowJ)
                                        }
                                    }
                                    mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
                                }
                            } catch (e: Exception) {

                            }
                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                        }
                    })
        }


        class CurriculumPresenter(private var mFragment: Fragment?) : Presenter() {
            override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
                val rootView = LayoutInflater.from(parent?.context).inflate(R.layout.curriculum_layout, parent, false)
                // val rootView = mFragment?.layoutInflater?.inflate(R.layout.curriculum_layout, null)
                return ViewHolder(rootView)
            }

            override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
                val viewGroup = viewHolder?.view?.findViewById<ViewGroup>(R.id.row_root)
                val arr = item as ArrayList<String>
                arr.forEachIndexed { index, s ->
                    if (index <= viewGroup?.childCount!!) {
                        (viewGroup.getChildAt(index) as TextView).text = s
                    }
                }
            }

            override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
            }
        }

    }


    /**我的成绩
     *
     */
    class ScoreFragment : SpinnerGridFragment() {
        private val ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_SMALL
        private var mAdapter: ArrayObjectAdapter? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setupAdapter()
            // loadData()
        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            receiveSemseterList()
        }

        private fun setupAdapter() {
            val presenter = VerticalGridPresenter(ZOOM_FACTOR)
            presenter.numberOfColumns = 6

            gridPresenter = presenter
            mAdapter = ArrayObjectAdapter(MarkPresenter(this))
            adapter = mAdapter
            onItemViewClickedListener = OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            }
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
                                        examinationClassifyId = (spinner2.adapter as BaseSpinnerAdapter<ExamClassifyBean>).getItem(position).id
                                        if (!TextUtils.isEmpty(semesterId) && !TextUtils.isEmpty(examinationClassifyId)) {
                                            loadData()
                                        }
                                    }

                                }

                                if (!examClassifyList.examClassifyList.isEmpty()) {
                                    spinner2.setSelection(0)
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
                                        semesterId = (spinner1.adapter as BaseSpinnerAdapter<UserSemstersEntity.SemstersBean>).getItem(position).id.toString()
                                        if (!TextUtils.isEmpty(semesterId) && !TextUtils.isEmpty(examinationClassifyId)) {
                                            loadData()
                                        }
                                    }

                                }
                                if (!semstersEntity.semesterList.isEmpty()) {
                                    spinner1.setSelection(0)
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
                                mAdapter?.clear()
                                val list = httpResultBody.result.mark
                                if (!list.isEmpty()) {
                                    val jo = list[0]
                                    val keys = jo.keySet()
                                    keys?.forEachIndexed { index, s ->
                                        mAdapter?.add(MarkBean(s, jo.get(s).asString))
                                    }
                                }
                                mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
                            }
                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                        }
                    })
        }


        class MarkPresenter(private var mFragment: Fragment?) : Presenter() {
            override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
                val rootView = LayoutInflater.from(parent?.context).inflate(R.layout.user_mark_layout, parent, false)
                // val rootView = mFragment?.layoutInflater?.inflate(R.layout.curriculum_layout, null)
                return ViewHolder(rootView)
            }

            override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
                val markNameTv = viewHolder?.view?.findViewById<TextView>(R.id.markNameTv)
                val markValueTv = viewHolder?.view?.findViewById<TextView>(R.id.markValueTv)
                val mark = (item as MarkBean)
                markNameTv?.text = mark.key
                markValueTv?.text = mark.vaule
            }

            override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
            }
        }

    }

     /**
     * 作业
     */
    class SchoolAssignmentFragment : SpinnerGridFragment() {
        private val ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_SMALL
        private var mAdapter: ArrayObjectAdapter? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setupAdapter()
        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            getSubjectList()
            spinner2?.visibility = View.GONE
        }

        private fun setupAdapter() {
            val presenter = VerticalGridPresenter(ZOOM_FACTOR)

            presenter.numberOfColumns = 1
            gridPresenter = presenter
            mAdapter = ArrayObjectAdapter(WorkPresenter(this))
            adapter = mAdapter
            onItemViewClickedListener = OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->

            }
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
                                        subjectId = (spinner1.adapter as BaseSpinnerAdapter<SubjectBean>).getItem(position).id
                                        if (!TextUtils.isEmpty(subjectId)) {
                                            loadData(subjectId)
                                        }
                                    }
                                }
                                if (!subs.isEmpty()) {
                                    spinner1.setSelection(0)
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
                                mAdapter?.clear()
                                val jo = httpResultBody.result.seatworkList
                                if (null != jo && !jo.isEmpty()) {
                                    jo.forEach {
                                        mAdapter?.add(it)
                                    }
                                }
                                mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
                            }
                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                        }
                    })
        }


        class WorkPresenter(private var mFragment: Fragment?) : Presenter() {
            override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
                val rootView = LayoutInflater.from(parent?.context).inflate(R.layout.user_work_layout, parent, false)
                // val rootView = mFragment?.layoutInflater?.inflate(R.layout.curriculum_layout, null)
                return ViewHolder(rootView)
            }

            override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
                val titleTv = viewHolder?.view?.findViewById<TextView>(R.id.titleTv)
                val contentTv = viewHolder?.view?.findViewById<TextView>(R.id.contentTv)
                val timeTv = viewHolder?.view?.findViewById<TextView>(R.id.timeTv)
                val workBean = (item as WorkBean)
                titleTv?.text = workBean.title
                contentTv?.text = workBean.content
                timeTv?.text = workBean.createTime
            }

            override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
            }
        }
    }

    /**
     *空白内容
     *
     */
    class EmptyFragment : GridFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val presenter = VerticalGridPresenter(FocusHighlight.ZOOM_FACTOR_SMALL)
            presenter.numberOfColumns = 1
            gridPresenter = presenter
        }

    }


}
