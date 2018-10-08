package com.apemoon.tvbox.entity.userCenter;

import com.apemoon.tvbox.entity.UserEntity;

import java.io.Serializable;

public class UserTeachersEntity implements Serializable{

    private UserEntity.UserInfoBean userInfo;

    public UserEntity.UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserEntity.UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public class UserInfoBean implements Serializable {
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




    }
}
