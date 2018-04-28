package com.and.framework;

import com.and.framework.common.AndFApplication;
import com.and.framework.common.agency.AndFConfigInterface;


public class MyApplication  extends AndFApplication implements AndFConfigInterface{

    private static MyApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }


    public static MyApplication getInstance(){
        return application;
    }


    @Override
    public String getFilePath() {
        return "com.and.framework";
    }
}
