package com.apemoon.tvbox.interfaces;

import com.apemoon.tvbox.entity.SchoolListEntity;
import com.apemoon.tvbox.entity.SchoolTypeListEntity;
import com.apemoon.tvbox.entity.information.InfoClassicalEntity;
import com.apemoon.tvbox.entity.information.InfoListEntity;

/**
 * Created by water on 2018/8/31/031.
 * des：
 */

public interface SchoolView {

    void receiveSchoolTypeSuccess(SchoolTypeListEntity entity);

    void receiveSchoolTypFail();



    void receiveSchoolSuccess(SchoolListEntity entity);

    void receiveSchoolFail();


}
