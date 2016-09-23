package com.zfsoftmh.listener;

/**
 * Created by wesley on 2016/9/22.
 * 回调接口
 */
public interface CallBackListener<T>
{
    //成功的回调
    void onSuccess(T t);

    //失败的回调
    void onFailure(String errorMessage);
}
