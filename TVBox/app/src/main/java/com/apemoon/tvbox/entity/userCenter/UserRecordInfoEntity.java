package com.apemoon.tvbox.entity.userCenter;

import com.apemoon.tvbox.entity.UserEntity;

import java.io.Serializable;

public class UserRecordInfoEntity implements Serializable{
    /**
     * userInfo : {"address":"","autograph":"","birthday":"2018-08-30","census":"","censusType":"","classId":1,"createTime":null,"createUser":"","dutyId":6,"dutyName":"学习委员","email":"","examNumber":"","gradeId":2,"headImage":"http://oocunhzw9.bkt.clouddn.com//PICTURE/20180831/5144AZYIL2.jpg","healthCondition":"","id":2,"idcard":"4211811989021231","loginId":2,"name":"张三","nation":"汉族","overseasChinese":"","page":1,"phone":"15377000000","politicsStatus":"","rows":10,"schoolId":1,"schoolName":"真光小学","sex":"男","stuNumber":"2222","type":"","updateTime":null,"updateUser":""}
     */
    private UserRecordInfoEntity.UserRecordsInfoBean infoRecordsList;

    public UserRecordInfoEntity.UserRecordsInfoBean getUserInfo() {
        return infoRecordsList;
    }

    public void setUserInfo(UserRecordInfoEntity.UserRecordsInfoBean infoRecordsList) {
        this.infoRecordsList = infoRecordsList;
    }

    public class UserRecordsInfoBean implements Serializable {

        /**
         "classId": 0,——》年级id
         "content": "市优秀高级教师",——》内容
         "dutyId": 0,——》职务id
         "endTime": "",——》结束时间
         "gradeName": "",——》班级名称
         "gradesId": 0,——》班级id
         "id": 6,
         "intro": "",——》备注
         "jcTime": "2015-07-01",——》奖惩日期
         "pjTime": "",
         "relevanceId": 3,关联的id，教师id或学生id
         "schoolId": 1,——》学校id
         "schoolName": "真光小学",——》学校名称
         "semesterId": 1,——》学期id
         "ssTime": "2016-01-30",——》实施日期
         "startTime": "",——》开始时间
         "subjectId": 0,——》任教科目id
         "teacherId": 0,——》教师id
         "type": "7",——》学生就读信息("1"),学生评价信息("2"),学生担任职务("3"),学生奖惩信息("4"),教师任教信息("5"),教师担任职务("6"),教师奖惩信息("7")
         "type1": "市优秀教师"——》奖惩类型
         */

        private String classId;
        private String content;
        private String dutyId;
        private String endTime;
        private String gradeName;
        private String gradesId;
        private String id;
        private String intro;
        private String jcTime;
        private String relevanceId;
        private String schoolId;
        private String schoolName;
        private String semesterId;
        private String ssTime;
        private String startTime;
        private String subjectId;
        private String teacherId;
        private String type;
        private String type1;


        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDutyId() {
            return dutyId;
        }

        public void setDutyId(String dutyId) {
            this.dutyId = dutyId;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getGradesId() {
            return gradesId;
        }

        public void setGradesId(String gradesId) {
            this.gradesId = gradesId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getJcTime() {
            return jcTime;
        }

        public void setJcTime(String jcTime) {
            this.jcTime = jcTime;
        }

        public String getRelevanceId() {
            return relevanceId;
        }

        public void setRelevanceId(String relevanceId) {
            this.relevanceId = relevanceId;
        }

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getSemesterId() {
            return semesterId;
        }

        public void setSemesterId(String semesterId) {
            this.semesterId = semesterId;
        }

        public String getSsTime() {
            return ssTime;
        }

        public void setSsTime(String ssTime) {
            this.ssTime = ssTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }





    }
}
