package com.apemoon.tvbox.entity;

import java.io.Serializable;
import java.util.List;

public class AccountListEntity implements Serializable{

    public List<InformationBean> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<InformationBean> informationList) {
        this.informationList = informationList;
    }

    private List<AccountListEntity.InformationBean> informationList;


    public class InformationBean implements Serializable {
/**
         "content": "1",——》富文本内容
         "cover": "1",封面图
         "createTeacherId": 4,——》创建该资讯的教师的id
         "createTime": 1537947985000,——》创建时间
         "id": 1,
         "images": "1",——》图片类型，图片列表
         "oneClassifyId": 1,——》一级分类列表
         "oneClassifyName": "校园新闻",——》一级分类名字
         "partakeObject": "1",——》参与对象
         "title": "sdf",——》标题
         "twoClassifyId": 1,——》二级分类id
         "twoClassifyName": "校园日报",——》二级分类名字
         "type": "1",——》内容类型：1富文本、2链接、3视频、4图片
         "url": "1",——》url类型：url
         "urlDescribe": "1",——》url类型：url描述
         "videos": "1"——》视频类型：视频列表

 */

        private String content;
        private String cover;
        private int createTeacherId;
        private long createTime;
        private int id;
        private String images;
        private int oneClassifyId;
        private String oneClassifyName;
        private String partakeObject;
        private String title;
        private int twoClassifyId;
        private String twoClassifyName;
        private String type;
        private String url;
        private String urlDescribe;
        private String videos;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getCreateTeacherId() {
            return createTeacherId;
        }

        public void setCreateTeacherId(int createTeacherId) {
            this.createTeacherId = createTeacherId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getOneClassifyId() {
            return oneClassifyId;
        }

        public void setOneClassifyId(int oneClassifyId) {
            this.oneClassifyId = oneClassifyId;
        }

        public String getOneClassifyName() {
            return oneClassifyName;
        }

        public void setOneClassifyName(String oneClassifyName) {
            this.oneClassifyName = oneClassifyName;
        }

        public String getPartakeObject() {
            return partakeObject;
        }

        public void setPartakeObject(String partakeObject) {
            this.partakeObject = partakeObject;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTwoClassifyId() {
            return twoClassifyId;
        }

        public void setTwoClassifyId(int twoClassifyId) {
            this.twoClassifyId = twoClassifyId;
        }

        public String getTwoClassifyName() {
            return twoClassifyName;
        }

        public void setTwoClassifyName(String twoClassifyName) {
            this.twoClassifyName = twoClassifyName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlDescribe() {
            return urlDescribe;
        }

        public void setUrlDescribe(String urlDescribe) {
            this.urlDescribe = urlDescribe;
        }

        public String getVideos() {
            return videos;
        }

        public void setVideos(String videos) {
            this.videos = videos;
        }


    }
}
