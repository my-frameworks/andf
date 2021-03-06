package com.and.framework.common.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.and.framework.common.mvp.BasePresenter;
import com.and.framework.common.mvp.BaseView;
import com.and.framework.common.widget.LoadingProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zyd on 2017/7/24.
 */

public abstract class BaseFragment<T extends BasePresenter> extends LazyFragment implements BaseView {

    protected String TAG = this.getClass().getSimpleName();

    private Unbinder mUnbinder;
    private LoadingProgressDialog mProgressDialog;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            onHandleArguments(arguments);
        }
    }

    /**
     * Called when this activity receives bundle arguments from other pages.
     */
    protected void onHandleArguments(final Bundle extras) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(inflateContentView(), container, false);
        mPresenter = getPresenter();
        setPreparedStatus(true);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        onInitializeView(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected View findViewById(int res) {
        return getView().findViewById(res);
    }

    protected abstract int inflateContentView();

    protected abstract void onInitializeView(View view);

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingProgressDialog(getContext());
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

    protected void setProgressDialogMessage(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingProgressDialog(getContext());
        }
        mProgressDialog.setMessage(message);
    }

    public void launchScreen(Class<? extends Activity> target, Bundle... bd) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), target);
        if (bd != null && bd.length > 0) {
            intent.putExtras(bd[0]);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
    }

    protected T getPresenter() {
        return null;
    }

}
