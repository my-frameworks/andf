package com.and.framework.common.mvp;

/**
 * Created by zyd on 2018/4/28.
 * presenter 实现Base类
 */

public class BaseImplPresenter<T extends BaseView> {
    protected final String TAG = this.getClass().getSimpleName();
    protected T mView;

    public BaseImplPresenter(T view) {
        mView = view;
    }
}
