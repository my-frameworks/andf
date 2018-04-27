package com.and.framework.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.and.framework.R;
import com.and.framework.common.activity.BaseActivity;
import com.and.framework.common.activity.ToolbarConfigurator;

import butterknife.BindView;

/**
 * Created by zhangyadong on 2018/4/27.
 */

public class TestBaseActivity extends BaseActivity {

    @BindView(R.id.content)
    LinearLayout contentLayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.demo_base_activity_layout;
    }

    @Override
    protected void onViewCreated() {
        showProgressDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissProgressDialog();
            }
        }, 5000);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.content, new TestBaseFragment());
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void initEventAndData(Bundle bundle) {

    }


    @Override
    protected void onInterceptMenuCreation(ToolbarConfigurator configurator) {
        super.onInterceptMenuCreation(configurator);
        configurator.setTitle("BaseActivity");
    }
}
