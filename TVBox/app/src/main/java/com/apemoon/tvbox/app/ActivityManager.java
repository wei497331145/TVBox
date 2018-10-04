package com.apemoon.tvbox.app;

/**
 * Created by Administrator on 2017/5/5.
 */

import android.app.Activity;
import android.content.Intent;
import com.apemoon.tvbox.ui.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity管理工具
 */

public class ActivityManager {

    private static ActivityManager manager;
    private List<Activity> activityList;

    private ActivityManager() {
        activityList = new ArrayList<>();
    }

    public static ActivityManager getIntence() {

        if (manager == null) {
            manager = new ActivityManager();
        }
        return manager;
    }

    /**
     * 添加Activity到管理工具
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityList != null) {
            activityList.add(activity);
        }
    }

    /**
     * 移除Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activityList != null && activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }

        }
    }

    public void logout(Activity activity) {//退出登录
        //结束所有Activity
        finishAllActivity();
        //跳到登录界面
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
