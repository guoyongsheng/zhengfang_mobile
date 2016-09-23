package com.zfsoftmh.base;

/**
 * Created by wesley on 2016/9/22.
 * 基类的view所用的fragment都实现这个接口
 */
public interface BaseView<T>
{
    //让view层持有presenter层的引用
    void setPresenter(T presenter);
}
