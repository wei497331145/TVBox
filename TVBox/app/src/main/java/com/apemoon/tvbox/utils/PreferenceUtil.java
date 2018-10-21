package com.apemoon.tvbox.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.apemoon.tvbox.entity.UserEntity;

/**
 * Description：SharedPreferences的管理类
 */
public class PreferenceUtil {

    private static SharedPreferences mSharedPreferences = null;
    private static Editor mEditor = null;

    public static void init(Context context) {
        if (null == mSharedPreferences) {
            mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static void removeKey(String key) {
        mEditor = mSharedPreferences.edit();
        mEditor.remove(key);
        mEditor.commit();
    }

    public static void removeAll() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear();
        mEditor.commit();
    }

    public static void commitString(String key, String value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public static String getString(String key, String faillValue) {
        return mSharedPreferences.getString(key, faillValue);
    }

    public static void commitInt(String key, int value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public static int getInt(String key, int failValue) {
        return mSharedPreferences.getInt(key, failValue);
    }

    public static void commitLong(String key, long value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    public static long getLong(String key, long failValue) {
        return mSharedPreferences.getLong(key, failValue);
    }

    public static void commitBoolean(String key, boolean value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public static Boolean getBoolean(String key, boolean failValue) {
        return mSharedPreferences.getBoolean(key, failValue);
    }

    public static void commitDouble(String key, double value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putLong(key, Double.doubleToRawLongBits(value));
        mEditor.commit();

    }

    public static double getDouble(String key, double failValue) {
        return Double.longBitsToDouble(mSharedPreferences.getLong(key, Double.doubleToLongBits(failValue)));
    }


    public static void saveAccountDdata(UserEntity userEntity,String mAccount,String mPassword){
        PreferenceUtil.commitString(ConstantUtil.TOKEN, userEntity.getToken());
        PreferenceUtil.commitString(ConstantUtil.USER_ACCOUNT, mAccount);
        PreferenceUtil.commitString(ConstantUtil.USER_PASSWORD, mPassword);
        PreferenceUtil.commitString(ConstantUtil.TOKEN, userEntity.getToken());
        PreferenceUtil.commitString(ConstantUtil.USER_ID, String.valueOf(userEntity.getUserId()));
        PreferenceUtil.commitString(ConstantUtil.USER_TYPE,  userEntity.getUserType());
        PreferenceUtil.commitString(ConstantUtil.GRADED_ID,  String.valueOf(userEntity.getUserInfo().getGradeId()));
        PreferenceUtil.commitString(ConstantUtil.SCHOOL_ID,  String.valueOf(userEntity.getUserInfo().getSchoolId()));
        PreferenceUtil.commitString(ConstantUtil.CLASS_ID,  String.valueOf(userEntity.getUserInfo().getClassId()));

        AccountInfoUtil.saveAccount(userEntity,mAccount,mPassword);
    }
}
