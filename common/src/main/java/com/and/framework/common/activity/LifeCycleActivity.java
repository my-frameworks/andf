package com.and.framework.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zyd on 2017/7/20.
 * @author zhangyadong
 * @version 1.0.0
 * @date 2017/7/20.
 * @see this is android Activity lifeCycle Management class
 */

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManager.getScreenManager().removeActivity(this);
    }

    public void launchScreen(Class<? extends Activity> target, Bundle... bd) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bd != null && bd.length > 0) {
            intent.putExtras(bd[0]);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
    }
}
