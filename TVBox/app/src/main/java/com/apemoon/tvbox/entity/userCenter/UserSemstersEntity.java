package com.apemoon.tvbox.entity.userCenter;

import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;

import java.io.Serializable;
import java.util.List;

public class UserSemstersEntity implements Serializable{

    public List<SemstersBean> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(List<SemstersBean> semesterList) {
        this.semesterList = semesterList;
    }

    private List<UserSemstersEntity.SemstersBean> semesterList;

    public String getCurrentSemesterId(){
        if(semesterList.size()<1){
            return "";
        }
        for(int i =0 ;i<semesterList.size();i++){
            if(1 == semesterList.get(i).getIsPresent()){
                return ""+semesterList.get(i).getId();
            }
        }
        return "";
    }

    public String getCurrentSemesterName(){
        if(semesterList.size()<1){
            return "";
        }
        for(int i =0 ;i<semesterList.size();i++){
            if(1 == semesterList.get(i).getIsPresent()){
                return ""+semesterList.get(i).getName();
            }
        }
        return "";
    }


    public class SemstersBean implements Serializable {
        /**
         "endData": "2019-01-31",——》学期结束时间
         "id": 4,
         "isPresent": 1,——》是否为当前学期：0否，1是
         "manual": "",——》学生手册
         "name": "2018-2019第二学期",——》学期名称
         "startDate": "2018-09-01",——》学期开始时间
         "type": "下学期",——》学期类型
         "year": "2018学年"——》学年
         */

        private String endData;
        private int id;
        private int isPresent;
        private String manual;
        private String name;
        private String startDate;
        private String type;
        private String year;


        public String getEndData() {
            return endData;
        }

        public void setEndData(String endData) {
            this.endData = endData;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsPresent() {
            return isPresent;
        }

        public void setIsPresent(int isPresent) {
            this.isPresent = isPresent;
        }

        public String getManual() {
            return manual;
        }

        public void setManual(String manual) {
            this.manual = manual;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }



    }
}
