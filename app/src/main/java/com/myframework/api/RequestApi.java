package com.myframework.api;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sll on 2016/3/10.
 */
public class RequestApi
{
    private static final String TAG = RequestApi.class.getSimpleName();

    private static final String BASE_URL = "https://github.com/FlameYagami/ZxAndroid/";

    private static final int DEFAULT_TIMEOUT = 5000;// 默认超时时间5秒

    private volatile static RequestApi instance;

    private RequestService requestService;

    /**
     * 获取单例模式
     *
     * @return RequestApi实例
     */
    private static RequestApi getInstance() {
        if (instance == null) {
            synchronized (RequestApi.class) {
                if (instance == null) {
                    instance = new RequestApi();
                }
            }
        }
        return instance;
    }

    private RequestApi() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new LogInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        requestService = retrofit.create(RequestService.class);
    }

    public RequestApi(String url) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new LogInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        requestService = retrofit.create(RequestService.class);
    }

    /**
     * OKHttp截断器
     */
    private class LogInterceptor implements Interceptor
    {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.d(TAG, "request:" + request.toString());
//            long             t1       = System.nanoTime();
            okhttp3.Response response = chain.proceed(chain.request());
//            long             t2       = System.nanoTime();
//            Log.d(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String            content   = response.body().string();
            Log.d(TAG, "response body:" + content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }
}
