package com.zfsoftmh.base;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.zfsoftmh.util.GlideUtil;

/**
 * Created by wesley on 2016/9/22.
 * 基类的applicatioon
 */
public class BaseApplication extends Application
{
    private static BaseApplication instance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        init();
    }

    //初始化
    private void init()
    {
        //禁止页面统计
        MobclickAgent.openActivityDurationTrack(false);
    }

    //获取application的实例
    public static BaseApplication getInstance()
    {
        return instance;
    }

    @Override
    public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);

        switch (level)
        {
        /**
         * 所有的界面都不见---清除内存缓存，因为图片的磁盘缓存还在
         * 优化内存
         */
        case TRIM_MEMORY_UI_HIDDEN:
            GlideUtil.clearMemory(getApplicationContext());
            break;

        default:
            break;
        }
    }
}
