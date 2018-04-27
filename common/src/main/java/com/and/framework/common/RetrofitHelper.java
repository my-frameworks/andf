package com.and.framework.common;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static Retrofit retrofit;

    private RetrofitHelper() {

    }

    public static RetrofitHelper getInstance() {
        return RetrofitHelperHolder.sInstance;
    }

    private static class RetrofitHelperHolder {

        private static RetrofitHelper sInstance = new RetrofitHelper();

    }

    public void init(OkHttpClient client, String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }


    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
