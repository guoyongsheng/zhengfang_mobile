package com.zfsoftmh.serviceImpl;

import com.zfsoftmh.entity.ResponseInfo;
import com.zfsoftmh.entity.User;
import com.zfsoftmh.listener.CallBackListener;
import com.zfsoftmh.listener.RetrofitCallBackListenter;
import com.zfsoftmh.service.UserApi;
import com.zfsoftmh.util.HttpManagerUtil;

import retrofit2.Call;

/**
 * Created by wesley on 2016/9/22.
 * 用户模块接口实现
 */
public class UserApiImpl implements UserApi
{
    private UserApi.UserApiRetrofit userApiRetrofit = HttpManagerUtil.getInstance().getUserApi();
    @Override
    public void login(String userName, String password, CallBackListener<User> listener)
    {
        Call<ResponseInfo<User>> call =  userApiRetrofit.login(userName, password);
        call.enqueue(new RetrofitCallBackListenter<>(listener));
    }
}
