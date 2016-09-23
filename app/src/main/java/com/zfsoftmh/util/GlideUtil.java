package com.zfsoftmh.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by wesley on 2016/9/22.
 * 图片加载的工具类--单例模式
 */
public class GlideUtil
{
    private GlideUtil()
    {

    }

    //加载图片---context
    public static void loadImage(Context context, String url, ImageView imageView, int width, int height)
    {
        Glide.with(context).load(url).crossFade().centerCrop().into(imageView);
    }

    //加载图片---activity
    public static void loadImage(Activity activity, String url, ImageView imageView, int width, int height)
    {
        Glide.with(activity).load(url).crossFade().centerCrop().into(imageView);
    }

    //加载图片---fragment
    public static void loadImage(Fragment fragment, String url, ImageView imageView, int width, int height)
    {
        Glide.with(fragment).load(url).crossFade().centerCrop().into(imageView);
    }

    //清除磁盘缓存
    public static void clearDiskCache(Context context)
    {
        Glide.get(context).clearDiskCache();
    }

    //清除内存缓存
    public static void clearMemory(Context context)
    {
        Glide.get(context).clearMemory();
    }

    //清除缓存
    public static void clear(Context context)
    {
        clearDiskCache(context);
        clearMemory(context);
    }
}
