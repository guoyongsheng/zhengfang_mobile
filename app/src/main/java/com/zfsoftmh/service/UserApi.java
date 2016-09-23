package com.zfsoftmh.service;

import com.zfsoftmh.entity.ResponseInfo;
import com.zfsoftmh.entity.User;
import com.zfsoftmh.listener.CallBackListener;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wesley on 2016/9/22.
 * 用户模块的接口
 */
public interface UserApi
{
    //登陆
    void login(String userName, String password, CallBackListener<User> listener);




    interface UserApiRetrofit
    {
        @FormUrlEncoded
        @POST("")
        Call<ResponseInfo<User>> login(String userName, String password);
    }
}
