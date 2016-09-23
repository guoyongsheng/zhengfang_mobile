package com.zfsoftmh.listener;

import com.zfsoftmh.entity.ResponseInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wesley on 2016/9/22.
 */
public class RetrofitCallBackListenter<T extends ResponseInfo<K>, K> implements Callback<ResponseInfo<K>>
{

    private CallBackListener<K> callBackListener;

    public RetrofitCallBackListenter(CallBackListener<K> callBackListener)
    {
        this.callBackListener = callBackListener;
    }


    @Override
    public void onResponse(Call<ResponseInfo<K>> call, Response<ResponseInfo<K>> response)
    {
        callBackListener.onSuccess(response.body().getDate());
    }

    @Override
    public void onFailure(Call<ResponseInfo<K>> call, Throwable t)
    {
        callBackListener.onFailure(t.getMessage());
    }
}
