package com.apemoon.tvbox.interfaces.fragment;

import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */

public interface IReceiveNoticeView {

    void getReceiveNoticeListSuccess(ReceiveNoticeListEntity receiveNoticeListEntity);

    void getReceiveNoticeListFail();

    void setNoticeReadSuccess();

    void setNoticeReadFail();
}
