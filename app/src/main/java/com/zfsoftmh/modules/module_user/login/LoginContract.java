package com.zfsoftmh.modules.module_user.login;

import com.zfsoftmh.base.BasePresenter;
import com.zfsoftmh.base.BaseView;

/**
 * Created by wesley on 2016/9/22.
 * 契约接口
 */
public interface LoginContract
{
    interface View<LoginPresenter> extends BaseView<LoginPresenter>
    {
        void login(String userName, String password);
    }

    interface Presenter extends BasePresenter
    {
        //登陆
        void login(String userName, String password);
    }
}
