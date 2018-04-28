package com.and.framework;

import android.app.Application;

import com.and.framework.common.component.RetrofitHelper;



public class MyApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            RetrofitHelper.getInstance().init(OkHttpClientUtils.getInstance().getClient(getAssets().open("")),"");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
