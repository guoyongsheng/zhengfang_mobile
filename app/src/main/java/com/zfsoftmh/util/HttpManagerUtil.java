package com.zfsoftmh.util;

import com.zfsoftmh.base.BaseApplication;
import com.zfsoftmh.config.Config;
import com.zfsoftmh.service.OaApi;
import com.zfsoftmh.service.UserApi;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wesley on 2016/9/22.
 * 网络请求的工具类---单例模式
 */
public class HttpManagerUtil
{
    private static volatile HttpManagerUtil instance;
    private static OaApi oaApi;
    private static UserApi.UserApiRetrofit userApi;

    private HttpManagerUtil()
    {
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Config.BASE_URL)
                            .client(getOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        oaApi = retrofit.create(OaApi.class);
        userApi = retrofit.create(UserApi.UserApiRetrofit.class);
    }

    //获取单例
    public static HttpManagerUtil getInstance()
    {
        synchronized (HttpManagerUtil.class)
        {
            if (instance == null)
            {
                synchronized (HttpManagerUtil.class)
                {
                    instance = new HttpManagerUtil();
                }
            }
        }

        return instance;
    }

    //获取OkHttpClient的实例
    private OkHttpClient getOkHttpClient()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (Config.DEBUG)
        {
            //日志
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        //网络缓存
        File cacheFile = new File(BaseApplication.getInstance().getExternalCacheDir(), "cache_net");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        builder.addInterceptor(new CacheInterceptor());
        builder.cache(cache);

        //公共参数
        builder.addInterceptor(new QueryParamterIntercepter());

        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    //缓存
    private static class CacheInterceptor implements Interceptor
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request request = chain.request();
            if (!NetworkUtil.isAvailable(BaseApplication.getInstance()))
            {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (NetworkUtil.isAvailable(BaseApplication.getInstance()))
            {
                int maxAge = 0;
                // 有网络时, 不缓存, 最大保存时长为0
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else
            {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }


    //公共参数
    private static class QueryParamterIntercepter implements Interceptor
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request originalRequest = chain.request();
            Request request;
            HttpUrl httpUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("key", "value")
                    .build();
            request = originalRequest.newBuilder().url(httpUrl).build();
            return chain.proceed(request);
        }
    }


    //获取移动oa模块的实例
    public OaApi getOaApi()
    {
        return oaApi;
    }


    //获取用户模块的实例
    public UserApi.UserApiRetrofit getUserApi()
    {
        return userApi;
    }

}
