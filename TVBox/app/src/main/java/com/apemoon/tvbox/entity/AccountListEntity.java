package com.apemoon.tvbox.entity;

import java.io.Serializable;
import java.util.List;

public class AccountListEntity implements Serializable {


    public List<AccountInfoBean> getAccountInfoBeanList() {
        return accountInfoBeanList;
    }

    public void setAccountInfoBeanList(List<AccountInfoBean> accountInfoBeanList) {
        this.accountInfoBeanList = accountInfoBeanList;
    }

    private List<AccountInfoBean> accountInfoBeanList;

    public class AccountInfoBean implements Serializable {
        private String accountNo;
        private String accountPwd;
        private String userName;
        private String userHeadImg;

        public AccountInfoBean(String accountNo, String accountPwd, String userName, String userHeadImg) {
            this.accountNo = accountNo;
            this.accountPwd = accountPwd;
            this.userName = userName;
            this.userHeadImg = userHeadImg;
        }


        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getAccountPwd() {
            return accountPwd;
        }

        public void setAccountPwd(String accountPwd) {
            this.accountPwd = accountPwd;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserHeadImg() {
            return userHeadImg;
        }

        public void setUserHeadImg(String userHeadImg) {
            this.userHeadImg = userHeadImg;
        }

    }

}