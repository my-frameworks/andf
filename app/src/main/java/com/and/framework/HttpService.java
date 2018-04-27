package com.and.framework;


import com.and.framework.common.BaseResponseBody;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpService {

    @GET("/getUserInfo")
    Observable<BaseResponseBody<User>> getUserInfo();
}
