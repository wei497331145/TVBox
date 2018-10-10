package com.apemoon.tvbox.interfaces.fragment;

import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;
import com.apemoon.tvbox.entity.userCenter.UserInfoEntity;
import com.apemoon.tvbox.entity.userCenter.UserRecordInfoEntity;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */

public interface IInformationView {

    void receiveInformationsSuccess(InfoListEntity entity);

    void receiveInformationsFail();

    void receiveInformationClassicalSuccess(InfoClassicalEntity entity);

    void receiveInformationClassicalFail();

}
