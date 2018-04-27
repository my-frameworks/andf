package com.and.framework.common.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.Stack;

/**
 * Created by zyd on 2017/7/20.
 * @author zhangyadong
 */

public class ScreenManager {

    private static Stack<AppCompatActivity> activityStack;
    private static ScreenManager instance;

    private ScreenManager(){}

    public static ScreenManager getScreenManager() {
            if (instance == null) {
                instance = new ScreenManager();
            }
        return instance;
    }

    /***
     * 栈中Activity的数
     *
     * @return Activity的数
     */
    public int stackSize() {
        if(activityStack!=null){
            return activityStack.size();
        }
        return 0;
    }

    public void removeActivity(AppCompatActivity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    public AppCompatActivity currentActivity() {
        if (activityStack != null && 0 < activityStack.size()) {
            AppCompatActivity activity = activityStack.lastElement();
            return activity;
        } else {
            return null;
        }
    }

    public void pushActivity(AppCompatActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<AppCompatActivity>();
        }
        activityStack.add(activity);
    }

    public void removeAllActivity() {
        while (true) {
            AppCompatActivity activity = currentActivity();
            if (activity == null) {
                break;
            }
            removeActivity(activity);
        }
    }

    public static void exitSystem(){
        getScreenManager().removeAllActivity();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void popSpecificActivity(Class<? extends Activity> target) {
        if (null == target || null == activityStack || activityStack.isEmpty()) {
            return;
        }

        for (AppCompatActivity act : activityStack ) {
            if (act.getClass() == target) {
                removeActivity(act);
                return;
            }
        }
    }

}
