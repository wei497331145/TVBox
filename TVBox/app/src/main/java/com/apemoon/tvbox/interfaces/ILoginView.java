package com.apemoon.tvbox.interfaces;

import com.apemoon.tvbox.entity.UserEntity;

/**
 * Created by water on 2018/8/27/027.
 * des：
 */

public interface ILoginView {

    void loginSuccess(UserEntity userEntity,String code);

    void loginFail();
}
