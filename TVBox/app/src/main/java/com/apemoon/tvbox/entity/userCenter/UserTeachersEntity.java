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
        private String headImage;
        private int address;
        private int birthday;
        private String census;
        private String certificate;
        private String characterC;
        private int createTime;
        private String createUser;
        private String degree;
        private int dualC;
        private String dutyId;
        private String dutyName;
        private String education;
        private String email;
        private int hireDate;
        private String hireState;
        private String id;
        private String idCard;
        private int loginId;
        private int name;
        private String nation;
        private String ofSchoolAge;
        private String orgId;
        private int overseasChinese;
        private String page;
        private String phone;
        private int politicsStatus;
        private String professionalTitle;
        private String rows;
        private String schoolId;
        private String schoolName;
        private int sex;
        private String speciality;
        private String subjectIds;
        private String type;
        private String type2;
        private String updateTime;
        private String updateUser;


        public String getActiveState() {
            return activeState;
        }

        public void setActiveState(String activeState) {
            this.activeState = activeState;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public int getAddress() {
            return address;
        }

        public void setAddress(int address) {
            this.address = address;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
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

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public int getDualC() {
            return dualC;
        }

        public void setDualC(int dualC) {
            this.dualC = dualC;
        }

        public String getDutyId() {
            return dutyId;
        }

        public void setDutyId(String dutyId) {
            this.dutyId = dutyId;
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

        public int getHireDate() {
            return hireDate;
        }

        public void setHireDate(int hireDate) {
            this.hireDate = hireDate;
        }

        public String getHireState() {
            return hireState;
        }

        public void setHireState(String hireState) {
            this.hireState = hireState;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getOfSchoolAge() {
            return ofSchoolAge;
        }

        public void setOfSchoolAge(String ofSchoolAge) {
            this.ofSchoolAge = ofSchoolAge;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public int getOverseasChinese() {
            return overseasChinese;
        }

        public void setOverseasChinese(int overseasChinese) {
            this.overseasChinese = overseasChinese;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPoliticsStatus() {
            return politicsStatus;
        }

        public void setPoliticsStatus(int politicsStatus) {
            this.politicsStatus = politicsStatus;
        }

        public String getProfessionalTitle() {
            return professionalTitle;
        }

        public void setProfessionalTitle(String professionalTitle) {
            this.professionalTitle = professionalTitle;
        }

        public String getRows() {
            return rows;
        }

        public void setRows(String rows) {
            this.rows = rows;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
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
