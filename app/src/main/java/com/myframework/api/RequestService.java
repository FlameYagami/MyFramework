package com.myframework.api;

import com.myframework.bean.LoginBean;
import com.myframework.bean.LoginHttpBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface RequestService
{
    /**
     * 登入接口
     *
     * @param data 加密的用户实例
     * @return HttpResult对象
     */
    @POST("api/Login")
    Observable<HttpResult<LoginBean>> onLogin(@Body LoginHttpBean data);
}
