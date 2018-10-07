package com.apemoon.tvbox.interfaces.personal;

import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */

public interface IPersonalView {

    void receivePersonalInfoSuccess(ReceiveNoticeListEntity receiveNoticeListEntity);

    void receivePersonalInfoFail();

    void receiveRecordsSuccess();

    void receiveRecordsFail();
}
