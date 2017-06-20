package com.myframework.api;

import android.content.Context;
import android.util.Log;

import com.myframework.bean.LoginBean;
import com.myframework.bean.LoginHttpBean;
import com.myframework.utils.rxjava.ProgressSubscriber;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sll on 2016/3/10.
 */
public class RequestApi
{
    private static final String TAG = RequestApi.class.getSimpleName();

    private static final String BASE_URL = "http://191.191.16.5:491/";

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
                    instance = new RequestApi(BASE_URL);
                }
            }
        }
        return instance;
    }

    /**
     * 获取单例模式
     *
     * @return RequestApi实例
     */
    private static RequestApi getInstance(String url) {
        if (instance == null) {
            synchronized (RequestApi.class) {
                if (instance == null) {
                    instance = new RequestApi(url);
                }
            }
        }
        return instance;
    }

    /**
     * 私有构造方法
     *
     * @param url 访问地址
     */
    private RequestApi(String url) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new LogInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    /**
     * 登录
     *
     * @param account 加密的用户实例
     * @return 登录信息
     */
    public static Observable<LoginBean> onLogin(Context context, LoginHttpBean account) {
        return new ProgressSubscriber<>(context, getInstance().requestService
                .onLogin(account)
                .subscribeOn(Schedulers.newThread())
                .map(new HttpFunc<>()))
                .apply();
    }

    /**
     * 登录
     *
     * @param account 加密的用户实例
     * @return 登录信息
     */
    public static Observable<LoginBean> onLogin(LoginHttpBean account) {
        return getInstance().requestService
                .onLogin(account)
                .subscribeOn(Schedulers.newThread())
                .map(new HttpFunc<>());
    }
}
