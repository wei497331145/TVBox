package com.apemoon.tvbox.entity.information;

import com.apemoon.tvbox.entity.UserEntity;

import java.io.Serializable;

public class InfoListEntity implements Serializable{

    private InfoListEntity.InformationBean informationList;

    public InfoListEntity.InformationBean getUserInfo() {
        return informationList;
    }

    public void setUserInfo(InfoListEntity.InformationBean informationList) {
        this.informationList = informationList;
    }

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
        private String createTime;
        private String certificate;
        private int id;
        private String images;
        private String oneClassifyId;
        private String oneClassifyName;
        private String partakeObject;
        private String title;
        private int twoClassifyId;
        private String twoClassifyName;
        private String type;
        private String url;
        private String urlDescribe;
        private String videos;

    }
}
