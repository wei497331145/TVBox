package com.apemoon.tvbox.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.target.BitmapImageViewTarget;


/**
 * @Author water
 * @Date 2017-11-29
 * @des  Glide的工具类
 */

public class GlideUtil {

    private static String IMAGE_URL = "";

    //没有加服务器域名的图片
    public static void imageLocal(final Context context, String imgUrl,final ImageView imageView) {
        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(context).load(imgUrl)
                    .transform(new FitCenter(context))
                    .into(imageView);
        }
    }

    //加服务器域名的图片
    public static void image(final Context context, String imgUrl,final ImageView imageView) {
        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(context)
                    .load(IMAGE_URL+imgUrl)
                    .into(imageView);
        }
    }

    //没有加服务器域名的圆形图片
    public static void imageCircleLocal(final Context context, String imgUrl,final ImageView imageView) {
        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(context).load(imgUrl)
                    .asBitmap()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }


    //加服务器域名的圆形图片
    public static void imageCircle(final Context context, String imgUrl,final ImageView imageView) {
        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(context).load(IMAGE_URL+imgUrl).asBitmap().centerCrop()
                    .into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }






}
