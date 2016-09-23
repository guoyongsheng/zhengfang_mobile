package com.zfsoftmh.modules.module_user.login;

import android.view.View;

import com.zfsoftmh.base.BaseFragment;

/**
 * Created by wesley on 2016/9/22.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View<LoginPresenter>
{
    private LoginPresenter presenter; //presenter层的实例

    @Override
    public void initVariables()
    {

    }

    @Override
    public int getLayoutId()
    {
        return 0;
    }

    @Override
    public void initView(View view)
    {

    }

    @Override
    public void login(String userName, String password)
    {

    }

    @Override
    public void setPresenter(LoginPresenter presenter)
    {
        this.presenter = presenter;
    }
}
