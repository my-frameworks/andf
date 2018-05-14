package com.and.framework.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.and.framework.common.R;
import com.and.framework.common.mvp.BasePresenter;
import com.and.framework.common.mvp.BaseView;
import com.and.framework.common.widget.LoadingProgressDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by zyd on 2017/7/20.
 * <p>
 * General UI Activity of the parent class
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxActivity implements BaseView {

    protected String TAG = this.getClass().getSimpleName();

    private Toolbar mToolbar;
    private TextView mTitleText;
    private FrameLayout mContentLayout;
    private LoadingProgressDialog mProgressDialog;
    private Unbinder mUnbinder;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (isTransitionAnimation()) {
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_activity_layout);
        mPresenter = getPresenter();
        initParentViews();

        mUnbinder = ButterKnife.bind(this);

        onViewCreated();

        initEventAndData(getIntent().getExtras());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (isTransitionAnimation()) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ToolbarConfigurator toolbarConfigurator = new ToolbarConfigurator(menu, mToolbar);

        this.onInterceptMenuCreation(toolbarConfigurator);
        mTitleText.setText(toolbarConfigurator.getTitle());

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(toolbarConfigurator.isBackPressEnabled());
            actionBar.setDisplayShowTitleEnabled(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private final void initParentViews() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mTitleText = (TextView) findViewById(R.id.tool_bar_title);
        mContentLayout = (FrameLayout) findViewById(R.id.content_layout);

        setSupportActionBar(mToolbar);

        if (getLayoutRes() > 0) {
            LayoutInflater inflater = LayoutInflater.from(this);
            inflater.inflate(getLayoutRes(), mContentLayout);
        }
    }

    protected abstract int getLayoutRes();

    protected abstract void onViewCreated();

    protected abstract void initEventAndData(Bundle bundle);

    /**
     * Config how your activity title should look like here.
     */
    protected void onInterceptMenuCreation(ToolbarConfigurator configurator) {
    }

    protected void setToolbarVisible(boolean visible) {
        mToolbar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingProgressDialog(this);
        }
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog == null) {
            return;
        }
        mProgressDialog.dismiss();
    }

    protected T getPresenter() {
        return null;
    }

    protected void setProgressDialogMessage(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingProgressDialog(this);
        }
        mProgressDialog.setMessage(message);
    }


    /**
     * 是否加载动画
     *
     * @return
     */
    protected boolean isTransitionAnimation() {
        return true;
    }

}
