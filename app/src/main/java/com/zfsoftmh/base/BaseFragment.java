package com.zfsoftmh.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by wesley on 2016/9/22.
 * 基类---抽象类---所用的fragment都继承这个类
 */
public abstract class BaseFragment extends Fragment
{
    protected Context context; //上下文对象
    private String pageName = getClass().getName(); //页面名称

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initVariables();
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if(inflater == null)
        {
            return null;
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //统计页面
        MobclickAgent.onPageStart(pageName);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        MobclickAgent.onPageEnd(pageName);
    }

    //初始化变量
    public abstract void initVariables();

    //获取布局文件id
    public abstract int getLayoutId();

    //初始化view
    public abstract void initView(View view);
}
