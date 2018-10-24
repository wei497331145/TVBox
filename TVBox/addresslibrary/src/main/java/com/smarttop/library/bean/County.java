package com.smarttop.library.bean;
/**
 * Created by smartTop on 2016/10/19.
 * 区 乡镇的实体类
 */
public class County {
    public int id;
    public County() {
    }

    public County(String name,int id) {
        this.name = name;
        this.id = id;
    }

    public String name;
    public String code;
}