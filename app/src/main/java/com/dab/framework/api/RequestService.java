package com.dab.framework.api;

import com.dab.framework.bean.LoginBean;
import com.dab.framework.bean.LoginHttpBean;

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
