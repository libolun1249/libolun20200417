package com.bawei.libolun20200417;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static RetrofitUtil sRretrofitUtil;
    private final Retrofit mRetrofit;

    private RetrofitUtil() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://blog.zhaoliang5156.cn/")
                //使用Retrofit+OKHttp+RxJava封装网络操作，并使用拦截器打印网络请求数据日志
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (sRretrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (sRretrofitUtil == null) {
                    sRretrofitUtil = new RetrofitUtil();
                }
            }
        }
        return sRretrofitUtil;
    }


    public <T> T sreateService(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }
}
