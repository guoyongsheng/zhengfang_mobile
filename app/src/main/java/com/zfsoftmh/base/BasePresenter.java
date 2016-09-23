package com.zfsoftmh.base;

/**
 * Created by wesley on 2016/9/22.
 * 基类的presenter所有的presenter都要实现这个接口
 */
public interface BasePresenter
{
    //刚进入界面的时候可能就会有请求
    void start();
}
