package com.zfsoftmh.entity;

/**
 * Created by wesley on 2016/9/22.
 * 响应的实体类
 */
public class ResponseInfo<T>
{
    private int errorCode; //错误码
    private int errorMessage; //错误信息
    private T date;

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public int getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(int errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public T getDate()
    {
        return date;
    }

    public void setDate(T date)
    {
        this.date = date;
    }
}
