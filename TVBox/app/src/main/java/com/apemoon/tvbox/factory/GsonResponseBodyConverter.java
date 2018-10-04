package com.apemoon.tvbox.factory;


import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.utils.LogUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

;


/**
 * @Author water
 * @Date 2017-11-24
 * @des  自定义的Gson解析工厂
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {

    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson, Type type){
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        //L.d(response+"\n....................");
        //先将返回的json数据解析到Response中，如果code==200，则解析到我们的实体基类中，否则抛异常
        HttpResultBody httpResult = gson.fromJson(response, HttpResultBody.class);
      /*  if(httpResult.error_code==1){//token失效被踢下线
            throw new LongTokenInvalidException();
        }else if(httpResult.error_code==5){//您的账号已被禁用,如需开通请联系客服
            throw new BannedInvalidException();
        }else if(httpResult.error_code==11){//用户禁止权限
            throw new PermissionsException();
        }else {

        }*/
        LogUtil.d("返回数据：" + response);
        return gson.fromJson(response, type);
    }





}