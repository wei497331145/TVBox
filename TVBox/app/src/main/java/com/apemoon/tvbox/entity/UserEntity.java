package com.apemoon.tvbox.entity;

import java.io.Serializable;

/**
 * Created by water on 2018/8/31/031.
 * des：用户登录接口的实体类
 */

public class UserEntity implements Serializable {
    /**
     * userId : 2
     * userInfo : {"address":"","autograph":"","birthday":"2018-08-30","census":"","censusType":"","classId":1,"createTime":null,"createUser":"","dutyId":6,"dutyName":"学习委员","email":"","examNumber":"","gradeId":2,"headImage":"http://oocunhzw9.bkt.clouddn.com//PICTURE/20180831/5144AZYIL2.jpg","healthCondition":"","id":2,"idcard":"4211811989021231","loginId":2,"name":"张三","nation":"汉族","overseasChinese":"","page":1,"phone":"15377000000","politicsStatus":"","rows":10,"schoolId":1,"schoolName":"真光小学","sex":"男","stuNumber":"2222","type":"","updateTime":null,"updateUser":""}
     * userType : 2
     */
    private int userId;
    private UserInfoBean userInfo;
    private String userType;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public class UserInfoBean implements Serializable {
        /**
         * address :
         * autograph :
         * birthday : 2018-08-30
         * census :
         * censusType :
         * classId : 1
         * createTime : null
         * createUser :
         * dutyId : 6
         * dutyName : 学习委员
         * email :
         * examNumber :
         * gradeId : 2
         * headImage : http://oocunhzw9.bkt.clouddn.com//PICTURE/20180831/5144AZYIL2.jpg
         * healthCondition :
         * id : 2
         * idcard : 4211811989021231
         * loginId : 2
         * name : 张三
         * nation : 汉族
         * overseasChinese :
         * page : 1
         * phone : 15377000000
         * politicsStatus :
         * rows : 10
         * schoolId : 1
         * schoolName : 真光小学
         * sex : 男
         * stuNumber : 2222
         * type :
         * updateTime : null
         * updateUser :
         */

        private String address;
        private String autograph;
        private String birthday;
        private String census;
        private String censusType;
        private int classId;
        private Object createTime;
        private String createUser;
        private int dutyId;
        private String dutyName;
        private String email;
        private String examNumber;
        private int gradeId;
        private String gradeName;
        private String headImage;
        private String healthCondition;
        private int id;
        private String idcard;
        private int loginId;
        private String name;
        private String nation;
        private String overseasChinese;
        private int page;
        private String phone;
        private String politicsStatus;
        private int rows;
        private int schoolId;
        private String schoolName;
        private String sex;
        private String stuNumber;
        private String type;
        private Object updateTime;
        private String updateUser;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

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

        public String getCensus() {
            return census;
        }

        public void setCensus(String census) {
            this.census = census;
        }

        public String getCensusType() {
            return censusType;
        }

        public void setCensusType(String censusType) {
            this.censusType = censusType;
        }

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
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

        public String getHealthCondition() {
            return healthCondition;
        }

        public void setHealthCondition(String healthCondition) {
            this.healthCondition = healthCondition;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public int getLoginId() {
            return loginId;
        }

        public void setLoginId(int loginId) {
            this.loginId = loginId;
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

        public String getOverseasChinese() {
            return overseasChinese;
        }

        public void setOverseasChinese(String overseasChinese) {
            this.overseasChinese = overseasChinese;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
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

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
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

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }
    }
}
