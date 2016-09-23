package com.zfsoftmh.modules.module_user.login;

import com.zfsoftmh.entity.User;
import com.zfsoftmh.listener.CallBackListener;
import com.zfsoftmh.service.UserApi;
import com.zfsoftmh.serviceImpl.UserApiImpl;

/**
 * Created by wesley on 2016/9/22.
 * 登陆界面的presenter层
 */
public class LoginPresenter implements LoginContract.Presenter
{
    private UserApi userApi; //modle层的实例
    private LoginFragment view; //view层的实例
    public LoginPresenter(LoginFragment view)
    {
        this.view = view;
        userApi = new UserApiImpl();
        view.setPresenter(this);
    }

    @Override
    public void start()
    {

    }

    @Override
    public void login(String userName, String password)
    {
        userApi.login(userName, password, new CallBackListener<User>()
        {
            @Override
            public void onSuccess(User user)
            {

            }

            @Override
            public void onFailure(String errorMessage)
            {

            }
        });
    }
}
