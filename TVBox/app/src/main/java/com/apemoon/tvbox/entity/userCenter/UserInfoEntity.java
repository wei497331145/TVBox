package com.apemoon.tvbox.entity.userCenter;

import com.apemoon.tvbox.entity.UserEntity;

import java.io.Serializable;

public class UserInfoEntity implements Serializable{
    /**
     * userInfo : {"address":"","autograph":"","birthday":"2018-08-30","census":"","censusType":"","classId":1,"createTime":null,"createUser":"","dutyId":6,"dutyName":"学习委员","email":"","examNumber":"","gradeId":2,"headImage":"http://oocunhzw9.bkt.clouddn.com//PICTURE/20180831/5144AZYIL2.jpg","healthCondition":"","id":2,"idcard":"4211811989021231","loginId":2,"name":"张三","nation":"汉族","overseasChinese":"","page":1,"phone":"15377000000","politicsStatus":"","rows":10,"schoolId":1,"schoolName":"真光小学","sex":"男","stuNumber":"2222","type":"","updateTime":null,"updateUser":""}
     */
    private UserEntity.UserInfoBean userInfo;

    public UserEntity.UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserEntity.UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public class UserInfoBean implements Serializable {
        /**
         "autograph": "瑟瑟发抖的学沫",——》个性签名
         "birthday": "2006-02-15",——》生日
         "classId": 4,——》年级id
         "dutyId": 4,——》职务id
         "dutyName": "",——》职务名字
         "email": "1021025639@qq.com",——》邮箱
         "examNumber": "201845102511111",——》考号
         "gradeId": 3,——》班级id
         "gradeName": "初一5班",——》班级名字
         "headImage": "http://JJI7MLUPVU.jpeg",——》头像
         "id": 4,
         "name": "肖虹",——》姓名
         "nation": "汉族",——》民族
         "phone": "15245615152",——》电话号码
         "politicsStatus": "团员",——》政治面貌
         "schoolId": 2,——》学校id
         "schoolName": "腾信中学",——》学校名字
         "sex": "女",——》性别
         "stuNumber": "2018451025",——》学号
         "type": "普通生",——》学生类型
         */

        private String autograph;
        private String birthday;
        private int classId;
        private int dutyId;
        private String dutyName;
        private String email;
        private String examNumber;
        private int gradeId;
        private String gradeName;
        private String headImage;
        private int id;
        private String name;
        private String nation;
        private String phone;
        private String politicsStatus;
        private int schoolId;
        private String schoolName;
        private String sex;
        private String stuNumber;
        private String type;


        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }


        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }


        public int getDutyId() {
            return dutyId;
        }

        public void setDutyId(int dutyId) {
            this.dutyId = dutyId;
        }

        public String getDutyName() {
            return dutyName;
        }

        public void setDutyName(String dutyName) {
            this.dutyName = dutyName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getExamNumber() {
            return examNumber;
        }

        public void setExamNumber(String examNumber) {
            this.examNumber = examNumber;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }


        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPoliticsStatus() {
            return politicsStatus;
        }

        public void setPoliticsStatus(String politicsStatus) {
            this.politicsStatus = politicsStatus;
        }


        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStuNumber() {
            return stuNumber;
        }

        public void setStuNumber(String stuNumber) {
            this.stuNumber = stuNumber;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }
}
