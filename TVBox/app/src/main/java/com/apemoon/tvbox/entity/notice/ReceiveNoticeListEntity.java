package com.apemoon.tvbox.entity.notice;

import java.util.List;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */

public class ReceiveNoticeListEntity {

    private List<NoticeListBean> noticeList;

    public List<NoticeListBean> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<NoticeListBean> noticeList) {
        this.noticeList = noticeList;
    }

    public static class NoticeListBean {
        /**
         * autograph :
         * content : 公告内容
         * createPeople : 0
         * createTime : 1535701339000
         * id : 1
         * isRead : 0
         * noticeUserId : 3
         * page : 1
         * partakeObject :
         * redaUserNum : 0
         * rows : 10
         * title : 这是公告标题
         */

        private String autograph;
        private String content;
        private int createPeople;
        private long createTime;
        private int id;
        private String isRead;
        private int noticeUserId;
        private int page;
        private String partakeObject;
        private int redaUserNum;
        private int rows;
        private String title;

        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCreatePeople() {
            return createPeople;
        }

        public void setCreatePeople(int createPeople) {
            this.createPeople = createPeople;
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

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public int getNoticeUserId() {
            return noticeUserId;
        }

        public void setNoticeUserId(int noticeUserId) {
            this.noticeUserId = noticeUserId;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getPartakeObject() {
            return partakeObject;
        }

        public void setPartakeObject(String partakeObject) {
            this.partakeObject = partakeObject;
        }

        public int getRedaUserNum() {
            return redaUserNum;
        }

        public void setRedaUserNum(int redaUserNum) {
            this.redaUserNum = redaUserNum;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
