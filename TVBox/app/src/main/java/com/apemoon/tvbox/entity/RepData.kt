package com.apemoon.tvbox.entity

import com.google.gson.JsonObject
import java.io.Serializable

/**
 *TVBox
 * Created by mukry on 2018/10/16.
 * 深圳市伯尚科技有限公司
 */
//
//{
//    "code": "0000",
//    "message": "操作成功",
//    "result": {
//    "classSchedule": {
//    "classId": 2,——》班级id
//    "contentType": "2",——》内容类型：1图片、2json字符串
//    "gradeId": 2,——》班级id
//    "id": 15,
//    "scheduleJson": "",——》课表json
//    "schedulePhoto": "",——》课表图片
//    "schoolId": 3,——》学校id
//}
//}
//}
open class BaseRepResultBean : Serializable

class BaseRepBean(var code: String = "",
                  var message: String = "",
                  var result: Any
) : Serializable


//
//"classSchedule": {
//    "classId": 2,——》班级id
//    "contentType": "2",——》内容类型：1图片、2json字符串
//    "gradeId": 2,——》班级id
//    "id": 15,
//    "scheduleJson": "",——》课表json
//    "schedulePhoto": "",——》课表图片
//    "schoolId": 3,——》学校id
//}

data class ClassSchedule(
        var classSchedule: CurriculumBean = CurriculumBean()
) : Serializable


data class CurriculumBean(var classId: String = "",
                          var contentType: String = "",
                          var gradeId: String = "",
                          var id: String = "",
                          var scheduleJson: String = "",
                          var schedulePhoto: String = "",
                          var schoolId: String = ""

) : BaseRepResultBean()


data class ExamClassifyList(
        var examClassifyList: ArrayList<ExamClassifyBean> = ArrayList<ExamClassifyBean>()
) : Serializable

//"createTeacherId": 4,——》创建的老师id
//"createTime": 1537252859000,——》创建时间
//"id": 2,
//"name": "期末考试",——》考试名称
//"schoolId": 1    ——》学校id
data class ExamClassifyBean(var createTeacherId: String = "",
                            var createTime: String = "",
                            var id: String = "",
                            var name: String = "",
                            var schoolId: String = ""

) : BaseRepResultBean()


data class UserMark(
        var mark: ArrayList<JsonObject> = ArrayList<JsonObject>()
) : Serializable


data class MarkBean(
        var key: String = "",
        var vaule: String = ""
) : Serializable


data class SubjectList(
        var subjectList: ArrayList<SubjectBean> = ArrayList<SubjectBean>()
) : Serializable

//"classId": 1,    ——》班级id
//"id": 11,
//"name": "音乐",——》科目名字
//"schoolId": 1,——》学校id
data class SubjectBean(
        var name: String = "",
        var schoolId: String = "",
        var classId: String = "",
        var id: String = ""
) : Serializable


data class WorkList(
        var seatworkList: ArrayList<WorkBean> = ArrayList<WorkBean>()
) : Serializable


//"content": "作业内容",
//"createTime": 1537183177000,——》创建时间
//"id": 16,
//"isAutograph": "1",——》是否签名：0否，1是
//"subjectId": 1,——》科目id
//"teacherId": 4,——教师id
//"teacherName": "",——教师名字
//"title": "作业标题"——作业标题
data class WorkBean(
        var content: String = "",
        var createTime: String = "",
        var id: String = "",
        var title: String = "",
        var teacherName: String = "",
        var teacherId: String = "",
        var subjectId: String = "",
        var isAutograph: String = ""
) : Serializable


data class WorkDetail(
        var seatwork: WorkDetailBean = WorkDetailBean()
) : Serializable

//"content": "作业内容",
//"createTime": 1537183177000,——》创建时间
//"id": 16,
//"isAutograph": "1",——》是否签名：0否，1是
//"subjectId": 1,——》科目id
//"teacherId": 4,——教师id
//"teacherName": "",——教师名字
//"title": "作业标题"——作业标题
data class WorkDetailBean(
        var content: String = "",
        var createTime: String = "",
        var id: String = "",
        var isAutograph: String = "",
        var subjectId: String = "",
        var teacherId: String = "",
        var teacherName: String = "",
        var title: String = ""
) : Serializable


