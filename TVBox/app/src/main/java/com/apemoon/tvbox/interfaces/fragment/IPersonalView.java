package com.apemoon.tvbox.interfaces.fragment;

import com.apemoon.tvbox.base.net.HttpResultBody;
import com.apemoon.tvbox.entity.notice.ReceiveNoticeListEntity;
import com.apemoon.tvbox.entity.userCenter.UserInfoEntity;
import com.apemoon.tvbox.entity.userCenter.UserRecordInfoEntity;
import com.apemoon.tvbox.entity.userCenter.UserSemstersEntity;
import com.apemoon.tvbox.entity.userCenter.UserTeachersEntity;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */

public interface IPersonalView {

    void receivePersonalInfoSuccess(UserInfoEntity entity);

    void receivePersonalInfoFail();

    void receiveTeachersInfoSuccess(UserTeachersEntity entity);

    void receiveTeachersInfoFail();

    void receiveRecords2Success(UserRecordInfoEntity entity);

    void receiveRecords2Fail();


    void receiveRecords4Success(UserRecordInfoEntity entity);

    void receiveRecords4Fail();

    void receiveRemstersSuccess(UserSemstersEntity entity);

    void receiveRemseFail();

}
