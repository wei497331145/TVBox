package com.apemoon.tvbox.entity;

import java.io.Serializable;
import java.util.List;

public class SchoolTypeListEntity implements Serializable {

    public List<SchoolTypeBean> getSchoolTypeList() {
        return schoolTypeList;
    }

    public void setSchoolTypeList(List<SchoolTypeBean> schoolTypeList) {
        this.schoolTypeList = schoolTypeList;
    }

    private List<SchoolTypeBean> schoolTypeList;

    public class SchoolTypeBean implements Serializable {
        private int  id;
        private String intro;
        private String name;
        private String number;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }



    }

}