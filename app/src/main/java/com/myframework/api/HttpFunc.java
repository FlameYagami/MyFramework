package com.myframework.api;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by 时空管理局 on 2016/8/4.
 */
class HttpFunc<T> implements Function<HttpResult<T>, T>
{

    @Override
    public T apply(@NonNull HttpResult<T> tHttpResult) throws Exception {
        if (200 != tHttpResult.getCode()) {
            throw new RuntimeException(tHttpResult.getCode() + tHttpResult.getReason());
        }
        return tHttpResult.getResult();
    }
}


