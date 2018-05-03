package com.and.framework;

import android.util.Log;

import com.and.framework.common.AndFApplication;
import com.and.framework.common.ProgressListener;
import com.and.framework.common.agency.AndFConfigInterface;

import com.and.framework.common.component.RetrofitHelper;



public class MyApplication  extends AndFApplication implements AndFConfigInterface{

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            OkHttpClientUtils.getInstance().setProgressResponseListener(progressListener);

            RetrofitHelper.getInstance().init(OkHttpClientUtils.getInstance().getClient(null),"http://www.baidu.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAndFConfigInterface(this);

    }

    @Override
    public String getFilePath() {
        return "com.and.framework";
    }

    ProgressListener progressListener = new ProgressListener() {
        @Override
        public void onProgress(long progress, long total, boolean done) {
            Log.e("yyzhang","$progress="+progress+"total="+total+"done="+done);


        }
    };

}
