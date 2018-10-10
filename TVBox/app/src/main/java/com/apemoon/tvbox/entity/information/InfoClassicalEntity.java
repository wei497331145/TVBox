package com.apemoon.tvbox.entity.information;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InfoClassicalEntity implements Serializable{

    private static final int TRDITION_ID = 1;//传统文化id
    private static final int SCHOOL_ID = 2;//校园风采id


    public List<InfoClassicalEntity.TwoClassicalBean> getTraditonalTwoClassical(){
        List<InfoClassicalEntity.TwoClassicalBean> twoClasscialList = new ArrayList<>();
        for(int i = 0;i<informationClassifyTwoList.size();i++){
            TwoClassicalBean twoClassicalBean = informationClassifyTwoList.get(i);
            if(TRDITION_ID == twoClassicalBean.getOneClassifyId()){
                twoClasscialList.add(twoClassicalBean);
            }
        }
        return twoClasscialList;
    }

    public List<InfoClassicalEntity.TwoClassicalBean> getSchoollTwoClassical(){
        List<InfoClassicalEntity.TwoClassicalBean> twoClasscialList = new ArrayList<>();
        for(int i = 0;i<informationClassifyTwoList.size();i++){
            TwoClassicalBean twoClassicalBean = informationClassifyTwoList.get(i);
            if(SCHOOL_ID == twoClassicalBean.getOneClassifyId()){
                twoClasscialList.add(twoClassicalBean);
            }
        }
        return  twoClasscialList;
    }

    private List<InfoClassicalEntity.OneClassicalBean> informationClassifyOneList;

    private List<InfoClassicalEntity.TwoClassicalBean> informationClassifyTwoList;

    public List<OneClassicalBean> getInformationClassifyOneList() {
        return informationClassifyOneList;
    }

    public void setInformationClassifyOneList(List<OneClassicalBean> informationClassifyOneList) {
        this.informationClassifyOneList = informationClassifyOneList;
    }

    public List<TwoClassicalBean> getInformationClassifyTwoList() {
        return informationClassifyTwoList;
    }

    public void setInformationClassifyTwoList(List<TwoClassicalBean> informationClassifyTwoList) {
        this.informationClassifyTwoList = informationClassifyTwoList;
    }


    public class OneClassicalBean implements Serializable {
        private int id;

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



    }

    public class TwoClassicalBean implements Serializable {
        private int createTeacherId;
        private long createTimel;
        private int id;
        private String name;
        private int oneClassifyId;
        private int schoolId;


        public int getCreateTeacherId() {
            return createTeacherId;
        }

        public void setCreateTeacherId(int createTeacherId) {
            this.createTeacherId = createTeacherId;
        }

        public long getCreateTimel() {
            return createTimel;
        }

        public void setCreateTimel(long createTimel) {
            this.createTimel = createTimel;
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

        public int getOneClassifyId() {
            return oneClassifyId;
        }

        public void setOneClassifyId(int oneClassifyId) {
            this.oneClassifyId = oneClassifyId;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }



    }
}
