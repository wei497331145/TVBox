package com.apemoon.tvbox.interfaces.fragment;

import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.entity.userCenter.UserInfoEntity;
import com.apemoon.tvbox.entity.userCenter.UserRecordInfoEntity;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */

public interface IPersonalView {

    void receivePersonalInfoSuccess(UserInfoEntity entity);

    void receivePersonalInfoFail();

    void receiveRecords1Success(UserRecordInfoEntity entity);

    void receiveRecords1Fail();


    void receiveRecords2Success(UserRecordInfoEntity entity);

    void receiveRecords2Fail();
}
