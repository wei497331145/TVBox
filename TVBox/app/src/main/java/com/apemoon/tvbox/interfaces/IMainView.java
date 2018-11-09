package com.apemoon.tvbox.interfaces;

import com.apemoon.tvbox.entity.AppUpdateEntity;

/**
 * Created by water on 2018/8/27/027.
 * des：
 */

public interface IMainView {

    void success();

    void fail();

    void getSystemAppVersion(AppUpdateEntity entity);

    void getSystemAppVersionFail();
}
