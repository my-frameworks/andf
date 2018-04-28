package com.and.framework;


import com.and.framework.common.BaseResponseBody;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface HttpService {

    @GET("/getUserInfo")
    Observable<BaseResponseBody<User>> getUserInfo();



}
