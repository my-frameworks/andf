package com.and.framework.common;

import android.app.Application;
import android.os.Process;

import com.and.framework.common.agency.AndFConfigInterface;
import com.and.framework.common.utils.SystemUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by zhangyadong on 2018/4/28.
 */

public class AndFApplication extends Application{

    private static AndFApplication instance;
    private AndFConfigInterface andFConfigInterface;


    @Override
    public void onCreate() {
        super.onCreate();
        String processName = SystemUtils.getProcessName(this, Process.myPid());
        if (processName != null) {
            if(processName.equals(getPackageName())){
                instance = this;
                Logger.addLogAdapter(new AndroidLogAdapter());
            }
        }
    }

    public static AndFApplication get() {
        return instance;
    }


    public AndFConfigInterface getAndFConfigInterface() {
        return andFConfigInterface;
    }

    public void setAndFConfigInterface(AndFConfigInterface andFConfigInterface) {
        this.andFConfigInterface = andFConfigInterface;
    }
}
