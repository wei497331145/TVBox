package com.apemoon.tvbox.entity.userCenter;

import com.apemoon.tvbox.entity.UserEntity;

import java.io.Serializable;
import java.util.List;

public class UserRecordInfoEntity implements Serializable{

    public List<UserRecordsInfoBean> getInfoRecordsList() {
        return infoRecordsList;
    }

    public void setInfoRecordsList(List<UserRecordsInfoBean> infoRecordsList) {
        this.infoRecordsList = infoRecordsList;
    }

    private List<UserRecordInfoEntity.UserRecordsInfoBean> infoRecordsList;


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

        private int classId;
        private String content;
        private int dutyId;
        private String endTime;
        private String gradeName;
        private int gradesId;
        private int id;
        private String intro;
        private String jcTime;
        private int relevanceId;
        private String schoolId;
        private String schoolName;
        private int semesterId;
        private String ssTime;
        private String startTime;
        private int subjectId;
        private String teacherId;
        private int type;
        private String type1;

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getDutyId() {
            return dutyId;
        }

        public void setDutyId(int dutyId) {
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

        public int getGradesId() {
            return gradesId;
        }

        public void setGradesId(int gradesId) {
            this.gradesId = gradesId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getRelevanceId() {
            return relevanceId;
        }

        public void setRelevanceId(int relevanceId) {
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

        public int getSemesterId() {
            return semesterId;
        }

        public void setSemesterId(int semesterId) {
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

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
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
