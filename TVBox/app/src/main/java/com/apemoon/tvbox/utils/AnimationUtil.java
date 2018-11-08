package com.apemoon.tvbox.utils;

import android.view.View;

/**
 * Created by water on 2018/8/29/029.
 * des：
 */

public class AnimationUtil {

    public static  void setAdapter(View view, boolean hasFocus){
        if (hasFocus) {
            view.animate().scaleX(1.005f).scaleY(1.005f).setDuration(300).start();
        } else {
            view.animate().scaleX(1f).scaleY(1f).setDuration(300).start();
        }
    }


    public static  void setTextAnimation(View view, boolean hasFocus,float valueX,float valueY,float unValueX,float unValueY){
        if (hasFocus) {
            view.animate().scaleX(valueX).scaleY(valueX).setDuration(300).start();
        } else {
            view.animate().scaleX(unValueX).scaleY(unValueY).setDuration(300).start();
        }
    }

    public static  void setSettingAdapter(View view, boolean hasFocus){
        if (hasFocus) {
            view.animate().scaleX(2f).scaleY(2f).setDuration(0).start();
        } else {
            view.animate().scaleX(1f).scaleY(1f).setDuration(0).start();
        }
    }
}
