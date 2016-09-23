package com.zfsoftmh.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by wesley on 2016/9/22.
 * 基类---抽象类---所用的activity都继承这个类
 */
public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initVariables();
        initView();
        initPresenter();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //统计时长
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    //获取布局文件id
    public abstract int getLayoutId();

    //初始化变量---包括Intent中传来的变量
    public abstract void initVariables();

    //初始化view
    public abstract void initView();

    //初始化presenter
    public abstract void initPresenter();
}
