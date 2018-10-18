package com.apemoon.tvbox.entity;

import java.io.Serializable;
import java.util.List;

public class SchoolListEntity implements Serializable {

    public List<SchoolBean> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolBean> schoolList) {
        this.schoolList = schoolList;
    }

    private List<SchoolBean> schoolList;

    public class SchoolBean implements Serializable {
        private int  id;
        private String address;
        private String area;
        private String city;
        private Long createTime;
        private String createUser;
        private String foundDate;
        private String image;
        private String intro;
        private String name;
        private String nature;
        private String number;
        private String phone;
        private String provience;
        private String schoolTypeId;
        private String schoolTypeName;
        private String state;
        private Long updateTime;
        private String updateUser;

        public String getFoundDate() {
            return foundDate;
        }

        public void setFoundDate(String foundDate) {
            this.foundDate = foundDate;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvience() {
            return provience;
        }

        public void setProvience(String provience) {
            this.provience = provience;
        }

        public String getSchoolTypeId() {
            return schoolTypeId;
        }

        public void setSchoolTypeId(String schoolTypeId) {
            this.schoolTypeId = schoolTypeId;
        }

        public String getSchoolTypeName() {
            return schoolTypeName;
        }

        public void setSchoolTypeName(String schoolTypeName) {
            this.schoolTypeName = schoolTypeName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Long updateTime) {
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