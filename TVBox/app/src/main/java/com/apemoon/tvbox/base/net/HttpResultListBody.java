package com.apemoon.tvbox.base.net;

import java.io.Serializable;
import java.util.List;

/**
 * @Des 返回数组时的数据结构
 * @Author zhouy
 * @Date 2018-01-05
 */

public class HttpResultListBody<T> implements Serializable {

    public String code;
    public String message;
    public List<T> result;

}
