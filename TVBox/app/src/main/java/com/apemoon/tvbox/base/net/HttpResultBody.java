package com.apemoon.tvbox.base.net;

import java.io.Serializable;

/**
 * @Author water
 * @Date 2017-11-24
 * @des  解析网络数据的基类
 */

public class HttpResultBody<T> implements Serializable {

    public String code;
    public String message;
    public T result;

}