data class ClassActivityList(
        var classActivityList: ArrayList<ClassActivityBean> = ArrayList<ClassActivityBean>()
) : Serializable


//"activityPeopleNum": 0,
//"content": "活动开始",
//"createTeacherId": 78,
//"createTime": 1539574285000,
//"endTime": 1540006256000,
//"gradePeopleNum": 0,
//"id": 104,
//"isRestrict": "1",
//"page": 1,
//"rows": 10,
//"sendObject": "2",
//"title": "活动",
//"type": "1"

data class ClassActivityBean(
        var activityPeopleNum: String = "",
        var content: String = "",
        var createTeacherId: String = "",
        var createTime: String = "",
        var gradePeopleNum: String = "",
        var id: String = "",
        var isRestrict: String = "",
        var rows: String = "",
        var sendObject: String = "",
        var title: String = "",
        var type: String = "",
        var page: String = ""
) : Serializable


data class ClassActivityDetail(
        var classActivity: ClassActivityDetailBean = ClassActivityDetailBean()
) : Serializable

// "activityPeopleNum": 0,
//"content": "<p>旅游景点</p>",
//"createTeacherId": 59,
//"createTime": 1538837126000,
//"endTime": 1538923494000,
//"gradePeopleNum": 0,
//"id": 95,
//"isRestrict": "1",
//"page": 1,
//"rows": 10,
//"sendObject": "1",
//"title": "旅游",
//"type": "1"
data class ClassActivityDetailBean(
        var activityPeopleNum: String = "",
        var content: String = "",
        var createTeacherId: String = "",
        var createTime: String = "",
        var endTime: String = "",
        var gradePeopleNum: String = "",
        var id: String = "",
        var isRestrict: String = "",
        var rows: String = "",
        var sendObject: String = "",
        var title: String = "",
        var type: String = "",
        var page: String = ""
) : Serializable


data class PhotoAlbumList(
        var photoAlbumCount: String = "",
        var photoAlbumList: ArrayList<PhotoAlbumBean> = ArrayList<PhotoAlbumBean>()
) : Serializable

data class PhotoList(
        var photoCount: String = "",
        var photoList: ArrayList<PhotoBean> = ArrayList<PhotoBean>()
) : Serializable

//"createTime": 1536571608000,
//"id": 3,
//"image": "url",
//"name": "",
//"photoAlbumId": 2,
data class PhotoBean(
        var createTime: String = "",
        var id: String = "",
        var image: String = "",
        var name: String = "",
        var photoAlbumId: String = ""
) : Serializable


//
//"cover": "封面图",
//"createTime": 1536569992000,——》创建时间
//"createUserId": 4,    ——》创建用户id
//"createUserType": "1",——》创建用户类型
//"id": 2,
//"isOpen": "2",——》是否公开：1公开相册，2私密相册，只有个人相册会使用该字段
//"isPaise": 0,    ——》当前用户是否已经点赞：0未点赞，1已点赞
//"name": "相册名字",
//"praiseNum": 0,——》点赞数量
//"schoolId": 0,——学校id，若若为0，则表示没有学校id，只有班级相册会使用该字段
//"type": "1", ——》类型：1个人相册、2班级相册
//"visibleGradeId": 0,——》可见班级id——》若为0，则表示可见班级id为空，只有班级相册会使用该字段
//"visibleScope": ""——》可见范围：1全校可见、2指定班级可见，只有班级相册会使用该字段
data class PhotoAlbumBean(
        var cover: String = "",
        var createTime: String = "",
        var createUserId: String = "",
        var createUserType: String = "",
        var id: String = "",
        var isOpen: String = "",
        var isPaise: String = "",
        var name: String = "",
        var praiseNum: String = "",
        var schoolId: String = "",
        var type: String = "",
        var visibleGradeId: String = "",
        var visibleScope: String = ""
) : Serializable