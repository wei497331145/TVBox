package com.apemoon.tvbox.entity.userCenter;

import com.apemoon.tvbox.entity.UserEntity;

import java.io.Serializable;
import java.util.List;

public class UserTeachersEntity implements Serializable{

    public List<TeachersInfoBean> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeachersInfoBean> teacherList) {
        this.teacherList = teacherList;
    }

    private List<UserTeachersEntity.TeachersInfoBean > teacherList;

    public class TeachersInfoBean implements Serializable {
/**
                               "activeState": "",
                               "address": "珠江新城中环路456号",
                               "birthday": "1995-06-21",
                               "census": "南宁",
                               "certificate": "计算机四级",
                               "characterC": "",
                               "createTime": null,
                               "createUser": "",
                               "degree": "本科",
                               "dualC": "",
                               "dutyId": 2,
                               "dutyName": "班主任",
                               "education": "本科",
                               "email": "1055574@qq.com",
                               "hireDate": "2017-09-01",
                               "hireState": "在职",
                               "id": 4,
                               "idCard": "456322199506213654",
                               "loginId": 13,
                               "name": "顾目",
                               "nation": "汉族",
                               "ofSchoolAge": 2,
                               "orgId": 0,
                               "overseasChinese": "0",
                               "page": 1,
                               "phone": "15245645781",
                               "politicsStatus": "群众",
                               "professionalTitle": "班主任",
                               "rows": 10,
                               "schoolId": 1,
                               "schoolName": "真光小学",
                               "sex": "女",
                               "speciality": "计算机技术",
                               "subject": "语文",
                               "subjectIds": "计算机技术",
                               "type": "小学教师",
                               "type2": "小学教师",
                               "updateTime": null,
                               "updateUser":
 */
    private String activeState;
    private String address;
    private String autograph;
        private String birthday;
        private String census;
        private String certificate;
        private String characterC;
        private long createTime;
        private String createUser;
        private String degree;
        private String headImage;
        private String hireDate;
        private String hireState;
        private int id;
        private String idCard;
        private int loginId;
        private String name;
        private String nation;
        private int ofSchoolAge;

        private String openId;
        private int orgId;
        private int page;
        private String phone;

        private String dualC;
        private int dutyId;


        private String dutyName;
        private String education;
        private String email;

        private String overseasChinese;

        private String politicsStatus;
        private String professionalTitle;
        private int rows;
        private int schoolId;
        private String schoolName;
        private String sex;
        private String speciality;
        private String subjectIds;
        private String type;
        private String type2;
        private long updateTime;
        private String updateUser;


        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getDualC() {
            return dualC;
        }

        public void setDualC(String dualC) {
            this.dualC = dualC;
        }

        public int getDutyId() {
            return dutyId;
        }

        public void setDutyId(int dutyId) {
            this.dutyId = dutyId;
        }

        public String getActiveState() {
            return activeState;
        }

        public void setActiveState(String activeState) {
            this.activeState = activeState;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getCharacterC() {
            return characterC;
        }

        public void setCharacterC(String characterC) {
            this.characterC = characterC;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getDutyName() {
            return dutyName;
        }

        public void setDutyName(String dutyName) {
            this.dutyName = dutyName;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getHireDate() {
            return hireDate;
        }

        public void setHireDate(String hireDate) {
            this.hireDate = hireDate;
        }

        public String getHireState() {
            return hireState;
        }

        public void setHireState(String hireState) {
            this.hireState = hireState;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
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

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }
        public void setNation(String nation) {
            this.nation = nation;
        }

        public int getOfSchoolAge() {
            return ofSchoolAge;
        }

        public void setOfSchoolAge(int ofSchoolAge) {
            this.ofSchoolAge = ofSchoolAge;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
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

        public String getProfessionalTitle() {
            return professionalTitle;
        }

        public void setProfessionalTitle(String professionalTitle) {
            this.professionalTitle = professionalTitle;
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

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public String getSubjectIds() {
            return subjectIds;
        }

        public void setSubjectIds(String subjectIds) {
            this.subjectIds = subjectIds;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
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
